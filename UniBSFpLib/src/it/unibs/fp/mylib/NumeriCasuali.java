package it.unibs.fp.mylib;

import java.util.*;

public class NumeriCasuali {
	
	private static Random estrattore = new Random();
	
	public static int estraiIntero(int min, int max)
	{
		 int range = max + 1 - min;
		 int casual = estrattore.nextInt(range);
		 return casual + min;
	}
	
	public static double estraiDouble(double min, double max)
	{
	 double range = max - min;
	 double casual = estrattore.nextDouble();
	 
	 double posEstratto = range*casual;
	 
	 return posEstratto + min;
	}
	
	public static int testaOcroce ()
	{
		int risultato = estraiIntero(0,1);
		if (risultato == 0) return -1;
		else return risultato;
	}
	
	public static int testaOcroce (double probabilitaPos)
	{
		double risultato = estraiIntero(0,100);
		risultato = Math.floor(risultato*(1+probabilitaPos));
		if (risultato <=49) return -1;
		else return 1;
	}

}
