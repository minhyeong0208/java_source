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
			list = sqlSession.selectList("selectDataAll");
		} catch (Exception e) {
			System.out.println("selectDataAll() err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return list;
	}
	
	@Override
	public DataDto selectPart(String para) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean insertData(DataFormBean form) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean updateData(DataFormBean form) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean deleteData(String para) {
		// TODO Auto-generated method stub
		return false;
	}
}
