package src.log;

import src.utente.Utente;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;

import java.util.StringTokenizer;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Vector;

import java.time.Clock;
import java.time.ZoneId;

/**
 * GIOCO DEL DIECI E LOTTO IN GUI
 * Realizzato da:
 * Vincenzo Cardea
 * Francesco Salvatore Rizzello
 * Matteo De Vito
 * Alessandro Serio
 * 
 * Classe che gestisce la scrittura
 * e lettura dei Log del progetto.
 */

public class Log {
    /**
     * Funziona che ritorna un Vector<Byte> scelti,
     * che contiene 
     * @param bScelti
     */
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
        return c.instant().atZone(zi).toString();
    }

    private static String leggi(final String INPUT) {
        FileInput fi = new FileInput(INPUT);
        return fi.read();
    }

    private static float leggiDato(String linea, int token) {
        StringTokenizer st = new StringTokenizer(linea, " ");
        float dato;
        int start;

        for (int i = 0; i < token; i++) {
            try {
                linea = st.nextToken();
            } catch (NoSuchElementException nse) {
                System.err.println(">! File dati danneggiato.");
            }
        }
        
        start = linea.indexOf("=") + 2;
        linea = linea.substring(start, linea.length() - 2);

        try {
            dato = Float.valueOf(linea).floatValue();
        } catch (NumberFormatException nfe) {
            System.err.println(">! Errore nella lettura dei dati.");
            dato = 0.0f;
        }

        return dato;
    }

    private static void controllaSeEsiste(final String CARTELLA, final String INPUT) {
        File dir = new File(CARTELLA);
        File f = new File(INPUT);

        if (!dir.exists()) {
            try {
                Files.createDirectories(dir.toPath());
            } catch (IOException ioe) {
                System.err.println(">! Errore nella creazione della directory.");
            }
        }

        if (!f.exists()) {
            try {
                Files.createFile(f.toPath());
                FileOutput fo = new FileOutput(INPUT);
                String linea = Log.generaDati(0, 0.0f, 0.0f, 0.0f, 0.0f);
                fo.write(linea, false);
            } catch (IOException ioe) {
                System.err.println(">! Errore durante la creazione del file " + INPUT);
            }
        }
    }

    private static String generaLog(int partite, float importo, float vincita, Vector<Byte> scelti, Vector<Byte> indovinati) {
        String linea = "<" + Utente.username + "[" + getDate() + "]" + ">[PartitaNumero=[" + partite + "]] [Importo=[" + importo;
        linea += "]] [Vincita=[" + vincita + "]] [NumeriScelti=" + scelti + "] [NumeriIndovinati=" + indovinati + "] ";
        return linea;
    }

    private static void aggiornaDati(int partite, float importo, float vincita, final String INPUT) {
        FileOutput fo = new FileOutput(INPUT);
        String linea = new String();
        float guadagnoTotale;
        float mediaVincite;

        guadagnoTotale = (float) vincita - importo;
        mediaVincite = (float) vincita / partite;

        linea = generaDati(partite, importo, vincita, guadagnoTotale, mediaVincite);
        fo.write(linea, false);
    }

    public static String generaDati(int partite, float importo, float vincita, float guadagnoTotale, float mediaVincite) {
        String linea = "<" + Utente.username + "[" + getDate() + "]" + ">[PartiteGiocate=[" + partite + "]] ";
        linea += "[ImportoTotale=[" + importo + "]] [VincitaTotale=[" + vincita + "]] ";
        linea += "[GuadagnoTotale=[" + guadagnoTotale + "]] [MediaVincite=[" + mediaVincite + "]] ";
        return linea;
    }

    public static void scriviLog(float importo, float vincita, boolean[] bScelti, Vector<Byte> indovinati) {
        final String OUTPUT = "src/log/users/" + Utente.username + "/Log" + Utente.username + ".txt";
        final String CARTELLA = "src/log/users/" + Utente.username;
        final String INPUT = CARTELLA + "/Dati" + Utente.username + ".txt";
        Vector<Byte> scelti = new Vector<Byte>();
        FileOutput fo = new FileOutput(OUTPUT);
        String log = new String();
        String dati = new String();
        int partite;
        
        controllaSeEsiste(CARTELLA, INPUT);

        scelti = converti(bScelti);
        Collections.sort(scelti);
        Collections.sort(indovinati);

        dati = leggi(INPUT);
        partite = (int) leggiDato(dati, 1) + 1;
        log = generaLog(partite, importo, vincita, scelti, indovinati);
        
        importo += leggiDato(dati, 2);
        vincita += leggiDato(dati, 3);
        aggiornaDati(partite, importo, vincita, INPUT);
        
        fo.write(log, true);
    }
}