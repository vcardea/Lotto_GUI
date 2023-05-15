package src.finestre;

import src.finestre.gestori.GestoreFinestraND;
import src.finestre.gestori.GestoreMouse;
import src.utente.Utente;

import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe per l'inserimento dei numeri da giocare
 * 
 * @author Vincenzo Cardea
 * @author Francesco Rizzello
 * @author Matteo De Vito
 * @author Alessandro Serio
 * 
 * @since 1.0.0
 */
public class InserimentoNumeri {

    private static boolean numeriScelti[] = new boolean[UtilFinestra.NUMERI]; 
    private static int contatore;
    private byte numeri = 0;
    private float importo;
    protected boolean[] selezionati = new boolean[UtilFinestra.NUMERI];

    private JFrame jf = new JFrame(UtilFinestra.TITOLO);
    private JPanel jp[] = new JPanel[UtilFinestra.PANNELLI];
    private JLabel jlTitolo = new JLabel("INSERIMENTO NUMERI", JLabel.CENTER);
    private JLabel jlUsername = new JLabel(Utente.username, JLabel.CENTER);
    private GridLayout glNumeri = new GridLayout(9, 10);
    private JButton jbProsegui = new JButton("Prosegui");
    private JButton[] jbNumeri = new JButton[UtilFinestra.NUMERI];

