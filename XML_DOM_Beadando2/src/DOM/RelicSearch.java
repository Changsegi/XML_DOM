package DOM;


public class RelicSearch {
	public static boolean KeyCheck(String mezo, String key) {
		
		boolean vi = false;
		Character fc=' ';
		String fs ="";
		
		if( mezo.equals("kod")) {
			if (key.substring(0, 1).equals("=")) {
				key= key.substring(1,key.length());
			}
				
			vi= goodStoInt(key);
		}
		
		if (mezo.equals("nev")) {
			fs=key.substring(0,1);
			if (Character.isLetter(key.charAt(0)) || fs.equals(" ")) {
				vi = true;
			}
		}
		
		if(mezo.equals("hely")) {
			if(Character.isLetter(key.charAt(0))) {
				vi=true;
			}
		}
		
		if(mezo.equals("kor")) {
			fs=key.substring(0,1);
			fc=key.charAt(0);
			if (fs.equals("<") || fs.equals(">") || fs.equals("=")) {
				if (key.length() > 1 && goodStoInt(key.substring(1,key.length()))) {
					vi = true;
				}else vi = false;
			}
			if (Character.isDigit(fc) && key.indexOf("..") > 0) {
				int x = key.indexOf("..");
				String k1 = key.substring(0,x);
				String k2 = key.substring(x+2, key.length());
				if (goodStoInt(k1) && goodStoInt(k2)) {
					vi = true;
				}else vi = false;
			}
		}
		return vi;
	}
	
	
	public static Table Select(Table tab, String mezo, String key) {
		Object tabn[] = {"Jel","Kód","Név","Kor","Hely","Érték"};
		Table looktm = new Table(tabn, 0);
		String k="", k1="", k2="";
		int x=0;
		
		if (mezo.equals("kod") && key.substring(0, 1).equals("=")) {
			key=key.substring(1,key.length());
		}
		if(mezo.equals("kor")) {
			k=key.substring(1, key.length());
		}
		if(mezo.equals("kor") && key.indexOf("..")>0) {
			x=key.indexOf("..");
			k1=key.substring(0, x);
			k2=key.substring(x+2, key.length());
		}
		
		for (int i = 0; i < tab.getRowCount(); i++) {
			if(mezo.equals("kod") && key.equals(tab.getValueAt(i, 1).toString())) Pack(tab, looktm,i);
				
			if(mezo.equals("nev") && tab.getValueAt(i, 2).toString().indexOf(key)>=0) Pack(tab, looktm, i);
		
			if(mezo.equals("hely") && key.equals(tab.getValueAt(i, 4).toString())) Pack(tab, looktm, i);
						
			if(mezo.equals("kor") && key.substring(0,1).equals("=") && k.equals(tab.getValueAt(i, 3).toString())) Pack(tab, looktm, i);
							
			if(mezo.equals("kor") && key.substring(0,1).equals(">") && Functions.StoI(k) < Functions.StoI(tab.getValueAt(i, 3).toString())) Pack(tab, looktm, i);
								
			if(mezo.equals("kor") && key.substring(0,1).equals("<") && Functions.StoI(k) > Functions.StoI(tab.getValueAt(i, 3).toString())) Pack(tab, looktm, i);
									
			if(mezo.equals("kor") && key.indexOf("..")>0) {
				String s = tab.getValueAt(i, 3).toString();
				if (Functions.StoI(k1) < Functions.StoI(k2) && Functions.StoI(k2) > Functions.StoI(s)) Pack(tab, looktm, i);
			}	
		}
		return looktm;
	}
	

	public static void Pack(Table tab, Table looktm, int row) {
		looktm.addRow(new Object[]{new Boolean(false),
				Functions.StoI(tab.getValueAt(row, 1).toString()),
				tab.getValueAt(row, 2).toString(),
				Functions.StoI(tab.getValueAt(row, 3).toString()),
				tab.getValueAt(row, 4).toString(),
				Functions.StoI(tab.getValueAt(row, 5).toString())
				});
	}

	public static boolean goodStoInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
		
	}
	
	
}
