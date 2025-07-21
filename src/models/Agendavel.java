package models;
import java.util.Date;

//define o que todo espaço que pode ser reservado precisa ter.
public interface Agendavel{
    public abstract boolean consultarDisponibilidade(Date data, String horaInicio, String horaFim); //método para verificar se o espaço está disponível.
    //método para mostrar os detalhes do espaço.
    public abstract void exibirDetalhes(); //método para mostrar os detalhes do espaço.
}