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

public class UtilitiesFinestra {
    protected static final BorderLayout LAYOUT = new BorderLayout();
    protected static final GridBagLayout LAYOUTCENTER = new GridBagLayout();
    
    protected static final String TITOLO = "Gioco del 10eLotto";
    
    protected static final ImageIcon ICON = new ImageIcon("src/img/logo.png");
    protected static final String INFOFILE = "src/finestre/info/info.txt";
    
    protected static final byte PANNELLI = 3;
    
    protected static final Dimension RESOLUTION = Toolkit.getDefaultToolkit().getScreenSize();
    protected static final int WIDTH = 700;
    protected static final int HEIGHT = 600;
    protected static final int LOCATIONX = (int)(RESOLUTION.getWidth() / 2) - (WIDTH / 2);
    protected static final int LOCATIONY = (int)(RESOLUTION.getHeight() / 2) - (HEIGHT / 2);
    protected static final Dimension DIMENSION = new Dimension(UtilitiesFinestra.WIDTH, UtilitiesFinestra.HEIGHT);
    
    protected static final Color BLUE = new Color(3, 63, 94);
    protected static final Color GREY = new Color(230, 230, 230);
    protected static final Color WHITE = new Color(255, 255, 255);
    protected static final Color LIGHTGREEN = new Color(0, 255, 0);
    protected static final Color GREEN = new Color(0, 220, 0);
    
    protected static final Font FTITLE = new Font("Sans-Serif", Font.BOLD, 28);
    protected static final Font FLABEL = new Font("Sans-Serif", Font.BOLD, 18);
    protected static final Font FTEXT = new Font("Sans-Serif", Font.PLAIN, 18);
    protected static final Font FBUTTON = new Font("Sans-Serif", Font.BOLD, 20);
   
    protected static final Dimension DBUTTON = new Dimension(210, 90);
    
    protected static final Border BORDER = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(UtilitiesFinestra.BLUE, 20),
        BorderFactory.createEmptyBorder(0, 0, 0, 0)
    );
} 