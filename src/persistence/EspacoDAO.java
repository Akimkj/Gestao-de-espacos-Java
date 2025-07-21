package persistence;
import models.Espaco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*; 
import java.util.stream.Stream;

public class EspacoDAO {
    private final String caminhoArquivo = "..\\data\\espacos.txt";

    public List<Espaco> listarTodos() {
        List<Espaco> espacos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String localizacao = dados[2];
                int capacidade = dados[3];
                
            }
        } 
        catch (Exception e) {
        }
    }
}
