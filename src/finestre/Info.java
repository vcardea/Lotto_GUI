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
import java.awt.Dimension;
import java.awt.GridBagLayout;

public class Info {
    
    private final Dimension INFO_DIMENSIONE = new Dimension(650, 300);
    private JFrame jf = new JFrame(UtilFinestra.TITOLO);
    private JPanel[] jp = new JPanel[UtilFinestra.PANNELLI];
    private JLabel jlTitolo = new JLabel("INFORMAZIONI");
    private JTextArea jta = new JTextArea(15, 25);
    private JButton jbChiudi = new JButton("Chiudi");
    private String info = new String("");

    public Info() {
        testo();
        stiliEColori();
        pannelli();
        componenti();
        frame();
    }

    private void testo() {
        if (info.equals("")) {
            FileInput fi = new FileInput(UtilFinestra.INFOFILE);
            info = fi.readText();
        }
        jta.setText(info);
    }

    private void stiliEColori() {
        // Etichetta del titolo
        jlTitolo.setForeground(UtilFinestra.GRIGIO);
        jlTitolo.setFont(UtilFinestra.FTITOLO);

        // Area di testo
        jta.setForeground(UtilFinestra.BIANCO);
        jta.setBackground(UtilFinestra.BLU);
        jta.setFont(UtilFinestra.FTESTO);

        // Pulsante di chiusura
        jbChiudi.setForeground(UtilFinestra.BLU);
        jbChiudi.setBackground(UtilFinestra.GRIGIO);
        jbChiudi.setPreferredSize(UtilFinestra.DPULSANTE);
        jbChiudi.setBorder(UtilFinestra.BPULSANTE);
        jbChiudi.setFont(UtilFinestra.FPULSANTE);
    }

    private void pannelli() {
        // Istanziazione
        for (int i = 0; i < UtilFinestra.PANNELLI; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(UtilFinestra.BLU);
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
        jta.setPreferredSize(INFO_DIMENSIONE);
        jta.setWrapStyleWord(true);
        jta.setLineWrap(true);
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
        jf.setLocation(UtilFinestra.POSX, UtilFinestra.POSY);
        jf.setMinimumSize(UtilFinestra.DIMENSIONE);
        jf.setSize(UtilFinestra.DIMENSIONE);
        jf.setVisible(true);

        // Icona
        jf.setIconImage(UtilFinestra.ICONA.getImage());

        // Contenitore
        jf.getContentPane();
    } 
}