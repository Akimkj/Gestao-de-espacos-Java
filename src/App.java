import javax.swing.SwingUtilities;
import view.*;

public class App{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastroEspacoView view = new CadastroEspacoView();
            view.setVisible(true);
        });
    }
}
