package view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import models.Espaco;
import persistence.EspacoDAO;

public class RemoverEspacoView extends JFrame {
    private JComboBox<Espaco> comboEspacos;
    private JButton btnRemover;
    private JLabel lblMensagem;

    private EspacoDAO dao;

    public RemoverEspacoView() {
        super("Remover Espaço - AMBIENTA");

        dao = new EspacoDAO();

        setLayout(new BorderLayout());

        comboEspacos = new JComboBox<>();
        atualizarComboBox();

        btnRemover = new JButton("Remover Espaço");
        lblMensagem = new JLabel(" ", JLabel.CENTER);
        lblMensagem.setForeground(Color.BLUE);

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerEspacoSelecionado();
            }
        });

        JPanel painelTopo = new JPanel();
        painelTopo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelTopo.setLayout(new GridLayout(2, 1, 10, 10));
        painelTopo.add(new JLabel("Selecione um espaço para remover:"));
        painelTopo.add(comboEspacos);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnRemover);

        add(painelTopo, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        add(lblMensagem, BorderLayout.NORTH);

        setSize(400, 300); // Tamanho ajustado aqui
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void atualizarComboBox() {
        comboEspacos.removeAllItems();
        List<Espaco> espacos = dao.listar();
        for (Espaco e : espacos) {
            comboEspacos.addItem(e);
        }
    }

    private void removerEspacoSelecionado() {
        Espaco selecionado = (Espaco) comboEspacos.getSelectedItem();
        if (selecionado != null) {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja remover o espaço:\n" + selecionado.exibirDetalhes() + "?",
                "Confirmação", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                dao.remover(selecionado.getID());
                lblMensagem.setText("Espaço removido com sucesso!");
                atualizarComboBox();
            }
        } else {
            lblMensagem.setText("Nenhum espaço selecionado.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RemoverEspacoView::new);
    }
}



