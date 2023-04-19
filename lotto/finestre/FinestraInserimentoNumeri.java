package lotto.finestre;

import lotto.finestre.gestori.GestoreFinestra;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraInserimentoNumeri {

    private class GestorePulsante implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            jf.dispose();
        }
    }

    private int[] numeri_totali = new int[10];
    private int numeri = 0;
    private JFrame jf = new JFrame("Giocata");
    private JPanel jp1 = new JPanel();
    private JPanel jp2 = new JPanel();
    private JLabel jl = new JLabel("Inserisci i numeri");
    private GridLayout gl = new GridLayout(3, 1);
    private GridLayout glP = new GridLayout(10, 1);
    private JButton jb = new JButton("Prosegui");
    private JList lista;

    private static void setup() {

    }

    public FinestraInserimentoNumeri(int n) {
        numeri = n;
    }

    public void creaFinestra() {
        // panel
        jp1.add(jl);
        jp2.setLayout(glP);
        setup();

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

}
