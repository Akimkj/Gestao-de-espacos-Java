package models;

/**
 * Classe abstrata que representa um espaço genérico que pode ser agendado.
 * Implementa a interface Agendavel, garantindo que todos os espaços possam ser agendados.
 */
public abstract class Espaco implements Agendavel {
    private int id;                 // Identificador único do espaço
    private String nome;            // Nome do espaço
    private String localizacao;     // Localização física do espaço
    private int capacidade;         // Capacidade máxima de pessoas do espaço

    

    //Método construtor da super classe Espaco
    public Espaco(int id, String nome, String localizacao, int capacidade) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.capacidade = capacidade;
    }
    
    

    
    @Override
    public String exibirDetalhes() {
        return this.id + " - " + this.nome;
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
    


    // método toString() para utilizar na persistencia de dados.
    @Override
    public String toString() {
        return this.id + ";" + this.nome + ";" + this.localizacao + ";" + this.capacidade;
    }
}
