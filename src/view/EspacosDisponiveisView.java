package view;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.*;
import persistence.*;

public class EspacosDisponiveisView extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private EspacoDAO espacoDAO;

    public EspacosDisponiveisView() {
        espacoDAO = new EspacoDAO();

        setTitle("Espaços Disponíveis");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel com gradiente pastel 
        JPanel painelTopo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color start = new Color(148, 187, 233);  // Azul claro
                Color end = new Color(238, 174, 202);    // Rosa claro
                GradientPaint gp = new GradientPaint(0, 0, start, getWidth(), getHeight(), end);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        painelTopo.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Espaços disponíveis", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.BLUE);
        painelTopo.add(titulo, BorderLayout.NORTH);

        add(painelTopo, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"NOME", "TIPO", "LOCALIZAÇÃO", "CAPACIDADE", "ATRIBUTO ESPECIAL"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        tabela.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        // Carrega dados
        carregarEspacos();
    }

    private void carregarEspacos() {
        List<Espaco> espacos = espacoDAO.listar();

        for (Espaco e : espacos) {
            modeloTabela.addRow(new Object[]{
                e.getNome(),
                e.getTipo(),
                e.getLocalizacao(),
                e.getCapacidade(),
                e.getAtrib_esp() // Atributo especial dinâmico
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EspacosDisponiveisView().setVisible(true));
    }
}
