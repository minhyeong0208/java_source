package pack.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class DateModel {
/*
	public ArrayList<String> getDate() {
		// 비즈니스 로직 처리.. 
		LocalDate date = LocalDate.now();
		String y = Integer.toString(date.getYear());
		String m = Integer.toString(date.getMonthValue());
		String d = Integer.toString(date.getDayOfMonth());

		ArrayList<String> list = new ArrayList<>();
		list.add(y);
		list.add(m);
		list.add(d);

		return list;
	}
*/
	private static DateModel instance = new DateModel();

	public ArrayList<String> getDate() {
		// 비즈니스 로직 처리.. 
		LocalDate date = LocalDate.now();
		String y = Integer.toString(date.getYear());
		String m = Integer.toString(date.getMonthValue());
		String d = Integer.toString(date.getDayOfMonth());

		ArrayList<String> list = new ArrayList<>();
		list.add(y);
		list.add(m);
		list.add(d);

		return list;
	}
	
	public static DateModel getInstance() {
		return instance;
	}
}
