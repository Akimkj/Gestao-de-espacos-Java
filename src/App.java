import javax.swing.SwingUtilities;
import view.*;
import models.*;

public class App {
    public static void main(String[] args) {
        // Criar usuário admin
        Usuario usuarioAdmin = new Usuario(1, "Administrador", "admin@email.com", "123456", true);
        // Criar usuário comum
        Usuario usuarioComum = new Usuario(2, "Usuário Comum", "user@email.com", "abcdef", false);

        // Executar interface no thread de Swing para usuário admin
        SwingUtilities.invokeLater(() -> {
            RelatoriosView viewAdmin = new RelatoriosView(usuarioAdmin);
            viewAdmin.setVisible(true);
        });

        // Executar interface no thread de Swing para usuário comum (pode abrir em outra janela)
        SwingUtilities.invokeLater(() -> {
            RelatoriosView viewComum = new RelatoriosView(usuarioComum);
            viewComum.setVisible(true);
        });
    }
}