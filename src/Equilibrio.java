import it.unibs.fp.mylib.*;

import java.util.*;

public class Equilibrio
{
	private static final ArrayList<Elemento> elementi = new ArrayList<>();  //lista di tutti i nodi
	private static ArrayList<Arco> archi = new ArrayList<>();       //lista di tutti gli archi(indipendente da nodi)


	public static ArrayList<Elemento> getElementi()
	{
		return elementi;
	}

	/**
	 * cerca se esiste già un elemento con questo nome
	 * @param nome	il nome da cercare
	 * @return true se esiste già
	 */
	private static boolean cercaEle(String nome)
	{
		for (Elemento e: elementi)
		{
			if(e.getNome().equals(nome))
				return true;
		}
		return false;
	}

	/**
	 * genera tutti i nodi, e tutti gli associamenti, poi associa a ogni nodo i suoi collegamenti con gli altri nodi
	 * @param nElements il numero di nodi da generare
	 */
	public static void generaEquilibrio(int nElements)
	{
		boolean trovato;
		for (int i = 0; i < nElements; i++)
		{
			String nomeEle;
			do
			{
				nomeEle = BelleStringhe.pickAnElement();
			}while(cercaEle(nomeEle));
			Elemento e = new Elemento(i, nomeEle);
			elementi.add(e);
		}
		for (Elemento n : elementi) //scorro i nodi
		{
			for (Elemento n2 : elementi) //scorro gli altri nodi
			{
				if (!n.equals(n2))//Entra solo se non è se stesso
				{
					trovato = false;
					Arco a = new Arco(n, n2, 0);
					for (Arco arco : archi)
					{
						if (arco.archiUguali(a)) //se trova che a è uguale o opposto ad un arco già creato dopo non lo aggiunge alla lista
						{
							trovato = true;
							break;
						}
					}
					if (!trovato)   //se non è stato trovato un arco uguale lo aggiunge alla lista degli archi globali e alla lista degli archi del nodo corrente
					{
						archi.add(a);
						n.setArray(new Arco(a));
						a.getFine().setArray(new Arco(a)); //aggiunge anche il suo opposto, ovvero se aggiunge 0,1 aggiunge anche 1,0
					}
				}
			}
		}
		riordinaNodi();
		equilibraNodi2();
	}
	

	
	/*public static void equilibraNodi()
	{
		int pesoMax = 8, pesoForte = 0, pesoDebole = 0;
		double bonusPos = 0;
		boolean last = false, secondToLast = false;
		for (Elemento n : elementi)
		{
			int i = 0;
			pesoForte = 0;
			pesoDebole = 0;
			for (Arco a : n.getContatti())
			{
				
				if (i == n.getContatti().size() - 1) last = true;
				if (i == n.getContatti().size() - 2) secondToLast = true;
				if (a.isFixed())
				{
					if (a.getPeso() > 0) pesoForte += a.getPeso();
					else pesoDebole += a.getPeso();
				}
				if (!a.isFixed() && !last)
				{
					int numero;
					do
					{
						numero = NumeriCasuali.estraiIntero(1, pesoMax) * NumeriCasuali.testaOcroce();
					}
					while (secondToLast && (pesoForte + pesoDebole == numero || pesoForte + pesoDebole == -numero));  //todo bilanciare le sfere
					a.setPeso(numero);
					if (a.getPeso() > 0) pesoForte += a.getPeso();
					else pesoDebole += a.getPeso();
				}
				if (!a.isFixed() && last)
				{
					a.setPeso((-1) * (pesoForte + pesoDebole));
				}
				last = false;
				secondToLast = false;
				a.setFixed(true);
				for (Elemento n2 : elementi)
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
				i++;
			}
		}
	}*/
	
