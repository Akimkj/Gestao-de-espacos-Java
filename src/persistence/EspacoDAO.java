package persistence;

import java.io.*; // Importa classes para operações de entrada e saída (arquivos)
import java.util.*; // Importa classes utilitárias, como List e ArrayList
import models.*; // Importa todas as classes do pacote models (Espaco, SalaAula, Laboratorio, Quadra, Auditorio)


public class EspacoDAO { // Declaração da classe EspacoDAO, responsável pela persistência de dados de Espaço

    private final String caminhoArquivo = "src\\data\\espacos.txt"; // Define o caminho do arquivo onde os dados dos espaços serão armazenados

    // Método para listar todos os espaços lidos do arquivo
    public List<Espaco> listar() {
        List<Espaco> espacos = new ArrayList<>(); // Cria uma nova lista para armazenar os objetos Espaco
        try (BufferedReader read = new BufferedReader(new FileReader(caminhoArquivo))) { // Tenta abrir o arquivo para leitura
            String linha; // Variável para armazenar cada linha lida do arquivo
            while ((linha = read.readLine()) != null) { // Loop enquanto houver linhas para ler
                String[] dados = linha.split(";"); // Divide a linha em um array de strings usando o ";" como delimitador
                if (dados.length < 5) { // Verifica se a linha tem o número mínimo de dados esperados
                    continue; // Pula para a próxima iteração se a linha for inválida
                }
                int id = Integer.parseInt(dados[0]); // Converte o primeiro dado para int (ID)
                String nome = dados[1]; // O segundo dado é o nome
                String localizacao = dados[2]; // O terceiro dado é a localização
                int capacidade = Integer.parseInt(dados[3]); // O quarto dado é a capacidade (convertido para int)
                String tipo = dados[4]; // O quinto dado é o tipo do espaço
                String atrib_especial = dados[5]; // O sexto dado é o atributo especial do espaço

                Espaco espaco = null; // Inicializa um objeto Espaco como nulo

                switch (tipo.toUpperCase()) { // Usa um switch para criar o objeto Espaco correto baseado no tipo
                    case "SALA DE AULA": // Se o tipo for "SALA DE AULA"
                        if (atrib_especial.equals("possuiProjetor=SIM")) { // Verifica o atributo especial para o projetor
                            espaco = new SalaAula(nome, localizacao, capacidade, true); // Cria uma SalaAula com projetor
                        }
                        else {
                            espaco = new SalaAula(nome, localizacao, capacidade, false); // Cria uma SalaAula sem projetor
                        }
                        break; // Sai do switch
                    case "LABORATORIO": // Se o tipo for "LABORATORIO"
                        String valorEquipamento = atrib_especial.replace("TIPOEQUIPAMENTO=", ""); // Remove o prefixo para obter o valor do equipamento
                        espaco = new Laboratorio(nome, localizacao, capacidade, valorEquipamento); // Cria um Laboratorio
                        break; // Sai do switch
                    case "QUADRA": // Se o tipo for "QUADRA"
                        espaco = new Quadra(nome, localizacao, capacidade, atrib_especial); // Cria uma Quadra
                        break; // Sai do switch
                    case "AUDITORIO": // Se o tipo for "AUDITORIO"
                        if (atrib_especial.equals("possuiPalco=SIM")) { // Verifica o atributo especial para o palco
                            espaco = new Auditorio(nome, localizacao, capacidade, true); // Cria um Auditorio com palco
                        }
                        else {
                            espaco = new Auditorio(nome, localizacao, capacidade, false); // Cria um Auditorio sem palco
                        }
                        break; // Sai do switch
                    default: // Se o tipo for desconhecido
                        System.out.println("Tipo desconhecido: " + tipo); // Imprime uma mensagem de erro
                        break; // Sai do switch
                }

                if (espaco != null) { // Se um objeto Espaco foi criado com sucesso
                    espaco.setID(id); // Define o ID do espaço
                    espacos.add(espaco); // Adiciona o espaço à lista
                }
            }

            return espacos; // Retorna a lista de espaços lidos
        }
        catch (Exception e) { // Captura qualquer exceção que possa ocorrer durante a leitura do arquivo
            e.printStackTrace(); // Imprime o stack trace da exceção
            return null; // Retorna nulo em caso de erro
        }
    }

