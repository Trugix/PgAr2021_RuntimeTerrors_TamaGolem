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

	public  Battaglia(Giocatore p1, Giocatore p2)
	{
		this.player1 = p1;
		this.player2 = p2;
	}
	public Battaglia(Giocatore p1, Giocatore p2, int elements){
	this.nElements =elements;
	this.player1=p1;
	this.player2=p2;
	this.nStonesInGolem = ((int) Math.ceil(((double)elements + 1.0) / 3.0) + 1);
	this.nGolems = (int) Math.ceil((double)((elements-1)*(elements-2))/(double)(2* nStonesInGolem));
	this.nSpareStones = (int) Math.ceil(2*(double)(nGolems * nStonesInGolem)/(double)elements)*elements;
	this.nSpareStonesforElement = (int) Math.ceil(2*(double)(nGolems * nStonesInGolem)/(double)elements);
	}
	
	public  Giocatore getPlayer1()
	{
		return player1;
	}
	
	public  Giocatore getPlayer2()
	{
		return player2;
	}

	public  boolean cercaGolemNome(String nome)
	{
		for (Golem g: player1.getGolemList())
		{
				if (g.getNome().equals(nome))	return true;
		}
		for (Golem g: player2.getGolemList())
		{
			if (g.getNome().equals(nome))	return true;
		}
		return false;
	}
	
	public void attacco(Elemento pietreA, Elemento pietreB)
	{
		for (int i=0; i< pietreA.getContatti().size();i++)
		{
			if (pietreA.getContatti().get(i).getFine().getId() == pietreB.getId())
			{
				if (pietreA.getContatti().get(i).getPeso() > 0)
				{
					player2.getGolemInCampo().setVita(player2.getGolemInCampo().getVita() - pietreA.getContatti().get(i).getPeso());
					System.out.println("Il golem " + player2.getGolemInCampo().getNome() + " subisce " + pietreA.getContatti().get(i).getPeso());
					if(player2.getGolemInCampo().getVita()>0)
						System.out.println("Vita rimanente del golem " + player2.getGolemInCampo().getNome() + ": " + player2.getGolemInCampo().getVita());
					else
					{
						System.out.println("Il golem: " + player2.getGolemInCampo().getNome() + " is no more");
						player2.getGolemInCampo().setIsMorto(true);
					}
				}
				else
				{
					player1.getGolemInCampo().setVita(player1.getGolemInCampo().getVita() - pietreB.getContatti().get(i).getPeso());
					System.out.println("Il golem " + player1.getGolemInCampo().getNome() + " subisce " + pietreB.getContatti().get(i).getPeso());
					System.out.println("Vita rimanente del golem " + player1.getGolemInCampo().getNome() + ": " + player1.getGolemInCampo().getVita());
					if(player1.getGolemInCampo().getVita()>0)
						System.out.println("Vita rimanente del golem " + player1.getGolemInCampo().getNome() + ": " + player1.getGolemInCampo().getVita());
					else
					{
						System.out.println("Il golem: " + player1.getGolemInCampo().getNome() + " is no more");
						player1.getGolemInCampo().setIsMorto(true);
					}
				}
				break;
			}
		}
	}
	
	
	public ArrayList<Elemento> giraPietre(ArrayList<Elemento> pietre)
	{
		Elemento temp;
		temp = pietre.get(0);
		for (int i=0; i<pietre.size()-1; i++)
		{
			pietre.set(i, pietre.get(i+1));
		}
		pietre.set(pietre.size()-1, temp);
		return pietre;
	}
}
