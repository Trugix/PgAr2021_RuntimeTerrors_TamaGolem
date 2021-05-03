public class Arco
{
	private Nodo inizio, fine;
	private int peso;
	private boolean fixed = false;
	public Arco(Nodo inizio, Nodo fine, int peso)
	{
		this.inizio = inizio;
		this.fine = fine;
		this.peso = peso;
	}
	
	public Nodo getInizio()
	{
		return inizio;
	}
	
	public void setInizio(Nodo inizio)
	{
		this.inizio = inizio;
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
		System.out.println("Arco:  " + "inizio: " + this.inizio.getNome() + " fine: " + this.fine.getNome() + " peso: " + this.peso);
	}
	
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
	
	public boolean archiUguali(Arco a)
	{
		if ((this.inizio.equals(a.getInizio())  && this.fine.equals(a.getFine())) || (this.inizio.equals(a.getFine()) && this.fine.equals(a.getInizio())))
			return true;
		else
			return false;
	}
}
