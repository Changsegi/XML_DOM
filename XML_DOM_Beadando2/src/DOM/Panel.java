package DOM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.LookupTable;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.OBJ_ADAPTER;
import org.omg.PortableServer.POAPackage.ServantAlreadyActiveHelper;


import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.print.attribute.standard.JobPriority;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.applet.*;
import javax.swing.JTable;
import java.applet.*;
import javax.swing.JRadioButton;

public class Panel extends JFrame{

	
	//frame
	private JPanel cont;
	private JLabel bg;
	
	//J tools
	private JButton load,list,nrec,mrec,drec,prin,stat,search,help,close,generate;
	private JComboBox fromcom, tocom;
	private JTextField fromtext,totext, rectext, searchtext;
	private JLabel fromlabel,tolabel, recnum,searchlab1,searchlab2;
	private JRadioButton id, name,place,age;
	private JTable table;
	
	
	//seged valtozok
	private String forras, forras2 ;
	private String cel= "Válasszon!";
	private File fin;
	private String elem[] = {"Válasszon!","Helyi .xml fájl","SQLiteDB"};
	private String elem2[] = {"Válasszon!",">>> Forrás","Helyi .xml fájl","SQLiteDB"};
	private static String mes = "XML-DOM massage";
	private String kerkif="id";
	
