package src.finestre;

import src.finestre.gestori.GestoreFinestra;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraLogin {

    private class GestorePulsante implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            username = jtf.getText();
            if (username.length() < 5) {
                JOptionPane.showMessageDialog(jf,
                        "Lo username deve essere di almeno 5 caratteri",
                        "Attenzione",
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
    private JLabel jlTitolo = new JLabel("INSERISCI IL NOME UTENTE", JLabel.CENTER);
    private JLabel jlUsername = new JLabel("Username", JLabel.CENTER);
    private JButton jb = new JButton("OK");
    private JTextField jtf = new JTextField(15);

    public FinestraLogin() {
        // panel
        jp.setLayout(new BorderLayout());
        jp.add(jlTitolo, BorderLayout.NORTH);
        jp.add(jlUsername, BorderLayout.CENTER);
        jp.add(jtf, BorderLayout.CENTER);
        jp.add(jb, BorderLayout.SOUTH);

        // frame
        jf.add(jp);

        // eventi
        jf.addWindowListener(new GestoreFinestra(jf));
        jb.addActionListener(new GestorePulsante());

        jf.setSize(400, 400);
        jf.setVisible(true);
    }

    public String getUsername() {
        return username;
    }

    public boolean isUsernameSet() {
        return usernameSet;
    }
}