package finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class FinestraLogin {

    /**
     * @author Cestello
     * @author DajeCardea
     */

    /*
     * fatta una inner class, cosi non dobbiamo fare tante file di classi
     * per la gestione dei pulsanti, che secondo me e' meglio interna
     */

    protected class GestorePulsante implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            username = jtf.getText();
            if (username.length() < 5) {
                JOptionPane.showMessageDialog(jf, "Lo username deve essere di almeno 5 caratteri", "Attenzione",
                        JOptionPane.WARNING_MESSAGE);
            } else { // TODO
                // SI COLLEGA ALL'ALTRA FINESTRA
                System.exit(0);
            }
        }
    }

    static String username = new String("");
    static JFrame jf = new JFrame("Login");
    static JPanel jp = new JPanel();
    static JLabel jl = new JLabel("username", JLabel.LEFT);
    static GridLayout gl = new GridLayout(2, 1);
    static JButton jb = new JButton("OK");
    static JTextField jtf = new JTextField(15);

    public static void main(String args[]) {
        GestorePulsante gp = new FinestraLogin().new GestorePulsante(); // si lo so e' brutto ma utile
        GestoreFinestra gf = new GestoreFinestra();

        // panel
        jp.add(jl);
        jp.add(jtf);

        // frame
        jf.setLayout(gl); // setta il layout
        jf.add(jp); // aggiungi panel
        jf.add(jb); // aggiungi button

        // eventi
        jf.addWindowListener(gf); // gestore finestra globale
        jb.addActionListener(gp); // gestore button locale

        // robe finali
        jf.setSize(350, 200);
        jf.setVisible(true);
    }

}