package models;

public class SalaAula extends Espaco { // Declaração da classe SalaAula que herda de Espaco
    private boolean possuiProjetor; // Atributo privado para armazenar se a sala de aula possui projetor ou não

    // Construtor da classe SalaAula
    public SalaAula(String nome, String localizacao, int capacidade, boolean possuiProjetor) {
        // Chama o construtor da superclasse (Espaco)
        super(0, nome, localizacao, capacidade); 
        this.possuiProjetor = possuiProjetor; // Inicializa o atributo possuiProjetor
    }

    // Sobrescreve o método exibirDetalhes() da superclasse Espaco
    @Override
    public String exibirDetalhes() {
        // Chama o método exibirDetalhes() da superclasse e concatena o tipo
        return super.exibirDetalhes() + getTipo();
    }

    // Getters e Setters importantes

    
    public boolean getPossuiprojetor() {
        return this.possuiProjetor;
    }

    public void setPossuiprojetor(boolean haProjetor) {
        this.possuiProjetor = haProjetor;
    }

    @Override
    public String getTipo() {
        return "SALA DE AULA"; // Retorna o tipo específico "SALA DE AULA"
    }

    // Sobrescreve o método getAtrib_esp() da superclasse Espaco
    @Override
    public String getAtrib_esp(){
       // Retorna uma string indicando se a sala possui projetor (SIM/NAO)
       if (getPossuiprojetor()) {
        return "POSSUIPROJETOR=SIM";
       }
       else {
        return "POSSUIPROJETOR=NAO";
       }
    }

    // Sobrescreve o método toString() para fornecer uma representação em string do objeto SalaAula
    @Override
    public String toString() {
        // Chama o método toString() da superclasse e concatena o tipo e os atributos específicos
        return super.toString() + ";" + getTipo() + ";" + getAtrib_esp();
    }
}
