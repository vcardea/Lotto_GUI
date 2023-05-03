package src.finestre;

import src.finestre.gestori.GestoreFinestraFN;
import src.utente.Utente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    private class GestorePulsante implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = jtfUsername.getText().replaceAll(" ", "");
            if (!isUsernameValid(username)) {
                JOptionPane.showMessageDialog(jf,
                    "Lo username deve essere di almeno 5 caratteri [A-Za-z0-9_]",
                    "Attenzione",
                    JOptionPane.WARNING_MESSAGE);
                    jtfUsername.setText("");
            } else {
                // Imposta il nome utente
                Utente.username = username;
                
                // Apre la finestra del menu principale
                Menu m = new Menu();

                jf.dispose();
            }
        }
    }

    private final Border BORDER = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(UtilitiesFinestra.BLUE, 30),
        BorderFactory.createEmptyBorder(0, 0, 0, 0)
    );
    private final Dimension DBUTTON = new Dimension(300, 105);
    private JFrame jf = new JFrame(UtilitiesFinestra.TITOLO);
    private JPanel[] jp = new JPanel[UtilitiesFinestra.PANELS];
    private JLabel jlTitolo = new JLabel("FINESTRA DI LOGIN", JLabel.CENTER);
    private JLabel jlUsername = new JLabel("Username ", JLabel.LEFT);
    private JTextField jtfUsername = new JTextField(15);
    private JButton jbLogin = new JButton("Login");

    public Login() {
        stiliEColori();
        pannelli();
        componenti();
        frame();
    }

    private void stiliEColori() {
        // Etichetta del titolo
        jlTitolo.setForeground(UtilitiesFinestra.GREY);
        jlTitolo.setFont(UtilitiesFinestra.FTITLE);

        // Etichetta del nome utente
        jlUsername.setForeground(UtilitiesFinestra.GREY);
        jlUsername.setFont(UtilitiesFinestra.FLABEL);

        // Casella di testo del nome utente
        jtfUsername.setFont(UtilitiesFinestra.FTEXT);
        jtfUsername.setForeground(UtilitiesFinestra.BLUE);
        jtfUsername.setBackground(UtilitiesFinestra.GREY);
        
        // Pulsante per il login
        jbLogin.setForeground(UtilitiesFinestra.BLUE);
        jbLogin.setBackground(UtilitiesFinestra.GREY);
        jbLogin.setPreferredSize(DBUTTON);
        jbLogin.setBorder(BORDER);
        jbLogin.setFont(UtilitiesFinestra.FBUTTON);
    }

    private void pannelli() {
        // Istanziazione
        for (int i = 0; i < UtilitiesFinestra.PANELS; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(UtilitiesFinestra.BLUE);
        }

        // Layout
        jp[1].setLayout(UtilitiesFinestra.LAYOUTCENTER);

        // Composizione
        jp[0].add(jlTitolo);
        jp[1].add(jlUsername);
        jp[1].add(jtfUsername);
        jp[2].add(jbLogin);
    }

    private void componenti() {
        // Pulsante di login
        jbLogin.addActionListener(new GestorePulsante());
    }

    private void frame() {
        // Ascoltatore
        jf.addWindowListener(new GestoreFinestraFN(jf));
        
        // Layout
        jf.setLayout(UtilitiesFinestra.LAYOUT);
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);
        jf.add(jp[2], BorderLayout.SOUTH);

        // Posizionamento, dimensione, visibilità finestra
        jf.setLocation(UtilitiesFinestra.LOCATIONX, UtilitiesFinestra.LOCATIONY);
        jf.setSize(UtilitiesFinestra.DIMENSION);
        jf.setResizable(false);
        jf.setVisible(true);

        // Icona
        jf.setIconImage(UtilitiesFinestra.ICON.getImage());
        
        // Contenitore
        jf.getContentPane();
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