package view; 
import controller.RelatorioController; 
import models.Usuario;
import java.io.*; //importa classes para leitura e escrita de arquivos
import java.awt.*; //importa classes para criar elementos visuais 
import java.awt.event.*; //importa classes para lidar com ações 
import javax.swing.*; //importa as classes da biblioteca swing 
import java.nio.file.*; //importa classes para manipular arquivos e diretórios
import java.util.zip.ZipEntry; //permite criar entradas dentro de arquivos zip
import java.util.zip.ZipOutputStream; //permite escrever arquivos no formato zip

public class RelatoriosView extends JFrame { 

    private JLabel tituloLabel; 
    private JButton botaoCsv; 
    private JButton botaoLog; 
    private JButton botaoPersistencia; 
    private JButton botaoVoltar; 
    
    private final RelatorioController controller; //controlador responsável por registrar logs
    private Usuario usuario;

    public RelatoriosView(Usuario usuario) {
        this.usuario = usuario;
        this.controller = new RelatorioController(); //cria o controlador que registra logs
        criarComponentes(); //cria os botões e o título
        configurarJanela(); //define tamanho e aparência da janela
        adicionarNaTela();  //adiciona os elementos visuais na janela
        configurarBotoes(); //define o que acontece quando os botões são clicados
    }

    //método que cria os componentes visuais da tela 
    private void criarComponentes() {
        tituloLabel = new JLabel("Relatórios"); //cria o texto do título
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 28)); //define a fonte do título
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER); //coloca o título no meio
        tituloLabel.setForeground(new Color(0,102,255)); //define a cor do título

        botaoCsv = new JButton("Relatório Log"); //botão para gerar csv 
        estilizarBotao(botaoCsv, new Color(0, 87, 183)); // aparencia do botao de pegar o relatorio csv
        
        botaoVoltar = new JButton("Voltar");
        estilizarBotao(botaoVoltar, Color.DARK_GRAY);

        if(usuario.isAdmin()){
            botaoLog = new JButton("Histórico do Log"); //botão para salvar o log se o usario for adm
            botaoPersistencia = new JButton("Salvar Banco de Dados (ZIP)"); //botão para salvar zip se o usuario for adm
            estilizarBotao(botaoLog, new Color(106, 13, 173)); //aparencia do botão historico de log
            estilizarBotao(botaoPersistencia, new Color(212, 20, 90)); //aparencia do botão de banco de dados
        }
        
    }

    //método que define o tamanho da janela, título e cor de fundo
    private void configurarJanela() {
        setTitle("AMBIENTA - Relatórios"); //texto que aparece na parte superior da janela
        setSize(600,500); //tamanho da janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //fechar só essa janela sem encerrar todo o programa
        setLocationRelativeTo(null); //centraliza a janela no meio da tela
        setLayout(new BorderLayout()); //organização dos elementos na tela
        getContentPane().setBackground(new Color(193, 219, 253)); //cor de fundo 
    }

    //método que adiciona o título e os botões na janela
    private void adicionarNaTela() {
        JPanel painelTitulo = new JPanel(); //cria um painel só para o título
        painelTitulo.setBackground(new Color(193, 219, 253)); //cor de fundo igual a da janela
        painelTitulo.add(tituloLabel); //adiciona o titulo ao painel
        add(painelTitulo, BorderLayout.NORTH); //coloca o painel do título no topo da janela

        JPanel painelBotoes = new JPanel(new GridLayout(3, 1, 10, 10)); //painel organizado em 3 linhas e 1 coluna, com 10px de espaço entre os elementos
        painelBotoes.setBackground(new Color(193, 219, 253)); //cor de fundo
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); //cria um espaço vazio ao redor dos botões

        painelBotoes.add(botaoCsv); //adiciona botão que gera csv

        if (usuario.isAdmin()){   
        painelBotoes.add(botaoLog); //adiciona botão que gera log se o usario for adm
        painelBotoes.add(botaoPersistencia); //adiciona botão que gera o zip se o usario for adm
        }

        add(painelBotoes, BorderLayout.CENTER); //adiciona os botões ao centro da janela
        JPanel painelRodape = new JPanel(); // cria um painel para o rodapé
        painelRodape.setBackground(new Color(193, 219, 253)); // define a cor de fundo do rodapé
        painelRodape.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));// define a borda interna do rodapé com espaçamento
        painelRodape.add(botaoVoltar);  // adiciona o botão voltar ao painel do rodapé
        add(painelRodape, BorderLayout.SOUTH); // adiciona o painel do rodapé na parte inferior da janela
    }

    //método que diz o que acontece quando o usuário clica nos botões
    private void configurarBotoes() {
        botaoCsv.addActionListener(new ActionListener() { //adiciona ação para o clique do botão csv
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.registrarLog(usuario.getNome(), "Clicou em Relatórios CSV"); //registra que o botão foi clicado
                salvarArquivo("relatorio log", "id,nome,data\n1,Relatório de Teste,2025-07-26\n"); //salva um arquivo com esses dados
            }
        });
    
        botaoVoltar.addActionListener(new ActionListener() { //adiciona ação para o clique do botão voltar
            @Override 
            public void actionPerformed(ActionEvent e){
                controller.registrarLog(usuario.getNome(), "Clicou em Voltar");  //registra no log que o botão voltar foi clicado
                if (usuario.isAdmin()){ 
                    new TelaMenuAdmin(usuario).setVisible(true); //abre a tela do menu do administrador
                }else{
                    new TelaMenuUsuario(usuario).setVisible(true); //abre a tela do menu do usuário comum
                }
                dispose();//fecha a tela atual
            }
        });


        if (usuario.isAdmin()){ //verifica se o usuário é administrador
            botaoLog.addActionListener(new ActionListener() { //adiciona ação para o clique do botão log
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.registrarLog(usuario.getNome(), "Clicou em Histórico do Log"); //registra o clique no botão log se o usario for adm
                    copiarArquivoExistente("src/data/log_sistema/log_sistema.txt"); //copia o arquivo de log para outro local, apenas o adm pode fazer
                }
            });

            botaoPersistencia.addActionListener(new ActionListener() { //adiciona ação para o clique do botão persistência (backup zip)
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.registrarLog(usuario.getNome(), "Clicou em Backup ZIP"); //registra o clique no botão zip se o usario for adm
                    salvarZip("banco1.txt", "Backup dos bancos de dados."); //cria um zip com esse conteúdo dentro se o usario for adm
                }
            });
        }
    }

    //método que define a aparência dos botões 
    private void estilizarBotao(JButton botao, Color corTexto) {
        botao.setFont(new Font("Arial", Font.BOLD, 14)); //a fonte
        botao.setForeground(corTexto); //cor do texto do botão
        botao.setBackground(new Color(148, 185, 255)); //a cor de fundo do botão
        botao.setFocusPainted(false); //tira o contorno quando o botão é selecionado
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); //adiciona espaço dentro do botão
    }

    //método que abre uma janela para o usuário escolher onde salvar e grava o conteúdo em um arquivo
    private void salvarArquivo(String nomePadrao, String conteudo) {
        JFileChooser chooser = new JFileChooser(); //abre a janela de escolha de arquivo
        chooser.setSelectedFile(new File(nomePadrao)); //sugere um nome de arquivo
        int escolha = chooser.showSaveDialog(this); //mostra a janela
        if (escolha == JFileChooser.APPROVE_OPTION) {
            try (FileWriter writer = new FileWriter(chooser.getSelectedFile())) {
                writer.write(conteudo); //escreve o conteudo no arquivo escolhido
                JOptionPane.showMessageDialog(this, "Arquivo salvo com sucesso!"); //mostra mensagem de sucesso
                controller.registrarLog(usuario.getNome(), "Salvo arquivo LOG: " + chooser.getSelectedFile().getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage()); //mostra erro se der problema
            }
        }
    }

    //método que copia um arquivo que já existe para um local que o usuário escolher
    private void copiarArquivoExistente(String nomeArquivoOrigem) {
        JFileChooser chooser = new JFileChooser(); //abre janela para escolher onde salvar
        chooser.setSelectedFile(new File(nomeArquivoOrigem)); //sugere o nome do arquivo
        int escolha = chooser.showSaveDialog(this); //mostra a janela
        if (escolha == JFileChooser.APPROVE_OPTION) {
            try {
                Files.copy(
                    Paths.get(nomeArquivoOrigem), //caminho do arquivo original
                    chooser.getSelectedFile().toPath(), //destino escolhido pelo usuário
                    StandardCopyOption.REPLACE_EXISTING //substitui se já existir um arquivo com o mesmo nome
                );
                JOptionPane.showMessageDialog(this, "Log salvo com sucesso!"); //mostra mensagem de sucesso
                controller.registrarLog(usuario.getNome(), "Salvou cópia do log: " + chooser.getSelectedFile().getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao copiar log: " + ex.getMessage()); //mostra erro se não conseguir copiar
            }
        }
    }

    //método que cria um arquivo zip e coloca dentro um arquivo de texto com o conteúdo informado
    private void salvarZip(String nomeInterno, String conteudo) {
        JFileChooser chooser = new JFileChooser(); //abre a janela para o usuário escolher onde salvar
        chooser.setSelectedFile(new File("bancos_dados.zip")); //sugere nome do arquivo zip
        int escolha = chooser.showSaveDialog(this); //mostra a janela
        if (escolha == JFileChooser.APPROVE_OPTION) {
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(chooser.getSelectedFile()))) {
                zos.putNextEntry(new ZipEntry(nomeInterno)); //cria uma entrada dentro do zip
                zos.write(conteudo.getBytes()); //escreve o conteúdo dentro da entrada
                zos.closeEntry(); //finaliza a entrada
                JOptionPane.showMessageDialog(this, "Banco de dados salvo com sucesso!"); //mensagem de sucesso
                controller.registrarLog(usuario.getNome(), "Salvou arquivo ZIP: " + chooser.getSelectedFile().getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao criar ZIP: " + ex.getMessage()); //mensagem de erro se não conseguir criar o zip
            }
        }
    }
}