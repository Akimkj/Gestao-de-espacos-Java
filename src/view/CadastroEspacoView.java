package view;
import controller.EspacoController;
import controller.RelatorioController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import models.*;


/*Classe da janela da interface gráfica para o administrador poder cadastrar um espaço.*/
public class CadastroEspacoView extends JFrame {
    private final EspacoController espacoController = new EspacoController(); // instancia do controller para salvar os dados do cadastro do espaço.

    private final JComboBox<String> tipoCombo; // caixa com os tipos de espaco
    private final JTextField nomeField, localizacaoField, capacidadeField; // caixas de texto para nome, loc e capacidade do espaco, respectivamente
    private final JPanel painelDinamico; // area dinamica dependendo do tipo do espaco
    private JRadioButton projetorSim, projetorNao, palcoSim, palcoNao; // opcao para os tipos sala de aula e auditorio
    private JTextField equipamentoField, esporteField; // opcao para tipos laboratorio e quadra
    private ButtonGroup grupoSimNao; // grupo dos botoes sim ou nao do campo dinamico
    private JButton botaoVoltar;

    //Método construtor onde inicializa a visualização da janela
    public CadastroEspacoView(Usuario usuarioLogado) {
        setTitle("AMBIENTA - Cadastro de Espaço"); // titulo da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // operacao de fechar a janela 
        setSize(750, 450); // tamanho da janela
        setLocationRelativeTo(null);
        setResizable(false);


        //Janela principal 
        JPanel mainPanel = new JPanel(); 
        mainPanel.setLayout(null);
        setContentPane(mainPanel);
        mainPanel.setBackground(new Color(194,220,255));

        // Label do titulo da janela principal
        JLabel titulo = new JLabel("Cadastro de Espaço");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        titulo.setForeground(new Color(0, 102, 255));
        titulo.setBounds(250, 20, 300, 30);
        mainPanel.add(titulo);

        //Janela do formulario
        GradientPanel formPanel = new GradientPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(60, 70, 620, 300);
        formPanel.setOpaque(false);

        // Label Tipo
        JLabel tipoLabel = criarLabel("TIPO: *");
        tipoLabel.setBounds(20, 35, 80, 20);
        formPanel.add(tipoLabel);
        tipoCombo = new JComboBox<>(new String[]{"Selecione", "SALA DE AULA", "LABORATÓRIO", "AUDITÓRIO", "QUADRA"});
        configurarCampo(tipoCombo, 100, 30, 180);
        tipoCombo.addActionListener(e -> atualizarCampoDinamico());
        formPanel.add(tipoCombo);

        // Label Nome
        JLabel nomeLabel = criarLabel("NOME: *");
        nomeLabel.setBounds(310, 30, 80, 20);
        formPanel.add(nomeLabel);
        nomeField = new JTextField();
        configurarCampo(nomeField, 380, 30, 180);
        formPanel.add(nomeField);

        // Label Localização
        JLabel localLabel = criarLabel("LOCALIZAÇÃO: *");
        localLabel.setBounds(20, 90, 140, 20);
        formPanel.add(localLabel);
        localizacaoField = new JTextField();
        configurarCampo(localizacaoField, 160, 90, 180);
        formPanel.add(localizacaoField);

        // Label Capacidade
        JLabel capLabel = criarLabel("CAPACIDADE: *");
        capLabel.setBounds(360, 90, 120, 20);
        formPanel.add(capLabel);
        capacidadeField = new JTextField();
        configurarCampo(capacidadeField, 470, 90, 100);
        formPanel.add(capacidadeField);

        // Label do campo do atributo especial
        painelDinamico = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelDinamico.setBounds(20, 140, 580, 50);
        painelDinamico.setOpaque(false);
        formPanel.add(painelDinamico);

        // Criação do botão Voltar
        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBackground(new Color(204, 229, 255));
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoVoltar.setForeground(Color.BLUE);
        botaoVoltar.setBounds(20, 20, 120, 35);
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                espacoController.registrarLog(usuarioLogado.getNome(), "Clicou em Voltar no Cadastro de Espaço");
                new TelaMenuAdmin(usuarioLogado).setVisible(true); // volta para o menu de adm 
                dispose(); // Fecha a tela de login, opcional
            }
        });
        mainPanel.add(botaoVoltar);

        // Botão cadastrar
        JButton cadastrarBtn = new JButton("Cadastrar");
        cadastrarBtn.setBounds(250, 200, 120, 35);
        cadastrarBtn.setBackground(new Color(204, 229, 255));
        cadastrarBtn.setForeground(Color.BLUE);
        cadastrarBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        cadastrarBtn.setFocusPainted(false);
        cadastrarBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        cadastrarBtn.addActionListener(e -> cadastrarEspaco());
        formPanel.add(cadastrarBtn);

        mainPanel.add(formPanel);
    }

    // método para atualizar o campo dos atributos especiais de acordo com o tipo de espaco selecionado
    private void atualizarCampoDinamico() {
        painelDinamico.removeAll();
        String tipo = (String) tipoCombo.getSelectedItem();

        switch (tipo) {
            case "SALA DE AULA":
                projetorSim = new JRadioButton("SIM");
                projetorNao = new JRadioButton("NÃO");
                grupoSimNao = new ButtonGroup();
                grupoSimNao.add(projetorSim);
                grupoSimNao.add(projetorNao);
                estilizarRadio(projetorSim);
                estilizarRadio(projetorNao);
                painelDinamico.add(criarLabel("POSSUI PROJETOR:"));
                painelDinamico.add(projetorSim);
                painelDinamico.add(projetorNao);
                break;

            case "LABORATÓRIO":
                equipamentoField = new JTextField(15);
                painelDinamico.add(criarLabel("EQUIPAMENTO:"));
                painelDinamico.add(equipamentoField);
                break;

            case "AUDITÓRIO":
                palcoSim = new JRadioButton("SIM");
                palcoNao = new JRadioButton("NÃO");
                grupoSimNao = new ButtonGroup();
                grupoSimNao.add(palcoSim);
                grupoSimNao.add(palcoNao);
                estilizarRadio(palcoSim);
                estilizarRadio(palcoNao);
                painelDinamico.add(criarLabel("POSSUI PALCO:"));
                painelDinamico.add(palcoSim);
                painelDinamico.add(palcoNao);
                break;

            case "QUADRA":
                esporteField = new JTextField(15);
                painelDinamico.add(criarLabel("ESPORTE:"));
                painelDinamico.add(esporteField);
                break;
        }

        painelDinamico.revalidate();
        painelDinamico.repaint();
    }

    //Pega as informações dos campos de textos e armazena.
    private void cadastrarEspaco() {
        String tipo = (String) tipoCombo.getSelectedItem();
        String nome = nomeField.getText();
        String localizacao = localizacaoField.getText();
        int capacidade;

        try {
            capacidade = Integer.parseInt(capacidadeField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Capacidade precisa ser número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Espaco novoEspaco = null;

        String infoAdicional = "";

        switch (tipo) {
            case "SALA DE AULA":
                if (projetorSim.isSelected()) infoAdicional = "Sim";
                else if (projetorNao.isSelected()) infoAdicional = "Não";
                else {
                    JOptionPane.showMessageDialog(this, "Informe se possui projetor.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean possuiProjetor = projetorSim.isSelected();

                novoEspaco = new SalaAula(nome, localizacao, capacidade, possuiProjetor);

                break;
            case "LABORATÓRIO":
                infoAdicional = equipamentoField.getText();

                novoEspaco = new Laboratorio(nome, localizacao, capacidade, infoAdicional);

                break;
            case "AUDITÓRIO":
                if (palcoSim.isSelected()) infoAdicional = "Sim";
                else if (palcoNao.isSelected()) infoAdicional = "Não";
                else {
                    JOptionPane.showMessageDialog(this, "Informe se possui palco.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean possuiPalco = palcoSim.isSelected();
                novoEspaco = new Auditorio(nome, localizacao, capacidade, possuiPalco);
                break;
            case "QUADRA":
                infoAdicional = esporteField.getText();

                novoEspaco = new Quadra(nome, localizacao, capacidade, infoAdicional);

                break;
            default:
                JOptionPane.showMessageDialog(this, "Selecione um tipo de espaço.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
        }

        espacoController.cadastrarEspaco(novoEspaco,usuarioLogado);
        espacoController.registrarLog(usuarioLogado.getNome(), "Cadastrou novo espaço: " + tipo + " - " + nome);
        JOptionPane.showMessageDialog(this,
            "Cadastro realizado:\n" +
            "Tipo: " + tipo + "\nNome: " + nome + "\nLocalização: " + localizacao +
            "\nCapacidade: " + capacidade + "\n" + infoAdicional);
    }

    //método auxiliar para criar os labels no construtor
    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(Color.BLACK);
        return label;
    }

    // configura os elementos nos espaços
    private void configurarCampo(JComponent comp, int x, int y, int largura) {
        comp.setBounds(x, y, largura, 30);
        comp.setBackground(new Color(232, 220, 255));
        comp.setForeground(Color.DARK_GRAY);
        if (comp instanceof JTextField)
            ((JTextField) comp).setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        comp.setFont(new Font("SansSerif", Font.PLAIN, 13));
    }

    private void estilizarRadio(JRadioButton radio) {
        radio.setOpaque(false);
        radio.setForeground(Color.WHITE);
        radio.setFont(new Font("SansSerif", Font.PLAIN, 13));
    }

    // Painel com gradiente
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, new Color(97, 111, 242), getWidth(), getHeight(), new Color(229, 112, 194));
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
