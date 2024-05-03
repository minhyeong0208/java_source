package pack;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DbTest9Ex4 extends JFrame implements ActionListener{
	JTextField txtNum = new JTextField("", 5);
	JTextField txtName = new JTextField("", 5);
	JTextArea txtArea = new JTextArea();
	
	JButton btn = new JButton("확인");
	
	
	Connection conn;
	PreparedStatement pstmt;  // 선처리 방식
	ResultSet rs;
	
	public DbTest9Ex4() {
		setTitle("직원 자료");
		
		layInit();
		
		setBounds(800, 200, 500, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void layInit() {
		JPanel panel1 = new JPanel();
		Label lblNum = new Label("Num : ");
		Label lblName = new Label("Name : ");
		panel1.add(lblNum);
		panel1.add(txtNum);
		panel1.add(lblName);
		panel1.add(txtName);
		panel1.add(btn);
		add(panel1);
		add(txtArea);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) {
		new DbTest9Ex4();
	}
}
