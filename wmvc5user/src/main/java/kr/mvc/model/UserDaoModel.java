package kr.mvc.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.mvc.controller.UserForm;

//Controller 클래스의 요청을 받아 DB 연동 처리를 담당
public class UserDaoModel {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public UserDaoModel() {
		
	}
	
	// 하나의 자료 읽기
	public UserDto findUser(String userid) {
		UserDto dto = null;
		
		SqlSession session = factory.openSession();
		
		try {
			dto = session.selectOne("findUser", userid);
		} catch (Exception e) {
			System.out.println("findUser err : " + e);
		} finally {
			session.close();
		}
		
		return dto;
	}
	
	// 전체 자료 읽기
	public ArrayList<UserDto> getUserDataAll() {
		List<UserDto> list = null;
		
		SqlSession session = factory.openSession();
		
		try {
			list = session.selectList("selectDataAll");
		} catch (Exception e) {
			System.out.println("getUserDataAll err : " + e);
		} finally {
			session.close();
		}
		
		return (ArrayList<UserDto>)list;
	}
	
	// insert 데이터 추가
	public int insertData(UserForm userForm) {
		int result = 0;
		
		SqlSession session = factory.openSession();
		
		try {
			result = session.insert("insertData", userForm);
			session.commit();
		} catch (Exception e) {
			System.out.println("insertData err : " + e);
			session.rollback();
		} finally {
			session.close();
		}
		
		return result;  // 0 or 1
	}
	
}
