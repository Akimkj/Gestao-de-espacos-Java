package persistence;
import models.Log;
import java.io.BufferedWriter; 
import java.io.FileWriter;     
import java.io.IOException;    
import java.util.ArrayList;    
import java.util.List;  
       
public class LogDAO {
    private static final String CAMINHO_ARQUIVO = "log_sistema.txt"; //nome do arquivo onde os logs serão guardados
    private static List<Log> logs = new ArrayList<>();  //lista que guardar os logs na memória 

    //método para escrever o log dentro do arquivo
    private static void salvarEmArquivo(Log log) {
        try {
            //abre o arquivo para adicionar novas informações no final, sem apagar o que já estava lá
            BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, true));
            writer.write(log.toString()); //escreve o log como texto (chama o método toString do log)
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            //se der erro, mostra a mensagem no terminal
            System.err.println("Erro ao salvar log: " + e.getMessage());
        }
    }

    //método para salvar um log
    public static void registrar(Log log) {
        logs.add(log); //adiciona o log na lista de memória
        salvarEmArquivo(log); //também salva o log no arquivo de texto
    }

    //método que retorna a lista com todos os logs que estão na memória
    public static List<Log> getLogs() {
        return logs;
    }
}