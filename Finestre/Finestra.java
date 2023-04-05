package Finestre;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Finestra {
	public static void main(String args[]) {
		JFrame jf = new JFrame("Sempre colpa di matteo");
		JPanel jp = new JPanel();
		JLabel jl = new JLabel("Tutta colpa di Matteo");

		jp.add(jl);
		jf.add(jp);

		GestoreFinestra g = new GestoreFinestra();
		jf.addWindowListener(g);

		jf.setSize(350, 100);
		jf.setVisible(true);
	}
}