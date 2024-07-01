package pack.model;

import java.util.ArrayList;

public class TestModel {
	
	public ArrayList<String> oper1(String t) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 1; i < 10; i++) {
			list.add( t + " * " + i + " = " + (Integer.parseInt(t) * i));
		}
		return list;
	}


}
