package models;

// A classe Auditorio representa um tipo específico de Espaço, caracterizado por possuir ou não um palco.
// Herda propriedades e comportamentos básicos da classe abstrata Espaco.
public class Auditorio extends Espaco{
    // Atributo privado que indica se o auditório possui um palco.
    private boolean temPalco;

    /**
     * Construtor da classe Auditorio.
     * Inicializa um novo objeto Auditorio com as informações fornecidas e define se possui palco.
     * O ID é inicialmente passado como 0 para ser gerado posteriormente pelo sistema de persistência.
     */
    public Auditorio(String nome, String localizacao, int capacidade, boolean temPalco) {
        // Chama o construtor da superclasse Espaco, passando 0 para o ID inicial, nome, localização e capacidade.
        super(0, nome, localizacao, capacidade); //
        // Atribui o valor de 'temPalco' ao atributo correspondente do auditório.
        this.temPalco = temPalco; //
    }

    /*
     Sobrescreve o método exibirDetalhes da superclasse Espaco.
      Este método retorna uma string contendo os detalhes básicos do espaço (vindos da superclasse)
      e o tipo específico do espaço (AUDITORIO).
     */
    @Override
    public String exibirDetalhes() { 
        // Chama o método exibirDetalhes da superclasse e concatena com o tipo do espaço.
        return super.exibirDetalhes() + getTipo(); //
    }

    /**
     Sobrescreve o método abstrato getAtrib_esp da superclasse Espaco.
     Retorna uma string que representa a característica especial do auditório (se possui palco)
     no formato "TEMPALCO=SIM" ou "TEMPALCO=NAO", para persistência em arquivo.
     */
    @Override
    public String getAtrib_esp(){
       if (isTemPalco()) { //
        return "TEMPALCO=SIM"; //
       }
       else {
        return "TEMPALCO=NAO"; //
       }
    }
    
    /*
     Sobrescreve o método toString para fornecer uma representação em string formatada do objeto Auditorio.
    Esta representação é usada para salvar os dados do objeto em um arquivo de texto,
    incluindo os detalhes do espaço da superclasse, seu tipo e seu atributo especial.
     */
    @Override
    public String toString() {
        // Concatena a representação string da superclasse com o tipo do espaço e seu atributo especial,
        // usando ";" como delimitador para o arquivo de texto.
        return super.toString() + ";" + getTipo() + ";" + getAtrib_esp(); //
    }

    // Getters e Setters importantes

    public boolean isTemPalco() {
        return this.temPalco; 
    }

    
    public void setTemPalco(boolean temPalco) {
        this.temPalco = temPalco; 
    }

    
    @Override
    public String getTipo() {
        return "AUDITORIO"; 
    }

}
