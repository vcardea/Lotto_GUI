package src.finestre;

import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Toolkit;

public class UtilitiesFinestra {
    protected static final Dimension RESOLUTION = Toolkit.getDefaultToolkit().getScreenSize();
    protected static final int WIDTH = 600;
    protected static final int HEIGHT = 600;
    protected static final int LOCATIONX = ((int)RESOLUTION.getWidth() / 2) - (WIDTH / 2);
    protected static final int LOCATIONY = ((int)RESOLUTION.getHeight() / 2) - (HEIGHT / 2);
    protected static final ImageIcon icon = new ImageIcon("src/img/logo.png");
    protected static final String TITOLO = "10eLotto"; 
} 