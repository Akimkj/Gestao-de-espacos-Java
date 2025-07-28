package view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import models.*;
import persistence.ReservaDAO;

public class ReservaView extends JFrame {

    // Campos acessíveis pela classe
    private JTextField campoNome, campoEmail, campoData, campoEspaco, campoHoraInicio, campoHoraFim;

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

        // Criar campos
        campoNome = criarCampo("Nome:", painelGradiente);
        campoEmail = criarCampo("Email:", painelGradiente);
        campoData = criarCampo("Data (dd/MM/yyyy):", painelGradiente);
        campoEspaco = criarCampo("Espaço:", painelGradiente);
        campoHoraInicio = criarCampo("Hora/Início:", painelGradiente);
        campoHoraFim = criarCampo("Hora/Fim:", painelGradiente);

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
                String dataTexto = campoData.getText().trim();
                String espacoNome = campoEspaco.getText().trim();
                String horaInicio = campoHoraInicio.getText().trim();
                String horaFim = campoHoraFim.getText().trim();

                if (nome.isEmpty() || email.isEmpty() || dataTexto.isEmpty() || espacoNome.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.");
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date data = sdf.parse(dataTexto);

                int novoId = new Random().nextInt(10000);
                Usuario responsavel = new Usuario(novoId, nome, email, "", false);
                Espaco espaco = new SalaAula(novoId, espacoNome, "Desconhecido", 0, false);

                Reserva reserva = new Reserva(novoId, espaco, data, horaInicio, horaFim, responsavel);

                ReservaDAO dao = new ReservaDAO();
                dao.salvar(reserva);

                JOptionPane.showMessageDialog(this, "Reserva salva com sucesso!");

                campoNome.setText("");
                campoEmail.setText("");
                campoData.setText("");
                campoEspaco.setText("");
                campoHoraInicio.setText("");
                campoHoraFim.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar reserva: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }

    // Método auxiliar para criar campo com rótulo e retornar o campo
    private JTextField criarCampo(String textoLabel, JPanel painel) {
        JLabel label = new JLabel(textoLabel);
        label.setForeground(new Color(60, 60, 80));
        label.setFont(new Font("SansSerif", Font.BOLD, 18));

        JTextField campo = new JTextField();
        campo.setBackground(new Color(210, 180, 255));
        campo.setForeground(Color.DARK_GRAY);
        campo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        campo.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        JPanel campoArredondado = new JPanel(new BorderLayout());
        campoArredondado.setBackground(new Color(210, 180, 255));
        campoArredondado.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        campoArredondado.add(campo);

        painel.add(label);
        painel.add(campoArredondado);

        return campo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReservaView());
    }
}
