package myboard.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myboard.dao.impl.BoardFileListDAOImpl;
import myboard.dao.impl.BoardFileUpdateDAOImpl;
import myboard.dao.impl.BoardUpdateDAOImpl;
import myboard.dao.impl.BoardViewDAOImpl;
import myboard.dto.BoardDTO;
import myboard.dto.BoardFileDTO;
import myboard.service.BoardFileService;
import myboard.service.BoardUpdateService;
import myboard.service.BoardViewService;

public class BoardUpdateProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBtitle(request.getParameter("btitle")==null ? "" : request.getParameter("btitle"));
		boardDTO.setBcontent(request.getParameter("bcontent")==null ? "" : request.getParameter("bcontent"));
		boardDTO.setBdomain(request.getParameter("bdomain")==null ? "" : request.getParameter("bdomain"));
		boardDTO.setBwriterid("anonymous");
		
		//게시글 수정
		BoardUpdateService boardUpdateService = new BoardUpdateDAOImpl();
		int result = 0;
		try {
			boardUpdateService.updateBoard(boardDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// 첨부파일 정보 수정
		int bid = Integer.parseInt(request.getParameter("bid"));
		BoardFileService boardFileService = new BoardFileUpdateDAOImpl();
		try {
			boardFileService.updateBoardFiles(bid, boardFileService.listBoardFile(bid));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
				
		return "boardlist.do";
	}

}
