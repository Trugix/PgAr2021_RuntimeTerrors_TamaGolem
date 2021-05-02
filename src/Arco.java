public class Arco
{
	private Nodo inizio,fine;
	int peso;

	public Arco(Nodo inizio, Nodo fine, int peso) {
		this.inizio = inizio;
		this.fine = fine;
		this.peso = peso;
	}

	public Nodo getInizio() {
		return inizio;
	}

	public void setInizio(Nodo inizio) {
		this.inizio = inizio;
	}

	public Nodo getFine() {
		return fine;
	}

	public void setFine(Nodo fine) {
		this.fine = fine;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public void stampaArco ()
	{
		System.out.println("Arco:  "+"inizio: "+this.inizio.getNome()+" fine: "+this.fine.getNome()+" peso: "+this.peso);
	}
}
