import it.unibs.fp.mylib.*;

import java.util.ArrayList;

public class Menu
{
	
	private static final String INSERISCI_NOME1 = "\nInserisci il nome del primo giocatore:";
	private static final String INSERISCI_NOME2 = "\nInserisci il nome del secondo giocatore:";
	private static final String INSERISCI_NUMERO_ELEMENTI = "\nInserisci il numero degli elementi da usare:";
	
	private static final String INSERISCI_DIFFICOLTA = "DIFFICOLTÀ\ninfluenzerà il numero degli elementi";
	private static final String[] SCELTE_DIFFICOLTA = {"Facile", "Intermedio", "Difficile", "Custom", "Distruggi computer"};
	private static final String GOLEMS = "GOLEMS";
	private static final String[] SCELTE_GOLEMS = {"Rent a Golem", "Personalizzati"};
	
	private static final String[] SCELTE_FINE_PARTITA = {"Stampa equilibrio partita", "Nuova partita", "Visualizza classifica giocatori", "Resetta classifica"/*, meme */};
	private static final String[] SCELTA_GIOCATORE = {"Nuovo giocatore", "Carica giocatore"};
	private static final String ADDIO = "Arrivederci";
	
	private static int scelta;
	private static Battaglia battle = null;
	private static MyMenu menu;
	private static boolean stessoEquilibrio = false;
	private static int nElements;
	private static int nGolems;
	
	private static Giocatore giocatore1, giocatore2;
	
	private static ArrayList<Giocatore> giocatori = new ArrayList<>();
	private static ArrayList<Giocatore> giocatoriDaOrdinare = new ArrayList<>();
	
	public static ArrayList<Giocatore> getGiocatori()
	{
		return giocatori;
	}
	
	/**
	 * stampa la classifica
	 */
	private static void stampaClassifica()
	{
		giocatoriDaOrdinare.addAll(giocatori);
		Utility.clearScreen();
		System.out.println("Classifica:");
		System.out.printf("%10s %15s %10s\n", "Posizione", "Nome", "Punteggio");
		int i = 1;
		do
		{
			int max = giocatoriDaOrdinare.get(0).getPunteggio();
			Giocatore massimo = giocatoriDaOrdinare.get(0);
			for (Giocatore giocatore : giocatoriDaOrdinare)
			{
				if (giocatore.getPunteggio() > max)
				{
					max = giocatore.getPunteggio();
					massimo = giocatore;
				}
			}
			System.out.printf("%10s %15s %10s\n", i, massimo.getName(), max);
			i++;
			giocatoriDaOrdinare.remove(massimo);
		}
		while (giocatoriDaOrdinare.size() > 0);
		InputDati.leggiStringa("Premi invio per continuare...");
		Utility.clearScreen();
	}
	
	public static void ilMenu()
	{
		inserimentoGiocatori();
		Utility.clearScreen();
		sceltaDifficolta();
		Utility.clearScreen();
		inserimentoGolem();
		Utility.clearScreen();
		battle.start();
		Writer.writeOutput();
	}
	
	/**
	 * menu di fine partita
	 */
	public static void nuovaPartita()
	{
		Writer.writeOutput();
		MyMenu menu = new MyMenu("Menu di fine partita", SCELTE_FINE_PARTITA);
		do
		{
			int scelta = menu.scegli();
			switch (scelta)
			{
				case 1:
					Equilibrio.stampaEquilibrio();
					break;
				case 2:
					stessoEquilibrio = InputDati.yesOrNo("Vuoi usare lo stesso equilibrio?");
					resettumTotalus();
					Utility.clearScreen();
					ilMenu();
					break;
				case 3:
					stampaClassifica();
					break;
				case 4:
					Writer.resetGiocatori();
					giocatori.removeAll(giocatori);
					Reader.readGiocatori();
					Utility.clearScreen();
					break;
				case 0:
					System.out.println(ADDIO);
					System.exit(99);
					break;
				default:
					System.out.println("Non dovresti essere qui  scelta nuova partità");
					break;
			}
		}
		while (scelta != 0);
	}
	
	/**
	 * gestitsce inserimento dei golem
	 */
	public static void inserimentoGolem()
	{
		//sezione golem
		ArrayList<String> listaNomiGolems = new ArrayList<>();
		listaNomiGolems.addAll(BelleStringhe.getCoolNames());
		menu = new MyMenu(GOLEMS, SCELTE_GOLEMS);
		scelta = menu.scegli();
		String nome;
		switch (scelta)
		{               //selezione nomi golem
			case 1:        //nomi scelti dal programma
				for (int i = 0; i < nGolems; i++)
				{
					nome = BelleStringhe.pickAString(listaNomiGolems);
					listaNomiGolems.remove(nome);
					battle.getPlayer1().addToGolemList(new Golem(nome));
					nome = BelleStringhe.pickAString(listaNomiGolems);
					listaNomiGolems.remove(nome);
					battle.getPlayer2().addToGolemList(new Golem(nome));
				}
				break;
			
			case 2:        //nomi scelti dall'utente
				for (int i = 0; i < nGolems; i++)
				{
					do
					{
						nome = InputDati.leggiStringaNonVuota("Inserisci il nome (univoco) del golem " + (i + 1) + " del giocatore 1: ");
					}
					while (battle.cercaGolemNome(nome));
					battle.getPlayer1().addToGolemList(new Golem(nome));
				}
				System.out.println("\n");
				for (int i = 0; i < nGolems; i++)
				{
					do
					{
						nome = InputDati.leggiStringaNonVuota("Inserisci il nome (univoco) del golem " + (i + 1) + " del giocatore 2: ");
					}
					while (battle.cercaGolemNome(nome));
					battle.getPlayer2().addToGolemList(new Golem(nome));
				}
				break;
			case 0:
				System.out.println(ADDIO);
				Writer.writeOutput();
				System.exit(99);
				break;
			default:
				System.out.println("HOW the fuck are u here golems");
		}
	}
	
