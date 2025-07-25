package models;
import java.util.Date;

public class Auditorio extends Espaco{
    private boolean temPalco;

	public Auditorio(int id, String nome, String localizacao, int capacidade, boolean temPalco) {
        super(id, nome, localizacao, capacidade);
        this.temPalco = temPalco;
    }
    
    //COLOCAR O METODO VERIFICARDISPONIBILIDADE PARA SWING

    //ALTERAR PARA INTERFACE GRAFICA
    @Override
    public boolean consultarDisponibilidade(Date data, String horaInicio, String horaFim){
        System.out.println("Verificando disponibilidade em um auditorio" + super.getNome() + " no dia " + data);
        return true;
    }

    @Override
    public void exibirDetalhes() { 
        super.exibirDetalhes();
        System.out.println("Possui Palco? " + (isTemPalco() ? "Sim" : "Não"));
    }
    
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

    @Override
    public String getAtrib_esp(){
       if (isTemPalco()) {
        return "TEMPALCO=SIM";
       }
       else {
        return "TEMPALCO=NAO";
       }
    }
    

}
