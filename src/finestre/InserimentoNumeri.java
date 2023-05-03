package src.finestre;

import src.finestre.gestori.GestoreFinestraND;
import src.utente.Utente;

import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InserimentoNumeri {

    protected static final Border BORDER = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(UtilitiesFinestra.BLUE, 0),
        BorderFactory.createEmptyBorder(0, 0, 0, 0)
    );
    private static final byte NUMERI = 90;
    private static boolean numeriScelti[] = new boolean[NUMERI];
    private static int contatore;
    private JFrame jf = new JFrame(UtilitiesFinestra.TITOLO);
    private JPanel jp[] = new JPanel[UtilitiesFinestra.PANELS];
    private JLabel jlTitolo = new JLabel("INSERIMENTO NUMERI", JLabel.CENTER);
    private JLabel jlUsername = new JLabel(Utente.username, JLabel.CENTER);
    private GridLayout glNumeri = new GridLayout(9, 10);
    private JButton jbProsegui = new JButton("Prosegui");
    private byte numeri = 0;
    private float importo;

    private class GestorePulsante implements ActionListener {
        private void errore(boolean tipo) {
            if (tipo) {
                JOptionPane.showMessageDialog(jf, "Hai gia' inserito tutti i numeri",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(jf, "Non hai scelto tutti i numeri.",
                    "Attenzione",
                    JOptionPane.WARNING_MESSAGE);
            }
        }

        private void reset() {
            for (int i = 0; i < NUMERI; ++i)
                if (numeriScelti[i])
                    numeriScelti[i] = false;
            contatore = 0;
        }

        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();
            if (comando.equals("Prosegui")) {
                if (numeri != contatore) {
                    errore(false);
                } else {
                    Finale f = new Finale(numeriScelti, importo, numeri);
                    reset();
                    jf.dispose();
                }
            } else {
                JButton jb = (JButton) e.getSource();
                int numero = Integer.valueOf(comando).intValue();
                --numero;
                if (!numeriScelti[numero]) {
                    if (contatore >= numeri) {
                        errore(true);
                    } else {
                        numeriScelti[numero] = true;
                        ++contatore;
                        jb.setForeground(UtilitiesFinestra.GREEN);
                        GestoreMouse.selezionati[numero] = true;
                    }
                } else {
                    numeriScelti[numero] = false;
                    --contatore;
                    jb.setForeground(UtilitiesFinestra.GREY);
                    GestoreMouse.selezionati[numero] = false;
                }
            }
        }
    }

    private class GestoreMouse extends MouseAdapter {
        protected static boolean[] selezionati = new boolean[NUMERI];

        public void mouseEntered(MouseEvent e) {
            JButton jb = (JButton) e.getComponent();
            jb.setForeground(UtilitiesFinestra.LIGHTGREEN);
        }

        public void mouseExited(MouseEvent e) {
            JButton jb = (JButton) e.getComponent();
            int indice = Integer.valueOf(jb.getActionCommand()).intValue();
            if (!selezionati[indice - 1]) {
                jb.setForeground(UtilitiesFinestra.GREY);
            }
        }
    }

    public InserimentoNumeri(byte numeri, float importo) {
        this.numeri = numeri;
        this.importo = importo;
        
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

        // Pulsante per il login
        jbProsegui.setForeground(UtilitiesFinestra.BLUE);
        jbProsegui.setBackground(UtilitiesFinestra.GREY);
        jbProsegui.setPreferredSize(UtilitiesFinestra.DBUTTON);
        jbProsegui.setBorder(UtilitiesFinestra.BORDER);
        jbProsegui.setFont(UtilitiesFinestra.FBUTTON);
    }

    private void setup() {
        contatore = 0;
        for (int i = 1; i <= NUMERI; i++) {
            numeriScelti[i - 1] = false;
            JButton btnNumero = new JButton(Integer.toString(i));
            btnNumero.setActionCommand(Integer.toString(i));
            btnNumero.addActionListener(new GestorePulsante());
            btnNumero.addMouseListener(new GestoreMouse());
            // Stile pulsante
            btnNumero.setForeground(UtilitiesFinestra.GREY);
            btnNumero.setBackground(UtilitiesFinestra.BLUE);
            btnNumero.setBorder(BORDER);
            btnNumero.setFont(UtilitiesFinestra.FBUTTON);
            jp[1].add(btnNumero);
        }
    }

    private void pannelli() {
        // Istanziazione
        for (int i = 0; i < UtilitiesFinestra.PANELS; ++i) {
            jp[i] = new JPanel();
            jp[i].setBackground(UtilitiesFinestra.BLUE);
        }

        // Imposta il layout dei pulsanti
        jp[1].setLayout(glNumeri);

        // Composizione di pannelli
        jp[0].add(jlTitolo);
        setup();
        jp[2].add(jbProsegui);
    }

    private void componenti() {
        // Ascoltatore pulsante prosegui
        jbProsegui.addActionListener(new GestorePulsante());
    }

    private void frame() {
        // Ascoltatore finestra
        jf.addWindowListener(new GestoreFinestraND(jf));

        // Layout
        jf.setLayout(UtilitiesFinestra.LAYOUT);
        jf.add(jp[0], BorderLayout.NORTH);
        jf.add(jp[1], BorderLayout.CENTER);
        jf.add(jp[2], BorderLayout.SOUTH);

        // Posizione, dimensione, visibilità finestra
        jf.setLocation(UtilitiesFinestra.LOCATIONX, UtilitiesFinestra.LOCATIONY);
        jf.setSize(UtilitiesFinestra.DIMENSION);
        jf.setResizable(false);
        jf.setVisible(true);
        
        // Icona
        jf.setIconImage(UtilitiesFinestra.ICON.getImage());
        
        // Contenitore
        jf.getContentPane();
    }
}