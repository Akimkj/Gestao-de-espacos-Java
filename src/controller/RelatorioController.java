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
    public void registrarLog(String usuario, String acao){

        Log log = new Log(usuario,acao); //cria o objeto Log com a data/hora atual, usuário e ação
        logDAOInstance.registrar(log); //registra o log na lista e no arquivo através do DAO
    }
}
