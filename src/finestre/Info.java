package src.finestre;

import src.log.FileInput;
import src.finestre.gestori.GestoreFinestraND;
import src.finestre.gestori.GInfo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

/**
 * Classe per la finestra delle informazioni
 * 
 * @author Vincenzo Cardea
 * @author Francesco Rizzello
 * @author Matteo De Vito
 * @author Alessandro Serio
 * 
 * @since 1.0.0
 */
public class Info {
    
    // elementi grafici
    private final Dimension INFO_DIMENSIONE = new Dimension(650, 400);
    private final Border BORDO = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(UtilFinestra.BLU, 0),
        BorderFactory.createEmptyBorder(0, 0, 0, 0)
    );
    private JFrame jf = new JFrame(UtilFinestra.TITOLO);
    private JPanel[] jp = new JPanel[UtilFinestra.PANNELLI];
    private JLabel jlTitolo = new JLabel("INFORMAZIONI");
    private JTextArea jta = new JTextArea(15, 25);
    private JScrollPane jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JButton jbChiudi = new JButton("Chiudi");
    private String info = new String("");

    /**
     * Costruttore
     */
    public Info() {
        stiliEColori();
        pannelli();
        componenti();
        frame();
    }

    /** 
     * Metodo che prende in input da file le informazioni
     * riguardanti il gioco del lotto
    */ 
    private void testo() {
        if (info.equals("")) {
            FileInput fi = new FileInput(UtilFinestra.INFOFILE);
            info = fi.readText();
        }
        jta.setText(info);
    }

    /**
     * Sistema lo stile ed i colori della finestra
     */
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
        jp[1].setLayout(new GridBagLayout());

        // Composizione
        jp[0].add(jlTitolo);
        jp[1].add(jsp);
        jp[2].add(jbChiudi);
    }

    /**
     * Crea e sistema i componenti
     */
    private void componenti() {
        // Area di testo
        testo();
        jta.setWrapStyleWord(true);
        jta.setLineWrap(true);
        jta.setHighlighter(null);
        jta.setEditable(false);

        // JScrollPane
        jsp.setPreferredSize(INFO_DIMENSIONE);
        jsp.setBorder(BORDO);

        // Gestione pulsante chiusura
        jbChiudi.setActionCommand("chiudi");
        jbChiudi.addActionListener(new GInfo(jf));
    }

    /**
     * Crea e sistema il frame
     */
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