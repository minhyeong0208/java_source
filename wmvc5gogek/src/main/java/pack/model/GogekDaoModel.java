package pack.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.checkerframework.checker.units.qual.A;

public class GogekDaoModel {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public GogekDaoModel() {
		// TODO Auto-generated constructor stub
	}
	
	// 전체 부서 읽기
	public ArrayList<BuserDto> getBuserDataAll() {
		List<BuserDto> list = null;
		
		SqlSession session = factory.openSession();
		
		try {
			list = session.selectList("selectBuserAll");
		} catch (Exception e) {
			System.out.println("getBuserDataAll err : " + e);
		} finally {
			session.close();
		}
		
		return (ArrayList<BuserDto>)list;
	}
	
	public ArrayList<GogekDto> findGogek(String buser_no) {
		List<GogekDto> list = null;
		
		SqlSession session = factory.openSession();
		
		try {
			list = session.selectList("selectPartGogek", buser_no);
		} catch (Exception e) {
			System.out.println("findGogek err : " + e);
		} finally {
			session.close();
		}
		
		return (ArrayList<GogekDto>)list;
	}
}
