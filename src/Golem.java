import java.util.ArrayList;

public class Golem
{
	public final static int VITA_MAX = 30;

	private String nome;
	private int vita;
	private boolean isMorto = false;
	private ArrayList<Elemento> stones = new ArrayList<>();


	public Golem(String nome) {
		this.nome = nome;
		this.vita = VITA_MAX;
	}
	
	public boolean isMorto()
	{
		return isMorto;
	}
	
	public void setIsMorto(boolean isMorto)
	{
		this.isMorto = isMorto;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Elemento> getStones() {
		return stones;
	}
}
