package pack.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.model.GogekDto;
import pack.model.GogekManager;

public class ViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String buser_no = request.getParameter("buser_num");
		
		// 모델과 통신
		ArrayList<GogekDto> gdto = GogekManager.instance().getGogekList(buser_no);
		request.setAttribute("glist", gdto);
		System.out.println(buser_no);
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("view.jsp");  // 상세보기 출력용 페이지
		modelAndView.setRedirect(false);
		
		return modelAndView;
	}
}
