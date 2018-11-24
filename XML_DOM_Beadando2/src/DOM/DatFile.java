package DOM;

import 	java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;



public class DatFile implements Serializable {
	
	private int kod;
	private String nev;
	private int szido;
	private String lakhely;
	private int iq;
	
	
	public DatFile(int kod, String nev, int szido, String lakhely, int iq) {
		this.kod=kod;
		this.nev=nev;
		this.szido=szido;
		this.lakhely=lakhely;
		this.iq=iq;
	}
	
	
	public int getKod() {
		return kod;
	}
	public void setKod(int kod) {
		this.kod = kod;
	}
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	public int getSzido() {
		return szido;
	}
	public void setSzido(int szido) {
		this.szido = szido;
	}
	public String getLakhely() {
		return lakhely;
	}
	public void setLakhely(String lakhely) {
		this.lakhely = lakhely;
	}
	public int getIq() {
		return iq;
	}
	public void setIq(int iq) {
		this.iq = iq;
	}
	

	
	
	
	
	
}
