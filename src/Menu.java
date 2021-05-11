import it.unibs.fp.mylib.*;

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

	private static final String[] SCELTE_FINE_PARTITA = {"Stampa equilibrio partita", "Nuova partita", "Visualizza classifica giocatori", "Concludi il programma" /*, meme */};
	private static final String ADDIO = "Arrivederci";
	
	private static String nome1;
	private static String nome2;
	private static int scelta;
	private static Battaglia battle = null;
	private static MyMenu menu;
	
	private static int nElements;
	private static int nGolems;
	private static int nStones;
	
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
    
    
    public static void ilMenu()
	{
		inserimentoGiocatori();
		sceltaDifficolta();
		inserimentoGolem();
		battle.start();
	}

	public static void nuovaPartita()
	{
		MyMenu menu = new MyMenu("Menu di fine partita", SCELTE_FINE_PARTITA);
		int scelta = menu.scegli();
		switch (scelta)
		{
			case 1:
				//todo stampa equilibrio per utente
				break;
			case 2:
				ilMenu();
				break;
			case 3:
				//todo classifica reader and writer
				break;
			case 0:
			
			default:
				System.out.println("Non dovresti essere qui  scelta nuova partità");
				break;
		}

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
				System.exit(99);
				break;
			default:
				System.out.println("HOW the fuck are u here difficoltà");
		}
		nStones = ((int) Math.ceil(((double) nElements + 1.0) / 3.0) + 1);                                            //genera numero pietre per golem
		nGolems = (int) Math.ceil((double) ((nElements - 1) * (nElements - 2)) / (double) (2 * nStones));       //genera numero golem
		Equilibrio.generaEquilibrio(nElements);
		battle = new Battaglia(new Giocatore(nome1), new Giocatore(nome2), nElements);
	}
	public static void inserimentoGiocatori()
	{
		do
		{
			nome1 = InputDati.leggiStringaNonVuota(INSERISCI_NOME1);
			nome2 = InputDati.leggiStringaNonVuota(INSERISCI_NOME2);
		}
		while (!InputDati.yesOrNo("Sei sicuro di usare questi nomi?"));
	}
}
