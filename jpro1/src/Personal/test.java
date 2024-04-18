package Personal;

import java.io.FileInputStream;
import java.io.InputStream;

public class test {
	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("C:/work/test1.db");
		while(true) {
			int data = is.read();   // 1byte씩 읽기
			if(data == -1) break;
			System.out.println(data);
		}
		is.close();
	
	}
}