    // Método para salvar um novo espaço no arquivo
    public void salvar(Espaco espaco) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) { // Tenta abrir o arquivo para escrita (modo append)
            String linha = espaco.toString(); // Converte o objeto Espaco para sua representação em string

            bw.write(linha); // Escreve a linha no arquivo
            bw.newLine(); // Adiciona uma nova linha

        }
        catch (IOException e) { // Captura exceções de IO
            e.printStackTrace(); // Imprime o stack trace da exceção
        }
    }

    // Método para atualizar um espaço existente no arquivo
    public boolean atualizar(Espaco espacoAtualizado) {
        List<Espaco> listaEspacos = listar(); // Lista todos os espaços do arquivo
        boolean atualizado = false; // Flag para indicar se o espaço foi atualizado

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, false))) { // Tenta abrir o arquivo para escrita (sobrescreve o arquivo)
            for(Espaco space: listaEspacos) { // Itera sobre a lista de espaços
                if (space.getID() == espacoAtualizado.getID()) { // Se o ID do espaço na lista for igual ao ID do espaço a ser atualizado
                    bw.write(espacoAtualizado.toString()); // Escreve a nova representação do espaço atualizado
                    atualizado = true; // Define a flag de atualização como true
                }
                else {
                    bw.write(space.toString()); // Caso contrário, escreve o espaço original de volta
                }
                bw.newLine(); // Adiciona uma nova linha
            }
        }
        catch (IOException e) { // Captura exceções de IO
            //Atualizar para interface gráfica
            System.out.println("Erro ao atualizar o espaço: " + e.getMessage()); // Imprime uma mensagem de erro
            return false; // Retorna false indicando falha na atualização
        }

        return atualizado; // Retorna true se o espaço foi atualizado, false caso contrário
    }

    // Método para remover um espaço do arquivo pelo ID
    public void remover(int idParaRemover) {
        List<Espaco> espacos = listar(); // Lista todos os espaços do arquivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) { // Tenta abrir o arquivo para escrita (sobrescreve o arquivo)
            for (Espaco space : espacos) { // Itera sobre a lista de espaços
                if (space.getID() != idParaRemover) { // Se o ID do espaço na lista não for o ID a ser removido
                    String linha = space.toString(); // Converte o objeto Espaco para sua representação em string

                    bw.write(linha); // Escreve a linha no arquivo
                    bw.newLine(); // Adiciona uma nova linha
                }
            }
            System.out.println("Espaco removido."); // Imprime uma mensagem de sucesso
        }
        catch (IOException e) { // Captura exceções de IO
            e.printStackTrace(); // Imprime o stack trace da exceção
        }
    }

    // Método para gerar o próximo ID disponível para um novo espaço
    public int gerarProximoId() {
        int maiorId = 0; // Inicializa o maior ID encontrado como 0

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) { // Tenta abrir o arquivo para leitura
            String linha; // Variável para armazenar cada linha lida do arquivo
            while ((linha = br.readLine()) != null) { // Loop enquanto houver linhas para ler
                String[] partes = linha.split(";"); // Divide a linha em partes
                int idAtual = Integer.parseInt(partes[0]); // Converte a primeira parte para int (ID)
                if (idAtual > maiorId) { // Se o ID atual for maior que o maior ID encontrado até agora
                    maiorId = idAtual; // Atualiza o maior ID
                }
            }
        } catch (IOException | NumberFormatException e) { // Captura exceções de IO ou formato de número inválido
            System.out.println("Erro ao gerar ID: " + e.getMessage()); // Imprime uma mensagem de erro
        }

        return maiorId + 1; // Retorna o maior ID encontrado mais 1 (o próximo ID disponível)
    }

}
