package src.finestre;

import src.finestre.gestori.GestoreFinestra;

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
            } else if (s.equals("esci")) {
                System.exit(0);
            }
        }
    }

    private static JFrame jf = new JFrame();
    private GridLayout gl = new GridLayout(2, 1, 5, 5);
    private JPanel jp = new JPanel();
    private JButton jbEsci = new JButton("Esci");
    private JButton jbNuovaPartita = new JButton("Nuova Partita");
    private JLabel jlUsername;
    private boolean nuovaPartitaOn;
    private ConfigurazioneLotto cl = null;

    public Menu(String username) {
        jlUsername = new JLabel(username, JLabel.CENTER);

        jbEsci.setActionCommand("esci");
        jbNuovaPartita.setActionCommand("nuovapartita");
        jf.setLayout(new BorderLayout());

        jp.setLayout(gl);
        jp.add(jbNuovaPartita);
        jp.add(jbEsci);

        jf.add(jlUsername, BorderLayout.NORTH);
        jf.add(jp, BorderLayout.CENTER);

        // Imposta dimensione pulsanti
        jbNuovaPartita.setPreferredSize(new java.awt.Dimension(30, 30));
        jbEsci.setPreferredSize(new java.awt.Dimension(30, 30));

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
