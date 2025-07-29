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

    /**
     * Construtor da classe EspacoController.
     * Inicializa a instância de EspacoDAO, preparando o controlador para interagir com o armazenamento de dados.
     */
    public EspacoController() {
        this.espacoDaoinstance = new EspacoDAO();
    }

    /**
     * Cadastra um novo espaço no sistema.
     * Esta função gera um novo ID único para o espaço, atribui este ID ao objeto Espaco e o salva usando o DAO.
     */
    public void cadastrarEspaco(Espaco space) {
        // Gera o próximo ID disponível para o novo espaço.
        int novoId = espacoDaoinstance.gerarProximoId();
        // Atribui o ID gerado ao objeto Espaco.
        space.setID(novoId);
        // Salva o espaço no sistema de persistência.
        espacoDaoinstance.salvar(space);
    }

    /**
     * Remove um espaço do sistema com base no seu ID.
     * Esta função delega a operação de remoção diretamente ao EspacoDAO.
     */
    public void removerEspaco(int id) {
        espacoDaoinstance.remover(id);
    }

    /**
     * Lista todos os espaços cadastrados no sistema.
     * Esta função delega a operação de listagem ao EspacoDAO e retorna a lista de espaços.
     */
    public List<Espaco> listarTodosEspacos() {
        return espacoDaoinstance.listar();
    }

    /**
     * Busca um espaço específico pelo seu ID.
     * Esta função recupera todos os espaços e itera sobre eles para encontrar o espaço com o ID correspondente.
     * Retorna o objeto Espaco se encontrado, ou null caso contrário.
     */
    public Espaco buscarPorId(int id) {
        // Obtém a lista completa de espaços.
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

    /**
     * Edita as informações de um espaço existente.
     * Esta função delega a operação de atualização ao EspacoDAO.
     * Retorna true se o espaço foi atualizado com sucesso, false caso contrário.
     */
    public boolean editarEspaco(Espaco espacoAtt) {
        return espacoDaoinstance.atualizar(espacoAtt);
    }
}