	//tábla
	private Table tab;
	private Table looktm;
	
	
	public Panel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, 515, 330);
		cont = new JPanel();
		cont.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cont);
		cont.setLayout(null);
		setTitle("XML-DOM Project");
		//setContentPane(new JLabel(geni));
		
		
		//Background - bg lesz az új "frame"
		bg = new JLabel();
		bg.setBounds(0, 0, 500, 530);
		bg.setOpaque(false);
		cont.add(bg);
		
		
		
		// feliratok
		
		fromlabel = new JLabel("Forrás: ");
		fromlabel.setBackground(Color.LIGHT_GRAY);
		fromlabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		fromlabel.setForeground(new Color(255, 0, 102));
		fromlabel.setBounds(120,10,100,25);
		bg.add(fromlabel);
		
		
		recnum = new JLabel("Adatok száma: ");
		recnum.setBackground(Color.LIGHT_GRAY);
		recnum.setFont(new Font("Tahoma", Font.BOLD, 14));
		recnum.setForeground(new Color(255, 0, 102));
		recnum.setBounds(120,50,150,25);
		bg.add(recnum);
		
		tolabel = new JLabel("Cél: ");
		tolabel.setBackground(Color.LIGHT_GRAY);
		tolabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		tolabel.setForeground(new Color(255, 0, 102));
		tolabel.setBounds(120,210,100,25);
		bg.add(tolabel);
		
		
		// comboboxok

	
		fromcom = new JComboBox();
		fromcom.setBounds(180,10,150,25);
		bg.add(fromcom);
		for (String s: elem) fromcom.addItem(s);
		forras = "Válasszon!";
		fromcom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				forras = (String)fromcom.getSelectedItem();
				forras2 = (String)fromcom.getSelectedItem();
				fromtext.setText(forras);
			}
		});
		
		tocom = new JComboBox();
		tocom.setBounds(180,210,150,25);
		bg.add(tocom);
		for (String s: elem2) {
			tocom.addItem(s);
		}
		tocom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cel = (String)tocom.getSelectedItem();
				
			}
		});
		
		// beviteli/kiviteli szöveg mezõk 
		
		fromtext = new JTextField();
		fromtext.setBounds(340,10,150,25);
		bg.add(fromtext);
		fromtext.setText(elem[0]);
		
		rectext = new JTextField();
		rectext.setBounds(250,50,50,25);
		bg.add(rectext);
		
		
		totext = new JTextField();
		totext.setBounds(340,210,150,25);
		bg.add(totext);
		//totext.setText("Adjon meg file nevet!");
		
		
		
		// gombok
		load = new JButton("Betöltés");
		load.setBounds(10,10,100,25);
		bg.add(load);
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (forras.equals("Válasszon!")) {
					JOptionPane.showMessageDialog(null, "Nem választott ki file-t!","Error",0);	
				}
				
				if (forras.equals("Helyi .xml fájl")) {
					FileDialog fd = new FileDialog(new Frame(), " ", FileDialog.LOAD);
					
					fd.setFile("*.xml");
					fd.show();
					if (fd.getFile() != null) {
						fin = new File(fd.getDirectory(),fd.getFile());
						String fin_name = fd.getFile();
						fromtext.setText(fin_name);
						FileManager.XmlReader(fin, tab);
					}
				}
				
				if (forras.equals("SQLiteDB")) {
					SMD("Comming soon...");
				}

				rectext.setText(""+tab.getRowCount());
				

			}
		});
			
		
		list = new JButton("Listázás");
		list.setBounds(10,50,100,25);
		bg.add(list);
		list.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List el = new List(Panel.this,tab);
				el.setVisible(true);
				
			}
		});
		
		
		nrec = new JButton("Új adat");
		nrec.setBounds(10,90,100,25);
		bg.add(nrec);
		nrec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int kodv=0;
				if (tab.getRowCount() == 0) kodv=20;
				else kodv = (int)tab.getValueAt(tab.getRowCount()-1, 1);
				newRelic nr = new newRelic(Panel.this, kodv);
				nr.setVisible(true);
				int kilep = nr.Kilep();
				if (kilep==1) {
					DatFile newDat = nr.getDat();
					tab.addRow(new Object[] {new Boolean(false), newDat.getKod(), newDat.getNev(), newDat.getSzido(), newDat.getLakhely(), newDat.getIq()});
					rectext.setText(""+tab.getRowCount());
				}
			}
		});
		
		
		
		mrec = new JButton("Módosítás");
		mrec.setBounds(10,130,100,25);
		bg.add(mrec);
		mrec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tab.getRowCount() == 0) Functions.showMD("Nincs módosítható adat!", 0);
				else  {
					ListMod lm = new ListMod(Panel.this, tab);
					lm.setVisible(true);
				}
			}
		});
		
		
		
		drec = new JButton("Törlés");
		drec.setBounds(10,170,100,25);
		bg.add(drec);
		drec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (tab.getRowCount() == 0) Functions.showMD("Nincs törölhetõ adat!", 0);
				else {
					ListDel ld = new ListDel(Panel.this, tab);
					ld.setVisible(true);
					rectext.setText(""+tab.getRowCount());
				}
			}
		});

		
		
		prin = new JButton("Kiírás");
		prin.setBounds(10,210,100,25);
		bg.add(prin);
		prin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					if (cel.equals("Válasszon!")) SMD("Addjon meg célt elõször!");
					else if (tab.getRowCount()==0) SMD("Nincs kiírható adat!");
					else if (cel.equals("Helyi .xml fájl")) {
						if (totext.getText().length()==0) SMD("Nincs megadva a cél fájl neve!");
						else {
							FileManager.XmlWriter(tab, totext.getText().toString());
							//System.out.println(forras2);
						}
					}
					else if ((cel.equals(">>> Forrás") && forras2.equals("Helyi .xml fájl"))){
						String totex = fromtext.getText();
						totext.setText(totex);
						FileManager.XmlWriter(tab, totex);
					}

					else if (cel.equals("SQLiteDB")) {
						SMD("Comming soon...");
					}
		}
		});
		
		
		
		
		/*search = new JButton("Keresés");
		search.setBounds(10,220,100,25);
		cont.add(search);
		*/
		
		close = new JButton("Bezárás");
		close.setBounds(390,250,100,25);
		bg.add(close);
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
	
		
		generate = new JButton("Generálás");
		generate.setBounds(10,250,100,25);
		bg.add(generate);
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileManager.XmlGenerate();
			}
		});
		
		
		//keresõ panel !!!
		
		JPanel panel = new JPanel();
		panel.setBounds(125, 90, 360, 105);
		panel.setBackground(new Color(0,0,0,150));
		bg.add(panel);
		panel.setLayout(null);
		
		id = new JRadioButton("Kód");
		id.setForeground(Color.RED);
		id.setBackground(new Color(0,0,0,150));
		id.setBounds(15, 33, 60, 25);
		panel.add(id);
		
		name = new JRadioButton("Név");
		name.setForeground(Color.RED);
		name.setBounds(105, 33, 60, 25);
		name.setBackground(new Color(0,0,0,150));
		panel.add(name);
		
		age = new JRadioButton("Kor");
		age.setForeground(Color.RED);
		age.setBackground(new Color(0,0,0,150));
		age.setBounds(195, 33, 60, 25);
		panel.add(age);
		
		place = new JRadioButton("Hely");
		place.setForeground(Color.RED);
		place.setBackground(new Color(0,0,0,150));
		place.setBounds(285, 33, 60, 25);
		panel.add(place);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(id);
		bg.add(name);
		bg.add(age);
		bg.add(place);
		
		search = new JButton("Keresés");
		search.setBounds(15,65,100,25);
		panel.add(search);
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(id.isSelected()) kerkif="kod";
				
				if(name.isSelected()) kerkif="nev";
				
				if(age.isSelected()) kerkif="kor";
				
				if(place.isSelected()) kerkif="hely";
				
				if(Functions.RF(rectext).equals("0")) Functions.showMD("Nincs betöltött adat!", 0);
				else if (!Functions.filled(searchtext)) Functions.showMD("A keresõkulcs (X=) nincs megadva!", 0);
				else if (!RelicSearch.KeyCheck(kerkif, Functions.RF(searchtext))) Functions.showMD("A keresõkulcs hibásan van megadva!", 0);
				else {
					looktm = RelicSearch.Select(tab, kerkif, Functions.RF(searchtext));
					ListSearch ek = new ListSearch(Panel.this, looktm, kerkif, Functions.RF(searchtext));
					ek.setVisible(true);
				}
				
			}
		});

		searchtext = new JTextField();
		searchtext.setBounds(195,65,150,25);
		panel.add(searchtext);
		
		searchlab1 = new JLabel("Keresés:");
		searchlab1.setForeground(Color.RED);
		searchlab1.setBounds(15,7,100,20);
		searchlab1.setBackground(new Color(0,0,0,0));
		panel.add(searchlab1);
		
		searchlab2 = new JLabel("X= ");
		searchlab2.setForeground(Color.RED);
		searchlab2.setBounds(125,65,60,25);
		searchlab2.setBackground(new Color(0,0,0,150));
		panel.add(searchlab2);
		

		Object tab0[] = {"Jel","Kód","Név","Kor","Hely","Érték"};
		tab = new Table(tab0,0);
		 
		
		
		
		
		/*JButton btnD = new JButton("d");
		btnD.setBounds(45, 35, 89, 23);
		cont.add(btnD);
		*/
		
		// táblából adat kinyerés teszt !
		/*
		FileManager.DatWriter(tab, "ttttteeeessssstttt");
		FileManager.JsonWriter(tab, "tttteeeesssstttt");
		FileManager.CsvWriter(tab, "ttttteeeessssttt");
		FileManager.XmlWriter(tab, "tteeeesssstttt");

		Object a = tab.getDataVector().get(1) ;
		String b = a.toString();
		String d = b.substring(1,b.length()-1);
		String c[] = d.split(", ");
		for (int i = 0; i < c.length; i++) {
			System.out.println(c[i]);	
		}
		*/
		
		
	}	
	
	public void SMD (String s) {
		JOptionPane.showMessageDialog(null, s, mes, 0);
	}
}
