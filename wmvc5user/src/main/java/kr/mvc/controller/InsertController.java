package kr.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mvc.model.UserManager;

public class InsertController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		// FormBean 사용
		UserForm userForm = new UserForm();
		userForm.setUserid(request.getParameter("userid"));
		userForm.setPassword(request.getParameter("password"));
		userForm.setName(request.getParameter("name"));
		userForm.setEmail(request.getParameter("email"));
		
		// 모델과 통신 <- Controller에서 Model로 이동
		int result = UserManager.instance().insert(userForm);
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(result > 0) {
			// insert 후 목록보기
			
			modelAndView.setViewName("list.m2"); 
		} else {
			modelAndView.setViewName("fail.html");
		}
		modelAndView.setRedirect(true);  // 클라이언트를 통해 호출해야하므로 redirect
		// insert, update, delete의 경우, redirect 방식으로 호출해야함.
		
		return modelAndView;
	}
}
