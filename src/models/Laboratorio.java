package models;

public class Laboratorio extends Espaco { // Declaração da classe Laboratorio que herda de Espaco
    private String tipoequipamentos; // Atributo privado para armazenar o tipo de equipamentos do laboratório

    // Construtor da classe Laboratorio
    public Laboratorio(String nome, String localizacao, int capacidade, String tipoequipamentos){
        // Chama o construtor da superclasse (Espaco)
        super(0,nome,localizacao,capacidade);
        this.tipoequipamentos = tipoequipamentos; // Inicializa o atributo tipoequipamentos
    }

    // Getters e Setters importantes

    
    public String getTipoequipamentos(){
        return tipoequipamentos;
    }

    
    public void setTipoequipamentos(String tipoequipamentos){
        this.tipoequipamentos = tipoequipamentos;
    }

    // Sobrescreve o método getTipo() da superclasse Espaco
    @Override
    public String getTipo() {
        return "LABORATORIO"; // Retorna o tipo específico "LABORATORIO"
    }

    // Sobrescreve o método getAtrib_esp() da superclasse Espaco
    @Override
    public String getAtrib_esp(){
        // Retorna uma string formatada com o tipo de equipamento
        return "TIPOEQUIPAMENTO=" + getTipoequipamentos();
    }

    // Sobrescreve o método exibirDetalhes() da superclasse Espaco
    @Override 
    public String exibirDetalhes(){
        // Chama o método exibirDetalhes() da superclasse e concatena o tipo
        return super.exibirDetalhes() + getTipo();
    }

    // Sobrescreve o método toString() para fornecer uma representação em string do objeto Laboratorio
    @Override
    public String toString() {
        // Chama o método toString() da superclasse e concatena o tipo e os atributos específicos
        return super.toString() + ";" + getTipo() + ";" + getAtrib_esp();
    }
}