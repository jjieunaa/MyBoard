package myboard.service;

import myboard.dto.BoardDTO;

public interface BoardUpdateService extends BoardService {

	public abstract int updateBoard(BoardDTO boardDTO) throws Exception;

}
