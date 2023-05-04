package src.finestre.gestori;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestoreUscita implements ActionListener {
    /**
    * @param ae evento che gestisce l'uscita tramite button
    */
    public void actionPerformed(ActionEvent ae) {
        String s = ae.getActionCommand();
        if (s.equals("esci")) {
            System.exit(0);
        }
    }
}