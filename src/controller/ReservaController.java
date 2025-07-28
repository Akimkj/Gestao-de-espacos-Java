package controller;

import models.Reserva;
import models.Espaco;
import models.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaController {
    private List<Reserva> reservas;
    private int proximoId;

    public ReservaController() {
        this.reservas = new ArrayList<>();
        this.proximoId = 1;
    }

    // Criar nova reserva
    public Reserva criarReserva(Espaco espaco, Date data, String horaInicio, String horaFim, Usuario responsavel) {
        Reserva novaReserva = new Reserva(proximoId++, espaco, data, horaInicio, horaFim, responsavel);
        reservas.add(novaReserva);
        return novaReserva;
    }

    // Listar todas as reservas
    public List<Reserva> listarReservas() {
        return reservas;
    }

    // Buscar reserva por ID
    public Reserva buscarReservaPorId(int id) {
        for (Reserva r : reservas) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null; // ou lançar uma exceção
    }

    // Remover reserva por ID
    public boolean removerReserva(int id) {
        Reserva reserva = buscarReservaPorId(id);
        if (reserva != null) {
            reservas.remove(reserva);
            return true;
        }
        return false;
    }

    // Exibir todos os detalhes das reservas
    public void exibirTodasReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada.");
            return;
        }

        for (Reserva r : reservas) {
            r.exibirDetalhesReserva();
            System.out.println("---------------------------");
        }
    }

    // Exportar todas as reservas como CSV
    public void exportarCSV() {
        System.out.println("ID;Espaço;Responsável;Data;Início;Fim;Status");
        for (Reserva r : reservas) {
            System.out.println(r.toCSV());
        }
    }

    public int gerarNovoId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gerarNovoId'");
    }

    public void adicionarReserva(Reserva reserva) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionarReserva'");
    }
}
