package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
    // Atributos da classe Reserva
    private int id; // Identificador único da reserva
    private Espaco espaco; // Objeto do tipo Espaco reservado
    private Date data; // Data da reserva
    private String horaInicio; // Hora de início da reserva
    private String horaFim; // Hora de término da reserva
    private Usuario responsavel; // Usuário responsável pela reserva

    // Construtor com todos os atributos
    public Reserva(int id, Espaco espaco, Date data, String horaInicio, String horaFim, Usuario responsavel) {
        this.id = id;
        this.espaco = espaco;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.responsavel = responsavel;
    }

    // Métodos getters e setters para acessar e modificar os atributos

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

    // Método que gera uma string com os dados da reserva no formato CSV
    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formatação da data
        return id + ";" 
             + (espaco != null ? espaco.getNome() : "") + ";"  // Nome do espaço
             + responsavel + ";"  // Aqui será impresso o toString() do objeto Usuario
             + sdf.format(data) + ";"  // Data formatada
             + horaInicio + ";" 
             + horaFim + ";" 
             + "Confirmada"; // Status fixo da reserva
    }

    // Método que exibe os detalhes da reserva no console (útil para depuração)
    public void exibirDetalhesReserva() {
        System.out.println("Reserva ID: " + id);
        System.out.println("Espaço reservado: " + espaco.getNome()); // Nome do espaço
        System.out.println("Localização: " + espaco.getLocalizacao()); // Localização do espaço
        System.out.println("Data: " + data); // Será exibido no formato padrão de Date
        System.out.println("Horário: " + horaInicio + " até " + horaFim);
        System.out.println("Responsável: " + responsavel); // Exibe toString() do objeto Usuario
    }
}
