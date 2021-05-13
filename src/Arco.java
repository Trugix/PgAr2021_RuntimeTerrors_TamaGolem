public class Arco
{
	private Elemento inizio, fine;
	private int peso;
	private boolean fixed = false; //attributo che viene usato nel bilanciamento, quando true l'arco non viene più cambiato
	
	public Arco(Elemento inizio, Elemento fine, int peso)
	{
		this.inizio = inizio;
		this.fine = fine;
		this.peso = peso;
	}
	
	public Arco(Arco a)
	{
		this.inizio = a.getInizio();
		this.fine = a.getFine();
		this.peso = a.getPeso();
		this.fixed = a.isFixed();
	}
	
	public Elemento getInizio()
	{
		return inizio;
	}
	
	/**
	 * inverte inizio e fine di un arco
	 */
	public void invertiInizioFine()
	{
		Arco a = new Arco(this);    //arco temporaneo
		this.inizio =a.getFine();
		this.fine = a.getInizio();
	}
	
	public void setFixed(boolean t)
	{
		this.fixed = t;
	}
	
	public boolean isFixed()
	{
		return fixed;
	}
	
	public Elemento getFine()
	{
		return fine;
	}
	
	public int getPeso()
	{
		return peso;
	}
	
	public void setPeso(int peso)
	{
		this.peso = peso;
	}
	
	public void stampaArco()
	{
		if (this.peso>0)
			System.out.println(this.inizio.getNome() + " fa danno " + Math.abs(this.peso) + " a " + this.fine.getNome());
		else
			System.out.println(this.inizio.getNome() + " subisce danno " + Math.abs(this.peso) + " da " + this.fine.getNome());
	}
	
	/**
	 * due archi sono uguali quando collegano gli stessi nodi
	 * @param a
	 * @return
	 */
	public boolean archiUguali(Arco a)
	{
		return (this.inizio.equals(a.getInizio()) && this.fine.equals(a.getFine())) || (this.inizio.equals(a.getFine()) && this.fine.equals(a.getInizio()));
	}
}
