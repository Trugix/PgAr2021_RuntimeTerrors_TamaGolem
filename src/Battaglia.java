import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.Utility;
import java.util.ArrayList;

public class Battaglia
{
	private int nElements;
	private int nStonesInGolem;
	private int nGolems;
	private int nSpareStones;
	private int nSpareStonesforElement;
	private Giocatore player1;
	private Giocatore player2;
	private ArrayList<String> nomiPietre = new ArrayList<>();
	private ArrayList<Integer> numeroPietre = new ArrayList<>();
	
	public Battaglia(Giocatore p1, Giocatore p2, int elements)
	{
		this.nElements = elements;
		this.player1 = p1;
		this.player2 = p2;
		this.nStonesInGolem = ((int) Math.ceil(((double) elements + 1.0) / 3.0) + 1);
		this.nGolems = (int) Math.ceil((double) ((elements - 1) * (elements - 2)) / (double) (2 * nStonesInGolem));
		this.nSpareStones = (int) Math.ceil(2 * (double) (nGolems * nStonesInGolem) / (double) elements) * elements;
		this.nSpareStonesforElement = (int) Math.ceil(2 * (double) (nGolems * nStonesInGolem) / (double) elements);
		inizializzaPietre();
	}
	
	/**
	 * inizializza le due liste di "elementi" e "quantità" per l'inserimento delle pietre
	 */
	private void inizializzaPietre()
	{
		for (Elemento e : Equilibrio.getElementi())
		{
			nomiPietre.add(e.getNome());
			numeroPietre.add(nSpareStonesforElement);
		}
	}
	
	/**
	 * stampa la tabella per l'inserimento delle pietre
	 */
	private void stampaPietre()
	{
		System.out.println("Pietre disponibili: \n");
		System.out.printf("%12s | %5s", "Elemento", "Disponibili \n");
		for (int i = 0; i < Equilibrio.getElementi().size(); i++)
		{
			System.out.printf("%12s - %5s\n", nomiPietre.get(i), numeroPietre.get(i));
		}
		System.out.println();
	}
	
	public Giocatore getPlayer1()
	{
		return player1;
	}
	
	public Giocatore getPlayer2()
	{
		return player2;
	}
	
	/**
	 * cerca se esiste gia un golem con quel nome nelle liste dei giocatori
	 * @param nome il nome da verificare
	 * @return true se esiste, false altrimenti
	 */
	public boolean cercaGolemNome(String nome)
	{
		for (Golem g : player1.getGolemList())
		{
			if (g.getNome().equals(nome)) return true;
		}
		for (Golem g : player2.getGolemList())
		{
			if (g.getNome().equals(nome)) return true;
		}
		return false;
	}
	
	/**
	 * gestisce la fase di lancio delle pietre
	 * @param pietreA pietra del golem di p1
	 * @param pietreB pietra del golem di p2
	 */
	public void attacco(Elemento pietreA, Elemento pietreB)
	{
		System.out.println("\nIl golem " + player1.getGolemInCampo().getNome() + " lancia la pietra: " + pietreA.getNome());
		System.out.println("Il golem " + player2.getGolemInCampo().getNome() + " lancia la pietra: " + pietreB.getNome());
		System.out.println();
		for (int i = 0; i < pietreA.getContatti().size(); i++)
		{
			if (pietreA.getNome().equals(pietreB.getNome())) //controlla se sono lo stesso elemento
			{
				System.out.println("I due non interagiscono!");
				break;
			}
			if (pietreA.getContatti().get(i).getFine().getId() == pietreB.getId()) //scorre i collgemaneti che la pietra "A" ha con le altre fino a trovare l'arco che la collgea a "B"
			{
				int danno = Math.abs(pietreB.getContatti().get(i).getPeso());
				if (pietreA.getContatti().get(i).getPeso() > 0) //se "A" fa danno
					aggiornaVita(player2.getGolemInCampo(), danno);
				else //se "A" riceve danno
					aggiornaVita(player1.getGolemInCampo(), danno);
				break;
			}
		}
	}
	
