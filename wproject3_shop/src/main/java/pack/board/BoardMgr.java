package pack.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	// paging 처리
	private int recTot;  		// tot : 전체 레코드 수
	private int pList = 10;      // 페이지 당 출력 행 수 
	private int pageSu;         // 전체 페이지 수 
	
//	20240611
	public BoardMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}
	
	/*
	public ArrayList<BoardDto> getDataAll(int page) {
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		
		String sql = "select * from board order by gnum desc, onum asc";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			for(int i = 0; i < (page - 1) * pList; i++) {
				rs.next(); // 레코드 포인터 이동    0, 9, 19 ...
			}
			
			int k = 0;
			while(rs.next() && k < pList) {
				BoardDto dto = new BoardDto();
				
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setNested(rs.getInt("nested"));
				list.add(dto);
				k++;
			}
		} catch (Exception e) {
			System.out.println("getDataAll() err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
		return list;
	}
	*/
	public ArrayList<BoardDto> getDataAll(int page, String stype, String sword) {
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		
		String sql = "select * from board";
		try {
			conn = ds.getConnection();
			
			if(sword == null) {  // 검색이 없는 경우
				sql += " order by gnum desc, onum asc";
				pstmt = conn.prepareStatement(sql);
			} else {             // 검색이 있는 경우
				sql += " where " + stype + " like ?";
				sql += " order by gnum desc, onum asc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + sword + "%");
			}
			
			rs = pstmt.executeQuery();
			
			for(int i = 0; i < (page - 1) * pList; i++) {
				rs.next(); // 레코드 포인터 이동    0, 9, 19 ...
			}
			
			int k = 0;
			while(rs.next() && k < pList) {
				BoardDto dto = new BoardDto();
				
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setNested(rs.getInt("nested"));
				list.add(dto);
				k++;
			}
		} catch (Exception e) {
			System.out.println("getDataAll() err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
		return list;
	}
	
	// board 테이블의 최대 번호 반환
	public int currentMaxNum() {  
		String sql = "select max(num) from board";
		int num = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) num = rs.getInt(1);  // 최대 num 값
		} catch (Exception e) {
			System.out.println("currentMaxNum() err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
		return num;
	}
	
	// board 테이블 데이터 삽입
	public void saveData(BoardFormBean bean) {  
		String sql = "insert into board values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9, 0); // readcnt -> 0으로 초기값을 설정하여 누적할 수 있도록 함
			pstmt.setInt(10, bean.getGnum());
			pstmt.setInt(11, 0); // onum
			pstmt.setInt(12, 0); // nested
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("saveData() err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
	}
	
	// 전체 레코드 수 구하기
	public void totalList() {  
		String sql = "select count(*) from board";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			recTot = rs.getInt(1);
			//System.out.println("전체 레코드 수 : " + recTot);
		} catch (Exception e) {
			System.out.println("totalList() err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
	}
	
	// 전체 페이지 수 반환
	public int getPageSu() {
		pageSu = recTot / pList;
		if(recTot % pList > 0) pageSu++;
		return pageSu;
	}

//	20240612
	// 조회수 업데이트
	public void updateReadcnt(String num) {
		String sql = "update board set readcnt=readcnt + 1 where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateReadcnt() err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
	}

	// 댓글 기능을 위한 메소드
	public BoardDto getReplyData(String num) {
		BoardDto dto = null;
		String sql = "select * from board where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BoardDto();
				
				// 상세보기에 어떤 것을 넣을지에 따라 변경
				dto.setTitle(rs.getString("title"));
				dto.setGnum(rs.getInt("gnum"));
				dto.setOnum(rs.getInt("onum"));
				dto.setNested(rs.getInt("nested"));
			}
		} catch (Exception e) {
			System.out.println("getReplyData() err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
		
		return dto;
	}

	public BoardDto getData(String num) {
		BoardDto dto = null;
		String sql = "select * from board where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BoardDto();
				
				// 상세보기에 어떤 것을 넣을지에 따라 변경
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setMail(rs.getString("mail"));
				dto.setTitle(rs.getString("title"));
				dto.setCont(rs.getString("cont"));
				dto.setBip(rs.getString("bip"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
			}
		} catch (Exception e) {
			System.out.println("getData() err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
		
		return dto;
	}

	// 댓글의 onum 갱신을 위한 메소드
	public void updateOnum(int gnum, int onum) {
		// 같은 그룹의 레코드는 모두 작업에 참여 - 같은 그룹의 onum 값 갱신
		// 댓글의 onum은 이미 db에 있는 onum 보다 크거나 같은 값을 변경함.
		String sql = "update board set onum=onum+1 where onum>=? and gnum=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, onum);
			pstmt.setInt(2, gnum);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateOnum() err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
	}

	// 댓글을 저장하기 위한 메소드
	public void replySave(BoardFormBean bean) {
		String sql = "insert into board values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9, 0);   // readcnt -> 0으로 초기값을 설정하여 누적할 수 있도록 함
			pstmt.setInt(10, bean.getGnum());
			pstmt.setInt(11, bean.getOnum());  // onum, nested 값을 replysave.jsp에 저장
			pstmt.setInt(12, bean.getNested());  
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("replySave() err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
	}

	// 글 수정 시, 비밀번호 비교를 위한 메소드
	public boolean checkPass(int num, String user_pass) {
		boolean b = false;
		String sql = "select pass from board where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(user_pass.equals(rs.getString("pass"))) {
					b = true;
				}
			}
		} catch (Exception e) {
			System.out.println("checkPass() err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
		
		return b;
	}

	// 글 수정 시, 실행될 SQL update 문
	public void saveEdit(BoardFormBean bean) {
		String sql = "update board set name=?,mail=?,title=?,cont=? where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getMail());
			pstmt.setString(3, bean.getTitle());
			pstmt.setString(4, bean.getCont());
			pstmt.setInt(5, bean.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("saveEdit() err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
	}

	// 데이터(글)를 삭제하기 위한 메소드
	public void delData(String num) {
		String sql = "delete from board where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("delData() err : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) { }
		}
	}
}
