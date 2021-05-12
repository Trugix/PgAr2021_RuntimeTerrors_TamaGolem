import it.unibs.fp.mylib.*;

import java.util.ArrayList;

public class Menu
{
	
	private static final String INSERISCI_NOME1 = "\nInserisci il nome del primo giocatore:";
	private static final String INSERISCI_NOME2 = "\nInserisci il nome del secondo giocatore:";
	private static final String INSERISCI_NUMERO_ELEMENTI = "\nInserisci il numero degli elementi da usare:";
	
	private static final String INSERISCI_DIFFICOLTA = "DIFFICOLTÀ\ninfluenzerà il numero degli elementi";
	private static final String[] SCELTE_DIFFICOLTA = {"Facile", "Intermedio", "Difficile", "Custom"/*, Distruggi computer*/};
	private static final String GOLEMS = "GOLEMS";
	private static final String[] SCELTE_GOLEMS = {"Rent a Golem", "Personalizzati"};
	private static final String STONES_P1 = "Pietre Giocatore 1";
	private static final String STONES_P2 = "Pietre Giocatore 2";

	private static final String[] SCELTE_FINE_PARTITA = {"Stampa equilibrio partita", "Nuova partita", "Visualizza classifica giocatori", "Resetta classifica"/*, meme */};
	private static final String[] SCELTA_GIOCATORE = {"Nuovo giocatore", "Carica giocatore"};
	private static final String ADDIO = "Arrivederci";
	
	private static boolean primo=true;
	private static String nome1;
	private static String nome2;
	private static int scelta;
	private static Battaglia battle = null;
	private static MyMenu menu;
	private static boolean stessoEquilibrio=false;
	private static int nElements;
	private static int nGolems;
	private static int nStones;
	
	private static Giocatore giocatore1, giocatore2;
	
	private static ArrayList <Giocatore> giocatori = new ArrayList<Giocatore>();
	private static ArrayList <Giocatore> giocatorDaOrdinare = new ArrayList<Giocatore>();
	
    public static int getnElements()
    {
        return nElements;
    }
    
    public static int getnStones()
    {
        return nStones;
    }
    
    public static int getnGolems()
    {
        return nGolems;
    }
	
	public static ArrayList<Giocatore> getGiocatori()
	{
		return giocatori;
	}
	
	public static void setGiocatori(ArrayList<Giocatore> giocatori)
	{
		Menu.giocatori = giocatori;
	}
	
	private static void stampaClassifica ()
	{
		giocatorDaOrdinare.addAll(giocatori);
		Utility.clearScreen();
		System.out.printf("%10s %15s %10s\n","Posizione","Nome","Punteggio");
		int i =1;
		do
		{
			int max = giocatorDaOrdinare.get(0).getPunteggio();
			Giocatore massimo = giocatorDaOrdinare.get(0);
			for (int j = 0; j < giocatorDaOrdinare.size(); j++)
			{
				if (giocatorDaOrdinare.get(j).getPunteggio() > max)
				{
					max = giocatorDaOrdinare.get(j).getPunteggio();
					massimo = giocatorDaOrdinare.get(j);
				}
			}
			System.out.printf("%10s %15s %10s\n", i , massimo.getName(), max);
			i++;
			giocatorDaOrdinare.remove(massimo);
		}
		while (giocatorDaOrdinare.size()>0);
		System.out.println("");
	}
	
	public static void ilMenu()
	{
		Reader.readGiocatori();
		inserimentoGiocatori();
		Utility.clearScreen();
		sceltaDifficolta();
		Utility.clearScreen();
		inserimentoGolem();
		Utility.clearScreen();
		battle.start();
		Writer.writeOutput();
		
	}

