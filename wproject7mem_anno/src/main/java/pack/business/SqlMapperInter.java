package pack.business;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SqlMapperInter {
	
	// MyBatis 어노테이션을 사용하여 SQL문 실행. 일반적으로 사용하는 방법
	@Select("select * from membertab")
	public List<DataDto> selectDataAll();
	
	// 이러한 방식을 통해 xml에 의존하지 않을 수 있게 됨.
	@Select("select id, name, passwd, reg_date from membertab where id=#{id}")
	public DataDto selectDataPart(String id); // 인수의 값(id)이 어노테이션에 존재하는 값에 매핑된다.
	
	
	@Insert("insert into membertab values(#{id},#{name},#{passwd},now())")
	public int insertData(DataFormBean form);
	
	
	// public 접근 지정자 생략 가능
	@Update("update membertab set name=#{name} where id=#{id}")
	int updateData(DataFormBean form);
	
	@Delete("delete from membertab where id=#{id}")
	public int deleteData(String id);
}
