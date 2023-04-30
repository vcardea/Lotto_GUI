package src.finestre.gestori;

import src.finestre.Menu;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GInfo implements ActionListener {
    private JFrame jf;

    public GInfo(JFrame jf) {
        this.jf = jf;
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("chiudi")) {
            Menu.apriFinestra();
            jf.dispose();
        }
    }
}
