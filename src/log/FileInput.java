package src.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;

public class FileInput {
    private final String INPUT;
    private FileReader fr;
    private BufferedReader br;

    public FileInput(String INPUT) {
        this.INPUT = INPUT;
    }

    private boolean openInput() {
        File f = new File(INPUT);

        if (!f.exists()) {
            try {
                Files.createFile(f.toPath());
            } catch (IOException ioe) {
                System.err.println(">! Errore durante la creazione del file " + INPUT);
                return false;
            }
        }

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
                if (line == null) {
                    FileOutput fo = new FileOutput(INPUT);
                    String linea = Log.generaDato(0, 0.0f, 0.0f, 0.0f, 0.0f);
                    fo.write(linea, false);
                }
            } catch (IOException ioe) {
                System.err.println(">! Errore durante la lectura del file " + INPUT);
            }
            closeInput();
        }
        return line;
    }
}