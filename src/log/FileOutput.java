package src.log;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Classe con funzioni utili per Output da file
 */
public class FileOutput {
    private final String OUTPUT;
    private FileWriter fw;
    private PrintWriter pw;
    
    /**
     * Costruttore
     * @param OUTPUT percorso del file di Output
     */
    public FileOutput(String OUTPUT) {
        this.OUTPUT = OUTPUT;
    }

    /**
     * Apre il flusso di Output
     * @param append per capire se il file va creato o solo modificato
     * @return se ci sono stati problemi nell'apertura del flusso
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
     * Chiude il flusso di Output
     */
    private void closeOutput() {
        try {
            fw.close();
        } catch (IOException ioe) {
            System.err.println(">! Errore durante la chiusura del file " + OUTPUT);
        }
    }

    /**
     * @param LINE linea letta da inserire nel file
     * @param append per capire se il file va riscritto o solo modificato
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