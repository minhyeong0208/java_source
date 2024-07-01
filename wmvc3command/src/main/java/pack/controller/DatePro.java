package pack.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.model.DateModel;

// controller 영역의 클래스 -> model과 통신
public class DatePro implements CommandInter {
	
	@Override
	public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DateModel dateModel = new DateModel();
		ArrayList<String> list = dateModel.getDate();
		request.setAttribute("datas", list);
		
		return "view2.jsp";
	}
}
