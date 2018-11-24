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
import javax.swing.JLabel;

public class ListSearch extends JDialog {
	

	private final JPanel listpanel = new JPanel();
	private JButton close;
	private JTable jtab;
	private Table tmodell;
	private JTextField kerert;
	
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
	
	
	public ListSearch(JFrame f, Table intm, String mezo, String kulcs) {
		super(f, "Keresett Ereklyék listája", true);
		tmodell = intm;
		
		/*kod.setVisible(false);
		nev.setVisible(false);
		kor.setVisible(false);
		hely.setVisible(false);
		
		
		if(mezo.equals("kod")) kod.setVisible(true);
		if(mezo.equals("nev")) kod.setVisible(true);
		if(mezo.equals("kor")) kod.setVisible(true);
		if(mezo.equals("hely")) kod.setVisible(true);
		*/
		
		/*Object tab0[] = {"Jel","Kód","Név","Kor","Hely","Érték"};
		tab = new Table(tab0,0);
		*/
		
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		listpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(listpanel, BorderLayout.CENTER);
		listpanel.setLayout(null);
		
		this.close = new JButton("Bezár");
		this.close.setBounds(173, 227, 89, 23);
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
		 
		 kerert = new JTextField(kulcs);
		 kerert.setBounds(162, 188, 100, 25);
		 listpanel.add(kerert);
		 kerert.setColumns(10);
		 
		 JLabel kereses = new JLabel("Keresett érték:");
		 kereses.setBounds(30, 188, 87, 25);
		 listpanel.add(kereses);
		 


		
	}
}
