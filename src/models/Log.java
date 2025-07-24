package models;
import java.util.Date;

//classe que registrar ações importantes que aconteceram dentro do seu sistema.
public class Log {
    private Date dataHora; //guardar a data e a hora do log
    private String usuario; 
    private String acao; //descrição do que foi feito 

    public Log(String usuario, String acao) {
        this.dataHora = new Date(); //define automaticamente a data e hora atual
        this.usuario = usuario;
        this.acao = acao;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getAcao() {
        return acao;
    }

    //método que transforma o log em um texto fácil de ler
    @Override
    public String toString() {
        return "[" + dataHora + "] " + usuario + ": " + acao;
    }
}
