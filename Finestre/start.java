package finestre;

public class start {
    public static void main(String args[])
    {
        FinestraLogin fl = new FinestraLogin();

        Utente u = new Utente();
        u.setUsername(fl.username());

        FinestraMenu fm = new FinestraMenu(u.getUsername());

    }
}
