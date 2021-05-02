
public class Battaglia {
	private static int elements;
	private static int stones;
	private static int golems;
	private static int spareStones;
	private static int spareStonesforElement;
	private static String player1;
	private static String player2;

	public  Battaglia()
	{

	}
	public Battaglia(String player1, String player2, int elements){
	this.elements=elements;
	this.player1=player1;
	this.player2=player2;
	this.stones = ((int) Math.ceil(((double)elements + 1.0) / 3.0) + 1);
	this.golems = (int) Math.ceil((double)((elements-1)*(elements-2))/(double)(2*stones));
	this.spareStones = (int) Math.ceil(2*(double)(golems*stones)/(double)elements)*elements;
	this.spareStonesforElement = (int) Math.ceil(2*(double)(golems*stones)/(double)elements);
	}

}
