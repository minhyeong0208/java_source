package pack.mybatis;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pack.business.SqlMapperInter;

public class SqlMapConfig {
	public static SqlSessionFactory sessionFactory; // DB의 SQL명령을 실행시킬 때 필요한 메소드를 갖고 있다.

	static {
		String resource = "pack/mybatis/Configuration.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);   
			sessionFactory = new SqlSessionFactoryBuilder().build(reader); 
			reader.close();
			
			// MyBatis Annotation을 사용하는 경우 추가할 코드
			Class[] mappers = {SqlMapperInter.class}; // 파일의 확장자까지 입력해야함
			
			for(Class m: mappers) {
				// Mapper 등록
				sessionFactory.getConfiguration().addMapper(m);
			}
			
			// 이전에는 SqlSessionFactory에서 sessionFatory만 가지고 감.
			
		} catch (Exception e) {
			System.out.println("SqlMapConfig 오류 : " + e);
		}
	}

	public static SqlSessionFactory getSqlSession() {
		return sessionFactory;
	}
}