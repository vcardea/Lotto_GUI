package src.log;

import java.util.Vector;

import src.finestre.Menu;

public class Log {
    private static Vector<Byte> converti(boolean[] bScelti) {
        Vector<Byte> scelti = new Vector<Byte>();
        for (int i = 0; i < bScelti.length; i++) {
            if (bScelti[i]) {
                scelti.add(Byte.valueOf("" + (i + 1)).byteValue());
            }
        }
        return scelti;
    }

    public static void scriviLog(float importo, float vincita, boolean[] bScelti, Vector<Byte> indovinati) {
        /*
         * Converti scelti
         * Scrivi (appendi)
         */
        FileIO fio = new FileIO("Log" + Menu.username + ".txt", "Dati" + Menu.username + ".txt");
        Vector<Byte> scelti = converti(bScelti);
    }

    public static void aggiornaDati(float importo, float vincita) {
        /*
         * Recupera i dati (Partite, ImportoTotale, VincitaTotale, GuadagnoTotale, MediaVincite)
         * Partite++;
         * ImportoTotale += importo;
         * VincitaTotale += vincita;
         * GuadagnoTotale += VincitaTotale - ImportoTotale;
         * MediaVincite = VincitaTotale / Partite
         * Scrivi dati (sovrascrivi)
         */
    }
}