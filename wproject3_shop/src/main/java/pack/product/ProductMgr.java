package pack.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import pack.order.OrderBean;

public class ProductMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;

	public ProductMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}

	// 전체 자료 읽기
	public ArrayList<ProductDto> getproductAll() {
		ArrayList<ProductDto> list = new ArrayList<ProductDto>();

		try {
			String sql = "select * from shop_product order by no desc";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getString("price"));
				dto.setDetail(rs.getString("detail"));
				dto.setSdate(rs.getString("sdate"));
				dto.setStock(rs.getString("stock"));
				dto.setImage(rs.getString("image"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("getproductAll() err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {}
		}
		return list;
	}

	public boolean insertProduct(HttpServletRequest request) {
		boolean b = false;

		try {
			// 업로드할 이미지 경로: upload 디렉토리(절대 경로)
			String uploadDir = "C:/work/jsou/wproject3_shop/src/main/webapp/upload";
			MultipartRequest multi = new MultipartRequest(request, uploadDir, 5 * 1024 * 1024, "UTF-8",
					new DefaultFileRenamePolicy()); // 3번째 인수는 파일의 크기(5MB)
			// System.out.println(multi.getParameter("name"));
			// System.out.println(multi.getParameter("price"));
			String sql = "insert into shop_product(name, price, detail, sdate, stock, image) values(?,?,?,now(),?,?)";

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("name"));
			pstmt.setString(2, multi.getParameter("price"));
			pstmt.setString(3, multi.getParameter("detail"));
			pstmt.setString(4, multi.getParameter("stock"));

			// 상품 등록 시, 이미지를 선택하지 않은 경우 다음 조건문 실행
			if (multi.getFilesystemName("image") == null) {
				pstmt.setString(5, multi.getFilesystemName("ready.gif"));
			} else {
				pstmt.setString(5, multi.getFilesystemName("image"));
			}

			if (pstmt.executeUpdate() > 0)
				b = true;

		} catch (Exception e) {
			System.out.println("insertProduct() err : " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}

		return b;
	}

	public ProductDto getProduct(String no) {
		ProductDto dto = null;

		try {
			String sql = "select * from shop_product where no=?";

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new ProductDto();

				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getString("price"));
				dto.setDetail(rs.getString("detail"));
				dto.setSdate(rs.getString("sdate"));
				dto.setStock(rs.getString("stock"));
				dto.setImage(rs.getString("image"));
			}
		} catch (Exception e) {
			System.out.println("getProduct() err : " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}

		return dto;
	}

//	20240617
	// 관리자 상품 수정
	public boolean updateProduct(HttpServletRequest request) {
		boolean b = false;

		try {
			// 업로드할 이미지 경로: upload 디렉토리(절대 경로)
			String uploadDir = "C:/work/jsou/wproject3_shop/src/main/webapp/upload";
			MultipartRequest multi = new MultipartRequest(request, uploadDir, 5 * 1024 * 1024, "UTF-8",
					new DefaultFileRenamePolicy()); // 3번째 인수는 파일의 크기(5MB)
			

			conn = ds.getConnection();
			
			if (multi.getFilesystemName("image") == null) {
				String sql = "update shop_product set name=?,price=?,detail=?,stock=? where no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, multi.getParameter("name"));
				pstmt.setString(2, multi.getParameter("price"));
				pstmt.setString(3, multi.getParameter("detail"));
				pstmt.setString(4, multi.getParameter("stock"));
				pstmt.setString(5, multi.getParameter("no"));
			
			} else {
				String sql = "update shop_product set name=?,price=?,detail=?,stock=?,image=? where no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, multi.getParameter("name"));
				pstmt.setString(2, multi.getParameter("price"));
				pstmt.setString(3, multi.getParameter("detail"));
				pstmt.setString(4, multi.getParameter("stock"));
				pstmt.setString(5, multi.getFilesystemName("image"));
				pstmt.setString(6, multi.getParameter("no"));				
			}

			if (pstmt.executeUpdate() > 0) b = true;

		} catch (Exception e) {
			System.out.println("updateProduct() err : " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) { }
		}

		return b;
	}
	
	public boolean deleteProduct(String no) {
		boolean b = false;
		
		try {
			conn = ds.getConnection();
			String sql = "delete from shop_product where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			
			if(pstmt.executeUpdate() > 0) b = true;
		} catch (Exception e) {
			System.out.println("deleteProduct() err : " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) { }
		}
		
		return b;
	}
	
	// 고객이 상품 주문 시, 주문 수량만큼 재고 차감
	public void reduceProduct(OrderBean bean) {
		try {
			String sql = "update shop_product set stock=(stock - ?) where no =?";
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getQuantity());
			pstmt.setString(2, bean.getProduct_no());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("reduceProduct() err : " + e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) { }
		}
	}
}
