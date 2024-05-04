package pack;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DbTest7Ex2 extends JFrame implements ActionListener {
	JButton btn = new JButton("추가");
	JTextArea txtResult = new JTextArea();
	JTextField txtCode, txtSu, txtDan, txtSang;

	// DB 연결
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public DbTest7Ex2() {
		setTitle("상품 처리");

		accDb();
		layInit();

		setBounds(800, 200, 550, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void layInit() {
		JPanel panel = new JPanel();

		txtCode = new JTextField("", 4);
		txtSang = new JTextField("", 4);
		txtSu = new JTextField("", 4);
		txtDan = new JTextField("", 4);

		panel.add(new Label("code : "));
		panel.add(txtCode);
		panel.add(new Label("pum : "));
		panel.add(txtSang);
		panel.add(new Label("su : "));
		panel.add(txtSu);
		panel.add(new Label("dan : "));
		panel.add(txtDan);
		panel.add(btn);
		JScrollPane pane = new JScrollPane(txtResult);
		add(pane);
		add("North", panel);
		add("Center", txtResult);

		btn.addActionListener(this);
	}

	private void display() {
		
	}
	
	private void accDb() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("accDb err : " + e);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	

	}

	public static void main(String[] args) {
		new DbTest7Ex2();
	}
}
