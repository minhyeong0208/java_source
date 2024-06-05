package pack;

// jsp13main.html에서 선언
public class ExamProcess {  // 연산용 클래스(Business Logic 용)
	private ExamBean bean;
	
	public void setBean(ExamBean bean) {
		this.bean = bean;
	}
	
	public int getSum() {
		return bean.getKor() + bean.getEng() + bean.getMat();
	}
	
	public double getAvg() {
		return getSum() / 3.0;
	}
}
