package myboard.test;

import java.util.HashMap;
import java.util.Map;

import myboard.dao.impl.BoardListDAOImpl;

public class TestSearchList {

	public static void main(String[] args) {
		
		BoardListDAOImpl boardListDAOImpl = new BoardListDAOImpl();
		Map<String, String> searchMap = new HashMap<String, String>();

		try {
			boardListDAOImpl.listBoard(null, null);
			boardListDAOImpl.listBoard("", null);
			boardListDAOImpl.listBoard("", searchMap);
			boardListDAOImpl.listBoard("free", searchMap);
			searchMap.put("searchDomain", "btitle");
			searchMap.put("searchText", "java");
			boardListDAOImpl.listBoard("", searchMap);
			boardListDAOImpl.listBoard("free", searchMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	} // main
	
} // class
