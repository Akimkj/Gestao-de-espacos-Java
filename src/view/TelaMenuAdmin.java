package view;
import javax.swing.*;
import java.awt.*;
import models.Usuario;

public class TelaMenuAdmin extends JFrame {
    private Usuario usuarioLogado;


    public TelaMenuAdmin(Usuario usuario) {
        super("Menu do Administrador");
        this.usuarioLogado = usuario;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230)); // azul claro

        // Painel com gradiente
        JPanel painel = new GradientPanel();
        painel.setLayout(null);
        painel.setBounds(60, 60, 320, 340);
        painel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(painel);

        JLabel titulo = new JLabel("Administrador: " + usuarioLogado.getNome());
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(30, 20, 260, 30);
        painel.add(titulo);

        JButton btnCadastrarEspaco = new JButton("Cadastrar Espaço");
        btnCadastrarEspaco.setBounds(30, 60, 260, 35);
        painel.add(btnCadastrarEspaco);


        JButton btnRelatorio = new JButton("Relatorios Gerais");
        btnRelatorio.setBounds(30, 100, 260, 35);
        painel.add(btnRelatorio);


        JButton btnExcluirUsuario = new JButton("Expulsar Usuário");
        btnExcluirUsuario.setBounds(30, 145, 260, 35);
        painel.add(btnExcluirUsuario);

        JButton btnEspaçosDisponíveis = new JButton("Espaços Disponíveis");
        btnEspaçosDisponíveis.setBounds(30, 190, 260, 35);
        painel.add(btnEspaçosDisponíveis);

        JButton btnRemoveEspacos = new JButton("Remove Espacos");
        btnRemoveEspacos.setBounds(30, 190, 260, 35);
        painel.add(btnRemoveEspacos);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(30, 285, 260, 30);
        painel.add(btnSair);

        // Ações dos botões (você pode ligar às classes corretas aqui)




        btnRelatorio.addActionListener(e -> {
            new RelatoriosView(usuarioLogado).setVisible(true);
            dispose();
        });


        btnSair.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });

        btnExcluirUsuario.addActionListener(e ->{
            new ExcluirUsuario(usuarioLogado).setVisible(true);
            dispose();
        });
        btnCadastrarEspaco.addActionListener(e ->{
            new CadastroEspacoView(usuarioLogado).setVisible(true);
            dispose();
        });

        btnEspaçosDisponíveis.addActionListener(e ->{
            new EspacosDisponiveisView().setVisible(true);
        });


    }

    // Painel com gradiente
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


