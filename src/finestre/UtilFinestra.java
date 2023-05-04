package src.finestre;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

/**
 * Classe con diverse costanti utili per le finestre
 */
public class UtilFinestra {
    protected static final BorderLayout LAYOUT = new BorderLayout();
    protected static final GridBagLayout LAYOUTCENTRO = new GridBagLayout();
    
    protected static final String TITOLO = "Gioco del 10eLotto";
    
    protected static final ImageIcon ICONA = new ImageIcon("src/img/logo.png");
    protected static final String INFOFILE = "src/finestre/info/info.txt";
    
    protected static final byte PANNELLI = 3;
    
    protected static final Dimension RISOLUZIONE = Toolkit.getDefaultToolkit().getScreenSize();
    protected static final int LARGHEZZA = 700;
    protected static final int ALTEZZA = 600;
    protected static final int POSX = (int)(RISOLUZIONE.getWidth() / 2) - (LARGHEZZA / 2);
    protected static final int POSY = (int)(RISOLUZIONE.getHeight() / 2) - (ALTEZZA / 2);
    protected static final Dimension DIMENSIONE = new Dimension(UtilFinestra.LARGHEZZA, UtilFinestra.ALTEZZA);
    
    protected static final Color BLU = new Color(3, 63, 94);
    protected static final Color GRIGIO = new Color(230, 230, 230);
    protected static final Color BIANCO = new Color(255, 255, 255);
    protected static final Color VERDECHIARO = new Color(0, 255, 0);
    protected static final Color VERDE = new Color(0, 220, 0);
    
    protected static final Font FTITOLO = new Font("Sans-Serif", Font.BOLD, 28);
    protected static final Font FETICHETTA = new Font("Sans-Serif", Font.BOLD, 18);
    protected static final Font FTESTO = new Font("Sans-Serif", Font.PLAIN, 18);
    protected static final Font FPULSANTE = new Font("Sans-Serif", Font.BOLD, 20);
   
    protected static final Dimension DPULSANTE = new Dimension(210, 100);
    protected static final Border BPULSANTE = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(UtilFinestra.BLU, 20),
        BorderFactory.createEmptyBorder(0, 0, 0, 0)
    );
} 