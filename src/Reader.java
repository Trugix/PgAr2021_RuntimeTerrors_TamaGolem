import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;
import java.io.FileInputStream;

public class Reader
{
	
	private static String filename = "classifica.xml";
	
	/**
	 * legge il file delle persone, crea un'istanza di persona e la adda alla lista che le contiene
	 */
	public static void readGiocatori()
	{
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		try
		{
			String tagName = "";
			String nome = "";
			int punteggio = 0;
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(new FileInputStream("Classifica/" + filename));
			while (xmlr.hasNext())
			{ // continua a leggere finché ha eventi a disposizione
				switch (xmlr.getEventType())
				{ // switch sul tipo di evento
					case XMLStreamConstants.START_ELEMENT: // inizio di un elemento
						tagName = xmlr.getLocalName();
						break;
					case XMLStreamConstants.END_ELEMENT: // fine di un elemento
						if (xmlr.getLocalName().equals("giocatore"))
							Menu.getGiocatori().add(new Giocatore(nome, punteggio)); //quando arriva all'endtag costruisce la persona
						break;
					case XMLStreamConstants.CHARACTERS:
						String s = xmlr.getText();
						if (s.contains("\n") || s.equals("") || s.contains("\t")) //Questo if serve ad evitare che le varie stringhe vengano sostituite da "\n" e sue varianti tipo "\n "
							break;
						switch (tagName)
						{
							case "nome":
								nome = s;
								break;
							case "punteggio":
								punteggio = Integer.parseInt(s);
								break;
						}
						break;
				}
				xmlr.next();
			}
		}
		catch (Exception e)
		{
			System.out.println("Errore in Input Persone");
			System.out.println(e.getMessage());
		}
	}
}
