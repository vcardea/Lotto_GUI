package src.finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Info {
    
    private JFrame jf = new JFrame(UtilitiesFinestra.TITOLO);
    private JPanel[] jp = new JPanel[UtilitiesFinestra.PANELS];
    private JLabel jlTitolo = new JLabel("INFORMAZIONI");
    private JTextArea jta = new JTextArea();
}