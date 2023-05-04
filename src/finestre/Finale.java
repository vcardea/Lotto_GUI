package src.finestre;

import src.log.Log;
import src.utente.Utente;
import src.calcoli.CalcoloVincita;
import src.finestre.gestori.GestoreFinestraND;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
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

    private static final byte NUMERI = 90;
    private static final byte ESTRAZIONI = 20;
    private boolean[] checkEstrazioni = new boolean[NUMERI];
    private Vector<Byte> numeriIndovinati = new Vector<Byte>();
    private Vector<Byte> numeriEstratti = new Vector<Byte>();
    private byte contNumeriVinti = 0;
    private float vincita = 0.0f;

    private final byte PANNELLI = 5;
    private JFrame jf = new JFrame(UtilitiesFinestra.TITOLO);
    private JPanel[] jp = new JPanel[PANNELLI];
    private GridLayout glNord = new GridLayout(2, 1);
    private GridBagLayout gblCentro = new GridBagLayout();
    private FlowLayout flSud = new FlowLayout();
    private BorderLayout blEstOvest = new BorderLayout();
    private JLabel jlTitolo = new JLabel("RISULTATO FINALE", JLabel.CENTER);
    private JLabel jlUsername = new JLabel(Utente.username, JLabel.CENTER);
    private JLabel jlEstratti = new JLabel("Numeri estratti", JLabel.CENTER);
    private JLabel jlIndovinati = new JLabel("Numeri indovinati", JLabel.CENTER);
    private JLabel jlVincita = new JLabel("Vincita", JLabel.CENTER);
    private JLabel jlMostraVincita;
    private String stringNumeriEstratti = new String("");
    private JLabel jlNumeriEstratti;
    private String stringNumeriIndovinati = new String("");
    private JLabel jlNumeriIndovinati;
    private JButton jbStatistiche = new JButton("Statistiche");
    private JButton jbChiudi = new JButton("Chiudi");

    /*
     * Costruttore. Genera la finestra e manipola i numeri estratti e scelti
     */
    public Finale(boolean[] numeriScelti, float importo, byte numeri) {
        estrai();

        for (byte i = 0; i < NUMERI; ++i) {
            if(checkEstrazioni[i]) {
                stringNumeriEstratti += (i + 1) + " ";
            }
            if (numeriScelti[i] && checkEstrazioni[i]) {
                numeriIndovinati.addElement((byte) (i + 1));
                ++contNumeriVinti;
                stringNumeriEstratti += (i + 1) + " ";
            }
        }

        CalcoloVincita cv = new CalcoloVincita(numeri, importo, contNumeriVinti);
        vincita = cv.getVincita();

        Log.scriviLog(importo, vincita, numeriScelti, numeriIndovinati);
        
        pannelli();
        stileEColori();
        componenti();
        frame();
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

    private void stileEColori() {
        // Etichetta del titolo
        jlTitolo.setForeground(UtilitiesFinestra.GREY);
        jlTitolo.setFont(UtilitiesFinestra.FTITLE);

        // Etichetta del nome utente
        jlUsername.setForeground(UtilitiesFinestra.GREY);
        jlUsername.setFont(UtilitiesFinestra.FLABEL);

        // Etichetta dei numeri estratti
        jlNumeriEstratti.setForeground(UtilitiesFinestra.GREY);
        jlNumeriEstratti.setFont(UtilitiesFinestra.FLABEL);

        // Etichetta dei numeri indovinati
        jlNumeriIndovinati.setForeground(UtilitiesFinestra.GREY);
        jlNumeriIndovinati.setFont(UtilitiesFinestra.FLABEL);
    }

    private void pannelli() {
        // Inizializza i pannelli
        for (int i = 0; i < PANNELLI; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(UtilitiesFinestra.BLUE);
        }

        // Istanzia componenti
        jlMostraVincita = new JLabel(String.valueOf(vincita), JLabel.CENTER);
        jlNumeriEstratti = new JLabel(stringNumeriEstratti, JLabel.CENTER);
        jlNumeriIndovinati = new JLabel(stringNumeriIndovinati, JLabel.CENTER);

        // Layout
        jp[0].setLayout(glNord);
        jp[1].setLayout(gblCentro);
        jp[2].setLayout(flSud);
        jp[3].setLayout(blEstOvest);
        jp[4].setLayout(blEstOvest);

        // Aggiunge componenti al pannello
        jp[0].add(jlTitolo);
        jp[0].add(jlUsername);
        jp[1].add(jlVincita);
        jp[1].add(jlMostraVincita);
        jp[2].add(jbStatistiche);
        jp[2].add(jbChiudi);
        jp[3].add(jlEstratti, BorderLayout.NORTH);
        jp[3].add(jlNumeriEstratti, BorderLayout.CENTER);
        jp[4].add(jlIndovinati, BorderLayout.NORTH);
        jp[4].add(jlNumeriIndovinati, BorderLayout.CENTER);
    }

    private void componenti() {
        // Pulsante di chiusura
        jbChiudi.setActionCommand("chiudi");
        jbChiudi.addActionListener(new GestoreInterno());
    }

    private void frame() {
        // Ascoltatore finestra
        jf.addWindowListener(new GestoreFinestraND(jf));

        // Layout
        jf.setLayout(UtilitiesFinestra.LAYOUT);
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);
        jf.add(jp[2], BorderLayout.SOUTH);
        jf.add(jp[3], BorderLayout.WEST);
        jf.add(jp[4], BorderLayout.EAST);
        
        // Posizione, dimensione, visibilità finestra
        jf.setLocation(UtilitiesFinestra.LOCATIONX, UtilitiesFinestra.LOCATIONY);
        jf.setResizable(false);
        jf.setSize(UtilitiesFinestra.DIMENSION);
        jf.setVisible(true);

        // Icona
        jf.setIconImage(UtilitiesFinestra.ICON.getImage());

        // Contenitore
        jf.getContentPane();
    }
}