package src.finestre;

import src.finestre.gestori.GestoreFinestraND;
import src.utente.Utente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe per la gestione dell'importo e la quantita' 
 * di numeri da giocare
 * 
 * @author Vincenzo Cardea
 * @author Francesco Rizzello
 * @author Matteo De Vito
 * @author Alessandro Serio
 */
public class DettagliPartita {

    /**
     * Inner class per la gestione di pulsanti e ComboBox
     */
    private class GestoreInterno implements ActionListener {
        /**
         * Comunica un messaggio di errore all'utente
         */
        private void errore() {
            JOptionPane.showMessageDialog(jf, "L'importo deve essere da 1 a 200 euro, a scatti di 0.50",
                "Attenzione",
                JOptionPane.WARNING_MESSAGE);
            jtfImporto.setText("");
        }

        /**
         * Gestione del pulsante prosegui
         * 
         * @param e evento
         */
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            String val = jtfImporto.getText();

            // Controlla se l'input e' vuoto
            if (val.isEmpty()) {
                errore();
            }
            else if (s.equals("prosegui")) {
                // Controlla se l'input e' un numero
                try {
                    importo = Float.valueOf(val);
                } catch (NumberFormatException nfe) {
                    importo = 0.0f;
                }

                // Controlla il formato dell'importo
                if (importo < 1 || importo > 200 || importo % 0.50 != 0) {
                    errore();
                } else {
                    numeri = (byte) (jcbNumeri.getSelectedIndex() + 1);
                    if (in == null) {
                        in = new InserimentoNumeri(importo);
                    } else {
                        in.apriFinestra();
                    }
                    in.setNumeri(numeri);
                    jtfImporto.setText("");
                    jf.setVisible(false);
                }
            }
        }
    }

    private InserimentoNumeri in = null;

    private float importo = 0.0f;
    private byte numeri = 0;

    // elementi grafici
    private JFrame jf = new JFrame(UtilFinestra.TITOLO);
    private GridLayout glNorth = new GridLayout(2, 1);
    private GridBagLayout gbl = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel[] jp = new JPanel[UtilFinestra.PANNELLI];
    private JLabel jlTitolo = new JLabel("DETTAGLI PARTITA", JLabel.CENTER);
    private JLabel jlUsername = new JLabel("", JLabel.CENTER);
    private JLabel jlNumeri = new JLabel("Inserire quanti numeri si vuole giocare", JLabel.CENTER);
    private JLabel jlImporto = new JLabel("Inserire l'importo (da 1 a 200 euro)", JLabel.CENTER);
    private JButton jbProsegui = new JButton("Prosegui");
    private JComboBox<Integer> jcbNumeri = new JComboBox<Integer>();
    private JTextField jtfImporto = new JTextField(6);

    /**
     * Costruttore
     */
    public DettagliPartita() {
        setupCombo();
        stiliEColori();
        pannelli();
        componenti();
        frame();
    }

    /**
     * Setup della ComboBox per l'inserimento
     * della quantita' di numeri
     */
    private void setupCombo() {
        for (int i = 1; i <= 10; ++i) {
            jcbNumeri.addItem(i);
        }
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

        // Etichetta dell'importo
        jlImporto.setForeground(UtilFinestra.GRIGIO);
        jlImporto.setFont(UtilFinestra.FETICHETTA);

        // Etichetta dei numeri
        jlNumeri.setForeground(UtilFinestra.GRIGIO);
        jlNumeri.setFont(UtilFinestra.FETICHETTA);

        // Pulsante prosegui
        jbProsegui.setForeground(UtilFinestra.BLU);
        jbProsegui.setBackground(UtilFinestra.GRIGIO);
        jbProsegui.setPreferredSize(UtilFinestra.DPULSANTE);
        jbProsegui.setBorder(UtilFinestra.BPULSANTE);
        jbProsegui.setFont(UtilFinestra.FPULSANTE);
    }

    /**
     * Crea e sistema i pannelli da utilizzare
     */
    private void pannelli() {
        // Istanziazione
        for (int i = 0; i < UtilFinestra.PANNELLI; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(UtilFinestra.BLU);
        }

        // Layout
        jp[0].setLayout(glNorth);
        jp[1].setLayout(gbl);

        // Composizione
        jp[0].add(jlTitolo);
        jp[0].add(jlUsername);

        // Disponi jlImporto
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;
        jp[1].setPreferredSize(UtilFinestra.DIMENSIONE);
        jp[1].add(jlImporto, gbc);

        // Disponi jtfImporto
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(10, 10, 10, 50);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;
        jp[1].add(jtfImporto, gbc);
        
        // Disponi jlNumeri
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;
        jp[1].add(jlNumeri, gbc);
        
        // Disponi jcbNumeri
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(20, 10, 10, 50);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;
        jp[1].add(jcbNumeri, gbc);

        jp[2].add(jbProsegui);
    }

    /**
     * Crea e sistema i componenti
     */
    private void componenti() {
        // Etichetta del nome utente
        jlUsername.setText(Utente.username);

        // Gestione pulsante prosegui
        jbProsegui.setActionCommand("prosegui");
        jbProsegui.addActionListener(new GestoreInterno());
    }

    /**
     * Crea ed apre il frame
     */
    private void frame() {
        // Ascoltatore finestra
        jf.addWindowListener(new GestoreFinestraND(jf));

        // Layout
        jf.setLayout(UtilFinestra.LAYOUT);
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);
        jf.add(jp[2], BorderLayout.SOUTH);

        // Posizionamento, dimensione e visibilità
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
     * Ritorna l'importo
     * 
     * @return importo inserito
     */
    public float getImporto() {
        return importo;
    }

    /**
     * Ritorna quanti numeri devono essere selezionati
     * 
     * @return quantita dei numeri selezionati
     */
    public int getNumeri() {
        return numeri;
    }
}