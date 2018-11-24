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

public class List extends JDialog {

	private final JPanel listpanel = new JPanel();
	private JButton close;
	private JTable jtab;
	private Table tmodell;

	public List(JFrame f, Table intm) {
		super(f, "Ereklyék lisája", true);
		 tmodell = intm;
		
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		listpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(listpanel, BorderLayout.CENTER);
		listpanel.setLayout(null);
		
		this.close = new JButton("Close");
		this.close.setBounds(180, 227, 89, 23);
		listpanel.add(this.close);
		this.close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);		
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 445, 200);
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
		
	}
}
