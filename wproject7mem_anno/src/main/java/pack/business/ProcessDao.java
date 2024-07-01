package pack.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pack.business.ProcessDao;
import pack.business.DataDto;
import pack.mybatis.SqlMapConfig;

public class ProcessDao implements ProcessInter {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();  // factory는 configuration.xml를 객체로 만들어 받음.
	
	@Override
	public List<DataDto> selectDataAll() {
		SqlSession sqlSession = factory.openSession();
		List<DataDto> list = null;
		
		try {
			// 변경 사항 -> MyBatis Annotation을 사용하는 방법
			SqlMapperInter inter = (SqlMapperInter)sqlSession.getMapper(SqlMapperInter.class);
			list = inter.selectDataAll();
		} catch (Exception e) {
			System.out.println("selectDataAll() err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return list;
	}
	
	// 비밀번호 비교를 위해 데이터 한 개를 반환
	@Override
	public DataDto selectPart(String id) {  // id에 해당하는 값이 없는 경우, null을 리턴
		SqlSession sqlSession = factory.openSession();
		DataDto dto = null;
		
		try {
			SqlMapperInter inter = (SqlMapperInter)sqlSession.getMapper(SqlMapperInter.class);
			dto = inter.selectDataPart(id);
		} catch (Exception e) {
			System.out.println("selectPart() err : " + e);

		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return dto;
	}
	
	@Override
	public boolean insertData(DataFormBean form) {
		boolean b = false;
		SqlSession sqlSession = factory.openSession();
			
		try {
			SqlMapperInter inter = (SqlMapperInter)sqlSession.getMapper(SqlMapperInter.class);
			if(inter.insertData(form) > 0) b = true;
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("insertData() err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return b;
	}
	
	@Override
	public boolean updateData(DataFormBean form) {
		boolean b = false;
		SqlSession sqlSession = factory.openSession();
		
		try {
			SqlMapperInter inter = (SqlMapperInter)sqlSession.getMapper(SqlMapperInter.class);
			
			// 비밀번호 비교 후, 수정 여부를 판단
			DataDto dto = inter.selectDataPart(form.getId());
			
			if(dto.getPasswd().equals(form.getPasswd())) {
				// form.getPasswd() : 클라이언트가 입력한 비밀번호
				// dto.getPasswd() : DB 서버가 가지고 있는 비밀번호
				if(inter.updateData(form) > 0) {
					// 수정 성공
					b = true;
					sqlSession.commit();
				}
			}
		} catch (Exception e) {
			// 수정 실패
			System.out.println("insertData() err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return b;
	}
	
	@Override
	public boolean deleteData(String id) {
		boolean b = false;
		SqlSession sqlSession = factory.openSession();
		
		try {
			SqlMapperInter inter = (SqlMapperInter)sqlSession.getMapper(SqlMapperInter.class);
			
			int cout = inter.deleteData(id);
			
			if(cout > 0) {
				b = true;
				sqlSession.commit();
			}
			
		} catch (Exception e) {
			// 수정 실패
			System.out.println("deleteData() err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return b;
	}
}
