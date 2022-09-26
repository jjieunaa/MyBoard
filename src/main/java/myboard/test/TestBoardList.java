package myboard.test;

import myboard.dao.impl.BoardListDAOImpl;
import myboard.service.BoardListService;

public class TestBoardList {
	
	public static void main(String[] args) {
		
		BoardListService listService = new BoardListDAOImpl();
		try {
			System.out.println(listService.listBoard(null, null));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
