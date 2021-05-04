import java.util.ArrayList;

import it.unibs.fp.mylib.*;

public class Menu {

	private static final String INSERISCI_NOME1 = "\nInserisci il nome del primo giocatore:";
	private static final String INSERISCI_NOME2 = "\nInserisci il nome del secondo giocatore:";
	private static final String INSERISCI_NUMERO_ELEMENTI = "\nInserisci il numero degli elementi da usare:";
	
	private static final String INSERISCI_DIFFICOLTA = "DIFFICOLTÀ\ninfluenzerà il numero degli elementi";
	private static final String[] SCELTE_DIFFICOLTA = {"Facile", "Intermedio", "Difficile", "Custom"};
	private static final String GOLEMS = "GOLEMS";
	private static final String[] SCELTE_GOLEMS = {"Rent a Golem", "Personalizzati"};
	private static String nome1;
	private static String nome2;
	private static int scelta;
	private static Battaglia battle = null;

	//private static Battle = new Battaglia();
	private static MyMenu menu;

	private static int elements;
	private static int stones;
	private static int golems;
	private static int spareStones;
	private static int spareStonesforElement;

	public static void ilMenu() {		//todo trasferire le varie sezioni in metodi a parte
		//sezione giocatori
		nome1 = InputDati.leggiStringaNonVuota(INSERISCI_NOME1);
		nome2 = InputDati.leggiStringaNonVuota(INSERISCI_NOME2);
		battle = new Battaglia(new Giocatore(nome1),new Giocatore(nome2));

		//sezione difficoltà
		menu = new MyMenu(INSERISCI_DIFFICOLTA, SCELTE_DIFFICOLTA);
		scelta = menu.scegli();
		switch (scelta) {               //selezione difficoltà
			case 1:
				elements = NumeriCasuali.estraiIntero(3, 5);
				break;

			case 2:
				elements = NumeriCasuali.estraiIntero(6, 8);
				break;

			case 3:
				elements = NumeriCasuali.estraiIntero(9, 10);
				break;

			case 4:
				elements = InputDati.leggiInteroPositivo(INSERISCI_NUMERO_ELEMENTI);
				break;
			default:
				System.out.println("HOW the fuck are u here difficoltà");
		}
		stones = ((int) Math.ceil(((double)elements + 1.0) / 3.0) + 1);                                  //genera numero pietre per golem
		golems = (int) Math.ceil((double)((elements-1)*(elements-2))/(double)(2*stones));                //genera numero golem
		spareStones = (int) Math.ceil(2*(double)(golems*stones)/(double)elements)*elements;              //genera numero pietre comuni
		spareStonesforElement = (int) Math.ceil(2*(double)(golems*stones)/(double)elements);             //genera numero pietre comuni per elemento

		//sezione golem
		menu = new MyMenu(GOLEMS, SCELTE_GOLEMS);
		scelta = menu.scegli();
		String nome;
		switch (scelta) {               //selezione nomi golem
			case 1:		//nomi scelti dal programma
				for (int i=0; i<golems; i++)
				{

					do {
						nome = BelleStringhe.pickAname();
					}while (!Battaglia.cercaGolemNome(nome));
					Battaglia.getPlayer1().addToGolemList(new Golem(nome));
					do {
						nome = BelleStringhe.pickAname();
					}while (!Battaglia.cercaGolemNome(nome));
					Battaglia.getPlayer2().addToGolemList(new Golem(nome));
				}
				break;
				
			case 2:		//nomi scelti dall'utente
				for (int i=0; i<golems; i++)
				{
					do {
						nome = InputDati.leggiStringaNonVuota("Inserisci il nome (univoco) del golem " + (i + 1) + " del giocatore 1: ");
					}while (Battaglia.cercaGolemNome(nome));
					Battaglia.getPlayer1().addToGolemList(new Golem(nome));
				}
				for (int i=0; i<golems; i++)
				{
					do {
						nome = InputDati.leggiStringaNonVuota("Inserisci il nome (univoco) del golem " + (i + 1) + " del giocatore 2: ");
					}while (Battaglia.cercaGolemNome(nome));
					Battaglia.getPlayer2().addToGolemList(new Golem(nome));
				}
				break;

			default:
				System.out.println("HOW the fuck are u here golem");
		}

		//sezione pietre golem giocatore 1
		//menu = new MyMenu(/*STONES, SCELTE_STONES*/);
		scelta = menu.scegli();
	}

}
