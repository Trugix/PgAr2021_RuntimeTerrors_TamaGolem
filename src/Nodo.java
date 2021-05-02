import java.util.ArrayList;

public class Nodo
{
	private String nome;
	private ArrayList<Arco> contatti = new ArrayList<Arco>();
	private int id;
	
	public Nodo(String nome, int id)
	{
		this.nome = nome;
		this.id = id;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
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
		System.out.println("Nodo: " + nome);
		for (Arco c : contatti)
		{
			c.stampaArco();
		}
		System.out.println("\n");
	}
}