	public static void nuovaPartita()
	{
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
					stessoEquilibrio=InputDati.yesOrNo("Vuoi usare lo stesso equilibrio?");
					ilMenu();
					break;
				case 3:
					stampaClassifica();
					break;
				case 4:
					Writer.resetGiocatori();
					Reader.readGiocatori();
					break;
				case 0:
					Writer.writeOutput();
					System.exit(99);
					break;
				default:
					System.out.println("Non dovresti essere qui  scelta nuova partità");
					break;
			}
		}while (scelta!=0);
	}

	public static void inserimentoGolem()
	{
		//sezione golem
		menu = new MyMenu(GOLEMS, SCELTE_GOLEMS);
		scelta = menu.scegli();
		String nome;
		switch (scelta)
		{               //selezione nomi golem
			case 1:        //nomi scelti dal programma
				for (int i = 0; i < nGolems; i++)
				{
					do
					{
						nome = BelleStringhe.pickAName();
					}
					while (battle.cercaGolemNome(nome));
					battle.getPlayer1().addToGolemList(new Golem(nome));
					do
					{
						nome = BelleStringhe.pickAName();
					}
					while (battle.cercaGolemNome(nome));
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
				Writer.writeOutput();
				System.exit(99);
				break;
			default:
				System.out.println("HOW the fuck are u here golems");
		}
	}
	
	public static void sceltaDifficolta()
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
				break;
			case 0:
				Writer.writeOutput();
				System.exit(99);
				break;
			default:
				System.out.println("HOW the fuck are u here difficoltà");
		}
		nStones = ((int) Math.ceil(((double) nElements + 1.0) / 3.0) + 1);                                            //genera numero pietre per golem
		nGolems = (int) Math.ceil((double) ((nElements - 1) * (nElements - 2)) / (double) (2 * nStones));       //genera numero golem
		if(!stessoEquilibrio) Equilibrio.generaEquilibrio(nElements);
		battle = new Battaglia(giocatore1, giocatore2, nElements);
	}
	
	public static void inserimentoGiocatori()
	{
		menu = new MyMenu("Inserimento giocatore", SCELTA_GIOCATORE);
		int scelta = menu.scegli();
		switch (scelta)
		{
			case 1: // nuovo giocatore
				do
				{
					do
					{
						nome1 = InputDati.leggiStringaNonVuota(INSERISCI_NOME1);
						nome2 = InputDati.leggiStringaNonVuota(INSERISCI_NOME2);
						if (nome1.trim().equals(nome2.trim()))
						{
							System.out.println("\nI nomi dei giocatori devono essere diversi");
						}
					}while (nome1.trim().equals(nome2.trim()));
				}
				while (!InputDati.yesOrNo("\nSei sicuro di usare questi nomi?"));
				giocatore1 = new Giocatore(nome1, 0);
				giocatore2 = new Giocatore(nome2, 0);
			break;
			case 2: // carica giocatore\
				String nome;
				boolean scelto;
				ArrayList <Giocatore> giocatoriSelezionabili = new ArrayList<>();
				giocatoriSelezionabili.addAll(giocatori);
				for (int i=1;i<=2;i++)
				{
					Utility.clearScreen();
					System.out.print("\n\nSelezionare uno dei seguenti: ");
					for (int j=0;j<giocatoriSelezionabili.size();j++)
					{
							if(j==giocatoriSelezionabili.size()-1) System.out.print(giocatoriSelezionabili.get(j).getName());
							else System.out.print(giocatoriSelezionabili.get(j).getName()+", ");
					}
					do
					{
						scelto =false;
						nome = InputDati.leggiStringaNonVuota("\nInserire il nome del giocatore "+i+" : ");
						for (Giocatore g:giocatoriSelezionabili)
						{
							if (g.getName().equals(nome) && i==1)
							{
								scelto = true;
								giocatore1 = g;
								giocatoriSelezionabili.remove(g);
								break;
							}
							if (g.getName().equals(nome) && i==2)
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
			break;
			case 0:
				Writer.writeOutput();
				System.exit(99);
			break;
		}
		
	}
}
