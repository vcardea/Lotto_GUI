package src.finestre;

import src.finestre.gestori.GestoreFinestra;

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
    private int numeri = 0;
    private static int contatore;
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

        private void display() {
            for (int i = 0; i < 90; ++i)
                if (numeri_scelti[i])
                    System.out.println(i + " acceso");
        }

        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();
            System.out.println("Entro inizio: " + contatore);
            if (comando.equals("Prosegui")) {
                if (numeri != contatore) {
                    errore(false);
                } else {
                    System.out.print("Ucciditi va bene");
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
                    System.out.println("TOLGO");
                    numeri_scelti[numero] = false;
                    --contatore;
                }
                display();
            }

        }
    }

    public FinestraInserimentoNumeri(int numeri) {
        this.numeri = numeri;
    }

    private void setup() { // setta la lista
        contatore = 0;
        for (int i = 1; i <= 90; i++) {
            numeri_scelti[i - 1] = false;
            JButton btnNumero = new JButton(Integer.toString(i));
            btnNumero.setActionCommand(Integer.toString(i));
            btnNumero.addActionListener(new FinestraInserimentoNumeri(numeri).new GestorePulsante());
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
        jf.addWindowListener(new GestoreFinestra(jf)); // gestore finestra globale
        jb.addActionListener(new FinestraInserimentoNumeri(numeri).new GestorePulsante()); // gestore button locale

        // robe finali
        jf.setSize(350, 200);
        jf.setVisible(true);
    }

    /*
     * public void getNumeri(int numeriScelti[]) {
     * for (int i = 0; i < 10; ++i) {
     * numeriScelti[i] = V[i];
     * }
     * }
     */
}