package myboard.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myboard.dao.impl.BoardFileListDAOImpl;
import myboard.dao.impl.BoardViewDAOImpl;
import myboard.dto.BoardDTO;
import myboard.dto.BoardFileDTO;
import myboard.service.BoardFileService;
import myboard.service.BoardViewService;

public class BoardViewHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		String bidParam = request.getParameter("bid")==null ? "" : request.getParameter("bid");	
		BoardViewService boardViewService = new BoardViewDAOImpl();
		BoardDTO boardDTO = null;
		try {
			boardViewService.viewBoard(Integer.parseInt(bidParam));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		request.setAttribute("boardDTO", boardDTO);
		
		BoardFileService boardFileService = new BoardFileListDAOImpl();
		List<BoardFileDTO> boardFileDTOList = null;
		try {
			boardFileService.listBoardFile(Integer.parseInt(bidParam));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		request.setAttribute("boardFileDTOList", boardFileDTOList);
		
		return "/jsp/viewBoard.jsp";
	}

}
