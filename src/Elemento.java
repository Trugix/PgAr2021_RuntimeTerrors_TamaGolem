import it.unibs.fp.mylib.BelleStringhe;

public class Elemento {
    private String nome;
    private Nodo nodo;

    public Elemento(Nodo n) {
        this.nome = BelleStringhe.pickAnElement();
        this.nodo = n;
    }
}
