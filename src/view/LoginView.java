package view;
import controller.UsuarioController;
import models.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LoginView extends JFrame{
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private UsuarioController usuarioController;

    public LoginView(){
        super("AMBIENTA - Login");
        usuarioController = new UsuarioController();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 450);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setResizable(false); // Manter como está, se desejar tamanho fixo

        // 1. Mude o layout da janela para BorderLayout
        // getContentPane().setLayout(new BorderLayout()); // Já é o padrão, mas explicitando
        getContentPane().setBackground(new Color(193, 219, 253));

        // Cria um painel 'holder' para centralizar o 'painel' original
        JPanel centerPanelHolder = new JPanel(new GridBagLayout()); // GridBagLayout é excelente para centralizar um único componente
        centerPanelHolder.setBackground(new Color(193, 219, 253)); // Mesma cor de fundo da janela para 'esconder'

        // Seu painel de login existente, mas sem setBounds() aqui
        JPanel painel = new GradientPanel(); // Mantém o GradientPanel personalizado
        painel.setLayout(null); // Você ainda pode usar null dentro deste painel se quiser controle absoluto lá
        painel.setPreferredSize(new Dimension(300, 330)); // Definir um tamanho preferencial para o painel
        painel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Adicionar o painel de login ao painel centralizador
        centerPanelHolder.add(painel); // GridBagLayout centraliza componentes adicionados diretamente

        // Adiciona o painel centralizador ao CENTRO da janela
        add(centerPanelHolder, BorderLayout.CENTER);


        // --- Componentes dentro do 'painel' (ainda usando setBounds) ---
        // Se você quer centralizar esses componentes internos também, precisaria
        // mudar o layout do 'painel' para algo como GridLayout ou GridBagLayout.
        // Por enquanto, eles continuam posicionados absolutamente dentro do 'painel'.

        JLabel titulo = new JLabel("Login");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(110, 20, 100, 30); // Posição absoluta dentro do 'painel'
        painel.add(titulo);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setBounds(30, 70, 240, 20);
        painel.add(lblEmail);

        campoEmail = new JTextField();
        campoEmail.setBounds(30, 90, 240, 30);
        painel.add(campoEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setBounds(30, 130, 240, 20);
        painel.add(lblSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(30, 150, 240, 30);
        painel.add(campoSenha);

        JButton btLogin = new JButton("Entrar");
        btLogin.setBounds(30, 200, 240, 35);
        btLogin.setBackground(new Color(255, 102, 204));
        btLogin.setForeground(Color.WHITE);
        painel.add(btLogin);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(30, 250, 240, 30);
        btnCadastrar.setForeground(Color.BLACK);
        painel.add(btnCadastrar);


        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCadastro().setVisible(true); // Abre a tela de cadastro
                dispose(); // Fecha a tela de login, opcional
            }
        });


        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = campoEmail.getText();
                String senha = new String(campoSenha.getPassword());

                Usuario usuario = usuarioController.login(email, senha);

                if (usuario != null) {
                    if (usuario.isAdmin()) {
                    JOptionPane.showConfirmDialog(null, "Login ADM realizado com sucesso.!");
                    new TelaMenuAdmin(usuario).setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(null, "Login realizado com sucesso.");
                        new TelaMenuUsuario(usuario).setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha inválidos.");
                }
            }
        });



    }
}
