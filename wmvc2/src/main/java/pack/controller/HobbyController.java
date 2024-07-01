package pack.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.model.HobbyModel;

//@WebServlet("/hobby.do")
@WebServlet({"/hobby.do", "/hobby2.do"})
public class HobbyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HobbyModel model;
	
	public void init(ServletConfig config) throws ServletException {
		// 초기화할 위치
		model = new HobbyModel();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hobby = request.getParameter("hobby");
		
		// Model을 통해 결과 얻기
		ArrayList<String> list = model.getHobby(hobby);
		
		request.setAttribute("datas", list);
		String viewName = "/WEB-INF/views/show.jsp";
		
		request.getRequestDispatcher(viewName).forward(request, response);
		
	}

}
