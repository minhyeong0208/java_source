package pack;

//jsp13main.html
public class ExamBean {  
	// 클라이언트로부터 전송되는 복수 개의 값을 한개의 그룹으로 묶어 처리하는 클래스
	// 이런 용도의 클래스를 FormBean이라고 부른다.
	private String name;  // 파라미터 변수명(name 속성)과 FormBean의 멤버 필드의 값은 동일해야한다.
	private int kor;
	private int eng;
	private int mat;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	
	public String getName() {
		return name;
	}
	public int getKor() {
		return kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	
	
}
