import java.util.ArrayList;

public class Nodo
{
	private ArrayList<Arco> contatti = new ArrayList<Arco>();   //lista degli archi per ogni nodo
	private int id;
	
	public Nodo(int id)
	{
		this.id = id;
	}
	
	public Nodo(Nodo n)
	{
		this.id = n.getId();
	}
	
	public void setContatti(ArrayList<Arco> contatti)
	{
		this.contatti = contatti;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setArray(Arco a)
	{
		contatti.add(a);
	}
	
	public ArrayList<Arco> getContatti()
	{
		return contatti;
	}
	
	public void stampaNodo()
	{
		System.out.println("Elemento: " + id);
		for (Arco c : contatti)
		{
			c.stampaArco();
		}
		System.out.println("\n");
	}
}
