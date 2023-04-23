package src.finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.finestre.gestori.GestoreFinestra_ND;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraConfigurazioneLotto {

    private class GestoreInterno implements ActionListener {
        private void errore() {
            JOptionPane.showMessageDialog(jf, "L'importo deve essere tra 2€ -> 200€, a scatti di 0.50€",
                    "Attenzione",
                    JOptionPane.WARNING_MESSAGE);
            jtf.setText("");
        }

        public void actionPerformed(ActionEvent e) {
            String val = jtf.getText();

            // Controlla se l'input e' vuoto
            if (val.isEmpty())
                errore();
            else {
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
                    numeri = (byte) (jcb.getSelectedIndex() + 1);
                    FinestraInserimentoNumeri fin = new FinestraInserimentoNumeri(numeri, importo);
                    jf.dispose();
                }
            }
        }
    }

    private float importo = 0.0f;
    private byte numeri = 0;
    private JFrame jf = null;
    private JPanel jp1 = new JPanel();
    private JPanel jp2 = new JPanel();
    private JLabel jl1 = new JLabel("Inserire quanti numeri si vuole giocare");
    private JLabel jl2 = new JLabel("Inserire l'importo (da 2€ a 200€)");
    private GridLayout gl = new GridLayout(3, 2);
    private JButton jb = new JButton("Prosegui");
    private JComboBox<Integer> jcb = new JComboBox<Integer>();
    private JTextField jtf = new JTextField(6);

    private void setupCombo() {
        for (int i = 1; i <= 10; ++i)
            jcb.addItem(i);
    }

    public FinestraConfigurazioneLotto() {
        creaFinestra();
    }

    public void creaFinestra() {
        jf = new JFrame("Giocata");
        setupCombo();

        // panel numero 1
        jp1.add(jl1);
        jp1.add(jcb);

        // panel numero 2
        jp2.add(jl2);
        jp2.add(jtf);

        // frame
        jf.setLayout(gl); // setta il layout
        jf.add(jp1); // aggiungi primo panel
        jf.add(jp2); // aggiungi secondo panel
        jf.add(jb); // aggiungi button

        // eventi
        jf.addWindowListener(new GestoreFinestra_ND(jf)); // gestore finestra globale
        jb.addActionListener(new GestoreInterno()); // gestore button locale

        // robe finali
        jf.setSize(350, 200);
        jf.setVisible(true);
    }

    public float getImporto() {
        return importo;
    }

    public int getNumeri() {
        return numeri;
    }
}