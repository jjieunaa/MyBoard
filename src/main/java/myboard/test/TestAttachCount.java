package myboard.test;

import myboard.dao.impl.BoardListDAOImpl;

public class TestAttachCount {
	
	public static void main(String[] args) {
		try {
			System.out.println(new BoardListDAOImpl().countAttachFiles(36));	// countAttachFiles(bfbid)
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
