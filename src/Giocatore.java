import java.util.ArrayList;

public class Giocatore
{
	private String name;
	private ArrayList<Golem> golemList = new ArrayList<>();
	
	public Giocatore(String name)
	{
		this.name = name;
	}
	
	public void addToGolemList(Golem g)
	{
		golemList.add(g);
	}
}
