package myboard.test;

import myboard.dao.BoardDAO;
import myboard.dao.impl.BoardDAOImpl;

public class TestConnection2 {
	
	public static void main(String[] args) {
		
		BoardDAO bdao = new BoardDAOImpl();
		System.out.println(bdao.getConnection());
		
	}

}
