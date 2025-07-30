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

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void alterarSenha(String novaSenha) {
        if (usuarioLogado != null) {
            usuarioLogado.alterarSenha(novaSenha);
            usuarioDao.atualizar(usuarioLogado);
            relatorioController.registrarLog(usuarioLogado.getNome(), "alterou a senha");
        }
    }




    public void editarPerfil(String novoNome, String novoEmail) {
        if (usuarioLogado != null) {
            usuarioLogado.editarPerfil(novoNome, novoEmail);
            usuarioDao.atualizar(usuarioLogado);
            relatorioController.registrarLog(usuarioLogado.getNome(), "editou o perfil");
        }
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDao.listarTodos();
    }

    public void removerUsuario(int id) {
        usuarioDao.remover(id);
    }
}
