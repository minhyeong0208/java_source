package pack.board;

import lombok.Data;

@Data
public class BoardDto {
	private String name, pass, mail, title, cont, bip, bdate;
	private int num, readcnt, gnum, onum, nested;
}
