/**
 * GIOCO DEL DIECI E LOTTO
 * Realizzato da:
 *  Matteo De Vito
 *  Francesco Rizzello
 *  Vincenzo Cardea
 *  Alessandro Serio
*/

// Classe principale
public class Lotto
{
  // Attributi costanti e pubblici
  public static final short MAXPUNTATA = 200;
  public static final byte NUMERI = 90;
  public static final byte ESTRAZIONI = 20;
  public static final byte MAXSCELTE = 10;
  public static final byte MINSCELTE = 1;

  // Istanziazione dell'oggetto per l'input controllato
  private static Input input = new Input();

  /**
   * Presentazione del programma
   */
  private static void presentazione()
  {
    System.out.println(">> DIECI E LOTTO <<\n");
  }
  
  /**
   * Metodo per il popolamento degli array di boolean
   */
  private static void popola(boolean checkEstrazioni[], boolean checkNumeri[])
  {
    // Popolamento degli array di boolean
    for (byte i = 0; i < NUMERI; i++)
    {
      checkEstrazioni[i] = false;
      checkNumeri[i] = false;
    }
  }

  /**
   * Metodo per l'estrazione dei numeri vincenti
   */
  private static void estrai(boolean checkEstrazioni[])
  {
    // Attributi locali
    boolean ripeti;
    byte estrazione = 0;

    // Estrazione dei numeri pseudo-casuali
    for (byte i = 0; i < ESTRAZIONI; i++)
    {
      do {
        ripeti = false;
        estrazione = (byte) (Math.random() * 90);
        if (checkEstrazioni[estrazione]) ripeti = true;
        else checkEstrazioni[estrazione] = true;
      } while (!checkEstrazioni[estrazione] || ripeti);
    }
  }

  /**
   * Metodo che conta prende in input i numeri giocati e conta quelli indovinati
   */
  private static byte contaNumeri(byte gong, byte numeri[], boolean checkNumeri[], boolean checkEstrazioni[])
  {
    // Dichiarazione attributi locali
    byte j;
    byte indovinati;
    boolean ripeti;

    // Inizializzazione attributi locali
    j = 0;
    indovinati = 0;

    while (j < numeri.length)
    {
      // Controllo sull'input
      do {
        ripeti = false;
        numeri[j] = input.scan(">: Inserire un numero da giocare (" + (j + 1) + "): ", MINSCELTE, NUMERI);
        if (checkNumeri[numeri[j] - 1])
        {
          System.out.println(">! Numero gia' inserito.\n");
          ripeti = true;
        }
        else checkNumeri[numeri[j] - 1] = true;
      } while (!checkNumeri[numeri[j] - 1] || ripeti);
	  
      // Conta se il numero inserito corrisponde
      // ad uno estratto
      if (checkEstrazioni[numeri[j] - 1])
        indovinati++;

      j++;
    }

    return indovinati;
  }

  /**
   * Metodo che controlla se il numero gong sia stato indovinato
   */
  private static boolean gongTrovato(byte gong, byte numeri[])
  {
    byte j;

    j = 0;
    do {
      if (numeri[j] == gong)
        return true;
      j++;
    } while (j < numeri.length);

    return false;
  }

  /**
   * Metodo per la visualizzazione a video della vincita e dei numeri vincenti 
   */
  private static void visualizzaVincita(float importo, byte contatore, byte gong, boolean gongTrovato, byte numeri[], boolean checkEstrazioni[])
  {
    byte j;
    
    if (gongTrovato)
      importo += 65.0f;

    // Comunicazione a video della vincita
    System.out.println("\n>: Vincita (in euro): " + importo);
    System.out.println("\n>! Hai indovinato " + contatore + " numeri su " + numeri.length + "\n");

    // Visualizzazione dei numeri vincenti
    j = 0;
    System.out.println(">: Numeri vincenti:\n");
    for (byte i = 0; i < NUMERI; i++)
    {
      if (checkEstrazioni[i])
      {
        System.out.print("\t" + (i + 1));
        if (j % 4 == 3) System.out.println("\n");
        j++;
      }
    }

    // Controlla che il numero gong sia stato indovinato
    visualizzaGong(gong, gongTrovato);
  } 

  /**
   * Metodo che comunica che il numero gong sia stato indovinato
   */
  private static void visualizzaGong(byte gong, boolean gongTrovato)
  {
    if (gongTrovato) System.out.println(">! GONG VINTO -> " + gong);
    else System.out.println(">: IL GONG ERA -> " + gong);
  }

  /**
   * Metodo principale
   */
  public static void main(String args[])
  {
    // Istanziazione e allocazione degli oggetti
    boolean checkEstrazioni[] = new boolean[NUMERI];
    boolean checkNumeri[] = new boolean[NUMERI];

    // Dichiarazione attributi locali
    float importo;
    byte indovinati;
    byte numeriGiocati;
    byte gong;
    boolean gongTrovato;

    // Inizializzazione
    importo = 0.0f;
    indovinati = 0;
    numeriGiocati = 0;
    gong = (byte) (Math.random() * NUMERI + 1);
    gongTrovato = false;

    // Presentazione del programma
    presentazione();

    // Popola gli array di boolean
    popola(checkEstrazioni, checkNumeri);
    
    // Effettua le estrazioni
    estrai(checkEstrazioni);
    
    // Quantità di numeri da giocare
    numeriGiocati = input.scan(">: Inserire la quantita' di numeri da giocare: ", MINSCELTE, MAXSCELTE);

    // Dichiarazione dell'array che contiene i numeri giocati
    byte numeri[] = new byte[numeriGiocati];
	
    // Importo giocato
    importo = input.scan(1, MAXPUNTATA, 0.50f);

    // Conteggio dei numeri indovinati
    indovinati = contaNumeri(gong, numeri, checkNumeri, checkEstrazioni);

    // Controlla che il numero gong sia stato indovinato
    gongTrovato = gongTrovato(gong, numeri);

    // Calcolo della vincita
    CalcoloVincita vincita = new CalcoloVincita(numeriGiocati, importo, indovinati);
    importo = vincita.getVincita();

    // Visualizza 
    visualizzaVincita(importo, indovinati, gong, gongTrovato, numeri, checkEstrazioni);
  }
}