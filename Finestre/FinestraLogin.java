package Finestre;

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

    /*
     * fatta una inner class, cosi non dobbiamo fare tante file di classi
     * la gestione dei pulsanti la facciamo interna
     */

    protected class GestorePulsante implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = jtf.getText();
            if (username.length() < 5) {
                JOptionPane.showMessageDialog(jf, "Lo username deve essere di almeno 5 caratteri", "Attenzione",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                // CHIAMA ALTRA FINESTRA
            }
        }
    }

    static JFrame jf = new JFrame("Login");
    static JPanel jp = new JPanel();
    static JLabel jl = new JLabel("username", JLabel.LEFT);
    static GridLayout gl = new GridLayout(2, 1);
    static JButton jb = new JButton("OK");
    static JTextField jtf = new JTextField(15);

    public static void main(String args[]) {
        GestorePulsante gp = new FinestraLogin().new GestorePulsante(); // si lo so e' brutto ma utile
        GestoreFinestra g = new GestoreFinestra();

        // panel
        jp.add(jl);
        jp.add(jtf);

        // frame
        jf.setLayout(gl); // setta il layout
        jf.add(jp); // aggiungi panel
        jf.add(jb); // aggiungi button

        // eventi
        jf.addWindowListener(g); // gestore finestra globale
        jb.addActionListener(gp); // gestore button locale

        // robe finali
        jf.setSize(350, 200);
        jf.setVisible(true);
    }

}