	/**
	 * aggiorna la vita del golem che prende danno
	 * @param golem golem che subisce il danno
	 * @param danno danno subito
	 */
	private void aggiornaVita(Golem golem, int danno)
	{
		golem.setVita(golem.getVita() - danno);
		System.out.println("Il golem " + golem.getNome() + " subisce " + danno);
		if (golem.getVita() > 0) // se dopo aver preso il danno è ancora vivo stampa la vita rimanente
			System.out.println("Vita rimanente del golem " + golem.getNome() + ": " + golem.getVita());
		else //se il golem muore stampa il messaggio di morte
			System.out.println("Il golem " + golem.getNome() + " is no more");
	}
	
	/**
	 * gira il vettore delle pietre (il golem lancia sempre la pos 0)
	 * @param pietre pietre di un golem
	 */
	public void giraPietre(ArrayList<Elemento> pietre)
	{
		Elemento temp;
		temp = pietre.get(0);
		for (int i = 0; i < pietre.size() - 1; i++)
		{
			pietre.set(i, pietre.get(i + 1));
		}
		pietre.set(pietre.size() - 1, temp);
	}
	
	/**
	 * inizia lo scontro decidendo chi far evocare prima
	 */
	public void start()
	{
		System.out.println("Numero di elementi: " + nElements + "\nNumero di pietre totali: " + nSpareStones + "\nNumero di pietre per ogni elemento: " + nSpareStonesforElement + "\nNumero di golem per giocatore: " + nGolems + "\nNumero di pietre per golem: " + nStonesInGolem + "\nVita dei golem: " + Golem.VITA_MAX + "\n");
		InputDati.leggiStringaNonVuota("\nPremere invio per iniziare lo scontro....");
		if (Math.random()>0.50) //decidere quale dei due giocatori evoca per primo
		{
			System.out.println("Il giocatore " + player1.getName() + " evoca per primo\n");
			evocazione(player1);
			evocazione(player2);
		}
		else
		{
			System.out.println("Il giocatore " + player2.getName() + " evoca per primo\n");
			evocazione(player2);
			evocazione(player1);
		}
		scontro();
	}
	
	/**
	 * Stampa il risultato della partita
	 */
	private void risultato ()
	{
		if(player1.getGolemList().size() == 0 && player2.getGolemList().size() == 0) //Nel raro caso di un pareggio
		{
			System.out.println("\nLo scontro è un pareggio!");
		}
		else
		{
			//Verifichiamo quale dei due giocatori ha vinto
			if (player1.getGolemList().size() == 0)
			{
				System.out.println("Il giocatore " + player2.getName() + " ha vinto lo scontro");
			}
			else
			{
				if (player2.getGolemList().size() == 0)
				{
					System.out.println("Il giocatore " + player1.getName() + " ha vinto lo scontro");
				}
			}
		}
		InputDati.leggiStringa("\nPremi invio per passare al menu di fine partita...");
		Utility.clearScreen();
		Menu.nuovaPartita(); //chiamo il menu per gestire una nuova partita
	}
	
	/**
	 * gestisce tutto lo scontro tra i due giocatori
	 * finchè ci sono golem disponibili
	 */
	public void scontro()
	{
		while (player1.getGolemList().size() > 0 && player2.getGolemList().size() > 0)
		{        // cicla finché ci sono golem da evocare
			if (player1.getGolemInCampo().getStones().equals((player2.getGolemInCampo().getStones()))) //controlla se le pietre dei golem sono esattamente uguali e nello stesso ordine
			{
				player1.getGolemInCampo().setVita(0);
				player2.getGolemInCampo().setVita(0);
				System.out.println("I golem hanno le stesse pietre nello stesso ordine, il round è in parità, i golem possono riposarsi");
				player1.getGolemList().remove(player1.getGolemInCampo());
				player2.getGolemList().remove(player2.getGolemInCampo());
				if (player1.getGolemList().size() == 0 || player2.getGolemList().size() == 0) // fermo lo scontro se è l'ultimo golem di uno dei due gicoatori
					break;
				evocazione(player1);
				evocazione(player2);
			}
			else
			{
				while (player1.getGolemInCampo().getVita() > 0 && player2.getGolemInCampo().getVita() > 0)
				{    // cicla finché non c'è un golem con vita <=0
					attacco(player1.getGolemInCampo().getStones().get(0), player2.getGolemInCampo().getStones().get(0)); //lancio delle pietre
					giraPietre(player1.getGolemInCampo().getStones());
					giraPietre(player2.getGolemInCampo().getStones());
					if (player1.getGolemInCampo().getVita() > 0 && player2.getGolemInCampo().getVita() > 0) // se entrambi i golem sono ancora vivi stampa
						InputDati.leggiStringa("\nPremi invio per passare al prossimo lancio... ");
					System.out.println("\n\n");
				}
				if (player1.getGolemInCampo().getVita() <= 0)
					morteGolem(player1,player2);
				if (player2.getGolemInCampo().getVita() <= 0)
					morteGolem(player2,player1);
			}
		}
		risultato ();
	}
	
