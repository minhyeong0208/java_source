package pack;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		ProcessDao dao = ProcessDao.getInstance();
		
		try {
			// MyBatis framework 사용
			
			// 데이터 추가
/*
			System.out.println("sangdata 추가");
			DataDto dataDto = new DataDto();
			dataDto.setCode("100");
			dataDto.setSang("바나나");
			dataDto.setSu("5");
			dataDto.setDan("3000");
			
			dao.insertData(dataDto); // 데이터 추가 -> commit이 되지 않았으므로 데이터가 추가되지 않음. 
*/
			
			// 데이터 수정
/*			
			System.out.println("sangdata 수정");
			DataDto dataDto = new DataDto();
			dataDto.setCode("100");
			dataDto.setSang("바나나우유");
			dataDto.setSu("7");
			
			dao.updateData(dataDto); // 데이터 변경 -> auto commit이므로 바로 업데이트됨.
*/
			
			// 데이터 삭제
			System.out.println("sangdata 삭제");
			boolean b = dao.deleteData(100);  // code가 100인 데이터 삭제
			
			if(b) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
			
			// 전체 자료 읽기
			System.out.println("sangdata 전체 자료 읽기");
			List<DataDto> list = dao.selectDataAll();
			System.out.println(list.size());
			
			for(DataDto s:list) {
				System.out.println(s.getCode() + " " +
								s.getSang() + " " +
								s.getSu() + " " + 
								s.getDan());
			}
			
			
			// 하나의 자료 읽기
			System.out.println("\nsangdata 자료 1개 읽기");
			DataDto dto = dao.selectPart("1"); // DataMapper.xml의 parameterType이 String이므로 String 타입을 인수로 전달
			System.out.println(dto.getCode() + " " +
					dto.getSang() + " " +
					dto.getSu() + " " + 
					dto.getDan());
			
		} catch (Exception e) {
			System.out.println("err : " + e.getMessage());
		}
	}

}