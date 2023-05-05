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
 * La classe Log gestisce la creazione delle cartelle
 * e dei file di log delle partite giocate dagli utenti.
 * 
 * @author Vincenzo Cardea
 * @author Francesco Rizzello
 * @author Matteo De Vito
 * @author Alessandro Serio
 */
public class Log {

    public static String DATI = "";

    /**
     * Converte un array di boolean in un Vector<Byte>.
     * 
     * @param bScelti array booleano di 90 elementi
     * @return        un Vector<Byte> contenente i numeri scelti dall'utente
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
     * Ritorna la data corrente.
     * 
     * @return la data corrente
     */
    private static String getDate() {
        ZoneId zi = ZoneId.of("Europe/Rome");
        Clock c = Clock.tickSeconds(zi);
        return c.instant().atZone(zi).toString();
    }

    /**
     * Legge una linea dal file di testo.
     * 
     * @param INPUT percorso del file
     * @return      la linea letta
     */
    private static String leggi(final String INPUT) {
        FileInput fi = new FileInput(INPUT);
        return fi.read();
    }

    /**
     * Ritorna il dato voluto in base al token:
     *      1 - partite giocate
     *      2 - importo totale
     *      3 - vincita totale
     * 
     * @param linea stringa da analizzare
     * @param token numero di token
     * @return      il dato letto
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
     * Controlla se la cartella e il file indicato esistono.
     * Se no, li crea.
     * 
     * @param CARTELLA percorso della cartella
     * @param INPUT    percorso del file
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
     * Ritorna il log da scrivere su file.
     * 
     * @param partite    giocate
     * @param importo    usato
     * @param vincita    della partita corrente
     * @param scelti     numeri scelti
     * @param indovinati numeri indovinati
     * @return           il log da scrivere nel file
     */
    private static String generaLog(int partite, float importo, float vincita, Vector<Byte> scelti, Vector<Byte> indovinati) {
        String linea = "<" + Utente.username + "[" + getDate() + "]" + ">[PartitaNumero=[" + partite + "]] [Importo=[" + importo;
        linea += "]] [Vincita=[" + vincita + "]] [NumeriScelti=" + scelti + "] [NumeriIndovinati=" + indovinati + "] ";
        return linea;
    }

    /**
     * Aggiorna i dati del file DatiNomeUtente.txt.
     * 
     * @param partite giocate
     * @param importo usato
     * @param vincita della partita corrente
     * @param INPUT   percorso del file
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
     * Ritorna i dati da sovrascrivere nel file DatiNomeUtente.txt.
     * 
     * @param partite        giocate
     * @param importo        usato
     * @param vincita        della partita corrente
     * @param guadagnoTotale di tutte le partite
     * @param guadagnoMedio  per partita
     * @return               una stringa con i dati
     */
    public static String generaDati(int partite, float importo, float vincita, float guadagnoTotale, float guadagnoMedio) {
        String linea = "<" + Utente.username + "[" + getDate() + "]" + ">[PartiteGiocate=[" + partite + "]] ";
        linea += "[ImportoTotale=[" + importo + "]] [VincitaTotale=[" + vincita + "]] ";
        linea += "[GuadagnoTotale=[" + guadagnoTotale + "]] [GuadagnoMedio=[" + guadagnoMedio + "]] ";
        return linea;
    }

    /**
     * Scrive il log sul file LogNomeUtente.txt.
     * 
     * @param importo    usato
     * @param vincita    della partita corrente
     * @param bScelti    array booleano con i numeri scelti
     * @param indovinati numeri indovinati
     */
    public static void scriviLog(float importo, float vincita, boolean[] bScelti, Vector<Byte> indovinati) {
        final String OUTPUT = "users/" + Utente.username + "/Log" + Utente.username + ".txt";
        final String CARTELLA = "users/" + Utente.username;
        final String INPUT = CARTELLA + "/Dati" + Utente.username + ".txt";
        DATI = INPUT;
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