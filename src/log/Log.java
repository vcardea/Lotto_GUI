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
     * @param bScelti array di 90 elementi di bool
     * @return un Vector<Byte> scelti, 
     * che contiene i numeri inseriti dall'utente
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

    /**
     * @return la data della giocata
     */
    private static String getDate() {
        ZoneId zi = ZoneId.of("Europe/Rome");
        Clock c = Clock.tickSeconds(zi);
        return c.instant().atZone(zi).toString();
    }

    /**
     * @param INPUT percorso del file
     * @return una singola linea del file letto
     */
    private static String leggi(final String INPUT) {
        FileInput fi = new FileInput(INPUT);
        return fi.read();
    }

    /**
     * @param linea stringa da analizzare
     * @param token numero di token
     * @return il dato letto
     */
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

    /**
     * @param CARTELLA percorso della cartella
     * @param INPUT percorso del file
     */
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

    /**
     * @param partite giocate totali
     * @param importo usato
     * @param vincita soldi vinti
     * @param scelti numeri scelti
     * @param indovinati numeri indovinati
     * @return il log da scrivere nel file
     */
    private static String generaLog(int partite, float importo, float vincita, Vector<Byte> scelti, Vector<Byte> indovinati) {
        String linea = "<" + Utente.username + "[" + getDate() + "]" + ">[PartitaNumero=[" + partite + "]] [Importo=[" + importo;
        linea += "]] [Vincita=[" + vincita + "]] [NumeriScelti=" + scelti + "] [NumeriIndovinati=" + indovinati + "] ";
        return linea;
    }

    /**
     * @param partite giocate totali
     * @param importo usato
     * @param vincita soldi vinti
     * @param INPUT percorso del file
     */
    private static void aggiornaDati(int partite, float importo, float vincita, final String INPUT) {
        FileOutput fo = new FileOutput(INPUT);
        String linea = new String();
        float guadagnoTotale;
        float guadagnoMedio;

        guadagnoTotale = (float) vincita - importo;
        guadagnoMedio = (float) guadagnoTotale / partite;

        linea = generaDati(partite, importo, vincita, guadagnoTotale, guadagnoMedio);
        fo.write(linea, false);
    }

    /**
     * @param partite giocate totali
     * @param importo usato
     * @param vincita soldi vinti
     * @param guadagnoTotale calcolato con la somma di tutte le vincite - importi
     * @param guadagnoMedio durante le partite
     * @return genera la stringa dei dati del giocatore
     */
    public static String generaDati(int partite, float importo, float vincita, float guadagnoTotale, float guadagnoMedio) {
        String linea = "<" + Utente.username + "[" + getDate() + "]" + ">[PartiteGiocate=[" + partite + "]] ";
        linea += "[ImportoTotale=[" + importo + "]] [VincitaTotale=[" + vincita + "]] ";
        linea += "[GuadagnoTotale=[" + guadagnoTotale + "]] [GuadagnoMedio=[" + guadagnoMedio + "]] ";
        return linea;
    }

    /**
     * @param importo usato
     * @param vincita soldi vinti
     * @param bScelti array di 90 elementi con i numeri scelti a forma di indice e segnati true
     * @param indovinati vector in cui ci sono i numeri vinti come elementi
     */
    public static void scriviLog(float importo, float vincita, boolean[] bScelti, Vector<Byte> indovinati) {
        final String OUTPUT = "users/" + Utente.username + "/Log" + Utente.username + ".txt";
        final String CARTELLA = "users/" + Utente.username;
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