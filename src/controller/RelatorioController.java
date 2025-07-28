package controller;
import models.Log;
import persistence.LogDAO;

/**
 * controlador responsável por registrar ações importantes no sistema.
 * recebe dados de usuário e ação, cria o log e envia para o LogDAO salvar.
 */

public class RelatorioController {
    //guarda a instância única do LogDAO para registrar logs
    private LogDAO logDAOInstance; 

    //construtor, obtem a instância única do LogDAO.
    public RelatorioController(){
        this.logDAOInstance= LogDAO.getInstance();
    }
    
    //método que cria um novo log com o usúario e ação informados, e registra esse log usando o LogDAO
    public void registrarLog(String usuario, String acao) {
        // Obtém a data e hora atual no formato desejado
        String dataHora = java.time.LocalDateTime.now()
        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // IP fixo simulado (substitua com o real se estiver em app web ou rede)
        String enderecoIP = "192.168.0.101"; // pode ser substituído por getLocalHost se quiser

        // Cria uma mensagem completa de log
        String mensagemLog = String.format("[%s] IP: %s | Usuário: %s | Ação: %s", dataHora, enderecoIP, usuario, acao);

        // Cria o objeto Log com a mensagem completa como "ação"
        Log log = new Log(usuario, mensagemLog);

        // Salva na memória e no arquivo
        logDAOInstance.registrar(log);
    }
}
