package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.mvc.model.UserManager;

public class LoginController implements Controller {
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// UserServlet을 수행 후 command가 login인 경우, 이곳으로 아이디와 비밀번호를 가지고 들어온다.
		String id = request.getParameter("id");
		String pwd = request.getParameter("password");
		
		// 모델과 통신
		UserManager manager = UserManager.instance(); // UserManager가 UserDao를 관리하므로
		boolean b = manager.login(id, pwd);
		
		ModelAndView modelAndView = new ModelAndView();
		if(b) { 
			// 로그인에 성공한 경우, 실행
			HttpSession session = request.getSession(true);
			session.setAttribute("userid", id);
			modelAndView.setViewName("list.m2");  // 확장자가 m2여야 servlet을 만난다. -> 목록보기로 이동
			// redirect 방식으로 요청해야 하는 이유 -> 파일명(list.m2)을 가지고 다시 UserServlet 서블릿 파일을 실행하는데 이동할 경로를 가지고 있어야 로그인 후, list.m2로 이동이 가능?
		} else {
			modelAndView.setViewName("fail.html");
		}
		// viewName과 redirect를 만족
		modelAndView.setRedirect(true);
		return modelAndView;
	}
}
