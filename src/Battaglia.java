import java.util.ArrayList;

public class Battaglia {
	private int elements;
	private int stones;
	private int golems;
	private int spareStones;
	private int spareStonesforElement;
	private Giocatore player1;
	private Giocatore player2;

	public  Battaglia(Giocatore p1, Giocatore p2)
	{
		this.player1 = p1;
		this.player2 = p2;
	}
	public Battaglia(Giocatore p1, Giocatore p2, int elements){
	this.elements=elements;
	this.player1=p1;
	this.player2=p2;
	this.stones = ((int) Math.ceil(((double)elements + 1.0) / 3.0) + 1);
	this.golems = (int) Math.ceil((double)((elements-1)*(elements-2))/(double)(2*stones));
	this.spareStones = (int) Math.ceil(2*(double)(golems*stones)/(double)elements)*elements;
	this.spareStonesforElement = (int) Math.ceil(2*(double)(golems*stones)/(double)elements);
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

	public void attacco(ArrayList<Elemento> pietreA, ArrayList<Elemento> pietreB)
	{

	}


	public ArrayList<Elemento> sceltaPietre(ArrayList<Elemento> pietre)
	{
		Elemento temp;
		temp = pietre.get(0);
		for (int i=0; i<pietre.size(); i++)
		{
			pietre.set(i, pietre.get(i+1));
		}
		pietre.set(pietre.size()-1, temp);
		return pietre;
	}
}
