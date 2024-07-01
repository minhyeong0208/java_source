package pack.business;

import java.util.List;

// 대규모의 프로젝트를 진행하는 경우, 인터페이스를 따로 구현.
public interface ProcessInter {
	List<DataDto> selectDataAll();
	DataDto selectPart(String para);
	boolean insertData(DataFormBean form);
	boolean updateData(DataFormBean form);
	boolean deleteData(String para);
}
