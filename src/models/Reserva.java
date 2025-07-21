package models;

<<<<<<< HEAD
=======
import java.text.SimpleDateFormat;
>>>>>>> bf84e6164e4e0a980804c72ffd73af46523b7ffb
import java.util.Date;

public class Reserva {
    private int id;
    private Espaco espaco;
    private Date data;
    private String horaInicio;
    private String horaFim;
<<<<<<< HEAD
    private Usuario responsavel;

    // Construtor
    public Reserva(int id, Espaco espaco, Date data, String horaInicio, String horaFim, Usuario responsavel) {
=======
    private String responsavel; //nome ou identificador de quem fez a reserva

    // Construtor
    public Reserva(int id, Espaco espaco, Date data, String horaInicio, String horaFim, String responsavel) {
>>>>>>> bf84e6164e4e0a980804c72ffd73af46523b7ffb
        this.id = id;
        this.espaco = espaco;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.responsavel = responsavel;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Espaco getEspaco() {
        return espaco;
    }

    public void setEspaco(Espaco espaco) {
        this.espaco = espaco;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

<<<<<<< HEAD
    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

=======
    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    //método toCSV para exportar uma linha CSV da reserva
    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return id + ";" 
             + (espaco != null ? espaco.getNome() : "") + ";" 
             + responsavel + ";" 
             + sdf.format(data) + ";" 
             + horaInicio + ";" 
             + horaFim + ";" 
             + "Confirmada"; // ou outro status dinâmico, se tiver
    }

>>>>>>> bf84e6164e4e0a980804c72ffd73af46523b7ffb
    // Método para exibir os detalhes da reserva
    public void exibirDetalhesReserva() {
        System.out.println("Reserva ID: " + id);
        System.out.println("Espaço reservado: " + espaco.getNome());
        System.out.println("Localização: " + espaco.getLocalizacao());
        System.out.println("Data: " + data);
        System.out.println("Horário: " + horaInicio + " até " + horaFim);
<<<<<<< HEAD
        System.out.println("Responsável: " + responsavel.getNome() + " (" + responsavel.getEmail() + ")");
=======
        System.out.println("Responsável: " + responsavel);
>>>>>>> bf84e6164e4e0a980804c72ffd73af46523b7ffb
    }
}
