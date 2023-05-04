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

public class DettagliPartita {

    private class GestoreInterno implements ActionListener {
        private void errore() {
            JOptionPane.showMessageDialog(jf, "L'importo deve essere da 2 a 200 euro, a scatti di 0.50",
                "Attenzione",
                JOptionPane.WARNING_MESSAGE);
            jtfImporto.setText("");
        }

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
                if (importo < 2 || importo > 200 || importo % 0.50 != 0) {
                    errore();
                } else {
                    numeri = (byte) (jcbNumeri.getSelectedIndex() + 1);
                    InserimentoNumeri in = new InserimentoNumeri(numeri, importo);
                    jf.dispose();
                }
            }
        }
    }

    private float importo = 0.0f;
    private byte numeri = 0;
    private JFrame jf = new JFrame(UtilitiesFinestra.TITOLO);
    private GridLayout glNorth = new GridLayout(2, 1);
    private GridBagLayout gbl = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel[] jp = new JPanel[UtilitiesFinestra.PANNELLI];
    private JLabel jlTitolo = new JLabel("DETTAGLI PARTITA", JLabel.CENTER);
    private JLabel jlUsername = new JLabel("", JLabel.CENTER);
    private JLabel jlNumeri = new JLabel("Inserire quanti numeri si vuole giocare", JLabel.CENTER);
    private JLabel jlImporto = new JLabel("Inserire l'importo (da 2 a 200 euro)", JLabel.CENTER);
    private JButton jbProsegui = new JButton("Prosegui");
    private JComboBox<Integer> jcbNumeri = new JComboBox<Integer>();
    private JTextField jtfImporto = new JTextField(6);

    public DettagliPartita() {
        setupCombo();
        stiliEColori();
        pannelli();
        componenti();
        frame();
    }

    private void setupCombo() {
        for (int i = 1; i <= 10; ++i) {
            jcbNumeri.addItem(i);
        }
    }

    private void stiliEColori() {
        // Etichetta del titolo
        jlTitolo.setForeground(UtilitiesFinestra.GREY);
        jlTitolo.setFont(UtilitiesFinestra.FTITLE);

        // Etichetta del nome utente
        jlUsername.setForeground(UtilitiesFinestra.GREY);
        jlUsername.setFont(UtilitiesFinestra.FLABEL);

        // Etichetta dell'importo
        jlImporto.setForeground(UtilitiesFinestra.GREY);
        jlImporto.setFont(UtilitiesFinestra.FLABEL);

        // Etichetta dei numeri
        jlNumeri.setForeground(UtilitiesFinestra.GREY);
        jlNumeri.setFont(UtilitiesFinestra.FLABEL);

        // Pulsante prosegui
        jbProsegui.setForeground(UtilitiesFinestra.BLUE);
        jbProsegui.setBackground(UtilitiesFinestra.GREY);
        jbProsegui.setPreferredSize(UtilitiesFinestra.DBUTTON);
        jbProsegui.setBorder(UtilitiesFinestra.BORDER);
        jbProsegui.setFont(UtilitiesFinestra.FBUTTON);
    }

    private void pannelli() {
        // Istanziazione
        for (int i = 0; i < UtilitiesFinestra.PANNELLI; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(UtilitiesFinestra.BLUE);
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

    private void componenti() {
        // Etichetta del nome utente
        jlUsername.setText(Utente.username);

        // Gestione pulsante prosegui
        jbProsegui.setActionCommand("prosegui");
        jbProsegui.addActionListener(new GestoreInterno());
    }

    private void frame() {
        // Ascoltatore finestra
        jf.addWindowListener(new GestoreFinestraND(jf));

        // Layout
        jf.setLayout(UtilitiesFinestra.LAYOUT);
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);
        jf.add(jp[2], BorderLayout.SOUTH);

        // Posizionamento, dimensione e visibilità
        jf.setLocation(UtilitiesFinestra.LOCATIONX, UtilitiesFinestra.LOCATIONY);
        jf.setSize(UtilitiesFinestra.DIMENSION);
        jf.setResizable(false);
        jf.setVisible(true);

        // Icona
        jf.setIconImage(UtilitiesFinestra.ICON.getImage());

        // Contenitore
        jf.getContentPane();
    }

    public float getImporto() {
        return importo;
    }

    public int getNumeri() {
        return numeri;
    }
}