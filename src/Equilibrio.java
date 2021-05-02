import it.unibs.fp.mylib.EstrazioniCasuali;
import java.util.ArrayList;

public class Equilibrio
{
	private static ArrayList<Nodo> nodi = new ArrayList<Nodo>();
	private static ArrayList<Arco> archi = new ArrayList<Arco>();

	public static void coso ()
	{
		boolean entrato =false,primo=true;
		for (int i=0;i<5;i++)
		{
			nodi.add(new Nodo(Integer.toString(i)));
		}
		for (int i=0;i<5;i++)
		{
			Nodo n = nodi.get(i);
			for (int c=0;c<5;c++)
			{
				if (n.equals(nodi.get(c))) continue;
				else
				{
				/*	Arco a = new Arco(n, nodi.get(c), EstrazioniCasuali.estraiIntero(0, 3));
					n.setArray(a);   //funziona circa
					archi.add(a);*/

					if (primo)
					{
						Arco a = new Arco(n, nodi.get(c), EstrazioniCasuali.estraiIntero(0, 3));
						n.setArray(a);
						archi.add(a);
					}
					else
					{
						for (int j = 0; j< archi.size(); j++)
						{
							if (n.getNome().equals(archi.get(j).getFine()))
							{
								Arco a = new Arco(n, archi.get(j).getInizio(),archi.get(j).getPeso());
								n.setArray(a);
								archi.add(a);
							}
							else
							{
								Arco a = new Arco(n, nodi.get(c), EstrazioniCasuali.estraiIntero(0, 3));
								n.setArray(a);
								archi.add(a);
							}
						}
					}
					primo=false;








					/*for (int j=0;j< archi.size();j++)
					{
						Arco archetti = archi.get(j);
						entrato = true;
						if (n.getNome().equals(archetti.getInizio().getNome()))
						{
							n.setArray(new Arco(nodi.get(c),n,archetti.getPeso()));
						}
						else
						{
							Arco a = new Arco(n, nodi.get(c), EstrazioniCasuali.estraiIntero(0, 3));
							n.setArray(a);
							archi.add(a);
						}
					}
					if (!entrato)
					{
						Arco a = new Arco(n, nodi.get(c), EstrazioniCasuali.estraiIntero(0, 3));
						n.setArray(a);
						archi.add(a);
						entrato =false;
					}*/
				}
			}
		}
		for (Nodo n:nodi)
		{
			n.stampaNodo();
		}
	}
}
