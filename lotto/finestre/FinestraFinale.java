package lotto.finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinestraFinale {
    /*
     * Classe interna per la gestione dei pulsanti
     */
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
    private GridLayout gl = new GridLayout(PANELS, 1);
    private JPanel[] jp = new JPanel[PANELS];
    private JLabel jlTitolo = new JLabel("RISULTATO FINALE");
    private JLabel jlNumeriEstratti = new JLabel("Numeri estratti");
    private JLabel jlEstratti = new JLabel();
    private JLabel jlNumeriIndovinati = new JLabel("Numeri indovinati");
    private JLabel jlIndovinati = new JLabel();
    private JLabel jlTitoloGuadagno = new JLabel("Guadagno (spesa - guadagno lordo)");
    private JLabel jlGuadagno = new JLabel();
    private JButton jbChiudi = new JButton("Chiudi");

    /*
     * Costruttore. Genera la finestra iniziale.
     */
    public FinestraFinale(int[] numeriEstratti, int[] numeriIndovinati, int importo) {
        // Imposta il layout
        jf.setLayout(gl);

        // Inizializza i pannelli
        for (int i = 0; i < jp.length; i++) {
            jp[i] = new JPanel();
        }

        // Aggiunge componenti al pannello
        jp[0].add(jlTitolo, JLabel.CENTER);
        jp[1].add(jlNumeriEstratti, JLabel.CENTER);
        jp[1].add(jlEstratti, JLabel.CENTER);
        jp[2].add(jlNumeriIndovinati, JLabel.CENTER);
        jp[2].add(jlIndovinati, JLabel.CENTER);
        jp[3].add(jlTitoloGuadagno, JLabel.CENTER);
        jp[3].add(jlGuadagno, JLabel.CENTER);
        jp[4].add(jbChiudi, BorderLayout.CENTER);

        // Pulsante di chiusura
        jbChiudi.setActionCommand("chiudi");
        jbChiudi.addActionListener(new GestoreInterno());

        // Aggiungi i pannelli al frame
        for (int i = 0; i < jp.length; i++) {
            jf.add(jp[i]);
        }

        // Imposta ulteriori dettagli del frame
        // jf.addWindowListener(new GestoreFinestra());
        // jf.setSize(400, 400);
        jf.pack();
        jf.setVisible(true);
    }
}