package src.finestre;

import src.finestre.gestori.GestoreFinestraND;

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

public class ConfigurazioneLotto {

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
                    InserimentoNumeri in = new InserimentoNumeri(numeri, importo);
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
    private JLabel jl2 = new JLabel("Inserire l'importo (da 2 a 200 euro)");
    private GridLayout gl = new GridLayout(3, 2);
    private JButton jb = new JButton("Prosegui");
    private JComboBox<Integer> jcb = new JComboBox<Integer>();
    private JTextField jtf = new JTextField(6);

    private void setupCombo() {
        for (int i = 1; i <= 10; ++i) {
            jcb.addItem(i);
        }
    }

    public ConfigurazioneLotto() {
        creaFinestra();
    }

    public void creaFinestra() {
        jf = new JFrame("Gioco del 10eLotto");
        setupCombo();

        // panel numero 1
        jp1.add(jl1);
        jp1.add(jcb);

        // panel numero 2
        jp2.add(jl2);
        jp2.add(jtf);

        jb.addActionListener(new GestoreInterno());

        jf.addWindowListener(new GestoreFinestraND(jf));
        jf.setLayout(gl);
        jf.add(jp1);
        jf.add(jp2);
        jf.add(jb);
        jf.setLocation(UtilitiesFinestra.LOCATIONX, UtilitiesFinestra.LOCATIONY);
        jf.setSize(UtilitiesFinestra.WIDTH, UtilitiesFinestra.HEIGHT);
        jf.setVisible(true);
        jf.setIconImage(UtilitiesFinestra.icon.getImage());
        jf.getContentPane();
    }

    public float getImporto() {
        return importo;
    }

    public int getNumeri() {
        return numeri;
    }
}