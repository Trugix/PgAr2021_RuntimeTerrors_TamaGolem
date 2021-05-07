import java.util.ArrayList;

public class Giocatore
{
	private String name;
	private ArrayList<Golem> golemList = new ArrayList<>();
	private int punteggio = 0;
	private Golem golemInCampo;
	public String getName()
	{
		return name;
	}
	
	public int getPunteggio()
	{
		return punteggio;
	}
	
	public void setPunteggio(int punteggio)
	{
		this.punteggio = punteggio;
	}
	
	public Giocatore(String name)
	{
		this.name = name;
	}
	
	public void addToGolemList(Golem g)
	{
		golemList.add(g);
	}

	public ArrayList<Golem> getGolemList() {
		return golemList;
	}
}