	/**
	 * metodo invocato alla morte di un golem
	 * @param p1 giocatore a cui è morto
	 * @param p2 giocatore che ha distrutto il golem
	 */
	private void morteGolem (Giocatore p1, Giocatore p2)
	{
		System.out.println("Il golem di " + p1.getName() + " è stato distrutto!");
		p1.getGolemList().remove(p1.getGolemInCampo());
		p2.setPunteggio(p2.getPunteggio()+1); //setta il punteggio del giocatore che ha distrutto il golem
		if (p1.getGolemList().size() == 0)
		{
			System.out.println("Il giocatore " + p1.getName() + " ha finito i golem");
			return;
		}
		System.out.println("Al giocatore " + p1.getName() + " rimangono " + p1.getGolemList().size() + " golem");
		InputDati.leggiStringa("\nPremere invio per passare all'evocazione...");
		Utility.clearScreen();
		evocazione(p1);
	}
	
	/**
	 * gestisce l'evocazione di un golem
	 * @param g giocatore che deve compiere l'evocazione
	 */
	public void evocazione(Giocatore g)
	{
		System.out.println("Evocazione del giocatore " + g.getName());
		System.out.print("Golem disponibili: ");
		for (int i = 0; i < g.getGolemList().size(); i++)   //stampa i golem disponibili del giocatore
		{
			if (i == g.getGolemList().size() - 1)
				System.out.print(g.getGolemList().get(i).getNome());
			else
				System.out.print(g.getGolemList().get(i).getNome() + ", ");
		}
		System.out.println();
		boolean trovato = false;
		String scelta;
		do
		{
			scelta = InputDati.leggiStringaNonVuota("Inserire il nome del golem da evocare: ").toUpperCase();
			for (Golem golem : g.getGolemList())
			{
				if (golem.getNome().toUpperCase().equals(scelta))
				{
					trovato = true;
					g.setGolemInCampo(golem);
				}
			}
			if (!trovato) System.out.println("Questo golem non è presente nella lista di questo giocatore");
		}
		while (!trovato);
		Utility.clearScreen();
		inserisciPietre(g.getGolemInCampo());
	}
	
	/**
	 * Questo metodo serve per gestire l'inserimento dello pietre per ogni golem
	 * @param g è il golem che deve ricevere le pietre
	 */
	public void inserisciPietre(Golem g)
	{
		System.out.println("Inserimento pietre del golem " + g.getNome() + "\n");
		for (int i = 0; i < nStonesInGolem; i++)
		{
			stampaPietre();
			String scelta;
			boolean trovato = false;
			do
			{
				scelta = InputDati.leggiStringaNonVuota("Quale pietra vuoi inserire? (nome dell'elemento)  ").toUpperCase();
				for (int j = 0; j < Equilibrio.getElementi().size(); j++)   //for che cerca l'elemento inserito
				{
					if (scelta.equals(nomiPietre.get(j).toUpperCase()))
					{
						trovato = true;
						if (numeroPietre.get(j) == 0)
						{
							System.out.println("\nQueste pietre sono finite");
							break;
						}
						numeroPietre.set(j, numeroPietre.get(j) - 1);  // aggiorna la quantità disponibile di pietre di questo tipo
						for (Elemento e : Equilibrio.getElementi()) // for che aggiunge la pietra al golem
						{
							if (e.getNome().equals(nomiPietre.get(j)))
							{
								g.getStones().add(e);
							}
						}
						
					}
				}
				if (!trovato) System.out.println("\nElemento non presente\n");
			}
			while (!trovato);
			Utility.clearScreen();
		}
		Utility.clearScreen();
	}
}
