import it.unibs.fp.mylib.EstrazioniCasuali;

import java.util.ArrayList;

public class Equilibrio
{
	private static final ArrayList<Nodo> nodi = new ArrayList<Nodo>();
	private static final ArrayList<Arco> archi = new ArrayList<Arco>();
	
	public static void coso()
	{
		boolean entrato = false, primo = true;
		int counter1 = 0, counter2 = 0, counterS = 0;
		for (int i = 0; i < 5; i++)
		{
			nodi.add(new Nodo(Integer.toString(i), i));
		}
		for (int i = 0; i < 5; i++)
		{
			Nodo n = nodi.get(i);
			for (int c = 0; c < 5; c++)
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
						for (int j = 0; j < archi.size() && counterS < 3; j++)
						{
							/*if (counterS>=3)
							{
								counterS=0;
								counter1 = 0;
								counter2 = 0;
								break;
							}*/
							if (n.getNome().equals(archi.get(j).getFine().getNome()) && counter1 < n.getId())
							{
								Arco a = new Arco(n, archi.get(j).getInizio(), archi.get(j).getPeso());
								n.setArray(a);
								archi.add(a);
								counter1++;
							}
							if (!n.getNome().equals(archi.get(j).getFine().getNome()) && counterS < 3)
							{
								Arco a = new Arco(n, nodi.get(c), EstrazioniCasuali.estraiIntero(0, 3));
								n.setArray(a);
								archi.add(a);
								counter2++;
							}
							counterS = counter1 + counter2;
						}
						counter1 = 0;
						counter2 = 0;
						counterS = 0;
						/*if (n.getNome().equals(archi.get(j).getFine())) continue;
						else {
							Arco a = new Arco(n, nodi.get(c), EstrazioniCasuali.estraiIntero(0, 3));
							n.setArray(a);
							archi.add(a);
						}*/
						
					}







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
			primo = false;
		}
		for (Nodo n : nodi)
		{
			n.stampaNodo();
		}
	}
}
