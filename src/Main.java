public class Main
{
	private static final String WELCOME = "Benvenuto al programma Tamagolem dei Runtime Terrors\nN.B. premere invio non sempre funziona, nel caso scrivere un qualsiasi carattere e poi premere invio";
	public static void main(String[] args)
	{
		System.out.println(WELCOME);
		Reader.readGiocatori();
		Menu.ilMenu(); //Chiamo il big menu
	}
}
