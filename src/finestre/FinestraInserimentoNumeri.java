package src.finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.finestre.gestori.GestoreFinestra;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraInserimentoNumeri {

    private class GestorePulsante implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Prosegui")) {
                jf.dispose();
            }
        }
    }

    private int numeri_array[] = new int[90];
    private int numeri = 0;
    private JFrame jf = new JFrame("Giocata");
    private JPanel jp1 = new JPanel();
    private JPanel jp2 = new JPanel();
    private JLabel jl = new JLabel("Inserisci i numeri");
    private GridLayout gl = new GridLayout(3, 1);
    private GridLayout glP = new GridLayout(10, 1);
    private JButton jb = new JButton("Prosegui");
    private JList<Integer> jl1 = new JList<Integer>();

    public FinestraInserimentoNumeri(int numeri) {
        this.numeri = numeri;
    }

    private void setup() {
        for (int i = 0; i < 90; ++i) {
            numeri_array[i] = i + 1;
        }
        jl1.setSelectedIndices(numeri_array);
    }

    public void creaFinestra() {
        // panel
        setup();
        jp1.add(jl);
        jp2.add(jl1);
        jp2.setLayout(glP);

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

    /*
     * public void getNumeri(int numeriScelti[]) {
     * for (int i = 0; i < 10; ++i) {
     * numeriScelti[i] = V[i];
     * }
     * }
     */
}