	/**
	 * menu scelta difficoltà
	 */
	public static void sceltaDifficolta()
	{
		if(!stessoEquilibrio)
		{
		menu = new MyMenu(INSERISCI_DIFFICOLTA, SCELTE_DIFFICOLTA);
		scelta = menu.scegli();
		switch (scelta)
		{               //selezione difficoltà
			case 1:
				nElements = NumeriCasuali.estraiIntero(3, 5);
				break;
			case 2:
				nElements = NumeriCasuali.estraiIntero(6, 8);
				break;
			case 3:
				nElements = NumeriCasuali.estraiIntero(9, 10);
				break;
			case 4:
				nElements = InputDati.leggiInteroPositivo(INSERISCI_NUMERO_ELEMENTI);
				while(nElements<3 || nElements>BelleStringhe.getElements().size()-1)
				{
					nElements=InputDati.leggiInteroPositivo("Inserisci un numero tra 3 e "+(BelleStringhe.getElements().size()-1)+": ");
				}
				break;
			case 5:
				System.out.println("Ci vorrà un po'.... Ti aspettavi un algoritmo un efficiente?");
				nElements=BelleStringhe.getElements().size()-1;
				break;
			case 0:
				System.out.println(ADDIO);
				Writer.writeOutput();
				System.exit(99);
				break;
			default:
				System.out.println("HOW the fuck are u here difficoltà");
				break;
		}
			int nStones = ((int) Math.ceil(((double) nElements + 1.0) / 3.0) + 1);                                            //genera numero pietre per golem
			nGolems = (int) Math.ceil((double) ((nElements - 1) * (nElements - 2)) / (double) (2 * nStones));       //genera numero golem
			Equilibrio.generaEquilibrio(nElements);
		}
		battle = new Battaglia(giocatore1, giocatore2, nElements);
	}
	
	/**
	 * menu inserimento giocatori
	 */
	public static void inserimentoGiocatori()
	{
		menu = new MyMenu("Inserimento giocatore", SCELTA_GIOCATORE);
		int scelta = menu.scegli();
		switch (scelta)
		{
			case 1:
				// nuovo giocatore
				String nome1;
				String nome2;
				boolean trovato;
				do
				{
					System.out.print("\nGiocatori esistenti: ");
					for (int i = 0; i < giocatori.size(); i++)
					{
						if (i == giocatori.size()-1 )
							System.out.print(giocatori.get(i).getName());
						else
							System.out.print(giocatori.get(i).getName() + ", ");
					}
					do
					{
						trovato = false;
						nome1 = InputDati.leggiStringaNonVuota(INSERISCI_NOME1);
						nome2 = InputDati.leggiStringaNonVuota(INSERISCI_NOME2);
						for (Giocatore g:giocatori)
						{
							if (g.getName().equals(nome1) || g.getName().equals(nome2))
							{
								trovato = true;
								System.out.println("\nUno dei due giocatori è già presente nella lista dei giocatori, inserire un nuovo nome");
								break;
							}
						}
						if (nome1.trim().equals(nome2.trim()))
						{
							System.out.println("\nI nomi dei giocatori devono essere diversi");
						}
					}
					while (nome1.trim().equals(nome2.trim()) || trovato);
				}
				while (!InputDati.yesOrNo("\nSei sicuro di usare questi nomi?"));
				giocatore1 = new Giocatore(nome1, 0);
				giocatore2 = new Giocatore(nome2, 0);
				giocatori.add(giocatore1);
				giocatori.add(giocatore2);
				break;
			case 2:  // carica giocatore
				String nome;
				boolean scelto;
				ArrayList<Giocatore> giocatoriSelezionabili = new ArrayList<>();
				giocatoriSelezionabili.addAll(giocatori);
				for (int i = 1; i <= 2; i++)
				{
					Utility.clearScreen();
					System.out.print("\n\nSelezionare uno dei seguenti: ");
					for (int j = 0; j < giocatoriSelezionabili.size(); j++)
					{
						if (j == giocatoriSelezionabili.size() - 1)
							System.out.print(giocatoriSelezionabili.get(j).getName());
						else System.out.print(giocatoriSelezionabili.get(j).getName() + ", ");
					}
					do
					{
						scelto = false;
						nome = InputDati.leggiStringaNonVuota("\nInserire il nome del giocatore " + i + " : ");
						for (Giocatore g : giocatoriSelezionabili)
						{
							if (g.getName().equals(nome) && i == 1)
							{
								scelto = true;
								giocatore1 = g;
								giocatoriSelezionabili.remove(g);
								break;
							}
							if (g.getName().equals(nome) && i == 2)
							{
								scelto = true;
								giocatore2 = g;
								giocatoriSelezionabili.remove(g);
								break;
							}
						}
					}
					while (!scelto);
				}
				giocatoriSelezionabili.removeAll(giocatoriSelezionabili);
				break;
			case 0:
				System.out.println(ADDIO);
				Writer.writeOutput();
				System.exit(69420);
				break;
		}
		
	}
	
	/**
	 * resetta le liste dei golem dei giocatori e elimina i nodi e gli archi se si deve rigenerare l'equilibrio
	 */
	public static void resettumTotalus()
	{
		for (Giocatore g: Menu.getGiocatori())
		{
			g.getGolemList().removeAll(g.getGolemList());
		}
		if(!stessoEquilibrio)
		{
			Equilibrio.getElementi().removeAll(Equilibrio.getElementi());
			Equilibrio.getArchi().removeAll(Equilibrio.getArchi());
		}
	}
}
