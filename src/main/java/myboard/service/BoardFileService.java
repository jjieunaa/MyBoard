package myboard.service;

import java.util.List;

import myboard.dto.BoardFileDTO;

public interface BoardFileService extends BoardService {

	// 파일 리스트
	public abstract List<BoardFileDTO> listBoardFile(int bid) throws Exception;
	// 파일 읽기
	public abstract BoardFileDTO viewBoardFile(int bfid) throws Exception;
	// 파일 등록
	public abstract int writeBoardFile(int bid, BoardFileDTO boardFileDTO) throws Exception;
	// 파일 삭제
	public abstract int deleteBoardFile(int bfid) throws Exception;
	
	public abstract int deleteBoardFiles(int bid) throws Exception;
	// 파일 수정
	public abstract int updateBoardFiles(int bid, List<BoardFileDTO> list) throws Exception;
	
}
