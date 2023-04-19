package lotto.finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinestraFinale {

    public class GestoreInterno implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            String s = ae.getActionCommand();
            if (s.equals("chiudi")) {
                jf.dispose();
            }
        }
    }

    private final byte PANELS = 5;
    private JFrame jf = new JFrame();
    private JPanel[] jp = new JPanel[PANELS];
    private JLabel jlTitolo = new JLabel("Risultato Finale");
    private JLabel jlNumeriEstratti = new JLabel("Numeri estratti");
    private JLabel jlEstratti = new JLabel();
    private JLabel jlNumeriIndovinati = new JLabel("Numeri indovinati");
    private JLabel jlIndovinati = new JLabel();
    private JLabel jlTitoloGuadagno = new JLabel("Guadagno (spesa - guadagno lordo)");
    private JLabel jlGuadagno = new JLabel();
    private JButton jbChiudi = new JButton("Chiudi");

    public FinestraFinale() {
        for (int i = 0; i < jp.length; i++) {
            jp[i] = new JPanel();
        }

        // Aggiunge componenti al pannello
        jp[0].add(jlTitolo);
        jp[1].add(jlNumeriEstratti);
        jp[1].add(jlEstratti);
        jp[2].add(jlNumeriIndovinati);
        jp[2].add(jlIndovinati);
        jp[3].add(jlTitoloGuadagno);
        jp[3].add(jlGuadagno);
        jp[4].add(jbChiudi);

        // Pulsante di chiusura
        jbChiudi.setActionCommand("chiudi");
        jbChiudi.addActionListener(new GestoreInterno());

        // 
        for (int i = 0; i < jp.length; i++) {
            jf.add(jp[i]);
        }

        // jf.addWindowListener(new GestoreFinestra());
        jf.setSize(400, 400);
        jf.setVisible(true);
    }
}