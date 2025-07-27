import javax.swing.SwingUtilities;
import view.RelatoriosView;

public class App{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RelatoriosView view = new RelatoriosView();
            view.setVisible(true);
        });
    }
}
