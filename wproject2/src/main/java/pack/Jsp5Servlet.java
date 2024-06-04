package pack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Jsp5Servlet")
public class Jsp5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String data = request.getParameter("data");
		
		System.out.println("수신 data : " + data);
		
		// 서버가 다른 파일 호출 방법1 : redirect 방식 - client를 통해 server에게 파일 요청
//		response.sendRedirect("aaa.html?data=" + data);  // html을 호출할 경우 값을 넘길 수 없다.
		//response.sendRedirect("jsp5called.jsp?data=" + data);  // jsp을 호출할 경우 값을 넘길 수 있다.
		// jsp를 호출한 경우 값을 넘길 수 있다. 값은 String만 가능.
		
		// 서버가 다른 파일 호출 방법2 : forward 방식 - server가 직접 서버에 있는 파일 호출
		request.setAttribute("datas", data);
		//request.setAttribute("data2", data2);
		//request.setAttribute("jikwons", jiklist);
		// request에 이름, 값의 형태로 String 또는 자바의 어떤 객체이든 전달이 가능하다.
		/*
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp5called.jsp"); // 매개변수로 호출할 파일명을 입력
		dispatcher.forward(request, response);  
		*/
		// jsp5called.jsp에 의한 결과가 출력되는데, 처음에 서블릿을 불러서 흔적이 남아있다. 
		// 서버에서 jsp를 불러 클라이언트에 푸시했기 때문에 주소는 서블릿 파일을 가지고 있다.
		
		request.getRequestDispatcher("jsp5called.jsp").forward(request, response);  // 위와 같은 코드
	}
}
