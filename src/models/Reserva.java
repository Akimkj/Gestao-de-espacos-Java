package models;

import java.util.Date;

public class Reserva {
    private int id;
    private Espaco espaco;
    private Date data;
    private String horaInicio;
    private String horaFim;
    private Usuario responsavel;

    // Construtor
    public Reserva(int id, Espaco espaco, Date data, String horaInicio, String horaFim, Usuario responsavel) {
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

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    // Método para exibir os detalhes da reserva
    public void exibirDetalhesReserva() {
        System.out.println("Reserva ID: " + id);
        System.out.println("Espaço reservado: " + espaco.getNome());
        System.out.println("Localização: " + espaco.getLocalizacao());
        System.out.println("Data: " + data);
        System.out.println("Horário: " + horaInicio + " até " + horaFim);
        System.out.println("Responsável: " + responsavel.getNome() + " (" + responsavel.getEmail() + ")");
    }
}
