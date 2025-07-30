package controller;

import java.util.List;
import models.Usuario;
import persistence.UsuarioDao;
import controller.RelatorioController;

public class UsuarioController {
    private final UsuarioDao usuarioDao = new UsuarioDao();
    private Usuario usuarioLogado;
    private RelatorioController relatorioController = new RelatorioController();

    public Usuario login(String email, String senha) {
        Usuario usuario = usuarioDao.buscarPorEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            usuarioLogado = usuario;
            relatorioController.registrarLog(usuario.getNome(), "realizou login com sucesso");
            System.out.println("Login realizado com sucesso.");
            return usuario;
        } else {
            System.out.println("Credenciais inválidas.");
            return null;
        }
    }

    public void logout() {
        if (usuarioLogado != null) {
            relatorioController.registrarLog(usuarioLogado.getNome(), "realizou logout");
            System.out.println("Logout realizado: " + usuarioLogado.getNome());
            usuarioLogado = null;
        } else {
            System.out.println("Nenhum usuário está logado.");
        }
    }

    public void cadastrarUsuario(Usuario novoUsuario) {
        usuarioDao.salvar(novoUsuario);
        relatorioController.registrarLog(novoUsuario.getNome(), "cadastrou novo usuário");
    }

    public boolean removerUsuarioPorEmail(String email) {
        for (Usuario u : usuarioDao.listarTodos()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                usuarioDao.remover(u.getId());
                return true;
            }
        }
        return false;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }










    public List<Usuario> listarUsuarios() {
        return usuarioDao.listarTodos();
    }

    public void removerUsuario(int id) {
        usuarioDao.remover(id);
    }
}
