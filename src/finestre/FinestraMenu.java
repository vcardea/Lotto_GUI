package src.finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.finestre.gestori.GestoreFinestra;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinestraMenu {
    protected class GestoreInterno implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            String s = ae.getActionCommand();

            if (s.equals("nuovapartita")) {
                fcl = new FinestraConfigurazioneLotto();
                jf.setVisible(false);
            } else if (s.equals("esci")) {
                System.exit(0);
            }
        }
    }

    private static JFrame jf = new JFrame();
    private GridLayout gl = new GridLayout(5, 1);
    // private GridLayout usernameGl = new GridLayout(1, 3);
    private GridLayout centralGl = new GridLayout(2, 1, 5, 5);
    private JPanel jp[] = new JPanel[5];
    private JButton jbEsci = new JButton("Esci");
    private JButton jbNuovaPartita = new JButton("Nuova Partita");
    private JLabel jlUsername;
    private boolean nuovaPartitaOn;
    private FinestraConfigurazioneLotto fcl = null;

    public FinestraMenu(String username) {
        jlUsername = new JLabel(username, JLabel.LEFT);

        jbEsci.setActionCommand("esci");
        jbNuovaPartita.setActionCommand("nuovapartita");
        jf.setLayout(gl);

        for (int i = 0; i < 5; i++)
            jp[i] = new JPanel();

        // jp[0].setLayout(usernameGl);
        jp[0].add(jlUsername);
        jp[2].setLayout(centralGl);
        jp[2].add(jbNuovaPartita);
        jp[2].add(jbEsci);

        for (int i = 0; i < 5; i++)
            jf.add(jp[i]);

        jbNuovaPartita.addActionListener(new GestoreInterno());
        jbEsci.addActionListener(new GestoreInterno());
        jf.addWindowListener(new GestoreFinestra(jf));

        jf.setLocation(300, 300);
        jf.setSize(400, 400);
        jf.setVisible(true);
    }

    public boolean isNuovaPartitaOn() {
        return nuovaPartitaOn;
    }

    public static void apriFinestra() {
        jf.setVisible(true);
    }
}
