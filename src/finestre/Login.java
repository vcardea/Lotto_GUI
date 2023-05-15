package src.finestre;

import src.finestre.gestori.GestoreFinestraFN;
import src.utente.Utente;
import src.finestre.gestori.GestoreMouse;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe per la gestione del login
 * 
 * @author Vincenzo Cardea
 * @author Francesco Rizzello
 * @author Matteo De Vito
 * @author Alessandro Serio
 * 
 * @see UtilFinestra
 * @see Menu
 * 
 * @since 1.0.0
 */
public class Login {

    /**
     * Inner class per la gestione del pulsante login
     */
    private class GestorePulsante implements ActionListener {
        /**
         * Gestisce la pressione del tasto di login
         * 
         * @param e evento generato sul componente
         */
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

    private JFrame jf = new JFrame(UtilFinestra.TITOLO);
    private JPanel[] jp = new JPanel[UtilFinestra.PANNELLI];
    private JLabel jlTitolo = new JLabel("FINESTRA DI LOGIN", JLabel.CENTER);
    private JLabel jlUsername = new JLabel("Username ", JLabel.LEFT);
    private JTextField jtfUsername = new JTextField(15);
    private JButton jbLogin = new JButton("Login");

    /**
     * Costruttore
     */
    public Login() {
        stiliEColori();
        pannelli();
        componenti();
        frame();
    }

    /**
     * Sistema lo stile ed i colori della finestra
     */
    private void stiliEColori() {
        // Etichetta del titolo
        jlTitolo.setForeground(UtilFinestra.GRIGIO);
        jlTitolo.setFont(UtilFinestra.FTITOLO);

        // Etichetta del nome utente
        jlUsername.setForeground(UtilFinestra.GRIGIO);
        jlUsername.setFont(UtilFinestra.FETICHETTA);

        // Casella di testo del nome utente
        jtfUsername.setFont(UtilFinestra.FTESTO);
        jtfUsername.setForeground(UtilFinestra.BLU);
        jtfUsername.setBackground(UtilFinestra.GRIGIO);
        
        // Pulsante per il login
        jbLogin.setForeground(UtilFinestra.BLU);
        jbLogin.setBackground(UtilFinestra.GRIGIO);
        jbLogin.setPreferredSize(UtilFinestra.DPULSANTE);
        jbLogin.setFont(UtilFinestra.FPULSANTE);
    }

    /**
     * Crea e sistema i pannelli da utilizzare
     */
    private void pannelli() {
        // Istanziazione
        for (int i = 0; i < UtilFinestra.PANNELLI; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(UtilFinestra.BLU);
        }

        // Layout
        jp[1].setLayout(UtilFinestra.GRIDBAGLAYOUT);
        jp[2].setLayout(new GridBagLayout());

        // Composizione
        jp[0].add(jlTitolo);
        jp[1].add(jlUsername);
        jp[1].add(jtfUsername);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = UtilFinestra.DISTBASSO;
        jp[2].add(jbLogin, gbc);
    }

    /**
     * Crea e sistema i componenti
     */
    private void componenti() {
        // Pulsante di login
        jbLogin.addActionListener(new GestorePulsante());
        jbLogin.addMouseListener(new GestoreMouse(jbLogin));
    }

    /**
     * Crea ed apre il frame
     */
    private void frame() {
        // Ascoltatore
        jf.addWindowListener(new GestoreFinestraFN(jf));
        
        // Layout
        jf.setLayout(UtilFinestra.BORDERLAYOUT);
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);
        jf.add(jp[2], BorderLayout.SOUTH);

        // Posizionamento, dimensione, visibilità finestra
        jf.setLocation(UtilFinestra.POSX, UtilFinestra.POSY);
        jf.setSize(UtilFinestra.DIMENSIONE);
        jf.setResizable(false);
        jf.setVisible(true);

        // Icona
        jf.setIconImage(UtilFinestra.ICONA.getImage());
        
        // Contenitore
        jf.getContentPane();
    }

    /**
     * Stabilisce se il nome utente è valido
     * 
     * @param username dell'utente
     * @return         se il nome utente è valido
     */
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