package models;
import java.util.Date;

//SubClasse que representa os espaços das salas de aula do âmbito acadêmico do sistema de gestão.
public class SalaAula extends Espaco{
    private boolean possuiProjetor;

    public SalaAula(String nome, String localizacao, int capacidade, boolean possuiProjetor) {
        super(0, nome, localizacao, capacidade);
        this.possuiProjetor = possuiProjetor;
    }



    //COLOCAR O METODO VERIFICARDISPONIBILIDADE PARA SWING

    //ALTERAR PARA INTERFACE GRAFICA
     @Override
    public boolean consultarDisponibilidade(Date data, String horaInicio, String horaFim){
        System.out.println("Verificando disponibilidade em uma sala de aula " + super.getNome() + " no dia " + data);
        return true;
    }

    @Override
    public void exibirDetalhes() { 
        super.exibirDetalhes();
        System.out.println("Possui projetor? " + (getPossuiprojetor() ? "Sim" : "Não"));
    }

    

    //Getters e Setters importantes

    public boolean getPossuiprojetor() {
        return this.possuiProjetor;
    }

    public void setPossuiprojetor(boolean haProjetor) {
        this.possuiProjetor = haProjetor;
    }

    @Override
    public String getTipo() {
        return "SALA DE AULA";
    }

    @Override
    public String getAtrib_esp(){
       if (getPossuiprojetor()) {
        return "POSSUIPROJETOR=SIM";
       }
       else {
        return "POSSUIPROJETOR=NAO";
       }
    }


    @Override
    public String toString() {
        return super.toString() + ";" + getTipo() + ";" + getAtrib_esp();
    }
}
