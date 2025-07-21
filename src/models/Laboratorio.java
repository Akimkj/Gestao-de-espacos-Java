package models;
import java.util.Date;

// A classe Laboratorio representa um espaço do tipo laboratório que pode ser reservado.
public class Laboratorio extends Espaco {
    private String tipoequipamentos;

    public Laboratorio(int id,String nome, String localizacao, int capacidade, String tipoequipamentos){
        super(id,nome,localizacao,capacidade);
        this.tipoequipamentos = tipoequipamentos;
    }

    //Getters e Setters importantes
    public String getTipoequipamentos(){
        return tipoequipamentos;
    }


    public void setTipoequipamentos(String tipoequipamentos){
        this.tipoequipamentos= tipoequipamentos;
    }

    @Override
    public String getTipo() {
        return "LABORATORIO";
    }

    @Override
    public String getAtrib_esp(){
        return "TIPOEQUIPAMENTO=" + getTipoequipamentos();
    }

    //COLOCAR O METODO VERIFICARDISPONIBILIDADE PARA SWING 
    @Override 
    public boolean consultarDisponibilidade(Date data, String horaInicio, String horaFim){
        System.out.println("Verificando disponibilidade do laboratório " + super.getNome() + " no dia " + data);
        return true;
    }


    @Override 
    public void exibirDetalhes(){
        super.exibirDetalhes();
        System.out.println("Tipo de equipamento: " + tipoequipamentos);
    }
}