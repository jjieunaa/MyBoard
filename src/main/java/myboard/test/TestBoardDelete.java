package myboard.test;

import myboard.dao.impl.BoardDeleteDAOImpl;

public class TestBoardDelete {

	public static void main(String[] args) {

		try {
			new BoardDeleteDAOImpl().deleteBoard(5);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	} // main
	
} // class
