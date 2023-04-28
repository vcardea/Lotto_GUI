package src.finestre;

import src.finestre.gestori.GestoreFinestra;
import src.finestre.gestori.GestoreUscita;
import src.utente.Utente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu {
    protected class GestoreInterno implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            String s = ae.getActionCommand();

            if (s.equals("nuovapartita")) {
                cl = new ConfigurazioneLotto();
                jf.setVisible(false);
            }
        }
    }

    private static JFrame jf = new JFrame("Gioco del 10eLotto");
    public static String username = new String();
    private GridLayout gl = new GridLayout(2, 1, 5, 5);
    private JPanel jp = new JPanel();
    private JButton jbEsci = new JButton("Esci");
    private JButton jbNuovaPartita = new JButton("Nuova Partita");
    public JLabel jlUsername;
    private ConfigurazioneLotto cl = null;

    public Menu(String username) {
        Utente.username = username;
        jlUsername = new JLabel(username, JLabel.CENTER);

        jf.setLayout(new BorderLayout());

        jp.setLayout(gl);
        jp.add(jbNuovaPartita);
        jp.add(jbEsci);

        jbEsci.addActionListener(new GestoreUscita(jf));
        jbEsci.setActionCommand("esci");
        jbNuovaPartita.addActionListener(new GestoreInterno());
        jbNuovaPartita.setActionCommand("nuovapartita");
        
        jf.addWindowListener(new GestoreFinestra(jf));
        jf.add(jlUsername, BorderLayout.NORTH);
        jf.add(jp, BorderLayout.CENTER);
        jf.setLocation(UtilitiesFinestra.LOCATIONX, UtilitiesFinestra.LOCATIONY);
        jf.setSize(UtilitiesFinestra.WIDTH, UtilitiesFinestra.HEIGHT);
        jf.setVisible(true);
        jf.setIconImage(UtilitiesFinestra.icon.getImage());
        jf.getContentPane();
    }

    public static void apriFinestra() {
        jf.setVisible(true);
    }
}
