package src.log;

import src.finestre.Menu;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;

import java.util.StringTokenizer;
import java.util.Collections;
import java.util.NoSuchElementException;
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

    private static String getDate() {
        ZoneId zi = ZoneId.of("Europe/Rome");
        Clock c = Clock.tickSeconds(zi);
        return c.instant().atZone(zi).toString().substring(0, 19);
    }

    private static String leggi(String INPUT) {
        FileInput fi = new FileInput(INPUT);
        return fi.read();
    }

    private static float leggiDato(String linea, int shift) {
        StringTokenizer st = new StringTokenizer(linea, " ");
        for (int i = 0; i < 3 + shift; i++) {
            try {
                linea = st.nextToken();
            } catch (NoSuchElementException nse) {
                System.err.println(">! File dati danneggiato.");
            }
        }
        int start = linea.indexOf(":") + 2;
        linea = linea.substring(start, linea.length() - 2);

        float dato = 0.0f;
        try {
            dato = (Float) Float.valueOf(linea).floatValue();
        } catch (NumberFormatException nfe) {
            System.err.println(">! Errore nella lettura dei dati.");
        }

        return dato;
    }

    private static void controllaSeEsiste(File dir) {
        if (!dir.exists()) {
            try {
                Files.createDirectories(dir.toPath());
            } catch (IOException ioe) {
                System.err.println(">! Errore nella creazione della directory.");
            }
        }
    }

    public static String generaDato(int partite, float importo, float vincita, float guadagnoTotale, float mediaVincite) {
        String linea = "<" + Menu.username + "[" + getDate() + "]" + ">[Partite:[" + partite + "]] [ImportoTotale:[" + importo;
        linea += "]] [VincitaTotale:[" + vincita + "]] [GuadagnoTotale:[" + guadagnoTotale + "]] [MediaVincite:[" + mediaVincite + "]] ";
        return linea;
    }

    public static String generaLog(int partite, float importo, float vincita, Vector<Byte> scelti, Vector<Byte> indovinati) {
        String linea = "<" + Menu.username + "[" + getDate() + "]" + ">[Partite:[" + partite + "]] [Importo:[" + importo;
        linea += "]] [Vincita:[" + vincita + "]] [NumeriScelti:" + scelti + "] [NumeriIndovinati:" + indovinati + "] ";
        return linea;
    }

    public static void aggiornaDati(float importo, float vincita) {
        final String INPUT = "src/log/users/" + Menu.username + "/Dati" + Menu.username + ".txt";
        final String OUTPUT = "src/log/users/" + Menu.username + "/Log" + Menu.username + ".txt";
        FileOutput fo = new FileOutput(OUTPUT);

        String linea = leggi(INPUT);
        
        int partite = (int) leggiDato(linea, 0) + 1;
        importo += leggiDato(linea, 1);
        vincita += leggiDato(linea, 2);
        float guadagnoTotale = vincita - importo;
        float mediaVincite = (float) vincita / partite;

        linea = generaDato(partite, importo, vincita, guadagnoTotale, mediaVincite);

        fo.write(linea, false);
    }

    public static void scriviLog(float importo, float vincita, boolean[] bScelti, Vector<Byte> indovinati) {
        String OUTPUT = "src/log/users/" + Menu.username + "/Log" + Menu.username + ".txt";

        String INPUT = "src/log/users/" + Menu.username;
        File dir = new File(INPUT);
        controllaSeEsiste(dir);
        INPUT += "/Dati" + Menu.username + ".txt";

        FileOutput fo = new FileOutput(OUTPUT);  

        Vector<Byte> scelti = converti(bScelti);
        Collections.sort(scelti);
        Collections.sort(indovinati);

        String linea = leggi(INPUT);
        int partite = (int) leggiDato(linea, 0);

        linea = generaLog(partite, importo, vincita, scelti, indovinati);

        fo.write(linea, true);
    }
}