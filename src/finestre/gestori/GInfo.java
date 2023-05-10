package src.finestre.gestori;

import src.finestre.Menu;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe per la gestione della chiusura della
 * finestra delle informazioni
 * 
 * @author Vincenzo Cardea
 * @author Francesco Rizzello
 * @author Matteo De Vito
 * @author Alessandro Serio
 * 
 * @since 1.0.0
 */
public class GInfo implements ActionListener {
    private JFrame jf;

    /**
     * Costruttore
     * 
     * @param jf jframe della finestra da chiudere
     */
    public GInfo(JFrame jf) {
        this.jf = jf;
    }

    /**
     * Gestisce il pulsante chiudi
     * 
     * @param e evento
    */
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("chiudi")) {
            Menu.apriFinestra();
            jf.dispose();
        }
    }
}
