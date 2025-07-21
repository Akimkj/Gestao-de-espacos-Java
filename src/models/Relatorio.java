package models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//classe que mostra as reservas feitas em um período e salvar essas informações em um arquivo CSV.
public class Relatorio {
    private String tipo;
    private Date dataInicio;
    private Date dataFim;

    public Relatorio(String tipo, Date dataInicio, Date dataFim) {
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    
    //método que mostra na tela todas as reservas dentro do período informado.
    public void gerarRelatorioReservas(List<Reserva> reservas) {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); //formatar a data como texto legível 

        System.out.println("=== RELATÓRIO DE RESERVAS ===");
        System.out.println("Tipo: " + tipo);
        System.out.println("De: " + formatador.format(dataInicio));
        System.out.println("Até: " + formatador.format(dataFim));
        System.out.println("--------------------------------");

        //verifica e mostra na tela cada reserva que foi feita dentro do intervalo de datas informado
        for (Reserva reservaAtual: reservas) {
            Date dataReserva = reservaAtual.getData(); //pega a data da reserva

            //verifica se a data da reserva está entre dataInicio e dataFim
            if (!dataReserva.before(dataInicio) && !dataReserva.after(dataFim)) {
                System.out.println(reservaAtual.toString()); //mostra os dados da reserva
            }
        }
    }

    //Salva as reservas dentro do período em um arquivo CSV.
    public void exportarCSV(List<Reserva> reservas, String caminhoArquivo) {
        try {
            //abre o arquivo para escrita
            BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo));

            //escreve o título das colunas(cabeçalho)
            escritor.write("ID;Espaco;Usuario;Data;HoraInicio;HoraFim;Status");
            escritor.newLine();

            for (Reserva reservaAtual : reservas) {
                Date dataReserva = reservaAtual.getData();

                //verifica se a data da reserva está dentro do período desejado antes de salvar no arquivo
                if (!dataReserva.before(dataInicio) && !dataReserva.after(dataFim)) {
                    // toCSV() transforma os dados da reserva em uma linha de texto que pode ser salva no arquivo
                    escritor.write(reservaAtual.toCSV());
                    escritor.newLine();
                }
            }

            escritor.close(); //fecha o arquivo
            System.out.println("Relatório salvo em: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o CSV: " + e.getMessage());
        }
    }

    // Getters e Setters 

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}