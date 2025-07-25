package persistence;
import java.io.*;
import java.util.*;
import models.*;


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
                String nome = dados[1];
                String localizacao = dados[2];
                int capacidade = Integer.parseInt(dados[3]);
                String tipo = dados[4];
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
            String linha = espaco.toString();

            bw.write(linha);
            bw.newLine();
            System.out.println("Reserva salva com sucesso no arquivo.");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean atualizar(Espaco espacoAtualizado) {
        List<Espaco> listaEspacos = listar();
        boolean atualizado = false;
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, false))) {
            for(Espaco space: listaEspacos) {
                if (space.getID() == espacoAtualizado.getID()) {
                    bw.write(espacoAtualizado.toString());
                    atualizado = true;
                } 
                else {
                    bw.write(space.toString());
                }
                bw.newLine();
            }
        }
        catch (IOException e) {
            //Atualizar para interface gráfica
            System.out.println("Erro ao atualizar o espaço: " + e.getMessage());
            return false;
        }

        return atualizado;
    }
    
    public void remover(int idParaRemover) {
        List<Espaco> espacos = listar();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Espaco space : espacos) {
                if (space.getID() != idParaRemover) {
                    String linha = space.toString();

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
