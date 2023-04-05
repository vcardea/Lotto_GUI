/**
 * GIOCO DEL DIECI E LOTTO
 * Realizzato da:
 *  Matteo De Vito
 *  Francesco Rizzello
 *  Vincenzo Cardea
 *  Alessandro Serio
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Input
{
  private static InputStreamReader isr = new InputStreamReader(System.in);
  private BufferedReader br = new BufferedReader(isr);
  private String input = new String();
  private boolean valido;

  public float scan(int inizio, int fine, float salto)
  {
    // Dichiarazione
    float ingresso;

    // Inizializzazione
    ingresso = 0.0f;

    // Ciclo principale
    do {
      valido = true;
      System.out.print(">: Inserire l'importo della giocata (da 1 a 200, ed a scatti di 0.50): ");
      try
      {
        input = br.readLine();
        ingresso = Float.valueOf(input).floatValue();
      }
      catch (IOException e)
      {
        System.out.println(">: ECCEZIONE: " + e.toString());
        valido = false;
      }
      catch (NumberFormatException e)
      {
        System.out.println(">: ECCEZIONE: " + e.toString());
        valido = false;
      }
      catch (RuntimeException e)
      {
        System.out.println(">: ECCEZIONE: " + e.toString());
        valido = false;
      }
      finally
      {
        if (ingresso < inizio || ingresso > fine || ingresso % salto != 0.0)
        {
          System.out.println(">: Numero inserito non valido.");
          valido = false;
        }
      }
      System.out.println();
    } while (!valido);

    return ingresso;
  }

  public byte scan(String messaggio, int inizio, int fine)
  {
    // Dichiarazione
    byte ingresso;

    // Inizializzazione
    ingresso = 0;

    // Ciclo principale
    do {
      valido = true;
      System.out.print(messaggio);
      try
      {
        input = br.readLine();
        ingresso = Byte.valueOf(input).byteValue();
        if (ingresso < inizio || ingresso > fine)
        {
          System.out.println(">: Numero inserito non valido.");
          valido = false;
        }
      }
      catch (IOException e)
      {
        System.out.println(">: ECCEZIONE: " + e.toString());
        valido = false;
      }
      catch (NumberFormatException e)
      {
        System.out.println(">: ECCEZIONE: " + e.toString());
        valido = false;
      }
      catch (RuntimeException e)
      {
        System.out.println(">: ECCEZIONE: " + e.toString());
        valido = false;
      }
      System.out.println();
    } while (!valido);

    return ingresso;
  }
}