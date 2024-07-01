package pack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GoMvc")
public class GoMvc extends HttpServlet {  // Controller 역할
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String name;
		String result = "", viewName = "/WEB-INF/";
		
		try {
			name = request.getParameter("name");
		} catch (Exception e) {
			name = null;
		}
		
		// 클라이언트의 요청을 받아 분석(판단) : 모델과 뷰를 선택하는 기능 기술
		if(name == null || name.equals("")) {
			 result = "환영합니다";    // Model(Business Logic)을 수행한 결과라 가정
			 viewName += "views/view1.jsp";
		} else if(name.equals("korea")) {
			result = "한국인이군요";
			viewName += "views/view2.jsp";
		} else {
			result = name + "님 반가워요";
			viewName += "views/view3.jsp";
		}
		
		// request에 담은 경우, forward으로 전달해야함.
		request.setAttribute("result", result);  // JSP를 호출하므로 가능. redirect로는 전달되지 않음. forwarding으로 전달.
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
		dispatcher.forward(request, response);
	}

}
