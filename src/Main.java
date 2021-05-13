public class Main
{
	private static final String WELCOME = "Benvenuto al programma Tamagolem dei Runtime Terrors";
	public static void main(String[] args)
	{
		System.out.println(WELCOME);
		Reader.readGiocatori();
		Menu.ilMenu(); //Chiamo il big menu
	}
}
