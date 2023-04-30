package src.finestre;

import src.finestre.gestori.GestoreFinestra;
import src.finestre.gestori.GestoreUscita;
import src.utente.Utente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
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
            } else if (s.equals("info")) {
                Info i = new Info();
                jf.setVisible(false);
            }
        }
    }

    private static JFrame jf = new JFrame(UtilitiesFinestra.TITOLO);
    private JPanel[] jp = new JPanel[UtilitiesFinestra.PANELS];
    private GridLayout glNorth = new GridLayout(2, 1, 10, 0);
    private JLabel jlTitolo = new JLabel("MENU PRINCIPALE", JLabel.CENTER);
    private JLabel jlUsername = new JLabel(Utente.username, JLabel.CENTER);
    private JButton jbNuovaPartita = new JButton("Nuova Partita");
    private JButton jbInfo = new JButton("Informazioni");
    private JButton jbEsci = new JButton("Esci");
    private ConfigurazioneLotto cl = null;

    public Menu() {
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

        // Pulsante nuova partita
        jbNuovaPartita.setForeground(UtilitiesFinestra.BLUE);
        jbNuovaPartita.setBackground(UtilitiesFinestra.GREY);
        jbNuovaPartita.setPreferredSize(new Dimension(200, 50));
        jbNuovaPartita.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UtilitiesFinestra.BLUE, 0),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        jbNuovaPartita.setFont(UtilitiesFinestra.FBUTTON);

        // Pulsante informazioni
        jbInfo.setForeground(UtilitiesFinestra.BLUE);
        jbInfo.setBackground(UtilitiesFinestra.GREY);
        jbInfo.setPreferredSize(new Dimension(200, 50));
        jbInfo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UtilitiesFinestra.BLUE, 0),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        jbInfo.setFont(UtilitiesFinestra.FBUTTON);

        // Pulsante esci
        jbEsci.setForeground(UtilitiesFinestra.BLUE);
        jbEsci.setBackground(UtilitiesFinestra.GREY);
        jbEsci.setPreferredSize(UtilitiesFinestra.BUTTON_DIMENSION);
        jbEsci.setBorder(UtilitiesFinestra.BORDER);
        jbEsci.setFont(UtilitiesFinestra.FBUTTON);
    }

    private void pannelli() {
        // Istanziazione
        for (int i = 0; i < UtilitiesFinestra.PANELS; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(UtilitiesFinestra.BLUE);
        }

        // Layout
        jp[0].setLayout(glNorth);
        jp[1].setLayout(new GridBagLayout());

        // Composizione
        jp[0].add(jlTitolo);
        jp[0].add(jlUsername);
        jp[1].add(jbNuovaPartita);
        jp[1].add(jbInfo);
        jp[2].add(jbEsci);
    }

    private void componenti() {
        // Gestione pulsante nuova partita
        jbNuovaPartita.setActionCommand("nuovapartita");
        jbNuovaPartita.addActionListener(new GestoreInterno());

        // Gestione pulsante informazioni
        jbInfo.setActionCommand("info");
        jbInfo.addActionListener(new GestoreInterno());

        // Gestione pulsante di uscita
        jbEsci.setActionCommand("esci");
        jbEsci.addActionListener(new GestoreUscita(jf));
    }

    private void frame() {
        // Layout
        jf.addWindowListener(new GestoreFinestra(jf));
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);
        jf.add(jp[2], BorderLayout.SOUTH);

        // Posizionamento, dimensione e visibilità finestra
        jf.setLocation(UtilitiesFinestra.LOCATIONX, UtilitiesFinestra.LOCATIONY);
        jf.setMinimumSize(UtilitiesFinestra.DIMENSION);
        jf.setSize(UtilitiesFinestra.DIMENSION);
        jf.setVisible(true);

        // Icona
        jf.setIconImage(UtilitiesFinestra.icon.getImage());

        // Contenitore
        jf.getContentPane();
    }

    public static void apriFinestra() {
        jf.setVisible(true);
    }
}
