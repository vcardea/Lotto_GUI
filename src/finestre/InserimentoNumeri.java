package src.finestre;

import src.finestre.gestori.GestoreFinestraND;
import src.utente.Utente;

import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InserimentoNumeri {

    private static final byte NUMERI = 90;
    private static boolean numeriScelti[] = new boolean[NUMERI]; 
    private static int contatore;
    private byte numeri = 0;
    private float importo;

    // elementi grafici
    private JFrame jf = new JFrame(UtilFinestra.TITOLO);
    private JPanel jp[] = new JPanel[UtilFinestra.PANNELLI];
    private JLabel jlTitolo = new JLabel("INSERIMENTO NUMERI", JLabel.CENTER);
    private JLabel jlUsername = new JLabel(Utente.username, JLabel.CENTER);
    private GridLayout glNumeri = new GridLayout(9, 10);
    private JButton jbProsegui = new JButton("Prosegui");
    
    protected static final Border BPULSANTE = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(UtilFinestra.BLU, 0),
        BorderFactory.createEmptyBorder(0, 0, 0, 0)
    );

    /**
     * Inner class per il gestore dei button
     */
    private class GestorePulsante implements ActionListener {
        /**
         * @param tipo di errore
         */
        private void errore(boolean tipo) {
            if (tipo) {
                JOptionPane.showMessageDialog(jf, "Hai gia' inserito tutti i numeri",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(jf, "Non hai scelto tutti i numeri.",
                    "Attenzione",
                    JOptionPane.WARNING_MESSAGE);
            }
        }

        /**
         * Reset dei diversi dati 
         */
        private void reset() {
            for (int i = 0; i < NUMERI; ++i)
                if (numeriScelti[i])
                    numeriScelti[i] = false; // resetta l'array dei numeri selezionati
            contatore = 0;
        }

        /**
         * @param e evento che gestisce il selezionamento
         * dei numeri
         */
        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();
            if (comando.equals("Prosegui")) {
                if (numeri != contatore) { // controlla se la quantita' di numeri scelti
                    errore(false); // e' uguale alla quantita di numeri selezionati precedentemente
                } else {
                    Finale f = new Finale(numeriScelti, importo, numeri); // procede alla prossima finestra
                    reset(); // resetta i diversi valori
                    jf.dispose(); // chiude la finestra 
                }
            } else {
                JButton jb = (JButton) e.getSource();
                int numero = Integer.valueOf(comando).intValue(); // prende il numero selezionato
                --numero;
                if (!numeriScelti[numero]) { // se il numero non e' stato selezionato
                    if (contatore >= numeri) {
                        errore(true); // se la quantita di numeri selezionati e' maggiore al massimo
                    } else {
                        numeriScelti[numero] = true; // lo seleziona
                        ++contatore; // aumenta il contatore
                        jb.setForeground(UtilFinestra.VERDE);
                        GestoreMouse.selezionati[numero] = true;
                    }
                } else {
                    numeriScelti[numero] = false; // se invece il numero e' gia' selezionato
                    --contatore; // diminuisce il contatore e lo deseleziona
                    jb.setForeground(UtilFinestra.GRIGIO);
                    GestoreMouse.selezionati[numero] = false;
                }
            }
        }
    }

    /**
     * Inner class per gestire il mouse
     */
    private class GestoreMouse extends MouseAdapter {
        /**
         * Attributo protected per salvarsi
         * i numeri fin'ora selezionati
         */
        protected static boolean[] selezionati = new boolean[NUMERI];

        /**
         * @param e evento che gestisce quando
         * il mouse passa sopra un numero
         */
        public void mouseEntered(MouseEvent e) {
            JButton jb = (JButton) e.getComponent();
            jb.setForeground(UtilFinestra.VERDECHIARO);
        }

        /**
         * @param e evento che gestisce quando
         * il mouse esce fuori dal numero 
         * (per capire leggere il metodo corrispondente "mouseEntered")
         */
        public void mouseExited(MouseEvent e) {
            JButton jb = (JButton) e.getComponent();
            int indice = Integer.valueOf(jb.getActionCommand()).intValue();
            if (!selezionati[indice - 1]) {
                jb.setForeground(UtilFinestra.GRIGIO);
            }
        }
    }

    /**
     * Costruttore
     * @param numeri la quantita di numeri scelti
     * @param importo usato
     */
    public InserimentoNumeri(byte numeri, float importo) {
        this.numeri = numeri;
        this.importo = importo;
        
        stiliEColori();
        pannelli();
        componenti();
        frame();
    }
    /**
     * Sistema lo stile ed i colori della finestra
     */
    private void stiliEColori() {
        // Etichetta del titolo
        jlTitolo.setForeground(UtilFinestra.GRIGIO);
        jlTitolo.setFont(UtilFinestra.FTITOLO);

        // Etichetta del nome utente
        jlUsername.setForeground(UtilFinestra.GRIGIO);
        jlUsername.setFont(UtilFinestra.FETICHETTA);

        // Pulsante per il login
        jbProsegui.setForeground(UtilFinestra.BLU);
        jbProsegui.setBackground(UtilFinestra.GRIGIO);
        jbProsegui.setPreferredSize(UtilFinestra.DPULSANTE);
        jbProsegui.setBorder(UtilFinestra.BPULSANTE);
        jbProsegui.setFont(UtilFinestra.FPULSANTE);
    }

    /**
     * Setup dei button, mouse e relative scelte
     */
    private void setup() {
        contatore = 0;
        for (int i = 1; i <= NUMERI; i++) {
            numeriScelti[i - 1] = false;
            JButton btnNumero = new JButton(Integer.toString(i));
            btnNumero.setActionCommand(Integer.toString(i));
            btnNumero.addActionListener(new GestorePulsante());
            btnNumero.addMouseListener(new GestoreMouse());
            // Stile pulsante
            btnNumero.setForeground(UtilFinestra.GRIGIO);
            btnNumero.setBackground(UtilFinestra.BLU);
            btnNumero.setBorder(BPULSANTE);
            btnNumero.setFont(UtilFinestra.FPULSANTE);
            jp[1].add(btnNumero);
        }
    }

    /**
     * Crea e sistema i pannelli
     */
    private void pannelli() {
        // Istanziazione
        for (int i = 0; i < UtilFinestra.PANNELLI; ++i) {
            jp[i] = new JPanel();
            jp[i].setBackground(UtilFinestra.BLU);
        }

        // Imposta il layout dei pulsanti
        jp[1].setLayout(glNumeri);

        // Composizione di pannelli
        jp[0].add(jlTitolo);
        setup();
        jp[2].add(jbProsegui);
    }

    /**
     * Aggiunge gli ascoltatori necessari
     */
    private void componenti() {
        // Ascoltatore pulsante prosegui
        jbProsegui.addActionListener(new GestorePulsante());
    }

    /**
     * Crea e sistema il frame
     */
    private void frame() {
        // Ascoltatore finestra
        jf.addWindowListener(new GestoreFinestraND(jf));

        // Layout
        jf.setLayout(UtilFinestra.LAYOUT);
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);
        jf.add(jp[2], BorderLayout.SOUTH);

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