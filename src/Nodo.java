import java.util.ArrayList;

public class Nodo
{
	private String nome;
	private ArrayList <Arco> contatti = new ArrayList<Arco>();

	public Nodo(String nome)
	{
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setArray(Arco a)
	{
		contatti.add(a);
	}

	public ArrayList<Arco> getContatti()
	{
		return contatti;
	}

	public void stampaNodo ()
	{
		System.out.println("Nodo: "+nome);
		for (Arco c: contatti)
		{
			c.stampaArco();
		}
		System.out.println("\n");
	}
}
