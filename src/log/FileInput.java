package src.log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInput {
    private final String INPUT;
    private FileReader fr;
    private BufferedReader br;

    public FileInput(String INPUT) {
        this.INPUT = INPUT;
    }

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

    private void closeInput() {
        try {
            fr.close();
        } catch (IOException ioe) {
            System.err.println(">! Errore durante la chiusura del file " + INPUT);
        }
    }

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
}