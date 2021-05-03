
public class Battaglia {
	private static int elements;
	private static int stones;
	private static int golems;
	private static int spareStones;
	private static int spareStonesforElement;
	private static Giocatore player1;
	private static Giocatore player2;

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

}
