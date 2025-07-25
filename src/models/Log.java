package models;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * classe que registra ações importantes que aconteceram dentro do seu sistema.
 * o log contém a data e hora em que foi criado, o usuário responsável pela ação e uma descrição da ação.
 */

public class Log {
    private Date dataHora; //guardar a data e a hora do log
    private String usuario; 
    private String acao; //descrição do que foi feito 

    public Log(String usuario, String acao) {
        this.dataHora = new Date(); //define automaticamente a data e hora atual
        this.usuario = usuario;
        this.acao = acao;
    }

    //Getters importantes
    public Date getDataHora() {
        return dataHora;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getAcao() {
        return acao;
    }

    //método que retorna uma representação em texto do log, formatando a data e hora, seguida do usuário e da descrição da ação.
    @Override
    public String toString() {
        SimpleDateFormat formatoDataHora  = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return "[" + formatoDataHora.format(dataHora) + "] " + usuario + ": " + acao;
    }
}
