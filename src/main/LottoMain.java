package src.main;

import src.finestre.Login;
import src.finestre.Menu;
import src.utente.Utente;

public class LottoMain {
    private static Login fl = new Login();
    private static Utente u = new Utente();
    private static Menu m;

    public static void main(String args[]) {
        while (!fl.isUsernameSet()) {
            // Apre la finestra del login e imposta il nome utente
            u.setUsername(fl.getUsername());
        }

        // Apre la finestra del menu principale
        m = new Menu(u.getUsername());
        m = null;
    }
}