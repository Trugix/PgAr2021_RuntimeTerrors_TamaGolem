package it.unibs.fp.mylib;

import java.util.List;
import java.util.Arrays;

public class BelleStringhe {
    private final static String SPAZIO = " ";
    private final static String CORNICE = "---------------------------------------------------";
    private final static String ACAPO = "\n";
    private static final List<String> coolNames = Arrays.asList("Giorgio", "Golemmo", "Gormita", "Giangiotto", "Argillo", "Argillino", "Sparkly", "Margot", "Hottie", "Shocky", "Asdrubale", "Kali", "Kibly", "Lucky", "Rick_Astley","Jagiellon","Rex","Sissy", "Whiskey", "Lola", "Gretololo", "Rak","Dhaggloc","Volgaun","Valgred","Yomi","Ginon","Alachi","Elyim","Nelai","Eidi","Abrayal","Azamar","Sharia","Draahn","Desed","Zur","Pinkiva","Brad","Vly","Adamar","Raphael","Petra","Tarto");
    private static final List<String> Elements = Arrays.asList("Aria", "Acqua", "Terra", "Tuono", "Fuoco", "Etere", "Oscurità", "Morte", "Lotta", "Erba", "Luce", "Ghiaccio", "Psichico", "Spirito", "Legno", "Gravità", "Benedetto", "Maledetto", "Cristallo", "Acciaio", "Natura","Kebab","Ying","Yang","Polvere","Diamante","Oro","Tempo","Ganon","Flora","Fauna","Sonno","RGB_LED","Never_gonna_give_you_up","Batterio","Virus","Nulla","Kebab","Kebab","Stellare","Nuvola","Geometria","Algebra_lineare");

	/**
	 * prende un nome da una lista
	 * @return
	 */
	public static String pickAName() {
        return coolNames.get(NumeriCasuali.estraiIntero(0, coolNames.size() - 1));
    }
    
    public static List<String> getElements()
    {
        return Elements;
    }
    
    /**
	 * prende un nome da una lista
	 * @return
	 */
    public static String pickAnElement() {
        return Elements.get(NumeriCasuali.estraiIntero(0, Elements.size() - 1));
    }

    public static String incornicia(String s) {
        StringBuffer res = new StringBuffer();

        res.append(CORNICE + ACAPO);
        res.append(s + ACAPO);
        res.append(CORNICE + ACAPO);

        return res.toString();

    }


    public static String incolonna(String s, int larghezza) {
        StringBuffer res = new StringBuffer(larghezza);
        int numCharDaStampare = Math.min(larghezza, s.length());
        res.append(s.substring(0, numCharDaStampare));
        for (int i = s.length() + 1; i <= larghezza; i++)
            res.append(SPAZIO);
        return res.toString();
    }

    public static String centrata(String s, int larghezza) {
        StringBuffer res = new StringBuffer(larghezza);
        if (larghezza <= s.length())
            res.append(s.substring(larghezza));
        else {
            int spaziPrima = (larghezza - s.length()) / 2;
            int spaziDopo = larghezza - spaziPrima - s.length();
            for (int i = 1; i <= spaziPrima; i++)
                res.append(SPAZIO);

            res.append(s);

            for (int i = 1; i <= spaziDopo; i++)
                res.append(SPAZIO);
        }
        return res.toString();

    }

    public static String ripetiChar(char elemento, int larghezza) {
        StringBuffer result = new StringBuffer(larghezza);
        for (int i = 0; i < larghezza; i++) {
            result.append(elemento);
        }
        return result.toString();

    }

    public static String rigaIsolata(String daIsolare) {
        StringBuffer result = new StringBuffer();
        result.append(ACAPO);
        result.append(daIsolare);
        result.append(ACAPO);
        return result.toString();
    }

}

