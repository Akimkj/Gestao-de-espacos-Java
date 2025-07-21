package models;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String tipo;


    public Usuario(int id, String nome, String email, String senha, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }


    public int getId() {
        return id;
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



    public String getSenha() {
        return senha;
    }



    public String getTipo() {
        return tipo;
    }


    public boolean login(SistemaAutenticacao autenticar){
        if (autenticar.validarCredencias(this.email, this.senha){
            autenticar.criarSessao(this);
            System.out.println("Login realizado com sucesso!");
            return true;

        } else{
            System.out.println("Credenciais inválidas");
            return false;
        }
    }

    public void logout(SistemaAutenticacao autenticar){
        autenticar.encerrarSessao();
        System.out.println("Logout realizado");
    }

    public void alterarSenha(String novaSenha){
        this.senha = novaSenha;
        System.out.println("Senha auterada com sucesso");
    }

    public void alterarPerfil(String novoNome, String novoEmail){
        this.nome = novoNome;
        this.email = novoEmail;
        System.out.println("Perfil atualizado com sucesso");


    }

    public boolean isAdmin(){
        return tipo.equalsIgnoreCase("admim")
    }
}
