import javax.swing.SwingUtilities;
import view.LoginView;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           LoginView login = new LoginView();
           login.setVisible(true);
        });
    }
}