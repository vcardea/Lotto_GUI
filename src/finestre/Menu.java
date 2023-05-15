package src.finestre;

import src.finestre.gestori.GestoreFinestra;
import src.finestre.gestori.GestoreMouse;
import src.finestre.gestori.GestoreUscita;
import src.utente.Utente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe per la gestione del menu
 * 
 * @author Vincenzo Cardea
 * @author Francesco Rizzello
 * @author Matteo De Vito
 * @author Alessandro Serio
 * 
 * @see UtilFinestra
 * @see DettagliPartita
 * @see Info
 * 
 * @since 1.0.0
 */
public class Menu {
    /**
     * Inner class per la gestione dei pulsanti
     */
    protected class GestoreInterno implements ActionListener {
        /**
         * Gestisce la pressione dei pulsanti
         * 
         * @param e evento generato sul pulsante
         */
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();

            if (s.equals("nuovapartita")) {
                if (cl == null) {
                    cl = new DettagliPartita();
                } else {
                    cl.apriFinestra();
                }
                jf.setVisible(false);
            } else if (s.equals("info")) {
                Info i = new Info();
                jf.setVisible(false);
            }
        }
    }

    private DettagliPartita cl = null;

    private final Insets DISTANZA = new Insets(10, 0, 0, 0);
    private static JFrame jf = new JFrame(UtilFinestra.TITOLO);
    private JPanel[] jp = new JPanel[UtilFinestra.PANNELLI - 1];
    private GridLayout glNorth = new GridLayout(2, 1, 10, 0);
    private JLabel jlTitolo = new JLabel("MENU PRINCIPALE", JLabel.CENTER);
    private JLabel jlUsername = new JLabel(Utente.username, JLabel.CENTER);
    private JButton jbNuovaPartita = new JButton("Nuova Partita");
    private JButton jbInfo = new JButton("Informazioni");
    private JButton jbEsci = new JButton("Esci");
    

    /**
     * Costruttore
     */
    public Menu() {
        stiliEColori();
        pannelli();
        componenti();
        frame();
    }

    /**
     * Imposta lo stile ed i colori della finestra
     */
    private void stiliEColori() {
        // Etichetta del titolo
        jlTitolo.setForeground(UtilFinestra.GRIGIO);
        jlTitolo.setFont(UtilFinestra.FTITOLO);

        // Etichetta del nome utente
        jlUsername.setForeground(UtilFinestra.GRIGIO);
        jlUsername.setFont(UtilFinestra.FETICHETTA);

        // Pulsante nuova partita
        jbNuovaPartita.setForeground(UtilFinestra.BLU);
        jbNuovaPartita.setBackground(UtilFinestra.GRIGIO);
        jbNuovaPartita.setPreferredSize(UtilFinestra.DPULSANTE);
        jbNuovaPartita.setFont(UtilFinestra.FPULSANTE);

        // Pulsante informazioni
        jbInfo.setForeground(UtilFinestra.BLU);
        jbInfo.setBackground(UtilFinestra.GRIGIO);
        jbInfo.setPreferredSize(UtilFinestra.DPULSANTE);
        jbInfo.setFont(UtilFinestra.FPULSANTE);

        // Pulsante esci
        jbEsci.setForeground(UtilFinestra.BLU);
        jbEsci.setBackground(UtilFinestra.GRIGIO);
        jbEsci.setPreferredSize(UtilFinestra.DPULSANTE);
        jbEsci.setFont(UtilFinestra.FPULSANTE);
    }

    /**
     * Crea e sistema i pannelli da utilizzare
     */
    private void pannelli() {
        // Istanziazione
        for (int i = 0; i < jp.length; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(UtilFinestra.BLU);
        }

        // Layout
        jp[0].setLayout(glNorth);
        jp[1].setLayout(new GridBagLayout());

        // Composizione
        GridBagConstraints gbc = new GridBagConstraints();

        jp[0].add(jlTitolo);
        jp[0].add(jlUsername);

        gbc.gridx = 0;
        gbc.gridy = 0;
        jp[1].add(jbNuovaPartita, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = DISTANZA;
        jp[1].add(jbInfo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = DISTANZA;
        jp[1].add(jbEsci, gbc);
    }

    /**
     * Crea e sistema i componenti
     */
    private void componenti() {
        // Gestione pulsante nuova partita
        jbNuovaPartita.setActionCommand("nuovapartita");
        jbNuovaPartita.addActionListener(new GestoreInterno());
        jbNuovaPartita.addMouseListener(new GestoreMouse(jbNuovaPartita));

        // Gestione pulsante informazioni
        jbInfo.setActionCommand("info");
        jbInfo.addActionListener(new GestoreInterno());
        jbInfo.addMouseListener(new GestoreMouse(jbInfo));

        // Gestione pulsante di uscita
        jbEsci.setActionCommand("esci");
        jbEsci.addActionListener(new GestoreUscita());
        jbEsci.addMouseListener(new GestoreMouse(jbEsci));
    }

    /**
     * Crea ed apre il frame
     */
    private void frame() {
        // Ascoltatore finestra
        jf.addWindowListener(new GestoreFinestra(jf));

        // Layout
        jf.setLayout(new BorderLayout());
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);

        // Posizionamento, dimensione e visibilità finestra
        jf.setLocation(UtilFinestra.POSX, UtilFinestra.POSY);
        jf.setSize(UtilFinestra.DIMENSIONE);
        jf.setResizable(false);
        jf.setVisible(true);

        // Icona
        jf.setIconImage(UtilFinestra.ICONA.getImage());

        // Contenitore
        jf.getContentPane();
    }

    /**
     * Imposta visibile la finestra
     */
    public static void apriFinestra() {
        jf.setVisible(true);
    }
}
