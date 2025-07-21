package models;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
<<<<<<< HEAD
    private String tipo;


    public Usuario(int id, String nome, String email, String senha, String tipo) {
=======
    private boolean ehADM;


    public Usuario(int id, String nome, String email, String senha, boolean ehADM) {
>>>>>>> bf84e6164e4e0a980804c72ffd73af46523b7ffb
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
<<<<<<< HEAD
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
=======
        this.ehADM = ehADM;
>>>>>>> bf84e6164e4e0a980804c72ffd73af46523b7ffb
    }


    public boolean login(SistemaAutenticacao autenticar){
<<<<<<< HEAD
        if (autenticar.validarCredencias(this.email, this.senha){
=======
        if (autenticar.validarCredencias(this.email, this.senha)){
>>>>>>> bf84e6164e4e0a980804c72ffd73af46523b7ffb
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

<<<<<<< HEAD
    public boolean isAdmin(){
        return tipo.equalsIgnoreCase("admim")
=======
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.equals("")) {
            System.err.println("Seu email não pode ser vazio!");
        }
        else {
            this.email = email;
        }
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha.equals("")) {
            System.err.println("Sua senha não pode ser vazia!");
        }
        else {
            this.senha = senha;
        }
    }


    public boolean getEhADM() {
        return this.ehADM;
    }

    public void setEhADM(boolean ehADM) {
        this.ehADM = ehADM;
>>>>>>> bf84e6164e4e0a980804c72ffd73af46523b7ffb
    }
}
