public class Arco
{
	private Nodo inizio, fine;
	private int peso;
	private boolean fixed = false; //attributo che viene usato nel bilanciamento, quando true l'arco non viene più cambiato
	
	public Arco(Nodo inizio, Nodo fine, int peso)
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
	
	public Nodo getInizio()
	{
		return inizio;
	}
	
	public void setInizio(Nodo inizio)
	{
		this.inizio = inizio;
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
	
	public Nodo getFine()
	{
		return fine;
	}
	
	public void setFine(Nodo fine)
	{
		this.fine = fine;
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
		System.out.println("Arco:  " + "inizio: " + this.inizio.getId() + " fine: " + this.fine.getId() + " peso: " + this.peso);
	}
	
	/**
	 * due archi sono uguali quando collegano gli stessi nodi
	 * @param a
	 * @return
	 */
	public boolean archiUguali(Arco a)
	{
		if ((this.inizio.equals(a.getInizio())  && this.fine.equals(a.getFine())) || (this.inizio.equals(a.getFine()) && this.fine.equals(a.getInizio())))
			return true;
		else
			return false;
	}
}
