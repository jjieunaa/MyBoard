package myboard.test;

import myboard.dao.impl.BoardWriteDAOImpl;
import myboard.dto.BoardDTO;
import myboard.service.BoardWriteService;

public class TestBoardWrite {

	public static void main(String[] args) {
		
		BoardWriteService writeService = new BoardWriteDAOImpl();
		BoardDTO boardDTO = new BoardDTO();
		
		boardDTO.setBtitle("test제목");
		boardDTO.setBcontent("test내용");
		boardDTO.setBdomain("test분류");
		boardDTO.setBwriterid("test작성자아이디");
		
		try {
			writeService.writeBoard(boardDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
				
	} // main
	
} // class
