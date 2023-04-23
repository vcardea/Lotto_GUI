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
            username = jtf.getText().replaceAll(" ", "");
            if (!isUsernameValid(username)) {
                JOptionPane.showMessageDialog(jf,
                    "Lo username deve essere di almeno 5 caratteri [A-Za-z0-9_]",
                    "Attenzione",
                    JOptionPane.WARNING_MESSAGE);
                jtf.setText("");
            } else {
                usernameSet = true;
                jf.dispose();
            }
        }
    }

    private final byte PANELS = 3;
    private boolean usernameSet = false;
    private String username = new String();
    private JFrame jf = new JFrame("Login");
    private JPanel[] jp = new JPanel[PANELS];
    private JLabel jlTitolo = new JLabel("INSERISCI IL NOME UTENTE", JLabel.CENTER);
    private JLabel jlUsername = new JLabel("Username", JLabel.CENTER);
    private JButton jb = new JButton("OK");
    private JTextField jtf = new JTextField(15);

    public FinestraLogin() {
        // Componenti
        for (int i = 0; i < PANELS; i++) {
            jp[i] = new JPanel();
        }

        jp[0].add(jlTitolo);
        jp[1].add(jlUsername);
        jp[1].add(jtf);
        jp[2].add(jb);
        
        jb.addActionListener(new GestorePulsante());

        // Frame
        jf.setLayout(new BorderLayout());
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);
        jf.add(jp[2], BorderLayout.SOUTH);
        jf.addWindowListener(new GestoreFinestra(jf));
        jf.setSize(400, 400);
        jf.setVisible(true);
        jf.getContentPane();
    }

    public String getUsername() {
        return username;
    }

    public boolean isUsernameSet() {
        return usernameSet;
    }

    private boolean isUsernameValid(String username) {
        if (username.length() < 5) {
            return false;
        }
        
        String appoggio = username.toLowerCase();
        for (int i = 0; i < username.length(); i++) {
            if (!((appoggio.charAt(i) >= 'a' && appoggio.charAt(i) <= 'z') || (appoggio.charAt(i) >= '0' && appoggio.charAt(i) <= '9') || (appoggio.charAt(i) >= '_'))) {
                return false;
            }
        }
        
        return true;
    }
}