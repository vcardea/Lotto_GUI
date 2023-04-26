package src.log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class FileIO {
    private final String INPUT;
    private final String OUTPUT;
    private FileReader fr;
    private FileWriter fw;
    private BufferedReader br;
    private PrintWriter pw;
    
    public FileIO(String INPUT, String OUTPUT) {
        this.INPUT = INPUT;
        this.OUTPUT = OUTPUT;
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

    private void closeInput() {
        try {
            fr.close();
        } catch (IOException ioe) {
            System.err.println(">! Errore durante la chiusura del file " + INPUT);
        }
    }

    private void closeOutput() {
        try {
            fw.close();
        } catch (IOException ioe) {
            System.err.println(">! Errore durante la chiusura del file " + OUTPUT);
        }
    }

    public String read() {
        String line = new String("");
        if (openInput()) {
            try {
                line = br.readLine();
            } catch (IOException ioe) {
                System.err.println(">! Errore durante la lectura del file " + INPUT);
            }
            closeInput();
        }
        return line;
    }

    public void flush()
    {
        if (openOutput(false)) {
            closeOutput();
        }
    }

    public void write(String LINE, boolean append) {
        if (openOutput(append)) {
            pw.println(LINE);
            closeOutput();
        }
    }
}