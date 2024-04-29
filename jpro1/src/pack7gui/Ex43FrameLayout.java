package pack7gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
AWT(Abstract Window Toolkit)

1) AWT란?
- GUI프로그래밍(윈도우 프로그래밍)을 위한 도구
- Java로 구현하지 않고 OS의 컴포넌트를 그대로 사용(OS 종속적)

2) Swing
- AWT를 확장한 GUI프로그래밍 도구
- AWT보다 더 많은 종류의 컴포넌트 제공
- OS의 컴포넌트를 사용하지 않고 Ja로 구현

3) AWT의 구성
- AWT관련 패키지는 모두 java.awt로 시작
- AWT컴포넌트의 최상위 조상은 java.awt.Component 클래스(메뉴 관련 컴포넌트 제외)

4) Container
- 다른 컴포넌트를 포함할 수 있는 컴포넌트
- 독립적인 컨테이너: 독립 사용 가능, 다른 컴포넌트나 종속적 컨테이너를 포함 가능
- 종속적인 컨테이너: 독립 사용 불가(다른 컨테이너에 포함)
- add()를 사용해서 추가
- 컨테이너에 담기는 컴포넌트는 컨테이너의 설정을 따름(변경 가능)
*/

// LayoutManager 참조: https://m.blog.naver.com/so_yuns/222172786073
// Frame : BorderLayout이 기본
// Panel : FlowLayout이 기본

public class Ex43FrameLayout extends Frame {  // Frame도 컨테이너, 가장 큰 그릇
	Panel pn1 = new Panel();    // 컨테이너(Visual Object)을 담는 그릇의 일종
	Panel pn2 = new Panel();    
	Panel pn3 = new Panel();    
	Panel pn4 = new Panel();    
	Panel pn5 = new Panel();
	Panel pn6 = new Panel();   
	
	TextField txtBun, txtIrum;
	Button btnGo;
	
	public Ex43FrameLayout() {
		// 배치 관리자로 화면 디자인 연습
		setLayout(new GridLayout(2,1));  // Frame의 Layout을 변경(2행 1열), 기본적으로 BorderLayout임.
		
		// 첫 번째 행 디자인
		Label lbl1 = new Label("bunho:");  // 메세지 컴포넌트
		txtBun = new TextField("10", 20);  // 키보드로 자료 입력 가능
		pn1.add(lbl1);  // Panel에 label 객체 담기
		pn1.add(txtBun);  // Panel에 txtField 객체 담기
		pn1.setBackground(Color.YELLOW);  // Panel 배경 색 변경
		super.add(pn1);  // Frame에 Panel을 담기
		
		// 두 번째 행 디자인
		
		super.setTitle("레이아웃 연습");
		super.setBounds(200, 200, 400, 300);
		super.setVisible(true);
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	public static void main(String[] args) {
		new Ex43FrameLayout();

	}

}
