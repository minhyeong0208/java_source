package pack.board;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BoardFormBean {
	private String name, pass, mail, title, cont, bip, bdate;
	private int num, readcnt, gnum, onum, nested;
	
	// overloading
	public void setBdate() {
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		
		this.bdate = year + "-" + month + "-" + day;
	}
}
