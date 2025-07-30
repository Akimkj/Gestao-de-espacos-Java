package view;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import models.*;
import persistence.ReservaDAO;

public class ReservaView extends JFrame {

    private JTextField campoNome, campoEmail, campoEspaco;
    private JFormattedTextField campoData, campoHoraInicio, campoHoraFim;
    private Usuario usuarioLogado;

    public ReservaView(Usuario usuario) {
        setTitle("Reserva");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        this.usuarioLogado = usuario;

        getContentPane().setBackground(new Color(191, 215, 255));

        JLabel titulo = new JLabel("Reserva");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 32));
        titulo.setForeground(new Color(50, 70, 255));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

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

        campoNome = criarCampoTexto("Nome:", painelGradiente);
        campoEmail = criarCampoTexto("Email:", painelGradiente);
        campoEspaco = criarCampoTexto("Espaço:", painelGradiente);

        try {
            campoData = criarCampoFormatado("Data (dd/MM/yyyy):", "##/##/####", painelGradiente);
            campoHoraInicio = criarCampoFormatado("Hora/Início (HH:mm):", "##:##", painelGradiente);
            campoHoraFim = criarCampoFormatado("Hora/Fim (HH:mm):", "##:##", painelGradiente);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Erro interno: Não foi possível configurar as máscaras de formato. " + e.getMessage());
            e.printStackTrace();
            return;
        }

        add(painelGradiente, BorderLayout.CENTER);

        // Botão de reservar
        JButton botaoReservar = new JButton("RESERVAR");
        botaoReservar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoReservar.setForeground(new Color(60, 70, 255));
        botaoReservar.setBackground(new Color(210, 180, 255));
        botaoReservar.setFocusPainted(false);
        botaoReservar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        // Botão de sair
        JButton btnVoltar = new JButton("Sair");
        btnVoltar.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnVoltar.setBackground(new Color(220, 220, 220));

        // Painel inferior com os dois botões em lados opostos
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

        add(painelInferior, BorderLayout.SOUTH);

        // Ação botão sair
        btnVoltar.addActionListener(e -> {
            new TelaMenuUsuario(usuarioLogado).setVisible(true);
            dispose();
        });

        // Ação botão reservar
        botaoReservar.addActionListener(e -> {
            try {
                String nome = campoNome.getText().trim();
                String email = campoEmail.getText().trim();
                String espacoNome = campoEspaco.getText().trim();
                String dataTexto = campoData.getText();
                String horaInicio = campoHoraInicio.getText();
                String horaFim = campoHoraFim.getText();

                if (nome.isEmpty() || email.isEmpty() || espacoNome.isEmpty() ||
                        dataTexto.contains("_") || horaInicio.contains("_") || horaFim.contains("_")) {
                    JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos corretamente. Verifique o formato de data e hora.");
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dataReserva = sdf.parse(dataTexto);

                int novoId = new Random().nextInt(10000);
                Usuario responsavel = new Usuario(novoId, nome, email, "", false);
                Espaco espaco = new SalaAula(espacoNome, "Desconhecido", 0, false);

                Reserva reserva = new Reserva(novoId, espaco, dataReserva, horaInicio, horaFim, responsavel);

                ReservaDAO dao = new ReservaDAO();
                dao.salvar(reserva);

                JOptionPane.showMessageDialog(this, "Reserva salva com sucesso!");

                campoNome.setText("");
                campoEmail.setText("");
                campoEspaco.setText("");
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

        setVisible(true);
    }

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
public static void main(String[] args) {
    // Simula um usuário logado apenas para teste
    Usuario usuarioTeste = new Usuario(1, "Usuário Teste", "teste@email.com", "", false);
    SwingUtilities.invokeLater(() -> new ReservaView(usuarioTeste));
}
}