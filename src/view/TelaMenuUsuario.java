package view;
import java.awt.*;
import javax.swing.*;
import models.Usuario;

public class TelaMenuUsuario extends JFrame {
    private Usuario usuarioLogado;


    public TelaMenuUsuario(Usuario usuario) {
        super("Menu do Usuário");
        this.usuarioLogado = usuario;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230)); // azul claro

        // Painel com gradiente e bordas arredondadas
        JPanel painel = new GradientPanel();
        painel.setLayout(null);
        painel.setBounds(50, 70, 300, 280);
        painel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(painel);

        JLabel titulo = new JLabel("Olá, " + usuarioLogado.getNome());
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(60, 20, 200, 30);
        painel.add(titulo);

        JButton btnReservar = new JButton("Reservar Espaço");
        btnReservar.setBounds(30, 80, 240, 40);
        btnReservar.setBackground(new Color(255, 105, 180));
        btnReservar.setForeground(Color.WHITE);
        painel.add(btnReservar);

        JButton btnRelatorio = new JButton("Relatorios Gerais");
        btnRelatorio.setBounds(30, 140, 240, 40);
        btnRelatorio.setBackground(Color.WHITE);
        painel.add(btnRelatorio);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(30, 200, 240, 30);
        btnSair.setBackground(new Color(220, 220, 220));
        painel.add(btnSair);

        // Evento: Reservar Espaço
        btnReservar.addActionListener(e ->{
            new ReservaView(usuarioLogado).setVisible(true);
        });

        // Evento: Relatório CSV
        btnRelatorio.addActionListener(e -> {
            new RelatoriosView(usuarioLogado).setVisible(true);
            dispose();
        });

        // Evento: Sair → volta para o login
        btnSair.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });
    }

    // Painel gradiente reutilizável
    public static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            Color cor1 = new Color(138, 43, 226);  // roxo
            Color cor2 = new Color(255, 105, 180); // rosa
            GradientPaint gp = new GradientPaint(0, 0, cor1, getWidth(), getHeight(), cor2);
            g2d.setPaint(gp);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
        }

        @Override
        public boolean isOpaque() {
            return false;
        }
    }
}


