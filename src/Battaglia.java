import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.NumeriCasuali;
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
	 * inizializza le due liste di elementi equantità per l'inserimento delle pietre
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
		System.out.printf("%12s | %5s", "Elemento:", "Disponibili: \n");
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
	 * @param nome
	 * @return true se esiste
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
		System.out.println("Il golem " + player1.getGolemInCampo().getNome() + " lancia la pietra: " + pietreA.getNome());
		System.out.println("Il golem " + player2.getGolemInCampo().getNome() + " lancia la pietra: " + pietreB.getNome());
		System.out.println();
		
		for (int i = 0; i < pietreA.getContatti().size(); i++)
		{
			if (pietreA.getNome().equals(pietreB.getNome()))//controlla se sono lo stesso elemento
			{
				System.out.println("I due non interagiscono!");
				break;
			}
			if (pietreA.getContatti().get(i).getFine().getId() == pietreB.getId())
			{
				if (pietreA.getContatti().get(i).getPeso() > 0)
				{
					int danno = Math.abs(pietreB.getContatti().get(i).getPeso());
					aggiornaVita(player2.getGolemInCampo(), danno);
				}
				else
				{
					int danno = Math.abs(pietreB.getContatti().get(i).getPeso());
					aggiornaVita(player1.getGolemInCampo(), danno);
				}
				break;
			}
		}
	}
	
	/**
	 * aggiorna la vita del golem che prende danno
	 * @param golem
	 * @param danno
	 */
	private void aggiornaVita(Golem golem, int danno)
	{
		golem.setVita(golem.getVita() - danno);
		System.out.println("Il golem " + golem.getNome() + " subisce " + danno);
		if (golem.getVita() > 0)
			System.out.println("Vita rimanente del golem " + golem.getNome() + ": " + golem.getVita());
		else
			System.out.println("Il golem " + golem.getNome() + " is no more");
	}
	
	public ArrayList<Elemento> giraPietre(ArrayList<Elemento> pietre)
	{
		Elemento temp;
		temp = pietre.get(0);
		for (int i = 0; i < pietre.size() - 1; i++)
		{
			pietre.set(i, pietre.get(i + 1));
		}
		pietre.set(pietre.size() - 1, temp);
		return pietre;
	}
	
	public void start()
	{
		System.out.println("Numero di elementi: " + nElements + "\nNumero di pietre totali: " + nSpareStones + "\nNumero di pietre per ogni elemento: " + nSpareStonesforElement + "\nNumero di golem per giocatore: " + nGolems + "\nNumero di pietre per golem: " + nStonesInGolem + "\nVita dei golem: " + Golem.VITA_MAX + "\n\n");
		InputDati.leggiStringaNonVuota("\nPremere invio per iniziare lo scontro....");
		if (NumeriCasuali.testaOcroce() == 1)
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
		
		//todo rivedere classifica e punteggi
		if(player1.getGolemList().size() == 0 && player2.getGolemList().size() == 0)
		{
			System.out.println("Lo scontro è un pareggio!");
		}
		else
		{
			if (player1.getGolemList().size() == 0)
			{
				System.out.println("Il giocatore " + player2.getName() + " ha vinto lo scontro!");
				player2.setPunteggio(player2.getPunteggio() + player2.getGolemList().size());
			}
				else
			{
				if (player2.getGolemList().size() == 0)
				{
					System.out.println("Il giocatore " + player1.getName() + " ha vinto lo scontro!");
					player2.setPunteggio(player2.getPunteggio() + player2.getGolemList().size());
				}
			}
		}
		InputDati.leggiStringa("Premi invio per passare al menu di fine partita...");
		Utility.clearScreen();
		Menu.nuovaPartita();
	}
	
	public void scontro()
	{
		while (player1.getGolemList().size() > 0 && player2.getGolemList().size() > 0)
		{        // cicla finché ci sono golem
			boolean pari = false;
			if (player1.getGolemInCampo().getStones().equals((player2.getGolemInCampo().getStones())))
			{
				pari = true;
				player1.getGolemInCampo().setVita(0);
				player2.getGolemInCampo().setVita(0);
				System.out.println("I golem hanno le stesse pietre nello stesso ordine, il round è in parità, i golem possono riposarsi");
				player1.getGolemList().remove(player1.getGolemInCampo());
				player2.getGolemList().remove(player2.getGolemInCampo());
				if (player1.getGolemList().size() == 0 || player2.getGolemList().size() == 0)
					break;
				evocazione(player1);
				evocazione(player2);
				
			}
			else
			{
				while (player1.getGolemInCampo().getVita() > 0 && player2.getGolemInCampo().getVita() > 0)
				{    // cicla finché non c'è un golem con vita <=0
					attacco(player1.getGolemInCampo().getStones().get(0), player2.getGolemInCampo().getStones().get(0));
					giraPietre(player1.getGolemInCampo().getStones());
					giraPietre(player2.getGolemInCampo().getStones());
					if (player1.getGolemInCampo().getVita() > 0 || player2.getGolemInCampo().getVita() > 0)
						InputDati.leggiStringa("\nPremi invio per passare al prossimo lancio... ");
				}
				if (player1.getGolemInCampo().getVita() <= 0)
				{
					System.out.println("Il golem di " + player1.getName() + " è stato distrutto!");
					player1.getGolemList().remove(player1.getGolemInCampo());
					if (player1.getGolemList().size() == 0)
					{
						System.out.println("Il giocatore " + player1.getName() + " ha finito i golem");
						break;
					}
					else
					{
						System.out.println("Al giocatore " + player1.getName() + " rimangono " + player1.getGolemList().size() + "golem");
					}
					InputDati.leggiStringa("Premere invio per passare all'evocazione");
					Utility.clearScreen();
					evocazione(player1);
				}
				if (player2.getGolemInCampo().getVita() <= 0)
				{
					System.out.println("Il golem di " + player2.getName() + " è stato distrutto!");
					player2.getGolemList().remove(player2.getGolemInCampo());
					if (player2.getGolemList().size() == 0)
					{
						System.out.println("Il giocatore " + player2.getName() + " ha finito i golem");
						break;
					}
					else
					{
						System.out.println("Al giocatore " + player2.getName() + " rimangono " + player2.getGolemList().size() + "golem");
					}
					InputDati.leggiStringa("Premere invio per passare all'evocazione");
					Utility.clearScreen();
					evocazione(player2);
				}
			}
		}
	}
	
	
	public void evocazione(Giocatore g)
	{
		System.out.println("Evocazione del giocatore: " + g.getName());
		System.out.print("Golem disponibili: ");
		for (int i = 0; i < g.getGolemList().size(); i++)
		{
			if (i == g.getGolemList().size() - 1)
				System.out.print(g.getGolemList().get(i).getNome());
			else
				System.out.print(g.getGolemList().get(i).getNome() + ", ");
		}
		System.out.println("");
		boolean pronto = false;
		String scelta;
		do
		{
			scelta = InputDati.leggiStringaNonVuota("Inserire il nome del golem da evocare: ").toUpperCase();
			for (Golem golem : g.getGolemList())
			{
				if (golem.getNome().toUpperCase().equals(scelta))
				{
					pronto = true;
					g.setGolemInCampo(golem);
				}
			}
			if (!pronto) System.out.println("Questo golem non è presente nella lista di questo giocatore");
		}
		while (!pronto);
		Utility.clearScreen();
		inserisciPietre(g.getGolemInCampo());
	}
	
	public void inserisciPietre(Golem g)
	{
		System.out.println("Inserimento pietre del golem: " + g.getNome() + "\n");
		for (int i = 0; i < nStonesInGolem; i++)
		{
			stampaPietre();
			String scelta;
			boolean trovato = false;
			do
			{
				scelta = InputDati.leggiStringaNonVuota("Quale pietra vuoi inserire?: (nome dell'elemento)").toUpperCase();
				for (int j = 0; j < Equilibrio.getElementi().size(); j++)
				{
					if (scelta.equals(nomiPietre.get(j).toUpperCase()))
					{
						if (numeroPietre.get(j) == 0)
						{
							System.out.println("\nQueste pietre sono finite");
							break;
						}
						trovato = true;
						numeroPietre.set(j, numeroPietre.get(j) - 1);
						for (Elemento e : Equilibrio.getElementi())
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
		}
		Utility.clearScreen();
	}
}
