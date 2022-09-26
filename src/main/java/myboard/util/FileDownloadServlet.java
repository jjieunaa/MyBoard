package myboard.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myboard.dao.impl.BoardFileViewDAOImpl;
import myboard.dto.BoardFileDTO;
import myboard.service.BoardFileService;

public class FileDownloadServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		filedownload(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		filedownload(req, resp);
	}
	
	private void filedownload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		BoardFileService boardFileService = new BoardFileViewDAOImpl();
		BoardFileDTO boardFileDTO = null;
		try {
			boardFileDTO = boardFileService.viewBoardFile(Integer.parseInt(req.getParameter("bfid")));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		resp.setContentType(boardFileDTO.getBfcontenttype());
		resp.setHeader("Content-disposition", "attachment; filename=" + boardFileDTO.getBfcfn());
		
		File sfnFile = new File(boardFileDTO.getBfsfn());
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sfnFile));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		byte[] buffer = new byte[10240];
		int length;
		while ((length = bis.read(buffer)) > 0) {
			bos.write(buffer, 0, length);
		}
		bis.close();
		bos.flush();	// flush할 때 전송됨, flush 안하면 Stream에만 저장
		bos.close();
	}
	
} // class
