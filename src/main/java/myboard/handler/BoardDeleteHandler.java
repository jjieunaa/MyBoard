package myboard.handler;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myboard.dao.impl.BoardDeleteDAOImpl;
import myboard.dao.impl.BoardFileDeleteDAOImpl;
import myboard.dao.impl.BoardFileListDAOImpl;
import myboard.dto.BoardFileDTO;

public class BoardDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		// 삭제할 게시물 bid
		Integer bid = Integer.parseInt(request.getParameter("bid")==null ? "": request.getParameter("bid"));
		
		// 첨부파일 삭제
		List<BoardFileDTO> boardFileDTOList = null;
		try {
			boardFileDTOList = new BoardFileListDAOImpl().listBoardFile(bid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (BoardFileDTO boardFileDTO : boardFileDTOList) {
			File file = new File(boardFileDTO.getBfsfn());
			if (file.exists()) {
				file.delete();
			}
		}
		
		
		try {
			// 첨부파일 데이터 삭제
			new BoardFileDeleteDAOImpl().deleteBoardFiles(bid);
			// 게시물 데이터 삭제
			new BoardDeleteDAOImpl().deleteBoard(bid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return "boardlist.do";
	}

}
