package src.log;

import src.finestre.Menu;

import java.util.StringTokenizer;
import java.util.Collections;
import java.util.Vector;

import java.time.Clock;
import java.time.ZoneId;

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

    public static int leggiPartite(String INPUT) {
        FileIO fio = new FileIO(INPUT, "");
        String linea = fio.read();
        StringTokenizer st = new StringTokenizer(linea, " ");

        linea = st.nextToken();
        int start = linea.indexOf(":") + 2;
        linea = linea.substring(start, linea.length() - 2);
        int partite = Integer.valueOf(linea).intValue();

        return partite;
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

    public static void scriviLog(float importo, float vincita, boolean[] bScelti, Vector<Byte> indovinati) {
        /*
         * Converti scelti
         * Scrivi (appendi)
         */
        final String INPUT = "./users/" + Menu.username + "/Log" + Menu.username + ".txt";
        final String OUTPUT = "./users/" + Menu.username + "/Dati" + Menu.username + ".txt";
        FileIO fio = new FileIO(INPUT, OUTPUT);
        Vector<Byte> scelti = converti(bScelti);
        Collections.sort(scelti);

        String linea = new String("");
        ZoneId zi = ZoneId.of("Europe/Rome");
        Clock c = Clock.tickSeconds(zi);
        String data = c.instant().atZone(zi).toString();
        int partite = leggiPartite(INPUT);
        linea += "<" + Menu.username + "[" + data + "]" + ">[ Partite:[" + partite + "]] [Importo:[" + importo;
        linea += "]] [Vincita:[" + vincita + "]] [NumeriScelti:[" + scelti + "]";
    }
}