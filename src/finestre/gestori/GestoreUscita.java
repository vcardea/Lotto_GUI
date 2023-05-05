package src.finestre.gestori;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe per la gestione dell'uscita dalla finestra del menu
 * e chiusura del programma
 * 
 * @author Vincenzo Cardea
 * @author Francesco Rizzello
 * @author Matteo De Vito
 * @author Alessandro Serio
 */
public class GestoreUscita implements ActionListener {
    /**
     * Gestisce il pulsante esci
     * 
    * @param e evento
    */
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("esci")) {
            System.exit(0);
        }
    }
}