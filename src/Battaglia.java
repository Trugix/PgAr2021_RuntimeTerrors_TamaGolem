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

	}


	public ArrayList<Elemento> sceltaPietre(ArrayList<Elemento> pietre)
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
