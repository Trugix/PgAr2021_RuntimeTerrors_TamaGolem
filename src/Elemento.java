public class Elemento extends Nodo{
    private String nome;

    public Elemento(int id, String nome)
    {
        super(id);
        this.nome = nome;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    public String getNome()
    {
        return nome;
    }
}