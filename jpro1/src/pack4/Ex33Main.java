package pack4;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ex33Main {
	private ArrayList<Ex33JikwonDto> list = new ArrayList<Ex33JikwonDto>();
	
	public void inputData(String[] datas) {
		for(int i = 0; i < datas.length; i++) {
			StringTokenizer tok = new StringTokenizer(datas[i], ",");
			String sabun = tok.nextToken();
			String ir = tok.nextToken();
			int gibon = Integer.parseInt(tok.nextToken());
			String ibsa = tok.nextToken();
			
			Ex33JikwonDto dto = new Ex33JikwonDto();  // DTO 처리용
			dto.setSabun(sabun);
			dto.setIrum(ir);
			dto.setGibon(gibon);
			dto.setIbsa(ibsa);
			
			list.add(dto);
		}
	}
	
	public void displayData() {
		for(Ex33JikwonDto hd: list) {
			//int sudang = 
			System.out.println(hd.getSabun() + " " + hd.getIrum() + " " + hd.getGibon() + " " + hd.getIbsa());
		}
	}
	
	public static void main(String[] args) {
		String[] datas = new String[3];
		datas[0] = "1,강나루,1500000,2005";   // 데이터를 콤마로 구분
		datas[1] = "2,나,2500000,2010";
		datas[2] = "3,다,3500000,2015";
		
		Ex33Main test2 = new Ex33Main();
		test2.inputData(datas);
		test2.displayData();
	}

}
