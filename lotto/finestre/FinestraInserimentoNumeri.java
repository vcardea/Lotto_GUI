package lotto.finestre;

import lotto.finestre.gestori.GestoreFinestra;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FinestraInserimentoNumeri {

    private class GestorePulsante implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Prosegui")) {
                if (contatore != numeri)
                    JOptionPane.showMessageDialog(jf, "Devi inserire " + numeri + " numeri",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                else {
                    flag = true;
                    jf.dispose();
                }
            }
        }
    }

    private class GestoreJCB implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            boolean flag = false;
            int i = 0;
            do {
                if (cb[i] == e.getSource()) {
                    flag = true;
                    ++contatore;
                    if (flags[i]) {
                        flags[i] = false;
                        int j = 0;
                        boolean flag2 = false;
                        do {
                            if (i == V[j]) {
                                V[j] = -1;
                                flag2 = true;
                            }
                            ++j;
                        } while (j < numeri && !flag2);
                        contatore -= 2;
                    } else if (contatore == numeri + 1) {
                        --contatore;
                        cb[i].setSelected(false);
                        // JOptionPane.showMessageDialog(null, "Hai gia' inserito tutti i numeri",
                        // "Attenzione", JOptionPane.WARNING_MESSAGE);
                    } else {
                        flags[i] = true;
                        int j = 0;
                        boolean flag2 = false;
                        do {
                            if (-1 == V[j]) {
                                V[j] = i;
                                flag2 = true;
                            }
                            ++j;
                        } while (j < numeri && !flag2);
                    }
                }
                ++i;
            } while (!flag && i < 90);
        }
    }

    private static boolean flag = false;
    private static int[] V = new int[10];
    private static boolean[] flags = new boolean[90];
    private static int contatore = 0;
    private static int numeri = 0;
    private static JFrame jf = new JFrame("Giocata");
    private static JPanel jp1 = new JPanel();
    private static JPanel jp2 = new JPanel();
    private static JLabel jl = new JLabel("Inserisci i numeri");
    private static GridLayout gl = new GridLayout(3, 1);
    private static GridLayout glP = new GridLayout(10, 1);
    private static JButton jb = new JButton("Prosegui");
    private static JCheckBox[] cb = new JCheckBox[90];

    private static void setup() {
        for (int i = 1; i <= 90; ++i) {
            if (i <= numeri)
                V[i - 1] = -1;
            cb[i - 1] = new JCheckBox("" + i);
            cb[i - 1].addItemListener(new FinestraInserimentoNumeri(numeri).new GestoreJCB());
            flags[i - 1] = false;
        }
    }

    public FinestraInserimentoNumeri(int n) {
        numeri = n;
    }

    public void creaFinestra() {
        setup();

        // panel
        jp1.add(jl);
        jp2.setLayout(glP);
        for (int i = 0; i < 90; ++i)
            jp2.add(cb[i]);

        // frame
        jf.setLayout(gl); // setta il layout
        jf.add(jp1); // aggiungi panel
        jf.add(jp2);
        jf.add(jb); // aggiungi button

        // eventi
        jf.addWindowListener(new GestoreFinestra(jf)); // gestore finestra globale
        jb.addActionListener(new FinestraInserimentoNumeri(numeri).new GestorePulsante()); // gestore button locale

        // robe finali
        jf.setSize(350, 200);
        jf.setVisible(true);
    }

    public void getNumeri(int numeriScelti[]) {
        for (int i = 0; i < 10; ++i) {
            numeriScelti[i] = V[i];
        }
    }

    public boolean getFlag() {
        return flag;
    }
}
