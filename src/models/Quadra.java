package models;
public class Quadra extends Espaco { // Declaração da classe Quadra que herda de Espaco
    private String tipoEsporte; // Atributo privado para armazenar o tipo de esporte da quadra

    // Construtor da classe Quadra
    public Quadra(String nome, String localizacao, int capacidade,String tipoEsporte){
        // Chama o construtor da superclasse (Espaco)
        super(0,nome,localizacao,capacidade);
        this.tipoEsporte = tipoEsporte; // Inicializa o atributo tipoEsporte
    }

    // Getters e Setters importantes

    
    public String getTipoEsporte() {
        return tipoEsporte;
    }

    
    public void setTipoEsporte(String tipoEsporte) {
        this.tipoEsporte = tipoEsporte;
    }

    @Override
    public String getTipo() {
        return "QUADRA"; // Retorna o tipo específico "QUADRA"
    }

    // Sobrescreve o método getAtrib_esp() da superclasse Espaco
    @Override
    public String getAtrib_esp(){
        // Retorna uma string formatada com o tipo de esporte
        return "TIPOESPORTE=" + tipoEsporte;
    }

    // Sobrescreve o método exibirDetalhes() da superclasse Espaco
    @Override 
    public String exibirDetalhes(){
        // Chama o método exibirDetalhes() da superclasse e concatena o tipo
        return super.exibirDetalhes() + getTipo();
    }

    // Sobrescreve o método toString() para fornecer uma representação em string do objeto Quadra
    @Override
    public String toString() {
        // Chama o método toString() da superclasse e concatena o tipo e os atributos específicos
        return super.toString() + ";" + getTipo() + ";" + getAtrib_esp();
    }
}