package lotto;

import lotto.finestre.FinestraLogin;
import lotto.finestre.FinestraMenu;

public class start {
    private static FinestraLogin fl = new FinestraLogin();
    private static Utente u = new Utente();
    private static FinestraMenu fm;
    
    public static void main(String args[]) {
        // Apre la finestra del login
        u.setUsername(fl.getUsername());

        // Apre la finestra del menu principale
        fm = new FinestraMenu(u.getUsername());
    }
}
