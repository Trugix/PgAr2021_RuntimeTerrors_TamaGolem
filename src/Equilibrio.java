import it.unibs.fp.mylib.*;
import java.util.*;

public class Equilibrio
{
	private static final ArrayList<Nodo> nodi = new ArrayList<>();
	//private static Set<Arco> archi = new HashSet<Arco>();
	private static ArrayList<Arco> archi = new ArrayList<>();
	
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return super.equals(obj);
	}
	
	public static void generaEquilibrio(/*numero elementi*/)
	{
		boolean entrato = false, trovato =false;
		int counter1 = 0, counter2 = 0, counterS = 0;
		for (int i = 0; i < 5; i++)
		{
			nodi.add(new Nodo(Integer.toString(i), i));
		}
		for (Nodo n : nodi) //scorro i nodi
		{
			for (Nodo n2 : nodi) // scorro gli altri nodi
			{
				if (n.equals(n2)) continue;
				else
				{
					trovato=false;
					Arco a = new Arco(n, n2,0);
					for (int j = 0; j < archi.size(); j++)
					{
						if(archi.get(j).archiUguali(a))
						{
							trovato = true;
							break;
						}
					}
					if (!trovato)
					{
						archi.add(a);
						n.setArray(a);
						a.getFine().setArray(a);
						trovato = false;
					}
				}
			}
		}
		for (Arco a : archi)
		{
			a.stampaArco();
		}
		for (Nodo a : nodi)
		{
			a.stampaNodo();
		}
	}
}
