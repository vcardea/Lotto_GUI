package src.finestre;

import src.finestre.gestori.GestoreFinestraFN;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    private class GestorePulsante implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            username = jtfUsername.getText().replaceAll(" ", "");
            if (!isUsernameValid(username)) {
                JOptionPane.showMessageDialog(jf,
                    "Lo username deve essere di almeno 5 caratteri [A-Za-z0-9_]",
                    "Attenzione",
                    JOptionPane.WARNING_MESSAGE);
                    jtfUsername.setText("");
            } else {
                // Apre la finestra del menu principale
                Menu m = new Menu(jtfUsername.getText());
                jf.dispose();
            }
        }
    }

    private final Color BLUE = new Color(3, 63, 94);
    private final Color WHITE = new Color(255, 255, 255);
    private final byte PANELS = 3;
    private String username = new String();
    private JFrame jf = new JFrame(UtilitiesFinestra.TITOLO);
    private Dimension d = new Dimension(UtilitiesFinestra.WIDTH, UtilitiesFinestra.HEIGHT);
    private JPanel[] jp = new JPanel[PANELS];
    private JLabel jlTitolo = new JLabel("FINESTRA DI LOGIN", JLabel.CENTER);
    private JLabel jlUsername = new JLabel("Username ", JLabel.LEFT);
    private JTextField jtfUsername = new JTextField(15);
    private JButton jbLogin = new JButton("Login");
    private Font fTitolo = new Font("Sans-Serif", Font.BOLD, 28);
    private Font fJlUsername = new Font("Sans-Serif", Font.BOLD, 18);
    private Font fJtfUsername = new Font("Sans-Serif", Font.PLAIN, 18);
    private Font fLogin = new Font("Sans-Serif", Font.BOLD, 20);

    public Login() {
        stiliEColori();
        pannelli();
        componenti();
        dettagliJFrame();
    }

    private void stiliEColori() {
        // Etichetta del titolo
        jlTitolo.setForeground(Color.WHITE);
        jlTitolo.setFont(fTitolo);

        // Etichetta del nome utente
        jlUsername.setForeground(Color.WHITE);
        jlUsername.setFont(fJlUsername);

        // Casella di testo del nome utente
        jtfUsername.setFont(fJtfUsername);
        jtfUsername.setForeground(BLUE);
        jtfUsername.setBackground(WHITE);
        
        // Pulsante per il login
        jbLogin.setForeground(new Color(3, 63, 94));
        jbLogin.setBackground(WHITE);
        jbLogin.setPreferredSize(new Dimension(300, 105));
        jbLogin.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BLUE, 30),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        jbLogin.setFont(fLogin);
    }

    private void pannelli() {
        // Istanziazione
        for (int i = 0; i < PANELS; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(BLUE);
        }

        // Layout
        jp[1].setLayout(new GridBagLayout());

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

    private void dettagliJFrame() {
        // Ascoltatore
        jf.addWindowListener(new GestoreFinestraFN(jf));
        
        // Layout
        jf.setLayout(new BorderLayout());
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);
        jf.add(jp[2], BorderLayout.SOUTH);

        // Posizionamento, dimensione, visibilità finestra
        jf.setLocation(UtilitiesFinestra.LOCATIONX, UtilitiesFinestra.LOCATIONY);
        jf.setMinimumSize(d);
        jf.setSize(d);
        jf.setVisible(true);

        // Icona
        jf.setIconImage(UtilitiesFinestra.icon.getImage());
        
        // Contenitore
        jf.getContentPane();
    }

    public String getUsername() {
        return username;
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