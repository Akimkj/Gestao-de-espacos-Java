package view;
import javax.swing.*;
import java.awt.*;
import controller.UsuarioController;
import models.Usuario;

public class ExcluirUsuario extends JFrame {
    private JTextField campoEmail;
    private UsuarioController controller;
    private Usuario usuarioLogado;
    private String usuarioremovido;

    public ExcluirUsuario(Usuario usuario) {
        controller = new UsuarioController();
        this.usuarioLogado = usuario;

        setTitle("Excluir Usuário");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230));

        JPanel painel = new GradientPanel();
        painel.setLayout(null);
        painel.setBounds(40, 60, 320, 340);
        painel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(painel);

        JLabel labelTitulo = new JLabel("Excluir Usuário");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setForeground(Color.WHITE);
        labelTitulo.setBounds(80, 10, 200, 30);
        painel.add(labelTitulo);

        JLabel labelEmail = new JLabel("Email do usuário:");
        labelEmail.setBounds(30, 50, 240, 20);
        painel.add(labelEmail);

        campoEmail = new JTextField();
        campoEmail.setBounds(30, 70, 240, 25);
        painel.add(campoEmail);

        JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(90, 100, 120, 30);
        painel.add(botaoExcluir);

        JButton btbVoltar = new JButton("Voltar");
        btbVoltar.setBounds(90, 150, 120, 30 );
        painel.add(btbVoltar);

        botaoExcluir.addActionListener(e -> {
            String email = campoEmail.getText();
            boolean removido = controller.removerUsuarioPorEmail(email,usuarioremovido);

            if (removido) {
                JOptionPane.showMessageDialog(this, "Usuário removido com sucesso.");

            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
            }
        });

        btbVoltar.addActionListener(e -> {
            new TelaMenuAdmin(usuarioLogado).setVisible(true);
            dispose();
        });
    }

    public ExcluirUsuario() {

    }

    public static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            Color cor1 = new Color(138, 43, 226);
            Color cor2 = new Color(255, 105, 180);
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

