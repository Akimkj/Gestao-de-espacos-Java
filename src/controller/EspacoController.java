package controller;
import java.util.*;
import models.*;
import persistence.EspacoDAO;
import controller.RelatorioController;

public class EspacoController {
    private final EspacoDAO espacoDaoinstance;
    private RelatorioController relatorioController = new RelatorioController();

    public EspacoController() {
        this.espacoDaoinstance = new EspacoDAO();
    }

    public void cadastrarEspaco(Espaco space, Usuario usuario) {
        int novoId = espacoDaoinstance.gerarProximoId();
        space.setID(novoId);
        espacoDaoinstance.salvar(space);
        relatorioController.registrarLog(usuario.getNome(), "cadastrou o espaço: " + space.getNome());
    }

    public void removerEspaco(int id, Usuario usuario) {
        relatorioController.registrarLog(usuario.getNome(), "removeu o espaço com ID: " + id);
        espacoDaoinstance.remover(id);
    }

    public List<Espaco> listarTodosEspacos() {
        return espacoDaoinstance.listar();
    }

    public Espaco buscarPorId(int id, Usuario usuario) {
        relatorioController.registrarLog(usuario.getNome(), "buscou espaço com ID: " + id);
        List<Espaco> espacos = espacoDaoinstance.listar();
        if (espacos != null) {
            for (Espaco space: espacos) {
                if (space.getID() == id) {
                    return space;
                }
            }
        }
        return null;
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
