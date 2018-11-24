package DOM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class ListDel extends JDialog {

	private final JPanel listpanel = new JPanel();
	private JButton close;
	private JTable jtab;
	private Table tmodell;
	private JButton torol;
	private boolean md=false;
	private JCheckBox jcb;

	public ListDel(JFrame f, Table intm) {
		super(f, "Ereklyék törélse", true);
		 tmodell = intm;
		
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		listpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(listpanel, BorderLayout.CENTER);
		listpanel.setLayout(null);
		
		this.close = new JButton("Bezár");
		this.close.setBounds(124, 227, 89, 23);
		listpanel.add(this.close);
		this.close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);		
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 445, 167);
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
		 
		 
		 
		 
		 jcb = new JCheckBox("Több rekord is törölhetõ egyszerre");
		 jcb.setBounds(20, 198, 219, 23);
		 listpanel.add(jcb);

		 
		 torol = new JButton("Törlés");
		 torol.setBounds(250, 227, 89, 23);
		 listpanel.add(torol);
		 torol.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				md = jcb.isSelected();
				
				int db=0, jel=0, x=0;
				
				for (int i = 0; i < tmodell.getRowCount(); i++) {
					if ((Boolean)tmodell.getValueAt(i, 0)) {db++; jel=i;}
					System.out.println(db);
				}
				
				for (x = 0; x < tmodell.getRowCount(); x++) {
					
					//if ((Boolean)tmodell.getValueAt(x, 0)) {db++; jel=x;}
					if (db==0) {Functions.showMD("Nincs kijelölve a törlendõ rekord!", 0); break;};
					if (!md) {
						if (db > 1) {Functions.showMD("Több rekord van kijelölve! \nPipálja ki a jelölõnégyzetet ha többet szeretne törölni!", 0); break;};
						if (db == 1) {
							System.out.println(jel);
							tmodell.removeRow(jel);
							Functions.showMD("A rekord törölve", 1);
							break;
						}	
					}else {
						for (int j = 0; j < tmodell.getRowCount(); j++) {
							if ((Boolean)tmodell.getValueAt(j, 0)) {tmodell.removeRow(j); j--;};
						}
						break;
					}

				}
				
			}
		});
		 
		 

		
	}
}
