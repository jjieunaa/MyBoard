package myboard.test;

import myboard.dao.impl.BoardUpdateDAOImpl;
import myboard.dto.BoardDTO;
import myboard.service.BoardUpdateService;

public class TestBoardUpdate {

	public static void main(String[] args) {
		
		BoardUpdateService updateService = new BoardUpdateDAOImpl();
		
		try {
			BoardDTO boardDTO = new BoardDTO();
			
			boardDTO.setBtitle("1111");
			boardDTO.setBcontent("1111");
			boardDTO.setBdomain("1111");
			boardDTO.setBwriterid("1111");
			boardDTO.setBid(1);
			
			updateService.updateBoard(boardDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	} // main
	
} // class
