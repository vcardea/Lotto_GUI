package src.finestre;

import src.finestre.gestori.GestoreFinestra_ND;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinestraInserimentoNumeri {

    private static boolean numeri_scelti[] = new boolean[90];
    private static int contatore;
    private byte numeri = 0;
    private float importo;
    private JFrame jf = new JFrame("Giocata");
    private JPanel jp1 = new JPanel();
    private JPanel jp2 = new JPanel();
    private JLabel jl = new JLabel("Inserisci i numeri");
    private GridLayout gl = new GridLayout(3, 1);
    private GridLayout gl_scelte = new GridLayout(9, 10);
    private JButton jb = new JButton("Prosegui");

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
            for (int i = 0; i < 90; ++i)
                if (numeri_scelti[i])
                    numeri_scelti[i] = false;
            contatore = 0;
        }

        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();
            if (comando.equals("Prosegui")) {
                if (numeri != contatore) {
                    errore(false);
                } else {
                    FinestraFinale ff = new FinestraFinale(numeri_scelti, importo, numeri);
                    reset();
                    jf.dispose();
                }
            } else {
                int numero = Integer.parseInt(comando);
                --numero;
                if (!numeri_scelti[numero]) {
                    if (contatore >= numeri) {
                        errore(true);
                    } else {
                        numeri_scelti[numero] = true;
                        ++contatore;
                    }
                } else {
                    numeri_scelti[numero] = false;
                    --contatore;
                }
            }
        }
    }

    public FinestraInserimentoNumeri(byte numeri, float importo) {
        this.numeri = numeri;
        this.importo = importo;
        creaFinestra();
    }

    private void setup() { // setta la lista
        contatore = 0;
        for (int i = 1; i <= 90; i++) {
            numeri_scelti[i - 1] = false;
            JButton btnNumero = new JButton(Integer.toString(i));
            btnNumero.setActionCommand(Integer.toString(i));
            btnNumero.addActionListener(new GestorePulsante());
            jp2.add(btnNumero);
        }
    }

    public void creaFinestra() {
        // panel
        setup();
        jp1.add(jl); // etichetta
        jp2.setLayout(gl_scelte);
        // frame
        jf.setLayout(gl); // setta il layout
        jf.add(jp1); // aggiungi panel
        jf.add(jp2); // aggiungi lista
        jf.add(jb); // aggiungi button

        // eventi
        jf.addWindowListener(new GestoreFinestra_ND(jf)); // gestore finestra globale
        jb.addActionListener(new GestorePulsante()); // gestore button

        // robe finali
        jf.setSize(350, 200);
        jf.setVisible(true);
    }
}