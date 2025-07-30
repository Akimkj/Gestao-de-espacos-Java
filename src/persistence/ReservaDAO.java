package persistence;

// Importações para manipulação de arquivos e listas
import java.io.*;
import java.nio.file.Files; // Permite criar diretórios e verificar existência de caminhos
import java.nio.file.Paths; // Permite definir caminhos de forma segura
import java.util.*;
import models.*; // Importa as classes de modelo como Reserva, Usuario, SalaAula, etc.

// Classe responsável por salvar, listar e remover reservas do arquivo
public class ReservaDAO {

    // Caminho para o arquivo onde as reservas são salvas
    private static final java.nio.file.Path CAMINHO_ARQUIVO = Paths.get("Gestao-de-espacos-java-src/data/reservas/reservas.txt");

    // Construtor
    public ReservaDAO() {
        // Garante que o diretório onde o arquivo está localizado exista
        try {
            Files.createDirectories(CAMINHO_ARQUIVO.getParent());
        } catch (IOException e) {
            // Exibe erro caso falhe ao criar diretórios
            System.err.println("Erro ao garantir que o diretório de dados exista: " + e.getMessage());
            e.printStackTrace();
            // Poderia-se lançar uma exceção ou encerrar o programa, dependendo da necessidade
        }
    }

    // Método para salvar uma reserva no arquivo
    public void salvar(Reserva reserva) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO.toFile(), true))) {
            // Cria uma linha no formato separado por ponto e vírgula com os dados da reserva
            String linha = String.format("%d;%d;%s;%s;%s;%d;%s;%s;%s",
                reserva.getId(), // ID da reserva
                reserva.getEspaco().getID(), // ID do espaço
                reserva.getData().getTime(), // Data em milissegundos
                reserva.getHoraInicio(), // Hora de início
                reserva.getHoraFim(), // Hora de fim
                reserva.getResponsavel().getId(), // ID do usuário responsável
                reserva.getResponsavel().getNome(), // Nome do usuário
                reserva.getResponsavel().getEmail(), // Email do usuário
                reserva.getEspaco().getNome() // Nome do espaço
            );
            writer.write(linha); // Escreve a linha no arquivo
            writer.newLine(); // Pula para a próxima linha
            System.out.println("Reserva salva com sucesso no arquivo.");
        } catch (IOException e) {
            // Exibe erro caso falhe ao salvar
            System.err.println("Erro ao salvar reserva no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para listar todas as reservas salvas no arquivo
    public List<Reserva> listarTodas() {
        List<Reserva> reservas = new ArrayList<>(); // Lista de reservas
        File arquivo = CAMINHO_ARQUIVO.toFile(); // Converte o Path em File

        // Se o arquivo não existir, retorna a lista vazia
        if (!arquivo.exists()) {
            System.out.println("Arquivo de reservas não encontrado, retornando lista vazia.");
            return reservas;
        }

        // Lê o arquivo linha por linha
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";"); // Separa os dados por ";"
                if (dados.length < 9) {
                    // Se a linha estiver incompleta, exibe aviso
                    System.err.println("Linha mal formatada no arquivo de reservas: " + linha);
                    continue;
                }

                try {
                    // Converte os dados de texto para os tipos corretos
                    int id = Integer.parseInt(dados[0]);
                    Date data = new Date(Long.parseLong(dados[2]));
                    String horaInicio = dados[3];
                    String horaFim = dados[4];
                    int usuarioId = Integer.parseInt(dados[5]);
                    String nomeUsuario = dados[6];
                    String emailUsuario = dados[7];
                    String nomeEspaco = dados[8];

                    // Cria objetos a partir dos dados
                    Usuario usuario = new Usuario(usuarioId, nomeUsuario, emailUsuario, "senha_dummy", false);
                    Espaco espaco = new SalaAula(nomeEspaco, "desc_dummy", 0, false);

                    Reserva reserva = new Reserva(id, espaco, data, horaInicio, horaFim, usuario);
                    reservas.add(reserva); // Adiciona a reserva na lista
                } catch (NumberFormatException e) {
                    // Exibe erro caso falhe ao converter números
                    System.err.println("Erro de formato numérico em linha: " + linha + " - " + e.getMessage());
                } catch (Exception e) {
                    // Exibe erro inesperado ao processar linha
                    System.err.println("Erro inesperado ao processar linha: " + linha + " - " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // Exibe erro caso falhe ao ler o arquivo
            System.err.println("Erro ao ler reservas do arquivo: " + e.getMessage());
            e.printStackTrace();
        }
        return reservas; // Retorna todas as reservas lidas
    }

    // Método para remover uma reserva do arquivo com base no ID
    public void remover(int idParaRemover) {
        List<Reserva> reservas = listarTodas(); // Lê todas as reservas
        boolean removido = false;
        List<Reserva> reservasRestantes = new ArrayList<>(); // Lista para armazenar as reservas restantes

        // Adiciona todas as reservas exceto a que será removida
        for (Reserva r : reservas) {
            if (r.getId() != idParaRemover) {
                reservasRestantes.add(r);
            } else {
                removido = true; // Marca que encontrou a reserva para remoção
            }
        }

        // Reescreve o arquivo com as reservas restantes
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO.toFile(), false))) {
            for (Reserva r : reservasRestantes) {
                String linha = String.format("%d;%d;%s;%s;%s;%d;%s;%s;%s",
                    r.getId(),
                    r.getEspaco().getID(),
                    r.getData().getTime(),
                    r.getHoraInicio(),
                    r.getHoraFim(),
                    r.getResponsavel().getId(),
                    r.getResponsavel().getNome(),
                    r.getResponsavel().getEmail(),
                    r.getEspaco().getNome()
                );
                writer.write(linha); // Escreve no arquivo
                writer.newLine();
            }

            // Informa o resultado da remoção
            if (removido) {
                System.out.println("Reserva com ID " + idParaRemover + " removida com sucesso.");
            } else {
                System.out.println("Reserva com ID " + idParaRemover + " não encontrada.");
            }
        } catch (IOException e) {
            // Exibe erro caso falhe ao reescrever o arquivo
            System.err.println("Erro ao remover reserva e reescrever o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
