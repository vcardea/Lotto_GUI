package src.finestre;

import src.log.FileInput;
import src.finestre.gestori.GestoreFinestraND;
import src.finestre.gestori.GInfo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

public class Info {
    
    private JFrame jf = new JFrame(UtilitiesFinestra.TITOLO);
    private JPanel[] jp = new JPanel[UtilitiesFinestra.PANELS];
    private JLabel jlTitolo = new JLabel("INFORMAZIONI");
    private JTextArea jta = new JTextArea();
    private JButton jbChiudi = new JButton("Chiudi");

    public Info() {
        testo();
        stiliEColori();
        pannelli();
        componenti();
        frame();
    }

    private void testo() {
        FileInput fi = new FileInput("src/");
        
    }

    private void stiliEColori() {
        // Etichetta del titolo
        jlTitolo.setForeground(UtilitiesFinestra.GREY);
        jlTitolo.setFont(UtilitiesFinestra.FTITLE);

        // Area di testo
        jta.setForeground(UtilitiesFinestra.BLUE);
        jta.setFont(UtilitiesFinestra.FTEXT);

        // Pulsante di chiusura
        jbChiudi.setForeground(UtilitiesFinestra.BLUE);
        jbChiudi.setBackground(UtilitiesFinestra.GREY);
        jbChiudi.setPreferredSize(UtilitiesFinestra.BUTTON_DIMENSION);
        jbChiudi.setBorder(UtilitiesFinestra.BORDER);
        jbChiudi.setFont(UtilitiesFinestra.FBUTTON);
    }

    private void pannelli() {
        // Istanziazione
        for (int i = 0; i < UtilitiesFinestra.PANELS; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(UtilitiesFinestra.BLUE);
        }

        // Layout
        jp[1].setLayout(new GridBagLayout());

        // Composizione
        jp[0].add(jlTitolo);
        jp[1].add(jta);
        jp[2].add(jbChiudi);
    }

    private void componenti() {
        // Area di testo
        jta.setEditable(false);

        // Gestione pulsante chiusura
        jbChiudi.setActionCommand("chiudi");
        jbChiudi.addActionListener(new GInfo(jf));
    }

    private void frame() {
        // Layout
        jf.addWindowListener(new GestoreFinestraND(jf));
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
}