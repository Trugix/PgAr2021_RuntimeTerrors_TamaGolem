import it.unibs.fp.mylib.*;
import java.util.*;

public class Equilibrio
{
	private static final ArrayList<Nodo> nodi = new ArrayList<>();
	//private static Set<Arco> archi = new HashSet<Arco>();
	private static ArrayList<Arco> archi = new ArrayList<>();
	
	public static ArrayList<Nodo> getNodi()
	{
		return nodi;
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
						n.setArray(new Arco (a));
						a.getFine().setArray(new Arco (a));
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
	
	public static void equilibraNodi ()
	{
		int pesoMax = 8, pesoForte=0,pesoDebole=0;
		double bonusPos = 0;
		boolean last=false,secondToLast=false;
		for (Nodo n: nodi)
		{
			int i=0;
			pesoForte=0;
			pesoDebole=0;
			for (Arco a: n.getContatti())
			{
				
				if (i==n.getContatti().size()-1) last=true;
				if (i==n.getContatti().size()-2) secondToLast=true;
				if(a.isFixed())
				{
					if (a.getPeso() > 0) pesoForte += a.getPeso();
					else pesoDebole += a.getPeso();
				}
				if(!a.isFixed() && !last)
				{
					int numero;
					do
					{
						numero = NumeriCasuali.estraiIntero(1, pesoMax) * NumeriCasuali.testaOcroce(/*bonusPos*/);
					}while(secondToLast && (pesoForte+pesoDebole==numero || pesoForte+pesoDebole==-numero));  //todo bilanciare le sfere
					a.setPeso(numero);
					if (a.getPeso() > 0) pesoForte += a.getPeso();
					else pesoDebole += a.getPeso();
				}
				//bonusPos = Math.floor((pesoForte+pesoDebole)*100);
				if(!a.isFixed() && last)
				{
					a.setPeso((-1)*(pesoForte+pesoDebole));
				}
				last = false;
				secondToLast = false;
				a.setFixed(true);
				for (Nodo n2: nodi)
				{
					if (n2.getId()==n.getId())
						continue;
					for (Arco a2: n2.getContatti())
					{
						if(a2.archiUguali(a))
						{
							a2.setFixed(true);
							a2.setPeso((-1)*a.getPeso());
						}
					}
				}
				i++;
			}
		}
	}
	
	public static void riordinaNodi()
	{
		for (Nodo n: nodi)
		{
			for (Arco a: n.getContatti())
			{
				if(!(a.getInizio().getId() == n.getId()))
				{
					a.invertiInizioFine();
				}
			}
			
		}
	}
}
