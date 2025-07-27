import javax.swing.SwingUtilities;
import view.*;

public class App {
    public static void main(String[] args) {
        
        

        /*SwingUtilities.invokeLater(() -> {
            CadastroEspacoView viewComum = new CadastroEspacoView();
            viewComum.setVisible(true);
        });*/

        SwingUtilities.invokeLater(() -> {
            EspacosDisponiveisView viewComum = new EspacosDisponiveisView();
            viewComum.setVisible(true);
        });
    }
}