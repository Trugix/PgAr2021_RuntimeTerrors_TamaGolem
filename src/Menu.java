import it.unibs.fp.mylib.*;

public class Menu
{
	
	private static final String INSERISCI_NOME1 = "\nInserisci il nome del primo giocatore:";
	private static final String INSERISCI_NOME2 = "\nInserisci il nome del secondo giocatore:";
	private static final String INSERISCI_NUMERO_ELEMENTI = "\nInserisci il numero degli elementi da usare:";
	
	private static final String INSERISCI_DIFFICOLTA = "DIFFICOLTÀ\ninfluenzerà il numero degli elementi";
	private static final String[] SCELTE_DIFFICOLTA = {"Facile", "Intermedio", "Difficile", "Custom"};
	private static final String GOLEMS = "GOLEMS";
	private static final String[] SCELTE_GOLEMS = {"Rent a Golem", "Personalizzati"};
	private static final String STONES_P1 = "Pietre Giocatore 1";
	private static final String STONES_P2 = "Pietre Giocatore 2";
	private static String[] SCELTE_STONES = {};
	private static int[] scelteStonesId = {};
	private static String nome1;
	private static String nome2;
	private static int scelta;
	private static Battaglia battle = null;
	
	//private static Battle = new Battaglia();
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
	{        //todo trasferire le varie sezioni in metodi a parte
		//sezione giocatori
		do
		{
			nome1 = InputDati.leggiStringaNonVuota(INSERISCI_NOME1);
			nome2 = InputDati.leggiStringaNonVuota(INSERISCI_NOME2);
		}
		while (!InputDati.yesOrNo("Sei sicuro di usare questi nomi?"));
		
		
		
		//sezione difficoltà
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
			
			default:
				System.out.println("HOW the fuck are u here difficoltà");
		}
		nStones = ((int) Math.ceil(((double) nElements + 1.0) / 3.0) + 1);                                            //genera numero pietre per golem
		nGolems = (int) Math.ceil((double) ((nElements - 1) * (nElements - 2)) / (double) (2 * nStones));               //genera numero gole
        System.out.println("Numero di elementi: " + nElements + "\nNumero di pietre totali: " + nStones + "\nNumero di pietre per ogni elemento: " + spareStonesforElement+ "\nNumero di golem per giocatore: " + nGolems);
		battle = new Battaglia(new Giocatore(nome1), new Giocatore(nome2), nElements);
  
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
						nome = BelleStringhe.pickAname();
					}
					while (battle.cercaGolemNome(nome));
					battle.getPlayer1().addToGolemList(new Golem(nome));
					do
					{
						nome = BelleStringhe.pickAname();
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
		
		//svolgimento battaglia
  
		//chiInizia
		//evocazione di chi inizia
            //scelta golem
            //caricamento pietre
        //evocazione del secondo
            //scelta golem
            //caricamento pietre
		//inizio tiro pietre finché vita =0 poi evocazione del plyer morto
		
		
		
		
		//sezione pietre golem giocatore 1
        /*for (int i = 0; i < spareStones; i++) {
            scelteStonesId[i] = NumeriCasuali.estraiIntero(0, elements);		todo menu pietre per i golem
        }
        for (int i = 0; i < stones; i++) {
            menu = new MyMenu(STONES_P1, SCELTE_STONES);
            scelta = menu.scegli();
        }*/
	}
}
