package controller;

import models.Usuario;
import persistence.UsuarioDao;
import java.util.List;

public class UsuarioController {
    private UsuarioDao usuarioDao = new UsuarioDao();
    private Usuario usuarioLogado;

    public boolean login(String email, String senha) {
        Usuario usuario = usuarioDao.buscarPorLogin(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            usuarioLogado = usuario;
            System.out.println("Login realizado com sucesso.");
            return true;
        } else {
            System.out.println("Credenciais inválidas.");
            return false;
        }
    }

    public void logout() {
        if (usuarioLogado != null) {
            System.out.println("Logout realizado: " + usuarioLogado.getNome());
            usuarioLogado = null;
        } else {
            System.out.println("Nenhum usuário está logado.");
        }
    }

    public void cadastrarUsuario(Usuario novoUsuario) {
        usuarioDao.salvar(novoUsuario);
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void alterarSenha(String novaSenha) {
        if (usuarioLogado != null) {
            usuarioLogado.alterarSenha(novaSenha);
            usuarioDao.atualizar(usuarioLogado);
        }
    }

    public void editarPerfil(String novoNome, String novoEmail) {
        if (usuarioLogado != null) {
            usuarioLogado.editarPerfil(novoNome, novoEmail);
            usuarioDao.atualizar(usuarioLogado);
        }
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDao.listarTodos();
    }

    public void removerUsuario(int id) {
        usuarioDao.remover(id);
    }
}
