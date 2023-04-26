package src.finestre;

import src.finestre.gestori.GestoreFinestra;
import src.finestre.gestori.GestoreUscita;

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

        jbNuovaPartita.addActionListener(new GestoreInterno());
        jbEsci.addActionListener(new GestoreUscita(jf));
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
