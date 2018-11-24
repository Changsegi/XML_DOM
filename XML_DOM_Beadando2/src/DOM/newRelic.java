package DOM;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.PartialResultException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.xml.parsers.ParserConfigurationException;


public class newRelic extends JDialog {
	
	private JTextField kod, nev, kor, hely, ertek;
	private JLabel kodl, nevl, korl, helyl, ertekl;
	private JButton leker, beszur, bezar;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	private String mes = "Project Genesis message";
	
	private DatFile adat;
	private int kilep = 0;
	
	/**
	 * Launch the application.
	 */
	
	//main külön teszt-re
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newRelic dialog = new newRelic(null, 52);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	/**
	 * Create the dialog.
	 */
	public newRelic(JFrame f, int maxKod) {
		super(f, true);
		setTitle("Új relikvia felvitele");
		
		getContentPane().setBackground(new Color(238, 232, 170));
		setBounds(100, 100, 325, 265);
		getContentPane().setLayout(null);
		
		
		// szöveg panelek
		
		kodl = new JLabel("Kód: ");
		kodl.setBounds(15,10,100,25);
		getContentPane().add(kodl);
		
		nevl = new JLabel("Név: ");
		nevl.setBounds(15,45,100,25);
		getContentPane().add(nevl);
		
		korl = new JLabel("Kor: ");
		korl.setBounds(15,80,100,25);
		getContentPane().add(korl);
		
		helyl = new JLabel("Hely: ");
		helyl.setBounds(15,115,100,25);
		getContentPane().add(helyl);
		
		ertekl = new JLabel("Érték: ");
		ertekl.setBounds(15,150,100,25);
		getContentPane().add(ertekl);
		
		// beviteli panelek
		
		kod = new JTextField();
		kod.setBounds(75,10,100,25);
		getContentPane().add(kod);
		
		nev = new JTextField();
		nev.setBounds(75,45,215,25);
		getContentPane().add(nev);
		
		kor = new JTextField();
		kor.setBounds(75,80,215,25);
		getContentPane().add(kor);
		
		hely = new JTextField();
		hely.setBounds(75,115,215,25);
		getContentPane().add(hely);
		
		ertek = new JTextField();
		ertek.setBounds(75,150,215,25);
		getContentPane().add(ertek);

		//gombok
		
		leker = new JButton("Lekérdez");
		leker.setBounds(190,10,100,25);
		getContentPane().add(leker);
		leker.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				kod.setText(""+(maxKod+1));
			}
		});
		
		bezar = new JButton("Bezárás");
		bezar.setBounds(160,190,100,25);
		getContentPane().add(bezar);
		bezar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		
		beszur = new JButton("Beszúrás");
		beszur.setBounds(30,190,100,25);
		getContentPane().add(beszur);
		beszur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!filled(kod)) kod.setText(""+(maxKod+1));
				if (!filled(nev)) showMD("A név mezõ üres!", 0);
				else if (!filled(kor)) showMD("A kor mezõ üres!", 0);
				else if (!goodInt(kor)) showMD("A kor mezõben hibás adat van!", 0);
				else if (!filled(hely)) showMD("A Hely mezõ üres!", 0);
				else if (!filled(ertek)) showMD("Az érték mezõ üres!", 0);
				else if (!goodInt(ertek)) showMD("Az érték mezõben hibás adat van!", 0);
				else {
					adat = new DatFile(StoI(RF(kod)), RF(nev), StoI(RF(kor)), RF(hely), StoI(RF(ertek)));
					showMD("Adat beszúrva!", 1);
					kilep = 1;
					dispose();
					setVisible(false);
				}
				
			}
		});
		
	}
	
	
	public String RF (JTextField a) {
		return a.getText().toString();
	}
	
	public boolean filled(JTextField a) {
		String s = RF(a);
		if (s.length()>0) return true ; else return false;
	}
	
	public boolean goodDate(JTextField a) {
		String s = RF(a);
		Date testDate = null;
		try {
			testDate = sdf.parse(s);
		} catch (ParseException r) {return false;}
		if (sdf.format(testDate).equals(s)) return true;
		else return false;
	}
	
	public boolean goodInt(JTextField a) {
		String s = RF(a);
		try {
			Integer.parseInt(s);
			return true;
		}catch ( NumberFormatException e) {
			return false;
		}
	}
	
	public void showMD(String s, int i) {
		JOptionPane.showMessageDialog(null, s, mes, i);
	}
	
	
	public Date StoD (String s) {
		Date testDate = null, vid = null;
		try {
			testDate = sdf.parse(s);
		}catch (ParseException e) {return vid;}
		if (!sdf.format(testDate).equals(s)) {return vid;}
		return testDate;
	}
	
	public int StoI ( String s) {
		int x=-1;
		x = Integer.parseInt(s);
		return x;
	}
	
	public DatFile getDat() {
		return adat;
	}
	
	public int Kilep() {
		return kilep;
	}
	
}
