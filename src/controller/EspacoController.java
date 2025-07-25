package controller;
import java.util.*;
import models.*;
import persistence.EspacoDAO;

public class EspacoController {
    private final EspacoDAO espacoDaoinstance;

    public EspacoController() {
        this.espacoDaoinstance = new EspacoDAO();
    }

    public void cadastrarEspaco(Espaco space) {
        espacoDaoinstance.salvar(space);
    }

    public void removerEspaco(int id) {
        espacoDaoinstance.remover(id);
    }

    public List<Espaco> listarTodosEspacos() {
        return espacoDaoinstance.listar();
    }

    public Espaco buscarPorId(int id) {
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

    public boolean editarEspaco(Espaco espacoAtt) {
        return espacoDaoinstance.atualizar(espacoAtt);
    }
}
