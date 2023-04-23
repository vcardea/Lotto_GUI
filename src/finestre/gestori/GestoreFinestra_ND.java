package src.finestre.gestori;

/**
 * ND = Non Destructive
 * GestoreFinestra_ND.java
 * Gestore finestra particolare:
 *  serve nelle classi: FinestraInserimentoNumeri, FinestraConfigurazioneNumeri
 *  nel caso si chiuda con la X, per aprire il menu
 */
import javax.swing.JFrame;

import src.finestre.FinestraMenu;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class GestoreFinestra_ND implements WindowListener {
    private JFrame jf = new JFrame();

    /**
     * Costruttore.
     * Prende come parametro il jframe della
     * finestra considerata
     * 
     * @param jf jframe della finestra
     */
    public GestoreFinestra_ND(JFrame jf) {
        this.jf = jf;
    }

    /**
     * Riduce a icona la finestra
     * 
     * @param we evento verificatosi
     */
    public void windowIconified(WindowEvent we) {
    }

    /**
     * Ripristina la finestra
     * 
     * @param we evento verificatosi
     */
    public void windowDeiconified(WindowEvent we) {
    }

    /**
     * Finestra attivata
     * 
     * @param we evento verificatosi
     */
    public void windowActivated(WindowEvent we) {
    }

    /**
     * Finestra disattivata
     * 
     * @param we evento verificatosi
     */
    public void windowDeactivated(WindowEvent we) {
    }

    /**
     * Finestra aperta
     * 
     * @param we evento verificatosi
     */
    public void windowOpened(WindowEvent we) {
    }

    /**
     * Finestra chiusa
     * 
     * @param we evento verificatosi
     */
    public void windowClosed(WindowEvent we) {
    }

    /**
     * Finestra in chiusura
     * 
     * @param we evento verificatosi
     */
    public void windowClosing(WindowEvent we) {
        FinestraMenu.apriFinestra();
        jf.dispose();
    }
}