package pack.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pack.business.ProcessDao;
import pack.business.DataDto;
import pack.mybatis.SqlMapConfig;

public class ProcessDao {
	private static ProcessDao dao = new ProcessDao();
	
	// Singleton
	public static ProcessDao getInstance() {
		return dao;
	}
	
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();  // factory는 configuration.xml를 객체로 만들어 받음.
	
	
	// 여러 개의 데이터 반환
	public List<DataDto> selectDataAll() throws SQLException {
		SqlSession sqlSession = factory.openSession();  // 세션 열기 -> 웹에서의 세션이 아님.
		List<DataDto> list = sqlSession.selectList("selectDataAll");  // 인수로 DataMapper.xml 내 select 태그의 id값을 전달
		sqlSession.close();
		
		return list;  // 처리한 select 문을 반환
	}
	
	
	// 하나의 데이터를 반환
	public DataDto selectPart(String para) throws SQLException {
		SqlSession sqlSession = factory.openSession();  // 세션 열기 -> 웹에서의 세션이 아님.
		DataDto dto = sqlSession.selectOne("selectDataById", para);
		sqlSession.close();
		
		return dto;
	}
	
	// insert 데이터 삽입(수동 commit) -> commit, rollback
	public void insertData(DataForm form) throws SQLException {
		SqlSession sqlSession = factory.openSession();  // transaction 수동 처리 -> factory.optionSession(false);와 동일
		sqlSession.insert("insertData", form);
		sqlSession.commit();  // 해당 코드를 작성하지 않으면 auto commit이 아니므로 데이터 추가가 되지 않음
		sqlSession.close();
	}
	
	// update 데이터 변경(자동 commit)
	public void updateData(DataForm form) throws SQLException {
		SqlSession sqlSession = factory.openSession(true);  // transaction 자동 처리 -> 인수로 true를 전달하면 auto commit이 된다.
		sqlSession.update("updateData", form);
		sqlSession.close();
	}
	
	// delete 데이터 삭제
	public boolean deleteData(int para) {
		boolean result = false;
		SqlSession sqlSession = factory.openSession();
		
		try {
			int cou = sqlSession.delete("deleteData", para);
			
			if(cou > 0) result = true;
			
			sqlSession.commit();    // 성공하면 commit
		} catch (Exception e) {
			System.out.println("deleteData err : " + e.getMessage());
			sqlSession.rollback();  // 실패하면 rollback
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return result;
	}
}
