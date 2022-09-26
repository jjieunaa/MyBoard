package myboard.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.RandomStringUtils;

import myboard.constants.CommonConstants;
import myboard.dao.impl.BoardFileListDAOImpl;
import myboard.dto.BoardFileDTO;
import myboard.service.BoardFileService;

public class FileuploadServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	} // doGet
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");

		if (req.getParameter("command").equals("write")) {
			process(req, resp, "/jsp/writeBoardProc.jsp");		
		} else { // command.equals("update")
			
			// 기존 파일들 삭제
			BoardFileService boardFileService = new BoardFileListDAOImpl();
			List<BoardFileDTO> boardFileDTOList = null;
			try {
				boardFileDTOList = boardFileService.listBoardFile(Integer.parseInt(req.getParameter("bid")));
				for (BoardFileDTO boardFileDTO : boardFileDTOList) {
					File sfnFile = new File(boardFileDTO.getBfsfn());
					if (sfnFile.exists()) sfnFile.delete();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			process(req, resp, "/jsp/updateBoardProc.jsp");
			
		} // if
		
	} // doPost
	
	private void process(HttpServletRequest req, HttpServletResponse resp, String dispatchURI) {
		try {
			Collection<Part> parts = req.getParts();	
			List<String> sfnList = new ArrayList<String>();
			List<String> contenttypeList = new ArrayList<String>();
			
			for (Part part : parts) {
				if (part.getHeader("Content-Disposition").contains("filename=") && part.getSize()>0) {
					
					File uploadDir = new File(
							CommonConstants.props.getProperty("FILE_UPLOAD_DIR")
							+ new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()))
					);
					
					if (!uploadDir.exists()) uploadDir.mkdir();
					
					String uploadedFileName = "";
					// hashcode() 문제점: 동일한 문자열에서는 hashcode()가 동일 => Math.random() 사용
					uploadedFileName = uploadDir.getPath() + "/" + RandomStringUtils.random(100, true, true);
					
					sfnList.add(uploadedFileName);
					contenttypeList.add(part.getContentType());
					
					part.write(uploadedFileName);
					part.delete();
				} // if
			} // for
			
			req.setAttribute("sfnList", sfnList);
			req.setAttribute("contenttypeList", contenttypeList);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(dispatchURI);
			dispatcher.forward(req, resp);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} // process
} // class
