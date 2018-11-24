package DOM;

import java.awt.EventQueue;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.time.Year;


public class G_Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel frame = new Panel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		/*
		DateFormat df = new SimpleDateFormat("G y");
		Calendar calendar = GregorianCalendar.getInstance();
		System.out.println(df.format(calendar.getTime()));
		
		calendar.add(Calendar.YEAR, -20530);
		System.out.println(df.format(calendar.getTime()));
		
		String asd = df.format(calendar.getTime());
		System.out.println(asd);
		
		//Date date;
		//calendar.setTime(LocalDate.now());
		calendar.clear();
		calendar.set(Calendar.YEAR, calendar.getActualMinimum(Calendar.YEAR));
		System.out.println(df.format(calendar.getTime()));
		String grrr[] = asd.split(" ");
		calendar.set(Calendar.ERA, GregorianCalendar.BC);
		calendar.set(Calendar.YEAR, Integer.parseInt(grrr[1]));
		System.out.println(df.format(calendar.getTime()));
		*/
		
		
		/*
		Year b = Year.of(100);
		Year a = b.minusYears(10000);
		String aaa = a.format(null);
		
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, 1000);
		Date date = calendar.getTime();
		
		System.out.print(a);
		*/
		
	}

}
