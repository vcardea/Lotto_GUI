package src.log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe per la lettura da file di testo
 * 
 * @author Vincenzo Cardea
 * @author Francesco Rizzello
 * @author Matteo De Vito
 * @author Alessandro Serio
 * 
 * @since 1.0.0
 */
public class FileInput {
    private final String INPUT;
    private FileReader fr;
    private BufferedReader br;

    /**
     * Costruttore
     * 
     * @param INPUT percorso del file di Input
     */
    public FileInput(String INPUT) {
        this.INPUT = INPUT;
    }

    /**
     * Apre il flusso di Input
     * 
     * @return se ci sono stati problemi nell'apertura
     */
    private boolean openInput() {
        try {
            fr = new FileReader(INPUT);
            br = new BufferedReader(fr);
        } catch (IOException ioe) {
            System.err.println(">! Errore durante l'apertura del file " + INPUT);
            return false;
        }
        return true;
    }

    /**
     * Chiude il flusso dell'Input
     */
    private void closeInput() {
        try {
            fr.close();
        } catch (IOException ioe) {
            System.err.println(">! Errore durante la chiusura del file " + INPUT);
        }
    }

    /**
     * Legge una linea da file
     * 
     * @return una sola linea del file
     */
    public String read() {
        String line = new String("");

        if (openInput()) {
            try {
                line = br.readLine();
            } catch (IOException ioe) {
                System.err.println(">! Errore durante la lettura del file " + INPUT);
            }
            closeInput();
        }
        return line;
    }

    /**
     * Legge tutto un file
     * 
     * @return tutte le linee del file
     */
    public String readText() {
        String text = new String();

        if (openInput()) {
            String line = new String();

            try {
                line = br.readLine();
            } catch (IOException ioe) {
                System.err.println(">! Errore durante la lettura del file " + INPUT);
            } 

            while (line != null) {
                text += line + '\n';
                try {
                    line = br.readLine();
                } catch (IOException ioe) {
                    System.err.println(">! Errore durante la lettura del file " + INPUT);
                }
            }

            closeInput();
        }

        return text;
    }
}