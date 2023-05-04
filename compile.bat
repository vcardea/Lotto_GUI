:: Diverse ccompilazioni
javac -d bin src/finestre/gestori/GestoreUscita.java
javac -d bin src/finestre/gestori/GestoreFinestra.java
javac -d bin src/finestre/gestori/GestoreFinestraND.java
javac -d bin src/finestre/gestori/GestoreFinestraFN.java

javac -d bin src/finestre/DettagliPartita.java
javac -d bin src/finestre/Finale.java
javac -d bin src/finestre/InserimentoNumeri.java
javac -d bin src/finestre/Login.java
javac -d bin src/finestre/Menu.java
javac -d bin src/finestre/Info.java
javac -d bin src/finestre/UtilFinestra.java

javac -d bin src/calcoli/CalcoloVincita.java

javac -d bin src/log/FileInput.java
javac -d bin src/log/FileOutput.java
javac -d bin src/log/Log.java

javac -d bin src/utente/Utente.java

javac -d bin src/main/LottoMain.java

:: L'effettiva apertura
java -cp bin src/main/LottoMain