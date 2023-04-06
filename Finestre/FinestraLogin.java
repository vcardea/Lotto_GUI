package Finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FinestraLogin {
    public static void main(String args[]) {
        JFrame jf = new JFrame("Login");
        JPanel jp = new JPanel();
        JLabel jl1 = new JLabel("Gioco del lotto");
        JLabel jl2 = new JLabel("username", JLabel.LEFT);

        JButton jb = new JButton("OK");
        JTextField jtf = new JTextField(15);

        jp.add(jl1);
        jp.add(jl2);
        jf.add(jp);

        GestoreFinestra g = new GestoreFinestra();
        jf.addWindowListener(g);

        jf.setSize(350, 100);
        jf.setVisible(true);
    }
}