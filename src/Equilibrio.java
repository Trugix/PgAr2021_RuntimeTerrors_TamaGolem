import it.unibs.fp.mylib.*;

import java.util.*;

public class Equilibrio
{
	private static final ArrayList<Nodo> nodi = new ArrayList<>();  //lista di tutti i nodi
	private static ArrayList<Arco> archi = new ArrayList<>();       //lista di tutti gli archi(indipendente da nodi)
	
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
			nodi.add(new Nodo(i));
		}
		for (Nodo n : nodi) //scorro i nodi
		{
			for (Nodo n2 : nodi) //scorro gli altri nodi
			{
				if (n.equals(n2)) continue; //skippa se stesso
				else
				{
					trovato=false;
					Arco a = new Arco(n, n2,0);
					for (int j = 0; j < archi.size(); j++)
					{
						if(archi.get(j).archiUguali(a)) //se trova che a è uguale o opposto ad un arco già creato dopo non lo aggiunge alla lista
						{
							trovato = true;
							break;
						}
					}
					if (!trovato)   //se non è stato trovato un arco uguale lo aggiunge alla lista degli archi globali e alla lista degli archi del nodo corrente
					{
						archi.add(a);
						n.setArray(new Arco (a));
						a.getFine().setArray(new Arco (a)); //aggiunge anche il suo opposto, ovvero se aggiunge 0,1 aggiunge anche 1,0
						trovato = false;
					}
				}
			}
		}
		for (Arco a : archi)
		{
			a.stampaArco();
		}
		riordinaNodi();
		equilibraNodi2();
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
						numero = NumeriCasuali.estraiIntero(1, pesoMax) * NumeriCasuali.testaOcroce();
					}while(secondToLast && (pesoForte+pesoDebole==numero || pesoForte+pesoDebole==-numero));  //todo bilanciare le sfere
					a.setPeso(numero);
					if (a.getPeso() > 0) pesoForte += a.getPeso();
					else pesoDebole += a.getPeso();
				}
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
	
	public static void equilibraNodi2 ()
	{
		int [] vettore = new int[nodi.size()-1];
		for (Nodo n: nodi)
		{
			int c=0,somma=0,i;
			for (Arco a: n.getContatti())
			{
				if (a.isFixed())
				{
					somma += vettore [c] = a.getPeso();
					c++;
				}
			}
			if (c==n.getContatti().size()-1) break;
			for (i=c;i<n.getContatti().size();i++)
			{
				somma += vettore [i]= NumeriCasuali.estraiIntero(1, 8) * NumeriCasuali.testaOcroce();;
			}
			i=c;
			do
			{
				if (i == n.getContatti().size() - 1) i = c;
				if (somma>0)
				{
						vettore[i]--;
						somma--;
				}
				if (somma<0)
				{
						vettore[i]++;
						somma++;
				}
				i++;
			}
			while (somma != 0);
			/*for (i=c;i<n.getContatti().size();i++)
			{
				if (n.getContatti().get(i).getPeso()==0)
				{
					for (int j=c;j<n.getContatti().size();j++)
					{
						if (n.getContatti().get(j).getPeso() > 1 )  //robbbba che come sempre non funziona
						{
							n.getContatti().get(i).setPeso(n.getContatti().get(i).getPeso()+1);
							n.getContatti().get(j).setPeso(n.getContatti().get(j).getPeso()-1);
						}
						if (n.getContatti().get(j).getPeso() < -1 )
						{
							n.getContatti().get(i).setPeso(n.getContatti().get(i).getPeso()-1);
							n.getContatti().get(j).setPeso(n.getContatti().get(j).getPeso()+1);
						}
					}
				}
			}*/
			
			for (i=c;i<n.getContatti().size();i++)
			{
				n.getContatti().get(i).setPeso(vettore[i]);
				n.getContatti().get(i).setFixed(true);
			}
			
			for (Arco a: n.getContatti())
			{
				for (Nodo n2 : nodi)
				{
					if (n2.getId() == n.getId())
						continue;
					for (Arco a2 : n2.getContatti())
					{
						if (a2.archiUguali(a))
						{
							a2.setFixed(true);
							a2.setPeso((-1) * a.getPeso());
						}
					}
				}
			}
		}
	}
	
	public static void equilibraNodi3 ()
	{
		boolean [] vettore = new boolean[nodi.size()-1];
		int forte, debole, numF, numD, contaF=1, contaD=1;
		for (Nodo n: nodi)
		{
			forte = NumeriCasuali.estraiIntero(0,nodi.size()-1);
			vettore[forte] = true;
			do
			{
				debole = NumeriCasuali.estraiIntero(0,nodi.size()-1);
				vettore[debole] = false;
			}while(debole == forte);
			n.getContatti().get(forte).setFixed(true);
			n.getContatti().get(debole).setFixed(true);
			for (int i=0; i<n.getContatti().size(); i++)
			{
				if(!n.getContatti().get(i).isFixed())
				{
					if(NumeriCasuali.testaOcroce()>0)
					{
						vettore[i] = true;
					}
					else
					{
						vettore[i] = false;
					}
				}
				numF = numForte(vettore);
				numD = n.getContatti().size()-numF;
				for(int conta=1; conta<n.getContatti().size(); conta++)
				{
					if(numF < numD)
					{
						if(numF == conta)
						{
							n.getContatti().get(forte).setPeso(NumeriCasuali.estraiIntero((int)Math.ceil((double)(n.getContatti().size()-conta)/(double)numF), 10));
						}
						if(!(n.getContatti().get(i).isFixed()) && contaF < numF && vettore[i] == true)
						{
							contaF++;
							n.getContatti().get(i).setPeso(NumeriCasuali.estraiIntero((int)Math.ceil((double)(n.getContatti().size()-conta)/(double)numF), 10));
						}
						else
						{
							if(!(n.getContatti().get(i).isFixed()) && contaD < numD && vettore[i] == false)
							{
								contaD++;
								n.getContatti().get(i).setPeso(NumeriCasuali.estraiIntero(1	, 10));
							}
						}

					}
					if(numD < numF)
					{
						if(numD == conta)
						{
							n.getContatti().get(debole).setPeso(NumeriCasuali.estraiIntero(n.getContatti().size()-conta, 10));
						}
					}
				}

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
	
	public static int numForte(boolean v[])
	{
		int conta=0;
		for(int i=0; i<v.length; i++)
		{
			if(v[i] == true)
				conta++;
		}
		return conta;
	}
}
