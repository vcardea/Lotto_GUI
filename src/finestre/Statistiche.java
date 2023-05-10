package src.finestre;

import src.log.Log;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.IOException;

/**
 * Classe per la visualizzazioe dei log
 * 
 * @author Vincenzo Cardea
 * @author Francesco Rizzello
 * @author Matteo De Vito
 * @author Alessandro Serio
 * 
 * @since 1.0.0
 */
public class Statistiche {
    
    /**
     * Apre il blocco note di Windows per mostrare i dati dell'utente
     * 
     * @param jf jframe della finestra finale
     */
    public static void mostraStatistiche(JFrame jf) {
        try {
            Process process = Runtime.getRuntime().exec("notepad " + Log.DATI);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(jf, "Errore durante l'apertura del file",
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}