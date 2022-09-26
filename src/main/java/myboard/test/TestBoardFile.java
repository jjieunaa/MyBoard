package myboard.test;

import myboard.dao.impl.BoardFileDeleteDAOImpl;
import myboard.dao.impl.BoardFileListDAOImpl;
import myboard.dao.impl.BoardFileViewDAOImpl;
import myboard.dao.impl.BoardFileWriteDAOImpl;
import myboard.dto.BoardFileDTO;
import myboard.service.BoardFileService;

public class TestBoardFile {
	
	public static void main(String[] args) {
		
		// write test
		/*
		BoardFileService boardFileService = new BoardFileWriteDAOImpl();
		
		BoardFileDTO boardFileDTO = new BoardFileDTO("bfcfn", "bfsfn", 1000, 7);
		try {
			boardFileService.writeBoardFile(7, boardFileDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		*/
		
		// list test
		/*
		BoardFileService boardFileService = new BoardFileListDAOImpl();
		try {
			System.out.println(boardFileService.listBoardFile());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		*/
		
		// view test
		/*
		BoardFileService boardFileService = new BoardFileViewDAOImpl();
		try {
			System.out.println(boardFileService.viewBoardFile(1));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		*/
		
		// delete test
		BoardFileService boardFileService = new BoardFileDeleteDAOImpl();
		try {
			System.out.println(boardFileService.deleteBoardFile(1));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	} // main

} // class
