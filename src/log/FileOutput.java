package src.log;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class FileOutput {
    private final String OUTPUT;
    private FileWriter fw;
    private PrintWriter pw;
    
    public FileOutput(String OUTPUT) {
        this.OUTPUT = OUTPUT;
    }

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

    private void closeOutput() {
        try {
            fw.close();
        } catch (IOException ioe) {
            System.err.println(">! Errore durante la chiusura del file " + OUTPUT);
        }
    }

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