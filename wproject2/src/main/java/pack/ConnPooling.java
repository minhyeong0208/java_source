package pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnPooling {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DataSource ds;
	
	public ConnPooling() {
//		JNDI Java Naming and Directory Interface.
//		이름지정 및 디렉토리 서비스에서 제공하는 데이터 및 객체를 참조(lookup)하기 위한 자바 API이다. 
//		일반적으로 자바 애플리케이션을 외부 디렉터리 서비스(DB server,LDAP server..)에 연결할 때 쓰인다.
		try {  // (외부의 파일을 읽으므로 예외 처리)
			// context.xml에 설정된 DB 연결정보 읽기. (pool에서 Connection 객체를 읽는다.)
			Context context = new InitialContext();
			// (context는 META-INF 내부 xml 파일 참조 
			// 이는 자동으로 pooling을 해준다.)
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria"); // Object 타입이므로 casting 필요. java:comp/env 여기까지 키워드. (env 다음은 Resource 태그의 name.)
		} catch (Exception e) {
			System.out.println("db 연결 실패" + e);
		}
	}
	
	public ArrayList<SangpumDto> getDataAll() {
		ArrayList<SangpumDto> list = new ArrayList<SangpumDto>();
		
		try {
			conn = ds.getConnection();  // 이전의 정보는 jdbc_maria가 가지고 있으므로 conn 객체만 생성.
			pstmt = conn.prepareStatement("select * from sangdata");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SangpumDto dto = new SangpumDto();
				dto.setCode(rs.getString(1));
				dto.setSang(rs.getString(2));
				dto.setSu(rs.getString(3));
				dto.setDan(rs.getString("dan"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("getDataAll err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
		return list;
	}
	
	public boolean insertData(SangpumBean bean) {
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			
			// 신상 번호 구하기
			String sql =  "select max(code) as max from sangdata";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int maxCode = 0;
			if(rs.next()) {  // 자료가 있으면 true, 자료가 없으면 maxCode = 1
				maxCode = rs.getInt("max");
			}
			maxCode++;  // 신상 번호
			
			// 추가 작업
			pstmt = conn.prepareStatement("insert into sangdata(code,sang,su,dan) values(?,?,?,?)");
			pstmt.setInt(1, maxCode);
			pstmt.setString(2, bean.getSang());
			pstmt.setString(3, bean.getSu());
			pstmt.setString(4, bean.getDan());
			
			int result = pstmt.executeUpdate();  // result 값 : 값 추가 성공하면 1, 실패하면 0
			if(result == 1) b = true;
		} catch (Exception e) {
			System.out.println("insertData err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		} 
		return b;
	}
	
	/*
	public SangpumDto updateData(String code) { 
		SangpumDto dto = null;
		클래식한 방식
		try {
			String sql = "select * from sangdata where code=?";

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 자료가 있는 경우, 조건문 실행
				dto = new SangpumDto();
				dto.setCode(rs.getString("code"));
				dto.setSang(rs.getString("sang"));
				dto.setSu(rs.getString("su"));
				dto.setDan(rs.getString("dan"));
			}
		} catch (Exception e) {
			System.out.println("updateData err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		} 
		return dto;
	}
	*/
	
	public SangpumDto updateData(String code) {
		SangpumDto dto = null;

		String sql = "select * from sangdata where code=?";
		
		// try ~ with ~ resource 문 -> finally 문 사용 X
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) { // 자료가 있는 경우, 조건문 실행
				dto = new SangpumDto();
				dto.setCode(rs.getString("code"));
				dto.setSang(rs.getString("sang"));
				dto.setSu(rs.getString("su"));
				dto.setDan(rs.getString("dan"));
			}
		} catch (Exception e) {
			System.out.println("updateData err : " + e);
		}
		return dto;
	}
	
	public boolean updateDataOk(SangpumBean bean) {
		boolean b = false;
		
		String sql = "update sangdata set sang=?,su=?,dan=? where code=?";
		
		// try ~ with ~ resource 문 -> finally 문 사용 X
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, bean.getSang());
			pstmt.setString(2, bean.getSu());
			pstmt.setString(3, bean.getDan());
			pstmt.setString(4, bean.getCode());
			
			if(pstmt.executeUpdate() > 0) b = true; // update가 되었을 경우, b = true, pstmt.executeUpdate() : 수정한 레코드의 개수만큼 반환
		} catch(Exception e) {
			System.out.println("updateDataOk err : " + e);
		}
		return b;
	}
	
	public boolean deleteData(String code) {
		boolean b = false;
		
		String sql = "delete from sangdata where code=?";
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, code);
				
			if(pstmt.executeUpdate() > 0) b = true; 
			} catch(Exception e) {
				System.out.println("deleteData err : " + e);
			}
		
		return b;
	}
}
