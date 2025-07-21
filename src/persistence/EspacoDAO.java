package persistence;
import java.io.*;
import java.util.*;
import models.Auditorio;
import models.Espaco;
import models.Laboratorio;
import models.Quadra;
import models.SalaAula; 



public class EspacoDAO {
    private final String caminhoArquivo = "..\\data\\espacos.txt";

    public List<Espaco> listar() {
        List<Espaco> espacos = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = read.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length < 5) {
                    continue;
                } 
                int id = Integer.parseInt(dados[0]);
                String tipo = dados[1];
                String nome = dados[2];
                String localizacao = dados[3];
                int capacidade = Integer.parseInt(dados[4]);
                String atrib_especial = dados[5];
                
                Espaco espaco = null;

                switch (tipo.toUpperCase()) {
                    case "SALAAULA":
                        if (atrib_especial.equals("possuiProjetor=SIM")) {
                            espaco = new SalaAula(id, nome, localizacao, capacidade, true);
                        }
                        else {
                            espaco = new SalaAula(id, nome, localizacao, capacidade, false);
                        }
                        break;
                    case "LABORATORIO":
                        espaco = new Laboratorio(id, nome, localizacao, capacidade, atrib_especial);
                        break;
                    case "QUADRA":
                        espaco = new Quadra(id, nome, localizacao, capacidade, atrib_especial);
                        break;
                    case "AUDITORIO":
                        if (atrib_especial.equals("possuiPalco=SIM")) {
                            espaco = new Auditorio(id, nome, localizacao, capacidade, true);
                        }
                        else {
                            espaco = new Auditorio(id, nome, localizacao, capacidade, false);
                        }
                        break;
                    default:
                        System.out.println("Tipo desconhecido: " + tipo);
                        break;
                }

                if (espaco != null) {
                    espacos.add(espaco);
                }
            }

            return espacos;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void salvar(Espaco espaco) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            String linha = String.format("%d;%s;%s;%s;%d;%s",
            espaco.getID(),
            espaco.getTipo(),
            espaco.getNome(),
            espaco.getLocalizacao(),
            espaco.getCapacidade(),
            espaco.getAtrib_esp());

            bw.write(linha);
            bw.newLine();
            System.out.println("Reserva salva com sucesso no arquivo.");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remover(int idParaRemover) {
        List<Espaco> espacos = listar();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Espaco space : espacos) {
                if (space.getID() != idParaRemover) {
                    String linha = String.format("%d;%s;%s;%s;%d;%s",
                            space.getID(),
                            space.getTipo(),
                            space.getNome(),
                            space.getLocalizacao(),
                            space.getCapacidade(),
                            space.getAtrib_esp());

                    bw.write(linha);
                    bw.newLine();
                }
            }
            System.out.println("Espaco removido.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
