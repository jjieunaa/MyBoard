package myboard.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myboard.dao.impl.BoardListDAOImpl;
import myboard.service.BoardListService;

public class BoardListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		String bdomainParam = request.getParameter("bdomain")==null ? "" : request.getParameter("bdomain"); // 분류
		
		String searchDomainParam = request.getParameter("searchDomain")==null ? "" : request.getParameter("searchDomain");
		String searchTextParam = request.getParameter("searchText")==null ? "" : request.getParameter("searchText");
		
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("searchDomain", searchDomainParam);
		searchMap.put("searchText", searchTextParam);
		
		BoardListService boardListService = new BoardListDAOImpl();
		
		try {
			request.setAttribute("list", boardListService.listBoard(bdomainParam, searchMap));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		request.setAttribute("bdomainParam", bdomainParam);
		request.setAttribute("boardListService", boardListService);
		
		return "/jsp/listBoard.jsp";
		
	} // process

} // class
