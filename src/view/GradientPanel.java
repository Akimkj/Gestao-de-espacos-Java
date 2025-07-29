package view;
import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color cor1 = new Color(138, 43, 226);  // roxo
        Color cor2 = new Color(255, 105, 180); // rosa
        GradientPaint gp = new GradientPaint(0, 0, cor1, getWidth(), getHeight(), cor2);
        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

}
