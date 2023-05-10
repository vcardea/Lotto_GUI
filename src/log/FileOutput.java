package src.log;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Classe per la scrittura su file di testo
 * 
 * @author Vincenzo Cardea
 * @author Francesco Rizzello
 * @author Matteo De Vito
 * @author Alessandro Serio
 * 
 * @since 1.0.0
 */
public class FileOutput {
    private final String OUTPUT;
    private FileWriter fw;
    private PrintWriter pw;
    
    /**
     * Costruttore.
     * 
     * @param OUTPUT percorso del file di Output
     */
    public FileOutput(String OUTPUT) {
        this.OUTPUT = OUTPUT;
    }

    /**
     * Apre il flusso di Output.
     * 
     * @param append se appende o sovrascrive i dati
     * @return       se ci sono stati problemi nell'apertura del flusso
     */
    private boolean openOutput(boolean append) {
        try {
            fw = new FileWriter(OUTPUT, append);
            pw = new PrintWriter(fw);
        } catch (IOException ioe) {
            System.err.println(">! Errore durante l'apertura del file " + OUTPUT);
            return false;
        }
        return true;
    }

    /**
     * Chiude il flusso di Output.
     */
    private void closeOutput() {
        try {
            fw.close();
        } catch (IOException ioe) {
            System.err.println(">! Errore durante la chiusura del file " + OUTPUT);
        }
    }

    /**
     * Scrive una linea su file.
     * 
     * @param LINE   linea da scrivere
     * @param append se appende o sovrascrive i dati
     */
    public void write(String LINE, boolean append) {
        if (openOutput(append)) {
            pw.println(LINE);
            pw.flush();
            if (pw.checkError()) {
                System.err.println(">! Errore durante la scrittura su file " + OUTPUT);
            }
            closeOutput();
        }
    }
}