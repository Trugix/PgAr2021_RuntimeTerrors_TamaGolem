import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;

public class Writer
{
	/**
	 * scrive tutte sul file xml formattato in modo circa decente
	 */
	public static void writeOutput()
	{
		XMLOutputFactory xmlof = null;
		XMLStreamWriter xmlw = null;
		try
		{
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("Classifica/classifica.xml"), "utf-8");
			xmlw.writeStartDocument("utf-8", "1.0");
			xmlw.writeCharacters("\n");
			xmlw.writeStartElement("classifica"); // scrittura del tag radice <output>
			xmlw.writeCharacters("\n\t");
			xmlw.writeStartElement("giocatori");
			xmlw.writeCharacters("\n\t\t");
			int i = 0;
			for (Giocatore g : Menu.getGiocatori())
			{
				xmlw.writeStartElement("giocatore");
				xmlw.writeAttribute("id", Integer.toString(i));
				xmlw.writeCharacters("\n\t\t\t");
				xmlw.writeStartElement("nome");
				xmlw.writeCharacters(g.getName());
				xmlw.writeEndElement(); //</nome>
				xmlw.writeCharacters("\n\t\t\t");
				xmlw.writeStartElement("punteggio");
				xmlw.writeCharacters(Integer.toString(g.getPunteggio()));
				xmlw.writeEndElement(); //</punteggio
				xmlw.writeCharacters("\n\t\t");
				xmlw.writeEndElement(); //</giocatore>
				xmlw.writeCharacters("\n\t\t");
				i++;
			}
			xmlw.writeEndElement(); // </giocatori>
			xmlw.writeCharacters("\n");
			xmlw.writeEndElement();//</classifica>
			xmlw.writeEndDocument(); // scrittura della fine del documento
			xmlw.flush(); // svuota il buffer e procede alla scrittura
			xmlw.close(); // chiusura del documento e delle risorse impiegate
			System.out.println("Output generato correttamente, arrivederci");
		}
		catch (Exception e)
		{
			System.out.println("Errore nel writer:");
			System.out.println(e.getMessage());
		}
	}
	
	public static void resetGiocatori()
	{
		XMLOutputFactory xmlof = null;
		XMLStreamWriter xmlw = null;
		try
		{
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("Classifica/classifica.xml"), "utf-8");
			xmlw.writeStartDocument("utf-8", "1.0");
			xmlw.writeCharacters("\n");
			xmlw.writeStartElement("classifica"); // scrittura del tag radice <output>
			xmlw.writeCharacters("\n\t");
			xmlw.writeStartElement("giocatori");
			xmlw.writeCharacters("\n\t\t");
			String[] nomiCreatori = {"Ruggero","Edoardo", "Andrea"};
			for (int i=0; i<nomiCreatori.length;i++)
			{
				xmlw.writeStartElement("giocatore");
				xmlw.writeAttribute("id", Integer.toString(i));
				xmlw.writeCharacters("\n\t\t\t");
				xmlw.writeStartElement("nome");
				xmlw.writeCharacters(nomiCreatori[i]);
				xmlw.writeEndElement(); //</nome>
				xmlw.writeCharacters("\n\t\t\t");
				xmlw.writeStartElement("punteggio");
				xmlw.writeCharacters(Integer.toString(0));
				xmlw.writeEndElement(); //</punteggio
				xmlw.writeCharacters("\n\t\t");
				xmlw.writeEndElement(); //</giocatore>
			}
			xmlw.writeCharacters("\n\t\t");
			xmlw.writeEndElement(); // </giocatori>
			xmlw.writeCharacters("\n");
			xmlw.writeEndElement();//</classifica>
			xmlw.writeEndDocument(); // scrittura della fine del documento
			xmlw.flush(); // svuota il buffer e procede alla scrittura
			xmlw.close(); // chiusura del documento e delle risorse impiegate
			System.out.println("Output generato correttamente, arrivederci");
		}
		catch (Exception e)
		{
			System.out.println("Errore nel writer:");
			System.out.println(e.getMessage());
		}
	}
}