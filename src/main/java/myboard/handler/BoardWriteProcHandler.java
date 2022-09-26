package myboard.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import myboard.dao.impl.BoardFileWriteDAOImpl;
import myboard.dao.impl.BoardIDDAOImpl;
import myboard.dao.impl.BoardWriteDAOImpl;
import myboard.dto.BoardDTO;
import myboard.dto.BoardFileDTO;
import myboard.service.BoardFileService;
import myboard.service.BoardIDService;
import myboard.service.BoardWriteService;

public class BoardWriteProcHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBtitle(request.getParameter("btitle")==null ? "" : request.getParameter("btitle"));
		boardDTO.setBcontent(request.getParameter("bcontent")==null ? "" : request.getParameter("bcontent"));
		boardDTO.setBdomain(request.getParameter("bdomain")==null ? "" : request.getParameter("bdomain"));
		boardDTO.setBwriterid("anonymous");
		
		// 게시물 정보를 테이블에 등록
		BoardWriteService boardWriteService = new BoardWriteDAOImpl();
		int resultBoard = 0;
		try {
			boardWriteService.writeBoard(boardDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// 등록된 게시물의 id 획득
		BoardIDService boardIDService = new BoardIDDAOImpl();
		int bid = 0;
		try {
			boardIDService.getBID();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// 첨부파일 정보를 테이블에 등록
		BoardFileService boardFileService = new BoardFileWriteDAOImpl();
		Collection <Part> parts = null;
		try {
			request.getParts();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		List<String> sfnList = (List<String>)request.getAttribute("sfnList");
		List<String> contenttypeList = (List<String>)request.getAttribute("contenttypeList");
		
		int listIndex = 0;
		for (Part part : parts) {
			if (part.getHeader("Content-Disposition").contains("filename=") && part.getSize()>0) {
				BoardFileDTO boardFileDTO = new BoardFileDTO();
				boardFileDTO.setBfcfn(part.getSubmittedFileName());
				boardFileDTO.setBfsfn(sfnList.get(listIndex));
				boardFileDTO.setBfsize((int)part.getSize());
				boardFileDTO.setBfcontenttype(contenttypeList.get(listIndex));
				try {
					boardFileService.writeBoardFile(bid, boardFileDTO);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				listIndex++;
			}
		}
		
		return "boardlist.do";
	}

}
