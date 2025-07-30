package persistence;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import models.*;

public class ReservaDAO {

    private static final java.nio.file.Path CAMINHO_ARQUIVO = Paths.get("src/data/reservas/reservas.txt");

    public ReservaDAO() {
        try {
            Files.createDirectories(CAMINHO_ARQUIVO.getParent());
        } catch (IOException e) {
            System.err.println("Erro ao garantir que o diretório de dados exista: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Novo método auxiliar para gerar ID único
    private int gerarNovoId() {
        List<Reserva> reservas = listarTodas();
        int maxId = 0;
        for (Reserva r : reservas) {
            if (r.getId() > maxId) {
                maxId = r.getId();
            }
        }
        return maxId + 1;
    }

    public void salvar(Reserva reserva) {
        // Gera e define o novo ID automaticamente
        int novoId = gerarNovoId();
        reserva.setId(novoId);

        

        

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO.toFile(), true))) {
            String linha = String.format("%d;%d;%s;%s;%s;%d;%s;%s;%s",
                reserva.getId(),
                reserva.getEspaco().getID(),
                reserva.getData().getTime(),
                reserva.getHoraInicio(),
                reserva.getHoraFim(),
                reserva.getResponsavel().getId(),
                reserva.getResponsavel().getNome(),
                reserva.getResponsavel().getEmail(),
                reserva.getEspaco().getNome()
            );
            writer.write(linha);
            writer.newLine();
            System.out.println("Reserva salva com sucesso no arquivo.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar reserva no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Reserva> listarTodas() {
        List<Reserva> reservas = new ArrayList<>();
        File arquivo = CAMINHO_ARQUIVO.toFile();

        if (!arquivo.exists()) {
            System.out.println("Arquivo de reservas não encontrado, retornando lista vazia.");
            return reservas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length < 9) {
                    System.err.println("Linha mal formatada no arquivo de reservas: " + linha);
                    continue;
                }

                try {
                    int id = Integer.parseInt(dados[0]);
                    Date data = new Date(Long.parseLong(dados[2]));
                    String horaInicio = dados[3];
                    String horaFim = dados[4];
                    int usuarioId = Integer.parseInt(dados[5]);
                    String nomeUsuario = dados[6];
                    String emailUsuario = dados[7];
                    String nomeEspaco = dados[8];

                    Usuario usuario = new Usuario(usuarioId, nomeUsuario, emailUsuario, "senha_dummy", false);
                    Espaco espaco = new SalaAula(nomeEspaco, "desc_dummy", 0, false);

                    Reserva reserva = new Reserva(id, espaco, data, horaInicio, horaFim, usuario);
                    reservas.add(reserva);
                } catch (NumberFormatException e) {
                    System.err.println("Erro de formato numérico em linha: " + linha + " - " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Erro inesperado ao processar linha: " + linha + " - " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler reservas do arquivo: " + e.getMessage());
            e.printStackTrace();
        }
        return reservas;
    }

    public void remover(int idParaRemover) {
        List<Reserva> reservas = listarTodas();
        boolean removido = false;
        List<Reserva> reservasRestantes = new ArrayList<>();

        for (Reserva r : reservas) {
            if (r.getId() != idParaRemover) {
                reservasRestantes.add(r);
            } else {
                removido = true;
            }
        }

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
                writer.write(linha);
                writer.newLine();
            }

            if (removido) {
                System.out.println("Reserva com ID " + idParaRemover + " removida com sucesso.");
            } else {
                System.out.println("Reserva com ID " + idParaRemover + " não encontrada.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao remover reserva e reescrever o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
