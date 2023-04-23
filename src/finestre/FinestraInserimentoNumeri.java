package src.finestre;

import src.finestre.gestori.GestoreFinestra;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FinestraInserimentoNumeri {

    private class GestorePulsante implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Prosegui")) {
                jf.dispose();
            }
        }
    }

    private class GestoreLista implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {

            }
        }
    }

    private Integer numeri_array[] = new Integer[90];
    private int numeri = 0;
    private JFrame jf = new JFrame("Giocata");
    private JPanel jp1 = new JPanel();
    private JPanel jp2 = new JPanel();
    private JLabel jl = new JLabel("Inserisci i numeri");
    private GridLayout gl = new GridLayout(3, 1);
    private GridLayout glP = new GridLayout(30, 5);
    private JButton jb = new JButton("Prosegui");

    private JList<Integer> jl1;

    public FinestraInserimentoNumeri(int numeri) {
        this.numeri = numeri;
    }

    private void setup() { // setta la lista
        for (int i = 0; i < 90; ++i) {
            numeri_array[i] = i + 1;
        }
        jl1 = new JList<Integer>(numeri_array);
        jl1.addListSelectionListener(new GestoreLista());
    }

    public void creaFinestra() {
        // panel
        setup();
        jp1.add(jl); // etichetta
        jp2.add(jl1); // lista
        jp2.setLayout(glP); // layout

        // frame
        jf.setLayout(gl); // setta il layout
        jf.add(jp1); // aggiungi panel
        jf.add(jp2); // aggiungi lista
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