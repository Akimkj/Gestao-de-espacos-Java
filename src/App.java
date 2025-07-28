import javax.swing.SwingUtilities;
import models.Usuario;
import view.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Usuario chato = new Usuario(0, "akim", "kaka.gmail", "1234", true);
            CadastroEspacoView CadastroEspacoView = new CadastroEspacoView(chato);
            CadastroEspacoView.setVisible(true);
        });
    }
}