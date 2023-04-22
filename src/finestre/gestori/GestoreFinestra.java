package src.finestre.gestori;

import javax.swing.JFrame;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class GestoreFinestra implements WindowListener {
	private JFrame jf = new JFrame();

	/**
	 * Costruttore.
	 * Prende come parametro il jframe della
	 * finestra considerata
	 * 
	 * @param jf jframe della finestra
	 */
	public GestoreFinestra(JFrame jf) {
		this.jf = jf;
	}

	/**
	 * Riduce a icona la finestra
	 * 
	 * @param we evento verificatosi
	 */
	public void windowIconified(WindowEvent we) {
	}

	/**
	 * Ripristina la finestra
	 * 
	 * @param we evento verificatosi
	 */
	public void windowDeiconified(WindowEvent we) {
	}

	/**
	 * Finestra attivata
	 * 
	 * @param we evento verificatosi
	 */
	public void windowActivated(WindowEvent we) {
	}

	/**
	 * Finestra disattivata
	 * 
	 * @param we evento verificatosi
	 */
	public void windowDeactivated(WindowEvent we) {
	}

	/**
	 * Finestra aperta
	 * 
	 * @param we evento verificatosi
	 */
	public void windowOpened(WindowEvent we) {
	}

	/**
	 * Finestra chiusa
	 * 
	 * @param we evento verificatosi
	 */
	public void windowClosed(WindowEvent we) {
	}

	/**
	 * Finestra in chiusura
	 * 
	 * @param we evento verificatosi
	 */
	public void windowClosing(WindowEvent we) {
		jf.dispose();
	}
}