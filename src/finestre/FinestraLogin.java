package src.finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.finestre.gestori.GestoreFinestra;

import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraLogin {

    /*
     * fatta una inner class, cosi non dobbiamo fare tante file di classi
     * per la gestione dei pulsanti, che secondo me e' meglio interna
     */

    private class GestorePulsante implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            username = jtf.getText();
            if (username.length() < 5) {
                JOptionPane.showMessageDialog(jf, "Lo username deve essere di almeno 5 caratteri", "Attenzione",
                        JOptionPane.WARNING_MESSAGE);
                jtf.setText("");
            } else {
                usernameSet = true;
                jf.dispose();
            }
        }
    }

    private boolean usernameSet = false;
    private String username = new String();
    private JFrame jf = new JFrame("Login");
    private JPanel jp = new JPanel();
    private JLabel jl = new JLabel("Username", JLabel.LEFT);
    private GridLayout gl = new GridLayout(2, 1);
    private JButton jb = new JButton("OK");
    private JTextField jtf = new JTextField(15);

    public FinestraLogin() {
        // panel
        jp.add(jl);
        jp.add(jtf);

        // frame
        jf.setLayout(gl); // setta il layout
        jf.add(jp); // aggiungi panel
        jf.add(jb); // aggiungi button

        // eventi
        jf.addWindowListener(new GestoreFinestra(jf)); // gestore finestra globale
        jb.addActionListener(new GestorePulsante()); // gestore button locale

        // robe finali
        jf.setSize(350, 200);
        jf.setVisible(true);
    }

    public String getUsername() {
        return username;
    }

    public boolean isUsernameSet() {
        return usernameSet;
    }
}