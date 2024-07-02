package pack.controller;

public class ModelAndView {  // 호출 방식과 view 파일명을 기억
	private boolean isRedirect = false; 
	private String viewName = "";
	
	public boolean isRedirect() {
		return isRedirect;
	}
	
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	public String getViewName() {
		return viewName;
	}
	
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
}
