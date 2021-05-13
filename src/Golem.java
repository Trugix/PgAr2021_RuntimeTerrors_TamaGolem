import java.util.ArrayList;

public class Golem
{
	public final static int VITA_MAX = 30;

	private String nome;
	private int vita;
	private ArrayList<Elemento> stones = new ArrayList<>();


	public Golem(String nome)
	{
		this.nome = nome;
		this.vita = VITA_MAX;
	}

	public String getNome() {
		return nome;
	}

	public int getVita() {
		return vita;
	}

	public void setVita(int vita) {
		this.vita = vita;
	}

	public ArrayList<Elemento> getStones() {
		return stones;
	}
}
