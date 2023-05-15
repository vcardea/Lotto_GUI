package src.finestre;

import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Classe con costanti utili per le finestre
 * 
 * @author Vincenzo Cardea
 * @author Francesco Rizzello
 * @author Matteo De Vito
 * @author Alessandro Serio
 * 
 * @see Login
 * @see Menu
 * @see Info
 * @see DettagliPartita
 * @see InserimentoNumeri
 * @see Finale
 * @see Statistiche
 * 
 * @since 1.0.0
 */
public class UtilFinestra {

    // Layout
    public static final BorderLayout BORDERLAYOUT = new BorderLayout();
    public static final GridBagLayout GRIDBAGLAYOUT = new GridBagLayout();
    
    // Titolo
    public static final String TITOLO = "Gioco del 10eLotto";
    
    // Percorsi di file utili
    public static final ImageIcon ICONA = new ImageIcon("img/logo.png");
    public static final String INFOFILE = "info/info.txt";
    
    // Costanti numeriche
    public static final byte NUMERI = 90;
    public static final byte ESTRAZIONI = 20;
    public static final byte PANNELLI = 3;
    
    // Risoluzione/Posizionamenti
    public static final Dimension RISOLUZIONE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int LARGHEZZA = 700;
    public static final int ALTEZZA = 600;
    public static final int POSX = (int)(RISOLUZIONE.getWidth() / 2) - (LARGHEZZA / 2);
    public static final int POSY = (int)(RISOLUZIONE.getHeight() / 2) - (ALTEZZA / 2);
    public static final Dimension DIMENSIONE = new Dimension(UtilFinestra.LARGHEZZA, UtilFinestra.ALTEZZA);
    public static final Insets DISTBASSO = new Insets(0, 0, 20, 0);

    // Colori
    public static final Color BLU = new Color(3, 63, 94);
    public static final Color BLUCHIARO = new Color(180, 180, 180);
    public static final Color GRIGIO = new Color(230, 230, 230);
    public static final Color BIANCO = new Color(230, 230, 230);
    public static final Color VERDECHIARO = new Color(0, 255, 0);
    public static final Color VERDE = new Color(0, 220, 0);
    
    // Font
    public static final Font FTITOLO = new Font("Sans-Serif", Font.BOLD, 28);
    public static final Font FETICHETTA = new Font("Sans-Serif", Font.BOLD, 18);
    public static final Font FTESTO = new Font("Sans-Serif", Font.PLAIN, 18);
    public static final Font FPULSANTE = new Font("Sans-Serif", Font.BOLD, 20);

    // Dimensioni
    public static final Dimension DPULSANTE = new Dimension(270, 50);
} 