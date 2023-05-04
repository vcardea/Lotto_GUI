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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Vector;

public class Finale {
    /*
     * Classe interna per la gestione dei pulsanti
     */
    public class GestoreInterno implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            String comando = ae.getActionCommand();
            if (comando.equals("chiudi")) {
                Menu.apriFinestra();
                jf.dispose();
            } else if (comando.equals("statistiche")) {
                Statistiche s = new Statistiche();
                jf.setVisible(false);
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

    private final byte PANNELLI = 7;
    private JFrame jf = new JFrame(UtilFinestra.TITOLO);
    private JPanel[] jp = new JPanel[PANNELLI];
    private GridLayout glNord = new GridLayout(2, 1);
    private GridBagLayout gblCentro = new GridBagLayout();
    private FlowLayout flSud = new FlowLayout();
    private GridLayout glMostraNumeri = new GridLayout(10, 2);
    private BorderLayout blEstOvest = new BorderLayout();
    private JLabel jlTitolo = new JLabel("RISULTATO FINALE", JLabel.CENTER);
    private JLabel jlUsername = new JLabel(Utente.username, JLabel.CENTER);
    private JLabel jlEstratti = new JLabel("Numeri estratti", JLabel.CENTER);
    private JLabel jlIndovinati = new JLabel("Numeri indovinati", JLabel.CENTER);
    private JLabel jlVincita = new JLabel("Vincita (euro)", JLabel.CENTER);
    private JLabel jlMostraVincita;
    private String sNumeriEstratti = new String("A");
    private JLabel jlNumeriEstratti;
    private String sNumeriIndovinati = new String("A");
    private JLabel jlNumeriIndovinati;
    private JButton jbStatistiche = new JButton("Statistiche");
    private JButton jbChiudi = new JButton("Chiudi");

    /*
     * Costruttore. Genera la finestra e manipola i numeri estratti e scelti
     */
    public Finale(boolean[] numeriScelti, float importo, byte numeri) {
        estrai();

        for (byte i = 0; i < NUMERI; ++i) {
            if (checkEstrazioni[i]) {
                sNumeriEstratti += (i + 1) + " ";
            }
            if (numeriScelti[i] && checkEstrazioni[i]) {
                numeriIndovinati.addElement((byte) (i + 1));
                ++contNumeriVinti;
                sNumeriEstratti += (i + 1) + " ";
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
        jlTitolo.setForeground(UtilFinestra.GRIGIO);
        jlTitolo.setFont(UtilFinestra.FTITOLO);

        // Etichetta del nome utente
        jlUsername.setForeground(UtilFinestra.GRIGIO);
        jlUsername.setFont(UtilFinestra.FETICHETTA);

        // Etichetta titolo dei numeri estratti
        jlEstratti.setForeground(UtilFinestra.GRIGIO);
        jlEstratti.setFont(UtilFinestra.FETICHETTA);

        // Etichetta dei numeri estratti
        jlNumeriEstratti.setForeground(UtilFinestra.GRIGIO);
        jlNumeriEstratti.setFont(UtilFinestra.FETICHETTA);

        // Etichetta titolo dei numeri indovinati
        jlIndovinati.setForeground(UtilFinestra.GRIGIO);
        jlIndovinati.setFont(UtilFinestra.FETICHETTA);

        // Etichetta dei numeri indovinati
        jlNumeriIndovinati.setForeground(UtilFinestra.GRIGIO);
        jlNumeriIndovinati.setFont(UtilFinestra.FETICHETTA);

        // Etichetta titolo vincita
        jlVincita.setForeground(UtilFinestra.GRIGIO);
        jlVincita.setFont(UtilFinestra.FETICHETTA);

        // Etichetta vincita
        jlMostraVincita.setForeground(UtilFinestra.GRIGIO);
        jlMostraVincita.setFont(UtilFinestra.FETICHETTA);

        // Pulsante statistiche
        jbStatistiche.setForeground(UtilFinestra.BLU);
        jbStatistiche.setBackground(UtilFinestra.GRIGIO);
        jbStatistiche.setPreferredSize(UtilFinestra.DPULSANTE);
        jbStatistiche.setBorder(UtilFinestra.BPULSANTE);
        jbStatistiche.setFont(UtilFinestra.FPULSANTE);

        // Pulsante chiudi
        jbChiudi.setForeground(UtilFinestra.BLU);
        jbChiudi.setBackground(UtilFinestra.GRIGIO);
        jbChiudi.setPreferredSize(UtilFinestra.DPULSANTE);
        jbChiudi.setBorder(UtilFinestra.BPULSANTE);
        jbChiudi.setFont(UtilFinestra.FPULSANTE);
    }

    private void pannelli() {
        // Inizializza i pannelli
        for (int i = 0; i < PANNELLI; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(UtilFinestra.BLU);
        }

        // Istanzia componenti
        jlMostraVincita = new JLabel(String.valueOf(vincita), JLabel.CENTER);
        jlNumeriEstratti = new JLabel(sNumeriEstratti, JLabel.CENTER);
        jlNumeriIndovinati = new JLabel(sNumeriIndovinati, JLabel.CENTER);

        // Layout
        jp[0].setLayout(glNord);
        jp[1].setLayout(gblCentro);
        jp[2].setLayout(flSud);
        jp[3].setLayout(blEstOvest);
        jp[4].setLayout(blEstOvest);
        jp[5].setLayout(glMostraNumeri);
        jp[6].setLayout(glMostraNumeri);

        // Aggiunge componenti al pannello
        GridBagConstraints gbc = new GridBagConstraints();

        jp[0].add(jlTitolo);
        jp[0].add(jlUsername);

        gbc.gridx = 0;
        gbc.gridy = 0;
        jp[1].add(jlVincita, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0);
        jp[1].add(jlMostraVincita, gbc);

        jp[2].add(jbStatistiche);
        jp[2].add(jbChiudi);

        jp[3].setPreferredSize(new Dimension(UtilFinestra.LARGHEZZA / 4, UtilFinestra.ALTEZZA));
        jp[3].add(jlEstratti, BorderLayout.NORTH);
        jp[5].add(jlNumeriEstratti);
        jp[3].add(jp[5], BorderLayout.CENTER);

        jp[4].setPreferredSize(new Dimension(UtilFinestra.LARGHEZZA / 4, UtilFinestra.ALTEZZA));
        jp[4].add(jlIndovinati, BorderLayout.NORTH);
        jp[6].add(jlNumeriIndovinati);
        jp[4].add(jp[6], BorderLayout.CENTER);
    }

    private void componenti() {
        // Pulsante statistiche
        jbStatistiche.setActionCommand("statistiche");
        jbStatistiche.addActionListener(new GestoreInterno());

        // Pulsante di chiusura
        jbChiudi.setActionCommand("chiudi");
        jbChiudi.addActionListener(new GestoreInterno());
    }

    private void frame() {
        // Ascoltatore finestra
        jf.addWindowListener(new GestoreFinestraND(jf));

        // Layout
        jf.setLayout(UtilFinestra.LAYOUT);
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);
        jf.add(jp[2], BorderLayout.SOUTH);
        jf.add(jp[3], BorderLayout.WEST);
        jf.add(jp[4], BorderLayout.EAST);
        
        // Posizione, dimensione, visibilità finestra
        jf.setLocation(UtilFinestra.POSX, UtilFinestra.POSY);
        jf.setSize(UtilFinestra.DIMENSIONE);
        jf.setResizable(false);
        jf.setVisible(true);

        // Icona
        jf.setIconImage(UtilFinestra.ICONA.getImage());

        // Contenitore
        jf.getContentPane();
    }
}