/**
 * GIOCO DEL DIECI E LOTTO
 * Realizzato da:
 * Matteo De Vito
 * Francesco Rizzello
 * Vincenzo Cardea
 * Alessandro Serio
 */ 

public class CalcoloVincita {
	private float importo;
	private byte indovinati;
	private float vincita = 0.0f;

	public CalcoloVincita(byte numGiocati, float importo, byte indovinati) {
		this.importo = importo;
		this.indovinati = indovinati;
		if (indovinati == 0)
			vincita = 0.0f;
		else {
			if (numGiocati == 1)
				CalcoloVincita1();
			else if (numGiocati == 2)
				CalcoloVincita2();
			else if (numGiocati == 3)
				CalcoloVincita3();
			else if (numGiocati == 4)
				CalcoloVincita4();
			else if (numGiocati == 5)
				CalcoloVincita5();
			else if (numGiocati == 6)
				CalcoloVincita6();
			else if (numGiocati == 7)
				CalcoloVincita7();
			else if (numGiocati == 8)
				CalcoloVincita8();
			else if (numGiocati == 9)
				CalcoloVincita9();
			else if (numGiocati == 10)
				CalcoloVincita10();
		}
	}

	private void CalcoloVincita1() {
		if (indovinati == 1)
			vincita = importo * 3;
	}

	private void CalcoloVincita2() {
		if (indovinati == 2)
			vincita = importo * 14;
	}

	private void CalcoloVincita3() {
		if (indovinati == 2)
			vincita = importo * 2;
		else if (indovinati == 3)
			vincita = importo * 45;
	}

	private void CalcoloVincita4() {
		if (indovinati == 2)
			vincita = importo * 1;
		else if (indovinati == 3)
			vincita = importo * 10;
		else if (indovinati == 4)
			vincita = importo * 90;
	}

	private void CalcoloVincita5() {
		if (indovinati == 2)
			vincita = importo * 1;
		else if (indovinati == 3)
			vincita = importo * 4;
		else if (indovinati == 4)
			vincita = importo * 15;
		else if (indovinati == 5)
			vincita = importo * 140;
	}

	private void CalcoloVincita6() {
		if (indovinati == 3)
			vincita = importo * 2;
		else if (indovinati == 4)
			vincita = importo * 10;
		else if (indovinati == 5)
			vincita = importo * 100;
		else if (indovinati == 6)
			vincita = importo * 1000;
	}

	private void CalcoloVincita7() {
		if (indovinati == 4)
			vincita = importo * 4;
		else if (indovinati == 5)
			vincita = importo * 40;
		else if (indovinati == 6)
			vincita = importo * 400;
		else if (indovinati == 7)
			vincita = importo * 1600;
		else if (indovinati == 0)
			vincita = importo * 1;
	}

	private void CalcoloVincita8() {
		if (indovinati == 5)
			vincita = importo * 20;
		else if (indovinati == 6)
			vincita = importo * 200;
		else if (indovinati == 7)
			vincita = importo * 800;
		else if (indovinati == 8)
			vincita = importo * 10000;
		else if (indovinati == 0)
			vincita = importo * 1;
	}

	private void CalcoloVincita9() {
		if (indovinati == 5)
			vincita = importo * 10;
		else if (indovinati == 6)
			vincita = importo * 40;
		else if (indovinati == 7)
			vincita = importo * 400;
		else if (indovinati == 8)
			vincita = importo * 2000;
		else if (indovinati == 9)
			vincita = importo * 100000;
		else if (indovinati == 0)
			vincita = importo * 2;
	}

	private void CalcoloVincita10() {
		if (indovinati == 5)
			vincita = importo * 5;
		else if (indovinati == 6)
			vincita = importo * 15;
		else if (indovinati == 7)
			vincita = importo * 150;
		else if (indovinati == 8)
			vincita = importo * 1000;
		else if (indovinati == 9)
			vincita = importo * 20000;
		else if (indovinati == 10)
			vincita = importo * 1000000;
		else if (indovinati == 0)
			vincita = importo * 2;
	}

	public float getVincita() {
		return vincita;
	}
}