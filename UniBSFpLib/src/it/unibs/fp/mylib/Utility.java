package it.unibs.fp.mylib;

import java.util.*;

public class Utility
{
	/**
	 * Pulisce lo schermo
	 */
	public static void clearScreen()
	{
		System.out.println("\n\n\n\n\n\n\n\n\n\n");
	}
	
	/**
	 * Controlla se la stringa passata è un numero
	 *
	 * @param s
	 * @return
	 */
	public static boolean isNumeri(String s)
	{
		for (int i = 0; i < s.length(); i++)
		{
			if (!(Character.isDigit(s.charAt(i))))
				return false;
		}
		return true;
	}
	
	/**
	 * Controlla se la stringa è una lettera
	 *
	 * @param s
	 * @return
	 */
	public static boolean isLettere(String s)
	{
		
		for (int i = 0; i < s.length(); i++)
		{
			if (!(Character.isLetter(s.charAt(i))))
				return false;
		}
		return true;
	}
	
	/**
	 * Controlla se il carattere è una vocale
	 *
	 * @param c
	 * @return
	 */
	public static boolean isVocale(char c)
	{
		char[] Vocali = {'A', 'E', 'I', 'O', 'U'};
		for (int i = 0; i < Vocali.length; i++)
		{
			if (c == Vocali[i])
				return true;
		}
		return false;
	}
	
	public static int getIndex(Set<? extends Object> set, Object value)
	{
		int result = 0;
		for (Object entry : set)
		{
			if (entry.equals(value)) return result;
			result++;
		}
		return -1;
	}
}
