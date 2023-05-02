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
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DettagliPartita {

    private class GestoreInterno implements ActionListener {
        private void errore() {
            JOptionPane.showMessageDialog(jf, "L'importo deve essere tra 2€ -> 200€, a scatti di 0.50€",
                "Attenzione",
                JOptionPane.WARNING_MESSAGE);
            jtfImporto.setText("");
        }

        public void actionPerformed(ActionEvent e) {
            String s = jtfImporto.getText();

            // Controlla se l'input e' vuoto
            if (s.isEmpty())
                errore();
            else if (s.equals("prosegui")) {
                // Controlla se l'input e' un numero
                try {
                    importo = Float.valueOf(s);
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
    private GridLayout glCenter = new GridLayout(2, 2, 10, 10);
    private JPanel[] jp = new JPanel[UtilitiesFinestra.PANELS];
    private JPanel jpCenter = new JPanel();
    private JLabel jlTitolo = new JLabel("DETTAGLI PARTITA", JLabel.CENTER);
    private JLabel jlUsername = new JLabel("", JLabel.CENTER);
    private JLabel jlNumeri = new JLabel("Inserire quanti numeri si vuole giocare");
    private JLabel jlImporto = new JLabel("Inserire l'importo (da 2 a 200 euro)");
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
        jlImporto.setBackground(UtilitiesFinestra.BLUE);
        jlImporto.setFont(UtilitiesFinestra.FLABEL);

        // Etichetta dei numeri
        jlNumeri.setForeground(UtilitiesFinestra.GREY);
        jlNumeri.setBackground(UtilitiesFinestra.BLUE);
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
        for (int i = 0; i < UtilitiesFinestra.PANELS; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(UtilitiesFinestra.BLUE);
        }

        // Layout
        jp[0].setLayout(glNorth);
        jpCenter.setLayout(glCenter);
        jp[1].setLayout(new GridBagLayout());

        // Composizione
        jp[0].add(jlTitolo);
        jp[0].add(jlUsername);
        jpCenter.add(jlImporto);
        jpCenter.add(jtfImporto);
        jpCenter.add(jlNumeri);
        jpCenter.add(jcbNumeri);
        jp[1].add(jpCenter);
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
        jf.setLayout(new BorderLayout());
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);
        jf.add(jp[2], BorderLayout.SOUTH);

        // Posizionamento, dimensione e visibilità
        jf.setLocation(UtilitiesFinestra.LOCATIONX, UtilitiesFinestra.LOCATIONY);
        jf.setMinimumSize(UtilitiesFinestra.DIMENSION);
        jf.setSize(UtilitiesFinestra.DIMENSION);
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