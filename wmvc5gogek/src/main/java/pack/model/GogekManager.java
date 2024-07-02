package pack.model;

import java.util.ArrayList;

public class GogekManager {
	private static GogekManager manager = new GogekManager();

	public static GogekManager instance() {
		return manager;
	}

	private GogekDaoModel getGogekDaoModel() {
		return new GogekDaoModel();
	}

	// 전체 자료 반환
	public ArrayList<BuserDto> getBuserAll() {
		return getGogekDaoModel().getBuserDataAll();
	}
	
	// 담당 직원 고객
	public ArrayList<GogekDto> getGogekList(String buser_no) {
		return getGogekDaoModel().findGogek(buser_no);
	}
}
