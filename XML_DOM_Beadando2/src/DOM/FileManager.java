package DOM;
import java.awt.datatransfer.Transferable;
import java.io.*;
import java.nio.channels.FileLockInterruptionException;
import java.util.StringTokenizer;

import javax.print.DocFlavor.STRING;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class FileManager {
	private static String mes = "XML-DOM massage";
	
	// olvasók
	

	public static void XmlReader(File fname, Table tab) {
		String kod="", nev="", szido="", lakh="", iq="";
		Document domxm;
		DocumentBuilderFactory dbfxm = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder dbxm = dbfxm.newDocumentBuilder();
			domxm = dbxm.parse(fname);
			NodeList nodeListxm = domxm.getElementsByTagName("ereklye");
			
			for (int i = 0; i < nodeListxm.getLength(); i++) {
				
				Node nodexm = nodeListxm.item(i);
				Element elementxm = (Element) nodexm;
				
				//NodeList valuelist .... akkor második for ha több értékû lehet!!!
				//for (int j = 0; j < array.length; j++) 
					kod = elementxm.getElementsByTagName("kod").item(0).getTextContent();
					nev = elementxm.getElementsByTagName("nev").item(0).getTextContent();
					szido = elementxm.getElementsByTagName("date").item(0).getTextContent();					
					lakh = elementxm.getElementsByTagName("cim").item(0).getTextContent();
					iq = elementxm.getElementsByTagName("ertek").item(0).getTextContent();
			
					tab.addRow(new Object[] {new Boolean(false), Integer.parseInt(kod), nev, szido, lakh, Integer.parseInt(iq)});
	
			}
			JOptionPane.showMessageDialog(null, "Adatok beolvasva!",mes,1);
		}catch (ParserConfigurationException pce) {
			JOptionPane.showMessageDialog(null, "XML reader: " + pce.getMessage(), mes,0);
		}catch (SAXException se) {
			JOptionPane.showMessageDialog(null, "XML reader: " + se.getMessage(), mes,0);
		}catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "XML reader: " + ioe.getMessage(), mes,0);
		}
		
	}
	

	// írók 
	
	public static void XmlWriter(Table tab, String fname) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document dom;
		Object obj;
		DatFile dat;
		String a,b,c[];
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.newDocument();
			Element rootEle = dom.createElement("root");
			Element dolg = null;
			Element kod = null;
			Element nev = null;
			Element szul = null;
			Element lakh = null;
			Element iq = null;
			
			for (int i = 0; i < tab.getRowCount(); i++) {
				obj= tab.getDataVector().get(i);
				a = obj.toString();
				b = a.substring(0,a.length()-1);
				c = b.split(", ");
				dat = new DatFile(Integer.parseInt(c[1]), c[2], Integer.parseInt(c[3]), c[4], Integer.parseInt(c[5]));
				dolg = dom.createElement("ereklye");
				rootEle.appendChild(dolg);
				kod = dom.createElement("kod");
				kod.appendChild(dom.createTextNode(new Integer(dat.getKod()).toString()));
				dolg.appendChild(kod);
				nev = dom.createElement("nev");
				nev.appendChild(dom.createTextNode(dat.getNev()));
				dolg.appendChild(nev);
				szul = dom.createElement("date");
				szul.appendChild(dom.createTextNode(new Integer(dat.getSzido()).toString()));
				dolg.appendChild(szul);
				lakh = dom.createElement("cim");
				lakh.appendChild(dom.createTextNode(dat.getLakhely()));
				dolg.appendChild(lakh);
				iq = dom.createElement("ertek");
				iq.appendChild(dom.createTextNode(new Integer(dat.getIq()).toString()));
				dolg.appendChild(iq);
			}
			dom.appendChild(rootEle);	
			
			try {
				Transformer tr = TransformerFactory.newInstance().newTransformer();
				tr.setOutputProperty(OutputKeys.INDENT, "yes");
				tr.setOutputProperty(OutputKeys.METHOD, "xml");
				tr.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-2");
				tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(fname+".xml")));
				
				JOptionPane.showMessageDialog(null, "Adatok kiírva .xml fájlba!",mes,1);
			}catch (TransformerException te) {
				JOptionPane.showMessageDialog(null, "Xml writer: " + te.getMessage(), mes,0);
			}catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "Xml writer: " + ioe.getMessage(), mes,0);
			}
		}catch (ParserConfigurationException pce) {
			JOptionPane.showMessageDialog(null, "Xml writer: " + pce.getMessage(), mes,0);
		}
		
	}
	

	// generálók


	public static void XmlGenerate() {
		DatFile[] data = new  DatFile[2];
		data[0] = new DatFile(12, "Relic 3", 1000, "Berlin", 88110);
		data[1] = new DatFile(13, "Relic 4", 1001, "Unknown", 44130);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document dom;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.newDocument();
			Element rootEle = dom.createElement("root");
			Element dolg = null;
			Element kod = null;
			Element nev = null;
			Element szul = null;
			Element lakh = null;
			Element iq = null;
			
			for (int i = 0; i < data.length; i++) {
				dolg = dom.createElement("ereklye");
				rootEle.appendChild(dolg);
				kod = dom.createElement("kod");
				kod.appendChild(dom.createTextNode(new Integer(data[i].getKod()).toString()));
				dolg.appendChild(kod);
				nev = dom.createElement("nev");
				nev.appendChild(dom.createTextNode(data[i].getNev()));
				dolg.appendChild(nev);
				szul = dom.createElement("date");
				szul.appendChild(dom.createTextNode(new Integer(data[i].getSzido()).toString()));
				dolg.appendChild(szul);
				lakh = dom.createElement("cim");
				lakh.appendChild(dom.createTextNode(data[i].getLakhely()));
				dolg.appendChild(lakh);
				iq = dom.createElement("ertek");
				iq.appendChild(dom.createTextNode(new Integer(data[i].getIq()).toString()));
				dolg.appendChild(iq);
			}
			dom.appendChild(rootEle);	
			
			try {
				Transformer tr = TransformerFactory.newInstance().newTransformer();
				tr.setOutputProperty(OutputKeys.INDENT, "yes");
				tr.setOutputProperty(OutputKeys.METHOD, "xml");
				tr.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-2");
				tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream("xmltest.xml")));
				
				System.out.println("Xml done");
			}catch (TransformerException te) {
				System.out.println(te.getMessage());
			}catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}catch (ParserConfigurationException pce) {
			System.out.println(pce.getMessage());
		}
		
	}
	
}
