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

	private void accDb() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
			
			String sql = "";
			sql = "select code, sang, su, dan, su*dan from sangdata";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "code");
			pstmt.setString(2, "sang");
			pstmt.setString(3, "su");
			pstmt.setString(4, "dan");
			rs = pstmt.executeQuery();
			
			txtResult.append("코드\t상품명\t수량\t단가\t금액\n");
			while(rs.next()) {
				String result = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4) + "\n";
				txtResult.append(result);
				
			}
		} catch (Exception e) {
			System.out.println("accDb err : " + e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");

			String sql = "";
			sql = "select code, sang, su, dan from sangdata";


			int code = Integer.parseInt(txtCode.getText());
			String sang = txtSang.getText();
			int su = Integer.parseInt(txtSu.getText());
			int dan = Integer.parseInt(txtDan.getText());
			
			if (e.getSource() == btn) {
				txtResult.setText(null);
				sql = "insert into sangdata values(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, code);
				pstmt.setString(2, sang);
				pstmt.setInt(3, su);
				pstmt.setInt(4, dan);
				rs = pstmt.executeQuery();
				
				DecimalFormat df = new DecimalFormat("###,###");
				int count = 0;
				sql = "select code, sang, su, dan, su*dan from sangdata";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, code);
				pstmt.setString(2, sang);
				pstmt.setInt(3, su);
				pstmt.setInt(4, dan);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					String result = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4)
					+ "\t" + df.format(rs.getInt(3)*rs.getInt(4)) + "\n";
					
					txtResult.append(result);
					count++;
				}
				txtResult.append("건 수: " + count);
			}

			
		} catch (Exception e2) {
			System.out.println("actionPerformed err : " + e2);// TODO: handle exception
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}

	}

	public static void main(String[] args) {
		new DbTest7Ex2();
	}
}