    protected static final Border BPULSANTE = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(UtilFinestra.BLU, 0),
        BorderFactory.createEmptyBorder(0, 0, 0, 0)
    );

    /**
     * Inner class per la gestione dei pulsanti
     */
    private class GestorePulsante implements ActionListener {
        /**
         * Comunica un errore all'utente
         * 
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
         * Reset dei dati 
         */
        private void reset() {
            for (int i = 0; i < UtilFinestra.NUMERI; ++i) {
                if (numeriScelti[i]) {
                    numeriScelti[i] = false;
                    jbNumeri[i].setForeground(UtilFinestra.GRIGIO);
                    selezionati[i] = false;
                }
            }
            
            // resetta l'array dei numeri selezionati
            contatore = 0;
        }

        /**
         * Gestisce l'evento sul pulsante premuto
         * 
         * @param e evento sul pulsante
         */
        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();
            if (comando.equals("Prosegui")) {
                if (numeri != contatore) { // controlla se la quantita' di numeri scelti
                    errore(false); // e' uguale alla quantita di numeri selezionati precedentemente
                } else {
                    Finale f = new Finale(numeriScelti, importo, numeri); // procede alla prossima finestra
                    reset(); // resetta i diversi valori
                    //jf.setVisible(false);
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
                        selezionati[numero] = true;
                    }
                } else {
                    numeriScelti[numero] = false; // se invece il numero e' gia' selezionato
                    --contatore; // diminuisce il contatore e lo deseleziona
                    jb.setForeground(UtilFinestra.GRIGIO);
                    selezionati[numero] = false;
                }
            }
        }
    }

    /**
     * Inner class per gestire il mouse
     */
    private class GestoreMouseNumeri extends MouseAdapter {

        /**
         * Gestisce il passaggio del mouse sui numeri
         * 
         * @param e evento del mouse
         */
        public void mouseEntered(MouseEvent e) {
            JButton jbTmp = (JButton) e.getComponent();
            int indice = Integer.valueOf(jbTmp.getActionCommand()).intValue() - 1;
            jbNumeri[indice].setForeground(UtilFinestra.VERDECHIARO);
        }

        /**
         * Gestisce l'uscita del cursore da una posizione sopra il componente
         * 
         * @param e evento del mouse
         */
        public void mouseExited(MouseEvent e) {
            JButton jbTmp = (JButton) e.getComponent();
            int indice = Integer.valueOf(jbTmp.getActionCommand()).intValue() - 1;
            if (!selezionati[indice]) {
                jbNumeri[indice].setForeground(UtilFinestra.GRIGIO);
            }
        }

        /**
         * Invocato quando il pulsante è stato premuto
         * (premuto e rilasciato)
         * 
         * @param e evento del mouse
         */
        public void mouseClicked(MouseEvent e) {
            // JButton jbTmp = (JButton) e.getComponent();
            // int indice = Integer.valueOf(jbTmp.getActionCommand()).intValue() - 1;
            // jbNumeri[indice].setBackground(UtilFinestra.BLU);
            // jbNumeri[indice].setForeground(UtilFinestra.VERDE);
        }

        /**
         * Invocato quando il mouse preme sul pulsante e poi
         * viene trascinato
         * 
         * @param e evento del mouse
         */
        public void mouseDragged(MouseEvent e) {
            // JButton jbTmp = (JButton) e.getComponent();
            // int indice = Integer.valueOf(jbTmp.getActionCommand()).intValue() - 1;
            // jbNumeri[indice].setBackground(UtilFinestra.BLU);
            // jbNumeri[indice].setForeground(UtilFinestra.BIANCO);
        }

        /**
         * Invocato quando il cursore è stato spostato
         * su un componente, ma nessun pulsante è stato premuto
         * 
         * @param e evento del mouse
         */
        public void mouseMoved(MouseEvent e) {
            
        }
        
        /**
         * Invocato quando un pulsante viene premuto
         * 
         * @param e evento del mouse
         */
        public void mousePressed(MouseEvent e) {
            // JButton jbTmp = (JButton) e.getComponent();
            // int indice = Integer.valueOf(jbTmp.getActionCommand()).intValue() - 1;
            // jbNumeri[indice].setBackground(UtilFinestra.BLU);
            // jbNumeri[indice].setForeground(UtilFinestra.VERDE);
        }

        /**
         * Invocato quando un pulsante viene rilasciato
         * 
         * @param e evento del mouse
         */
        public void mouseReleased(MouseEvent e) {
            // JButton jbTmp = (JButton) e.getComponent();
            // int indice = Integer.valueOf(jbTmp.getActionCommand()).intValue() - 1;
            // jbNumeri[indice].setBackground(UtilFinestra.BLU);
            // jbNumeri[indice].setForeground(UtilFinestra.BIANCO);
        }

        /**
         * Invocato quando la rotellina del pulsante viene
         * ruotata
         * 
         * @param e evento del mouse
         */
        public void mouseWheelMoved(MouseEvent e) {

        }
    }

    /**
     * Costruttore
     * 
     * @param numeri  la quantita' di numeri scelti
     * @param importo usato
     */
    public InserimentoNumeri(float importo) {
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
        jbProsegui.setFont(UtilFinestra.FPULSANTE);
    }

    /**
     * Setup dei pulsanti, mouse e relative scelte
     */
    private void setup() {
        contatore = 0;
        for (int i = 0; i < UtilFinestra.NUMERI; i++) {
            numeriScelti[i] = false;
            jbNumeri[i] = new JButton(Integer.toString(i + 1));
            jbNumeri[i].setActionCommand(Integer.toString(i + 1));
            jbNumeri[i].addActionListener(new GestorePulsante());
            jbNumeri[i].addMouseListener(new GestoreMouseNumeri());
            // Stile pulsante
            jbNumeri[i].setForeground(UtilFinestra.GRIGIO);
            jbNumeri[i].setBackground(UtilFinestra.BLU);
            jbNumeri[i].setBorder(BPULSANTE);
            jbNumeri[i].setFont(UtilFinestra.FPULSANTE);
            jp[1].add(jbNumeri[i]);
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
        jp[2].setLayout(UtilFinestra.GRIDBAGLAYOUT);

        // Composizione di pannelli
        jp[0].add(jlTitolo);
        setup();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = UtilFinestra.DISTBASSO;
        jp[2].add(jbProsegui, gbc);
    }

    /**
     * Aggiunge gli ascoltatori necessari
     */
    private void componenti() {
        // Ascoltatore pulsante prosegui
        jbProsegui.addActionListener(new GestorePulsante());
        jbProsegui.addMouseListener(new GestoreMouse(jbProsegui));
    }

    /**
     * Crea e sistema il frame
     */
    private void frame() {
        // Ascoltatore finestra
        jf.addWindowListener(new GestoreFinestraND(jf));

        // Layout
        jf.setLayout(UtilFinestra.BORDERLAYOUT);
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

    /**
     * Rende visibile la finestra
     */
    public void apriFinestra() {
        jf.setVisible(true);
    }

    /**
     * Imposta quanti numeri giocare
     * 
     * @param numeri quanti numeri giocare
     */
    public void setNumeri(byte numeri) {
        this.numeri = numeri;
    }
}