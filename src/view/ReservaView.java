package view;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat; // Importe para lidar com erros de formatação
import java.util.Date;
import java.util.Random;
import javax.swing.*;
import javax.swing.text.MaskFormatter; // Importe para criar máscaras
import models.*; // Seus modelos (Reserva, Usuario, Espaco, SalaAula)
import persistence.ReservaDAO; // Use o seu ReservaDAO corrigido

public class ReservaView extends JFrame {

    // Altere os tipos dos campos de data e hora para JFormattedTextField
    private JTextField campoNome, campoEmail, campoEspaco;
    private JFormattedTextField campoData, campoHoraInicio, campoHoraFim;

    public ReservaView() {
        setTitle("Reserva");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Cor de fundo da janela
        getContentPane().setBackground(new Color(191, 215, 255));

        // Título
        JLabel titulo = new JLabel("Reserva");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 32));
        titulo.setForeground(new Color(50, 70, 255));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        // Painel com gradiente
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
        painelGradiente.setOpaque(false);
        painelGradiente.setLayout(new GridLayout(6, 2, 10, 10));
        painelGradiente.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        // Criar campos: usando o novo método para campos de texto simples e campos formatados
        campoNome = criarCampoTexto("Nome:", painelGradiente);
        campoEmail = criarCampoTexto("Email:", painelGradiente);
        campoEspaco = criarCampoTexto("Espaço:", painelGradiente); // Este não tem máscara
        
        try {
            campoData = criarCampoFormatado("Data (dd/MM/yyyy):", "##/##/####", painelGradiente);
            campoHoraInicio = criarCampoFormatado("Hora/Início (HH:mm):", "##:##", painelGradiente);
            campoHoraFim = criarCampoFormatado("Hora/Fim (HH:mm):", "##:##", painelGradiente);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Erro interno: Não foi possível configurar as máscaras de formato. " + e.getMessage());
            e.printStackTrace();
            return; // Impede que a aplicação continue com erro grave nas máscaras
        }
       
        add(painelGradiente, BorderLayout.CENTER);

        // Botão de reservar
        JButton botaoReservar = new JButton("RESERVAR");
        botaoReservar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoReservar.setForeground(new Color(60, 70, 255));
        botaoReservar.setBackground(new Color(210, 180, 255));
        botaoReservar.setFocusPainted(false);
        botaoReservar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        // Painel do botão
        JPanel painelBotao = new JPanel();
        painelBotao.setBackground(new Color(191, 215, 255));
        painelBotao.add(botaoReservar);

        add(painelBotao, BorderLayout.SOUTH);

        // Ação do botão
        botaoReservar.addActionListener(e -> {
            try {
                String nome = campoNome.getText().trim();
                String email = campoEmail.getText().trim();
                String espacoNome = campoEspaco.getText().trim();

                // Para JFormattedTextField, pegamos o texto completo
                String dataTexto = campoData.getText();
                String horaInicio = campoHoraInicio.getText();
                String horaFim = campoHoraFim.getText();

                // Validação para JFormattedTextField: verifica se a máscara foi preenchida
                // Caractere '_' é o placeholder da máscara
                if (nome.isEmpty() || email.isEmpty() || espacoNome.isEmpty() || 
                    dataTexto.contains("_") || horaInicio.contains("_") || horaFim.contains("_")) {
                    
                    JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos corretamente. Verifique o formato de data e hora (dd/MM/yyyy e HH:mm).");
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dataReserva = sdf.parse(dataTexto); // Parseia a data do campo

                int novoId = new Random().nextInt(10000);
                // NOTA: Na sua classe ReservaDAO, você recria Usuario e Espaco com base nos dados.
                // Aqui, criamos instâncias mínimas para a Reserva
                Usuario responsavel = new Usuario(novoId, nome, email, "", false);
                Espaco espaco = new SalaAula(espacoNome, "Desconhecido", 0, false); 

                Reserva reserva = new Reserva(novoId, espaco, dataReserva, horaInicio, horaFim, responsavel);

                // Usa o seu ReservaDAO corrigido
                ReservaDAO dao = new ReservaDAO();
                dao.salvar(reserva);

                JOptionPane.showMessageDialog(this, "Reserva salva com sucesso!");

                // Limpar campos
                campoNome.setText("");
                campoEmail.setText("");
                campoData.setValue(null); // Limpa JFormattedTextField
                campoEspaco.setText("");
                campoHoraInicio.setValue(null); // Limpa JFormattedTextField
                campoHoraFim.setValue(null); // Limpa JFormattedTextField

            } catch (ParseException pe) {
                JOptionPane.showMessageDialog(this, "Formato de data ou hora inválido. Use dd/MM/yyyy para data e HH:mm para horas.");
                pe.printStackTrace();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar reserva: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }

    // Método auxiliar para criar campo de texto normal (sem máscara)
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

    // Novo método auxiliar para criar campo formatado com máscara
    private JFormattedTextField criarCampoFormatado(String textoLabel, String mascara, JPanel painel) throws ParseException {
        JLabel label = new JLabel(textoLabel);
        label.setForeground(new Color(60, 60, 80));
        label.setFont(new Font("SansSerif", Font.BOLD, 18));

        MaskFormatter mask = new MaskFormatter(mascara);
        mask.setPlaceholderCharacter('_'); // Caractere para os espaços vazios da máscara
        mask.setAllowsInvalid(false); // Não permite caracteres fora da máscara
        
        JFormattedTextField campo = new JFormattedTextField(mask);
        campo.setBackground(new Color(210, 180, 255));
        campo.setForeground(Color.DARK_GRAY);
        campo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(170, 150, 200), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        campo.setCaretColor(Color.DARK_GRAY);
        campo.setFocusLostBehavior(JFormattedTextField.PERSIST); // Mantém o valor mesmo perdendo o foco

        painel.add(label);
        painel.add(campo);

        return campo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReservaView());
    }
}