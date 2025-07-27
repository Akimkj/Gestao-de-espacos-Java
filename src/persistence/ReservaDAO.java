package persistence;

import java.io.*;
import java.util.*;
import models.*;
// GRAVE: Com erros em relação a estrutura de organização do reservas.txt e erros ao criar instancia para usuario e espaco nas linhas 48 e 49, além de ter que informar o caminho completo do arquivo na linha 7. Por favor, corrija seu código para ficar coerente com o restante do projeto.
public class ReservaDAO {
    private static final String ARQUIVO = "reservas.txt";

    // Salvar uma reserva no arquivo
    public void salvar(Reserva reserva) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            String linha = String.format("%d;%d;%s;%s;%s;%d;%s;%s",
                reserva.getId(),
                reserva.getEspaco().getID(),
                reserva.getData().getTime(),
                reserva.getHoraInicio(),
                reserva.getHoraFim(),
                reserva.getResponsavel().getId(),
                reserva.getResponsavel().getNome(),
                reserva.getResponsavel().getEmail()
            );
            writer.write(linha);
            writer.newLine();
            System.out.println("Reserva salva com sucesso no arquivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ler todas as reservas do arquivo
    public List<Reserva> listar() {
        List<Reserva> reservas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                int espacoId = Integer.parseInt(dados[1]);
                Date data = new Date(Long.parseLong(dados[2]));
                String horaInicio = dados[3];
                String horaFim = dados[4];
                int usuarioId = Integer.parseInt(dados[5]);
                String nome = dados[6];
                String email = dados[7];

                Usuario usuario = new Usuario(usuarioId, nome, email, email, false);
                Espaco espaco = new SalaAula("Sala " + espacoId, "Desconhecido", 0, false); // Simulação

                Reserva reserva = new Reserva(id, espaco, data, horaInicio, horaFim, usuario);
                reservas.add(reserva);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reservas;
    }

    // Remover uma reserva pelo ID
    public void remover(int idParaRemover) {
        List<Reserva> reservas = listar();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Reserva r : reservas) {
                if (r.getId() != idParaRemover) {
                    String linha = String.format("%d;%d;%s;%s;%s;%d;%s;%s",
                        r.getId(),
                        r.getEspaco().getID(),
                        r.getData().getTime(),
                        r.getHoraInicio(),
                        r.getHoraFim(),
                        r.getResponsavel().getId(),
                        r.getResponsavel().getNome(),
                        r.getResponsavel().getEmail()
                    );
                    writer.write(linha);
                    writer.newLine();
                }
            }
            System.out.println("Reserva removida (se existente).");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
