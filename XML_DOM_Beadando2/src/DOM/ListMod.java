package DOM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ListMod extends JDialog {
	

	private final JPanel listpanel = new JPanel();
	private JButton close;
	private JTable jtab;
	private Table tmodell;
	//private Table tab;
	private JTextField ertek;
	private JTextField hely;
	private JTextField kor;
	private JTextField nev;
	
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListMod dialog = new ListMod(null);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	
	
	public ListMod(JFrame f, Table intm) {
		super(f, "Ereklyék módosítása", true);
		tmodell = intm;
		
		/*Object tab0[] = {"Jel","Kód","Név","Kor","Hely","Érték"};
		tab = new Table(tab0,0);
		*/
		
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		listpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(listpanel, BorderLayout.CENTER);
		listpanel.setLayout(null);
		
		this.close = new JButton("Bezár");
		this.close.setBounds(138, 227, 89, 23);
		listpanel.add(this.close);
		this.close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);		
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 445, 157);
		listpanel.add(scrollPane);
		
		jtab = new JTable(tmodell);
		scrollPane.setViewportView(jtab);
		
		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
			tc = jtab.getColumnModel().getColumn(i);
			if (i==0  || i==1 ) {
				tc.setPreferredWidth(30);
			}else if(i == 3) {
				tc.setPreferredWidth(50);
			}else {tc.setPreferredWidth(100);}
		}
		 jtab.setAutoCreateRowSorter(true);
		 TableRowSorter<Table> trs = (TableRowSorter<Table>)jtab.getRowSorter();
		 trs.setSortable(0, false);
		 
		 ertek = new JTextField();
		 ertek.setBounds(366, 188, 100, 20);
		 listpanel.add(ertek);
		 ertek.setColumns(10);
		 
		 hely = new JTextField();
		 hely.setBounds(256, 188, 100, 20);
		 listpanel.add(hely);
		 hely.setColumns(10);
		 
		 kor = new JTextField();
		 kor.setBounds(196, 188, 50, 20);
		 listpanel.add(kor);
		 kor.setColumns(10);
		 
		 nev = new JTextField();
		 nev.setBounds(84, 188, 100, 20);
		 listpanel.add(nev);
		 nev.setColumns(10);
		 
		 JButton mod = new JButton("Módosít");
		 mod.setBounds(256, 227, 89, 23);
		 listpanel.add(mod);
		 mod.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!Functions.filled(nev) && !Functions.filled(kor) && !Functions.filled(hely) && !Functions.filled(ertek)) Functions.showMD("Egyetelen módosító adat sincs beírva!",0);
				else if (Functions.filled(kor) && !Functions.goodInt(kor)) Functions.showMD("A kor mezõben hibás adat van!", 0);
				else if (Functions.filled(ertek) && !Functions.goodInt(ertek)) Functions.showMD("Az ertek mezõben hibás adat van!", 0);
				else {
					int db=0, jel=0, x=0;
					for (int i = 0; i < tmodell.getRowCount(); i++) {
						if ((Boolean)tmodell.getValueAt(i, 0)) {db++; jel=i;}
					}
					for (x = 0; x < tmodell.getRowCount(); x++) {
						//if ((Boolean)tmodell.getValueAt(x, 0)) {db++; jel=x;}
						if (db == 0) {Functions.showMD("Nincs kijelölve a módosítandó rekord!", 0); break;};
						if (db > 1) {Functions.showMD("Több rekord van kijelölve!  \nEgyszerre csak egy rekord módosítható!", 0); break;};
						if (db == 1) {
							if (Functions.filled(nev)) tmodell.setValueAt(Functions.RF(nev), jel, 2);
							if (Functions.filled(kor)) tmodell.setValueAt(Functions.RF(kor), jel, 3);
							if (Functions.filled(hely)) tmodell.setValueAt(Functions.RF(hely), jel, 4);
							if (Functions.filled(ertek)) tmodell.setValueAt(Functions.RF(ertek), jel, 5);
							Functions.showMD("A rekord módosítva!", 1);
							Functions.DF(nev);
							Functions.DF(kor);
							Functions.DF(hely);
							Functions.DF(ertek);
							tmodell.setValueAt(new Boolean(false), jel, 0);
							break;
						}
					}
				}
			}
		});
		 


		
	}
}
