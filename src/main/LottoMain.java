package src.main;

import src.finestre.FinestraLogin;
import src.finestre.FinestraMenu;
import src.utente.Utente;

public class LottoMain {
    private static FinestraLogin fl = new FinestraLogin();
    private static Utente u = new Utente();
    private static FinestraMenu fm;

    public static void main(String args[]) {
        while (!fl.isUsernameSet()) {
            // Apre la finestra del login e imposta il nome utente
            u.setUsername(fl.getUsername());
        }

        // Apre la finestra del menu principale
        fm = new FinestraMenu(u.getUsername());
    }
}