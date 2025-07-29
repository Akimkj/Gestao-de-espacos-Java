package view;

// Importações necessárias para a interface gráfica e manipulação de dados
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import models.*;
import persistence.ReservaDAO;
import persistence.EspacoDAO;

// Classe principal que representa a tela de reserva
public class ReservaView extends JFrame {

    // Campos da interface
    private JTextField campoNome, campoEmail;
    private JFormattedTextField campoData, campoHoraInicio, campoHoraFim;
    private JComboBox<String> comboEspacos;
    private Usuario usuarioLogado;

    // Construtor que recebe o usuário logado
    public ReservaView(Usuario usuario) {
        setTitle("Reserva"); // Título da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Comportamento ao fechar
        setSize(600, 400); // Tamanho da janela
        setLocationRelativeTo(null); // Centraliza na tela
        setLayout(new BorderLayout()); // Layout principal
        this.usuarioLogado = usuario; // Guarda usuário logado

        getContentPane().setBackground(new Color(191, 215, 255)); // Cor de fundo

        // Criação do título
        JLabel titulo = new JLabel("Reserva");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 32));
        titulo.setForeground(new Color(50, 70, 255));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        // Painel com efeito gradiente para os campos de entrada
        JPanel painelGradiente = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(102, 102, 255), getWidth(), getHeight(), new Color(255, 102, 204));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        painelGradiente.setOpaque(false); // Torna o painel transparente
        painelGradiente.setLayout(new GridLayout(6, 2, 10, 10)); // Layout em grade
        painelGradiente.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80)); // Margens internas

        // Criação dos campos de entrada
        campoNome = criarCampoTexto("Nome:", painelGradiente);
        campoEmail = criarCampoTexto("Email:", painelGradiente);
        criarComboEspacos("Espaço:", painelGradiente); // Combo box com os espaços disponíveis

        try {
            // Campos com máscara para data e hora
            campoData = criarCampoFormatado("Data (dd/MM/yyyy):", "##/##/####", painelGradiente);
            campoHoraInicio = criarCampoFormatado("Hora/Início (HH:mm):", "##:##", painelGradiente);
            campoHoraFim = criarCampoFormatado("Hora/Fim (HH:mm):", "##:##", painelGradiente);
        } catch (ParseException e) {
            // Caso haja erro na criação das máscaras
            JOptionPane.showMessageDialog(this, "Erro interno: Não foi possível configurar as máscaras de formato. " + e.getMessage());
            e.printStackTrace();
            return;
        }

        add(painelGradiente, BorderLayout.CENTER); // Adiciona o painel principal ao centro da tela

        // Botão para reservar
        JButton botaoReservar = new JButton("RESERVAR");
        botaoReservar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoReservar.setForeground(new Color(60, 70, 255));
        botaoReservar.setBackground(new Color(210, 180, 255));
        botaoReservar.setFocusPainted(false);
        botaoReservar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        // Botão para voltar/sair
        JButton btnVoltar = new JButton("Sair");
        btnVoltar.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnVoltar.setBackground(new Color(220, 220, 220));

        // Painel inferior com os botões
        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(new Color(191, 215, 255));

        JPanel painelEsquerda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelEsquerda.setBackground(new Color(191, 215, 255));
        painelEsquerda.add(btnVoltar);

        JPanel painelDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelDireita.setBackground(new Color(191, 215, 255));
        painelDireita.add(botaoReservar);

        painelInferior.add(painelEsquerda, BorderLayout.WEST);
        painelInferior.add(painelDireita, BorderLayout.EAST);

        add(painelInferior, BorderLayout.SOUTH); // Adiciona os botões na parte inferior

        // Ação do botão Voltar
        btnVoltar.addActionListener(e -> {
            new TelaMenuUsuario(usuarioLogado).setVisible(true); // Abre menu do usuário
            dispose(); // Fecha a tela atual
        });

        // Ação do botão Reservar
        botaoReservar.addActionListener(e -> {
            try {
                // Captura dos dados do formulário
                String nome = campoNome.getText().trim();
                String email = campoEmail.getText().trim();
                String espacoNome = (String) comboEspacos.getSelectedItem();
                String dataTexto = campoData.getText();
                String horaInicio = campoHoraInicio.getText();
                String horaFim = campoHoraFim.getText();

                // Validação dos campos
                if (nome.isEmpty() || email.isEmpty() || espacoNome == null ||
                        dataTexto.contains("_") || horaInicio.contains("_") || horaFim.contains("_")) {
                    JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos corretamente. Verifique o formato de data e hora.");
                    return;
                }

                // Converte a data para o formato Date
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dataReserva = sdf.parse(dataTexto);

                // Cria uma nova reserva com ID aleatório
                int novoId = new Random().nextInt(10000);
                Usuario responsavel = new Usuario(novoId, nome, email, "", false);
                Espaco espaco = new SalaAula(espacoNome, "Desconhecido", 0, false); // Espaço fictício

                Reserva reserva = new Reserva(novoId, espaco, dataReserva, horaInicio, horaFim, responsavel);

                ReservaDAO dao = new ReservaDAO();
                dao.salvar(reserva); // Salva a reserva

                JOptionPane.showMessageDialog(this, "Reserva salva com sucesso!");

                // Limpa os campos após salvar
                campoNome.setText("");
                campoEmail.setText("");
                comboEspacos.setSelectedIndex(0);
                campoData.setValue(null);
                campoHoraInicio.setValue(null);
                campoHoraFim.setValue(null);

            } catch (ParseException pe) {
                JOptionPane.showMessageDialog(this, "Formato de data ou hora inválido. Use dd/MM/yyyy para data e HH:mm para horas.");
                pe.printStackTrace();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar reserva: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        setVisible(true); // Exibe a janela
    }

    // Método para criar campo de texto padrão com rótulo
    private JTextField criarCampoTexto(String textoLabel, JPanel painel) {
        JLabel label = new JLabel(textoLabel);
        label.setForeground(new Color(60, 60, 80));
        label.setFont(new Font("SansSerif", Font.BOLD, 18));

        JTextField campo = new JTextField();
        campo.setBackground(new Color(210, 180, 255));
        campo.setForeground(Color.DARK_GRAY);
        campo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(170, 150, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        campo.setCaretColor(Color.DARK_GRAY);

        painel.add(label);
        painel.add(campo);

        return campo;
    }

    // Método para criar campo formatado com máscara
    private JFormattedTextField criarCampoFormatado(String textoLabel, String mascara, JPanel painel) throws ParseException {
        JLabel label = new JLabel(textoLabel);
        label.setForeground(new Color(60, 60, 80));
        label.setFont(new Font("SansSerif", Font.BOLD, 18));

        MaskFormatter mask = new MaskFormatter(mascara);
        mask.setPlaceholderCharacter('_');
        mask.setAllowsInvalid(false);

        JFormattedTextField campo = new JFormattedTextField(mask);
        campo.setBackground(new Color(210, 180, 255));
        campo.setForeground(Color.DARK_GRAY);
        campo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(170, 150, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        campo.setCaretColor(Color.DARK_GRAY);
        campo.setFocusLostBehavior(JFormattedTextField.PERSIST);

        painel.add(label);
        painel.add(campo);

        return campo;
    }

    // Método para criar o comboBox de espaços
    private void criarComboEspacos(String textoLabel, JPanel painel) {
        JLabel label = new JLabel(textoLabel);
        label.setForeground(new Color(60, 60, 80));
        label.setFont(new Font("SansSerif", Font.BOLD, 18));

        comboEspacos = new JComboBox<>();
        comboEspacos.setBackground(new Color(210, 180, 255));
        comboEspacos.setForeground(Color.DARK_GRAY);
        comboEspacos.setFont(new Font("SansSerif", Font.PLAIN, 16));
        comboEspacos.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(170, 150, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        EspacoDAO dao = new EspacoDAO();
        List<Espaco> espacos = dao.listar(); // Busca todos os espaços
        for (Espaco e : espacos) {
            comboEspacos.addItem(e.getNome()); // Adiciona ao comboBox
        }

        painel.add(label);
        painel.add(comboEspacos);
    }

    // Método main para testar a tela
    public static void main(String[] args) {
        Usuario usuarioTeste = new Usuario(1, "Usuário Teste", "teste@email.com", "", false);
        SwingUtilities.invokeLater(() -> new ReservaView(usuarioTeste));
    }
}
