package pack.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.page")
public class GogekServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModelAndView modelAndView = null;
	private Controller controller = null;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		try {
			// 파일명을 요청으로 사용
			String ss = request.getRequestURI(); 
			int idx = ss.lastIndexOf('/'); 
			StringTokenizer st = new StringTokenizer(ss.substring(idx + 1), ".");
			ss = st.nextToken(); 
			String command = ss;  
			
			controller = getController(command);
			
			modelAndView = controller.execute(request, response);
			
			// 파일 호출 방식 선택
			if(modelAndView.isRedirect()) {
				// 리다이렉트 방식인 경우 -> 로그인일 때
				response.sendRedirect(modelAndView.getViewName());
			} else {
				// 데이터를 list.jsp에 전달할 때
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/" + modelAndView.getViewName());
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			System.out.println("service err : " + e);
		}
	}

	public Controller getController(String command) throws Exception {
		// 로그인인 경우
		if(command.equals("list")) {
			controller = new ListController();
		} else if(command.equals("view")) {
			controller = new ViewController();
		}
		
		return controller;
	}
}
