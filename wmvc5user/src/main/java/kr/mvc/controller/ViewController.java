package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserDto;
import kr.mvc.model.UserManager;

public class ViewController implements Controller {
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 상세 보기 처리
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		
		// 모델과 통신
		UserDto dto = UserManager.instance().findUser(userid);
		// view.jsp에 데이터를 dto 형태로 전달이 불가(오직 String만 전달 가능) -> forward 방식
		// ex. view.jsp?data=dto (X)
		request.setAttribute("user", dto);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("view.jsp");  // 상세보기 출력용 페이지
		modelAndView.setRedirect(false);  // forwarding 방식
		
		return modelAndView;
	}
}
