package persistence;
import models.Log;
import java.io.BufferedWriter; 
import java.io.FileWriter;     
import java.io.IOException;    
import java.util.ArrayList;    
import java.util.List;  

/**
 *classe LogDAO responsável por armazenar logs em memória e em arquivo.
 *implementa o padrão singleton para garantir uma única instância em toda a aplicação.
 */

public class LogDAO {
    private static final String CAMINHO_ARQUIVO = "log_sistema.txt"; //nome do arquivo onde os logs serão guardados
    private List<Log> logs;  //lista que guardar os logs na memória 

    //garantir que so exista uma única instância da classe LogDAO em toda a aplicação 
    private static LogDAO instanciaUnica;

    //construtor privado para impedir que outras classes criem instâncias diretamente
    private LogDAO() {
        this.logs = new ArrayList<>();
    }

    //método público estático para obter a instância única
    public static synchronized LogDAO getInstance(){ //synchronized: permite que apenas uma thread por vez execute este método, evitando que múltiplas instâncias sejam criadas ao mesmo tempo.
        if(instanciaUnica == null){
            instanciaUnica= new LogDAO(); //cria a instância única se ainda não existir
        }
        return instanciaUnica; //retorna a instância única
    }


    //método para escrever o log dentro do arquivo
    private void salvarEmArquivo(Log log) {
        //abre o arquivo para adicionar novas informações no final, sem apagar o que já estava lá
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, true))){
            writer.write(log.toString()); //escreve o log como texto (chama o método toString do log)
            writer.newLine();
        } catch (IOException e) {
            //se der erro, mostra a mensagem no terminal
            System.err.println("Erro ao salvar log: " + e.getMessage());
        }
    }

    //método para salvar um log
    public void registrar(Log log) {
        logs.add(log); //adiciona o log na lista de memória
        salvarEmArquivo(log); //também salva o log no arquivo de texto
    }

    //método que retorna a lista com todos os logs que estão na memória
    public List<Log> getLogs() {
        return new ArrayList<>(logs); //retorna uma nova lista com os mesmos elementos
    }
}