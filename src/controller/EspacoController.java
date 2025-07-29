package controller;
import java.util.*;
import models.*;
import persistence.EspacoDAO;


/**
 * A classe EspacoController atua como uma camada de controle entre a interface do usuário (ou outras partes do sistema)
 * e a camada de persistência (EspacoDAO) para operações relacionadas a espaços. Ela gerencia a lógica de negócio
 * para cadastrar, remover, listar, buscar e editar espaços, delegando as operações de persistência ao EspacoDAO.
 */
public class EspacoController {
    // Declara uma instância final de EspacoDAO para interagir com o mecanismo de persistência de dados.
    private final EspacoDAO espacoDaoinstance;
    private RelatorioController relatorioController = new RelatorioController();

    /**
     * Construtor da classe EspacoController.
     * Inicializa a instância de EspacoDAO, preparando o controlador para interagir com o armazenamento de dados.
     */
    public EspacoController() {
        this.espacoDaoinstance = new EspacoDAO();
    }

    public void cadastrarEspaco(Espaco space, Usuario usuario) {
        int novoId = espacoDaoinstance.gerarProximoId();
        // Atribui o ID gerado ao objeto Espaco.
        space.setID(novoId);
        // Salva o espaço no sistema de persistência.
        espacoDaoinstance.salvar(space);
        relatorioController.registrarLog(usuario.getNome(), "cadastrou o espaço: " + space.getNome());
    }

    public void removerEspaco(int id, Usuario usuario) {
        relatorioController.registrarLog(usuario.getNome(), "removeu o espaço com ID: " + id);
        espacoDaoinstance.remover(id);
    }

    /**
     * Lista todos os espaços cadastrados no sistema.
     * Esta função delega a operação de listagem ao EspacoDAO e retorna a lista de espaços.
     */
    public List<Espaco> listarTodosEspacos() {
        return espacoDaoinstance.listar();
    }

    public Espaco buscarPorId(int id, Usuario usuario) {
        relatorioController.registrarLog(usuario.getNome(), "buscou espaço com ID: " + id);
        List<Espaco> espacos = espacoDaoinstance.listar();
        // Verifica se a lista não é nula para evitar NullPointerException.
        if (espacos != null) {
            // Itera sobre cada espaço na lista.
            for (Espaco space: espacos) {
                // Compara o ID do espaço atual com o ID procurado.
                if (space.getID() == id) {
                    return space; // Retorna o espaço se o ID coincidir.
                }
            }
        }
        return null; // Retorna null se nenhum espaço com o ID for encontrado.
    }

    public boolean editarEspaco(Espaco espacoAtt,Usuario usuario) {
        boolean sucesso = espacoDaoinstance.atualizar(espacoAtt);
        if (sucesso){
            relatorioController.registrarLog(usuario.getNome(), "editou o espaço com ID: " + espacoAtt.getID());
        }
        return sucesso;
    }
    public void registrarLog(String usuario, String acao) {
        relatorioController.registrarLog(usuario, acao);
    }
}
