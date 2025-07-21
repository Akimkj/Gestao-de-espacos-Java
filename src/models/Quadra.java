package models;
import java.util.Date;

// A classe Quadra representa uma quadra esportiva que pode ser reservada.
public class Quadra extends Espaco{
    private String tipoEsporte;

    public Quadra(int id,String nome, String localizacao, int capacidade,String tipoEsporte){
        super(id,nome,localizacao,capacidade);
        this.tipoEsporte=tipoEsporte;
    }
    //Getters e Setters importantes
    public String getTipoEsporte() {
        return tipoEsporte;
    }

    public void setTipoEsporte(String tipoEsporte) {
        this.tipoEsporte = tipoEsporte;
    }

    @Override
    public String getTipo() {
        return "QUADRA";
    }

    @Override
    public String getAtrib_esp(){
        return "TIPOESPORTE=" + tipoEsporte;
    }

    //COLOCAR O METODO VERIFICARDISPONIBILIDADE PARA SWING
    @Override
    public boolean consultarDisponibilidade(Date data, String horaInicio, String horaFim){
        System.out.println("Verificando disponibilidade da quadra " + super.getNome() + " no dia " + data);
        return true;
    }


    @Override 
    public void exibirDetalhes(){
        super.exibirDetalhes();
        System.out.println("Tipo de esportes: " + tipoEsporte);
    }
}