package models;
import java.util.Date;

// Classe responsável pelo molde em comum dos espaços gerais do ambiente acadêmico.
public abstract class Espaco implements Agendavel {
    private int id; 
    private String nome;
    private String localizacao;
    private int capacidade;

    // Discutir sobre um atributo booleano para a disponibilidade do espaço.

    //Método construtor da super classe
    public Espaco(int id, String nome, String localizacao, int capacidade) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.capacidade = capacidade;
    }
    
    //Metódos implementados pela interface Agendavel

    //Metodo abstrato para verificar a disponibilidade para reserva no banco de dados
    @Override
    public abstract boolean consultarDisponibilidade(Date data, String horaInicio, String horaFim);

    //Metodo para exibir informações do espaço MUDAR PARA ADERIR À INTERFACE GRAFICA !!!!!!!
    @Override
    public void exibirDetalhes() {
        System.out.println("Espaço: " + nome);
        System.out.println("Localização: " + localizacao);
        System.out.println("Capacidade: " + capacidade);
    }

    //Getters e Setters importantes
    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getLocalizacao() {
        return this.localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getCapacidade() {
        return this.capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public abstract String getAtrib_esp();

    public abstract String getTipo();
    
    @Override
    public String toString() {
        return this.id + ";" + this.nome + ";" + this.localizacao + ";" + this.capacidade;
    }
}
