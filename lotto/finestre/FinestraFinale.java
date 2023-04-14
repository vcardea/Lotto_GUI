package lotto.finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.GridLayout;

public class FinestraFinale {
    private JFrame jf = new JFrame();
    private JPanel jp = new JPanel();
    private JLabel jlTitolo = new JLabel("Risultato Finale");
    private JLabel jlNumeriEstratti = new JLabel("Numeri estratti");
    private JLabel jlEstratti = new JLabel();
    private JLabel jlNumeriIndovinati = new JLabel("Numeri indovinati");
    private JLabel jlIndovinati = new JLabel();
    private JLabel jlTitoloGuadagno = new JLabel("Guadagno (spesa - guadagno lordo)");
    private JLabel jlGuadagno = new JLabel();
    private JButton jbChiudi = new JButton("Chiudi");

    public FinestraFinale() {
        jp.setLayout(new GridLayout(5, 1));

        jf.setSize(400, 400);
        jf.setVisible(true);
    }
}