	/**
	 * Genera i danni che ogni nodo fa o riceve, controllando che non sia 0 od un numero troppo alto
	 */
	public static void equilibraNodi2()
	{
		int[] vettore = new int[elementi.size() - 1];
		int zero,max;
		do
		{
			for (Elemento n: elementi)          // nel caso si debba rigenerare l'equilibrio, rende gloi archi modificabili di nuovo
			{
				for (Arco a:n.getContatti())
				{
					a.setFixed(false);
				}
			}
			zero=1;
			max=0;
			for (Elemento n : elementi) //scorro gli elementi
			{
				int somma = 0, c = 0, i;
				for (i = 0; i < n.getContatti().size(); i++) vettore[i] = 0; //inizializzo il vettore
				
				for (Arco a : n.getContatti())  //scorro i collegamenti dell'elemento
				{
					if (a.isFixed()) //controllo quali numeri vanno modificati e quali no
					{
						somma += vettore[c] = a.getPeso(); //sommo i numeri che non posso modificare
						c++; //punto di partenza da dove posso iniziare a modificare i numeri
					}
				}
				for (i = c; i < n.getContatti().size(); i++) //scorro i numeri che posso modificare
				{
					somma += vettore[i] = NumeriCasuali.estraiIntero(-10, 10); //genera casualmente i valori del vettore
				}
				i = c;
				do
				{           // cerco di avere la somma dei numeri = 0 impedendo che un numero sia troppo alto o troppo basso
					if (i == n.getContatti().size()) i = c;
					if (somma > 0) //se la somma è troppo alta abbasso qualche numero
					{
						vettore[i]--;
						somma--;
					}
					if (somma < 0) //se la somma è troppo bassa alzo qualche numero
					{
						vettore[i]++;
						somma++;
					}
					i++;
				}
				while (somma != 0); //finché non è equilibrato ripete il procedimento

				vettore = sistemaZeri(n, vettore, c);
				setPesoArchi(n, vettore);
				sistemaOpposti(n);
			}
			for (Elemento n : elementi)
			{
				for (Arco a:n.getContatti())
				{
					if ((a.getPeso()==0)) zero =0;   //controllo che non ci siano interazioni che fanno danno 0 o che bi-shottino l'avversario.
					if(a.getPeso()>max) max=a.getPeso();
				}
			}
		}
		while (zero==0 || max>=Golem.VITA_MAX/2); //nel caso estremo in cui tutto il resto non sia riuscito a bilanciare il gioco l'equilibrio viene rigenerato.
													// è una cosa relativamente rara che non rallenta (non troppo) il codice e non dovrebbe verificarsi troppo spesso E.
													//leggi panick button R.
	}

	/**
	 * può succedere che nella generazione l'ultima arco (usato per equilibrare), sia zero, quindi questo metodo sistema questa evenienza
	 * @param n il nodo
	 * @param v i pesi degli archi
	 * @param c l'indice da cui iniziare a poter modificare (quelli prima sarranno già fixed)
	 * @return il vettore sistemato
	 */
	private static int[] sistemaZeri(Elemento n, int[] v, int c)
	{
		for (int j = c; j < n.getContatti().size(); j++)
		{
			if (v[j] == 0)
			{
				for (int k = c; k < n.getContatti().size(); k++)
				{
					if (v[k] != 1 && k != j)
					{
						v[k]--;
						v[j]++;
						break;
					}
				}
			}
		}
		return v;
	}

	/**
	 *
	 * @param n il nodo
	 * @param v il vettore dei pesi da settare agli archi del nodo
	 */
	private static void setPesoArchi(Elemento n, int[] v)
	{
		int i = 0;
		for (Arco a : n.getContatti())
		{
			if (!a.isFixed())
			{
				a.setPeso(v[i]);
				a.setFixed(true);
			}
			i++;
		}
	}

	/**
	 * setta gli archi opposti a quelli già generati (es. già generato e settato l'arco 0,1 con peso 3, allora setta anche 1,0 con peso -3)
	 * @param n il nodo
	 */
	private static void sistemaOpposti(Elemento n)
	{
		for (Arco a : n.getContatti())
		{
			for (Elemento n2 : elementi)
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
	
	/**
	 * preso un nodo ordino il suo vettore di archi in modo che gli archi partano tutti da esso e terminino in altri nodi.
	 */
	public static void riordinaNodi()
	{
		for (Elemento n : elementi)
		{
			for (Arco a : n.getContatti())
			{
				if (!(a.getInizio().getId() == n.getId()))
				{
					a.invertiInizioFine();
				}
			}
		}
	}
	
	public static void stampaEquilibrio ()
	{
		Utility.clearScreen();
		System.out.println("Ecco l'equilibrio di questa partita: \n");
		for (Elemento e: elementi)
		{
			e.stampaElemento();
		}
	}
	
}
