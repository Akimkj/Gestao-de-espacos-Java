package models;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private boolean ehADM;

    public Usuario(int id, String nome, String email, String senha, boolean ehADM) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ehADM = ehADM;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getEhADM() {
        return ehADM;
    }
    public void setEhADM(boolean ehADM) {
        this.ehADM = ehADM;
    }

    public boolean isAdmin() {
        return this.ehADM;
    }

    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
    }

    public void editarPerfil(String novoNome, String novoEmail) {
        this.nome = novoNome;
        this.email = novoEmail;
    }
}
