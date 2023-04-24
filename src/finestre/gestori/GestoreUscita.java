package src.finestre.gestori;

import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestoreUscita implements ActionListener {
    private JFrame jf;

    public GestoreUscita(JFrame jf) {
        this.jf = jf;
    }

    public void actionPerformed(ActionEvent ae) {
        String s = ae.getActionCommand();
        if (s.equals("esci")) {
            jf.dispose();
        }
    }
}