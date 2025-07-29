package view;
import javax.swing.*;
import java.awt.*;
import controller.UsuarioController;
import models.Usuario;

public class TelaCadastro extends JFrame {
    private JTextField campoNome;
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JPasswordField campoConfirmarSenha;
    private UsuarioController controller;

    public TelaCadastro() {
        controller = new UsuarioController();

        setTitle("Cadastro de Usuário");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230));

        JPanel painel = new GradientPanel();
        painel.setLayout(null);
        painel.setBounds(50, 70, 300, 320);
        painel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(painel);

        JLabel labelTitulo = new JLabel("Criar Conta");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setForeground(Color.WHITE);
        labelTitulo.setBounds(100, 10, 200, 30);
        painel.add(labelTitulo);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(30, 50, 100, 20);
        painel.add(labelNome);
        campoNome = new JTextField();
        campoNome.setBounds(30, 70, 240, 25);
        painel.add(campoNome);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setBounds(30, 100, 100, 20);
        painel.add(labelEmail);
        campoEmail = new JTextField();
        campoEmail.setBounds(30, 120, 240, 25);
        painel.add(campoEmail);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(30, 150, 100, 20);
        painel.add(labelSenha);
        campoSenha = new JPasswordField();
        campoSenha.setBounds(30, 170, 240, 25);
        painel.add(campoSenha);

        JLabel labelConfirmar = new JLabel("Confirmar Senha:");
        labelConfirmar.setBounds(30, 200, 150, 20);
        painel.add(labelConfirmar);
        campoConfirmarSenha = new JPasswordField();
        campoConfirmarSenha.setBounds(30, 220, 240, 25);
        painel.add(campoConfirmarSenha);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(30, 260, 110, 30);
        painel.add(botaoCadastrar);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(160, 260, 110, 30);
        painel.add(botaoVoltar);

        botaoCadastrar.addActionListener(e -> {
            String nome = campoNome.getText();
            String email = campoEmail.getText();
            String senha = new String(campoSenha.getPassword());
            String confirmar = new String(campoConfirmarSenha.getPassword());

            if (!senha.equals(confirmar)) {
                JOptionPane.showMessageDialog(this, "Senhas não conferem.");
                return;
            }

            Usuario novoUsuario = new Usuario(0, nome, email, senha, false);
            controller.cadastrarUsuario(novoUsuario);
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
            new LoginView().setVisible(true);
            dispose();
        });

        botaoVoltar.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });
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

