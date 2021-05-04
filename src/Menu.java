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

	public static void schifo() {

		nome1 = InputDati.leggiStringaNonVuota(INSERISCI_NOME1);
		nome2 = InputDati.leggiStringaNonVuota(INSERISCI_NOME2);
		battle = new Battaglia(new Giocatore(nome1),new Giocatore(nome2));
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
		
		menu = new MyMenu(GOLEMS, SCELTE_GOLEMS);
		
		scelta = menu.scegli();
		switch (scelta) {               //selezione nomi golem
			case 1:
				for (int i=0; i<golems; i++)
				{
					Battaglia.getPlayer1().addToGolemList(new Golem(BelleStringhe.pickAname()));
					Battaglia.getPlayer2().addToGolemList(new Golem(BelleStringhe.pickAname()));
				}
				break;
				
			case 2:
				for (int i=0; i<golems; i++)
				{
					Battaglia.getPlayer1().addToGolemList(new Golem(InputDati.leggiStringaNonVuota("Inserisci il nome del golem " + (i+1) + " del giocatore 1: ")));
					
				}
				for (int i=0; i<golems; i++)
				{
					Battaglia.getPlayer2().addToGolemList(new Golem(InputDati.leggiStringaNonVuota("Inserisci il nome del golem " + (i+1) + " del giocatore 2: ")));
				}
				break;
			default:
				System.out.println("HOW the fuck are u here golem");
		}
		
	}

}
