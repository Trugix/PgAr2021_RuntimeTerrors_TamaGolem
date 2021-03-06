public class Elemento extends Nodo{
	private String nome;
	
	public Elemento(int id, String nome)
	{
		super(id);
		this.nome = nome;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public void stampaElemento()
	{
			System.out.println("Elemento: " + this.nome);
			for (Arco c : this.getContatti())
			{
				c.stampaArco();
			}
			System.out.println("\n");
	}
	
}