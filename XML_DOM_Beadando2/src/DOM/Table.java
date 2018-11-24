package DOM;

import javax.swing.table.DefaultTableModel;
import javax.swing.tree.RowMapper;

public class Table extends DefaultTableModel {
	// must part ?! ;D
	public Table(Object fieldNames[], int rows) {
		super(fieldNames, rows);
	}
	public boolean isCellEditable(int row, int col) {
		if (col == 0) {return true;}
		return false;
	}
	public Class<?> getColumnClass(int index) {
		if(index == 0) return(Boolean.class);
		else if (index == 1 || index == 5 || index == 3) return(Integer.class);
		return(String.class);
	}
	

}