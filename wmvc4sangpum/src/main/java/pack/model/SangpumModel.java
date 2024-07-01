package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


// DB와 연결 Model
public class SangpumModel {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public List<SangpumDto> selectDataAll() {
		List<SangpumDto> list = null;
		
		SqlSession session = factory.openSession();
		list = session.selectList("selectDataAll");  // 매개변수의 selectDataAll 값은 DataMapper.xml의 id값에 해당
		session.close();
		
		return list;
	}
}
