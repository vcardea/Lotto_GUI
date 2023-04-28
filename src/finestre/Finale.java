package src.finestre;

import src.log.Log;
import src.calcoli.CalcoloVincita;
import src.finestre.gestori.GestoreFinestraND;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Vector;

public class Finale {
    /*
     * Classe interna per la gestione dei pulsanti
     */
    public class GestoreInterno implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            String s = ae.getActionCommand();
            if (s.equals("chiudi")) {
                Menu.apriFinestra();
                jf.dispose();
            }
        }
    }

    private void estrai() {
        // Attributi locali
        boolean ripeti;
        byte estrazione = 0;

        // Estrazione dei numeri pseudo-casuali
        for (byte i = 0; i < ESTRAZIONI; i++) {
            do {
                ripeti = false;
                estrazione = (byte) (Math.random() * 90);
                if (checkEstrazioni[estrazione])
                    ripeti = true;
                else {
                    checkEstrazioni[estrazione] = true;
                    numeriEstratti.addElement(estrazione);
                }
            } while (!checkEstrazioni[estrazione] || ripeti);
        }
    }

    private static final byte ESTRAZIONI = 20;
    private boolean[] checkEstrazioni = new boolean[90];
    private Vector<Byte> numeriIndovinati = new Vector<Byte>();
    private Vector<Byte> numeriEstratti = new Vector<Byte>();
    private byte contNumeriVinti = 0;
    private float vincita = 0.0f;

    private final byte PANELS = 3;
    private String numeriEstrattiString = new String("");
    private String numeriIndovinatiString = new String("");
    private JFrame jf = new JFrame(UtilitiesFinestra.TITOLO);
    private JPanel[] jp = new JPanel[PANELS];
    private GridLayout gl = new GridLayout(6, 1);
    private JLabel jlTitolo = new JLabel("RISULTATO FINALE", JLabel.CENTER);
    private JLabel jlNumeriEstratti = new JLabel("Numeri estratti", JLabel.CENTER);
    private JLabel jlNumeriIndovinati = new JLabel("Numeri indovinati", JLabel.CENTER);
    private JLabel jlTitoloGuadagno = new JLabel("Guadagno (importo - vincita)", JLabel.CENTER);
    private JButton jbChiudi = new JButton("Chiudi");

    /*
     * Costruttore. Genera la finestra e manipola i numeri estratti e scelti
     */
    public Finale(boolean[] numeriScelti, float importo, byte numeri) {
        estrai();
        for (byte i = 0; i < 90; ++i) {
            if(checkEstrazioni[i]) numeriEstrattiString += i + 1 + " ";
            if (numeriScelti[i] && checkEstrazioni[i]) {
                numeriIndovinati.addElement((byte) (i + 1));
                ++contNumeriVinti;
                numeriIndovinatiString += i + 1 + " ";
            }
        }
        CalcoloVincita cv = new CalcoloVincita(numeri, importo, contNumeriVinti);
        vincita = cv.getVincita();
        Log.scriviLog(importo, vincita, numeriScelti, numeriIndovinati);
        creaFinestra();
    }

    /**
     * Genera la finestra iniziale.
     */
    public void creaFinestra() {
        // Imposta il layout
        jf.setLayout(new BorderLayout());

        // Inizializza i pannelli
        for (int i = 0; i < jp.length; i++) {
            jp[i] = new JPanel();
        }

        // Imposta il layout del pannello centrale
        jp[1].setLayout(gl);
        System.out.println(numeriIndovinatiString.length());
        // Aggiunge componenti al pannello
        jp[0].add(jlTitolo);
        jp[1].add(jlNumeriEstratti);
        jp[1].add(new JLabel(numeriEstrattiString, JLabel.CENTER));
        jp[1].add(jlNumeriIndovinati);
        if(numeriEstrattiString.length() != 0) jp[1].add(new JLabel(numeriIndovinatiString, JLabel.CENTER));
        else jp[1].add(new JLabel("Non hai indovinato numeri", JLabel.CENTER));
        jp[1].add(jlTitoloGuadagno);
        jp[1].add(new JLabel(String.valueOf(vincita), JLabel.CENTER));
        jp[2].add(jbChiudi, BorderLayout.CENTER);

        // Pulsante di chiusura
        jbChiudi.setActionCommand("chiudi");
        jbChiudi.addActionListener(new GestoreInterno());

        // Imposta ulteriori dettagli del frame
        jf.addWindowListener(new GestoreFinestraND(jf));
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);
        jf.add(jp[2], BorderLayout.SOUTH);
        jf.setLocation(UtilitiesFinestra.LOCATIONX, UtilitiesFinestra.LOCATIONY);
        jf.setSize(UtilitiesFinestra.WIDTH, UtilitiesFinestra.HEIGHT);
        jf.setIconImage(UtilitiesFinestra.icon.getImage());
        jf.setVisible(true);
        jf.getContentPane();
    }
}