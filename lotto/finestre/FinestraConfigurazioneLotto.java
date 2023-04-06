package lotto.finestre;

import lotto.finestre.gestori.GestoreFinestra;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraConfigurazioneLotto {

    private class GestorePulsante implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String val = jtf.getText();
            if (val.isEmpty()) // controllo se l'input e' vuoto
                JOptionPane.showMessageDialog(jf, "L'importo deve essere tra 2€ -> 200€, a scatti di 0.50€",
                        "Attenzione",
                        JOptionPane.WARNING_MESSAGE);
            else {
                try {
                    importo = Float.valueOf(val); // controllo se l'input e' un numero
                } catch (NumberFormatException nfe) {
                    importo = 0.0f;
                }

                if (importo < 2 || importo > 200 || importo % 0.50 != 0) { // controllo se l'importo e' nella giusta
                                                                           // modalita'
                    JOptionPane.showMessageDialog(jf, "L'importo deve essere tra 2€ -> 200€, a scatti di 0.50€",
                            "Attenzione",
                            JOptionPane.WARNING_MESSAGE);
                    jtf.setText("");
                } else {
                    numeri = jcb.getSelectedIndex();
                    int numeriScelti[] = new int[numeri];
                    // Chiama FinestraInserimentoNumeri 
                    /*
                     * FinestraInserimentoNumeri riceve quanti numeri da giocare
                     * Ritorna i numeri giocati dall'utente per chiamare poi
                     * la finestra finale con i risultati
                     */
                    // Chiama FinestraOutput
                }
            }
        }
    }

    private static float importo = 0.0f;
    private static int numeri = 0;
    private static JFrame jf = new JFrame("Giocata");
    private static JPanel jp1 = new JPanel();
    private static JPanel jp2 = new JPanel();
    private static JLabel jl1 = new JLabel("Inserire quanti numeri si vuole giocare");
    private static JLabel jl2 = new JLabel("Inserire l'importo (da 2€ a 200€)");
    private static GridLayout gl = new GridLayout(3, 2);
    private static JButton jb = new JButton("Prosegui");
    private static JComboBox<Integer> jcb = new JComboBox<Integer>();
    private static JTextField jtf = new JTextField(6);

    private static void setupCombo() {
        for (int i = 1; i <= 10; ++i)
            jcb.addItem(i);
    }

    public FinestraConfigurazioneLotto() {
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
        jf.addWindowListener(new GestoreFinestra(jf)); // gestore finestra globale
        jb.addActionListener(new GestorePulsante()); // gestore button locale

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