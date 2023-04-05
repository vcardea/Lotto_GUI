import javax.JFrame;
import javax.JPanel;
import javax.JLabel;

public class Eventi01 {
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