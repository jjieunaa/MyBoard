package myboard.service;

import myboard.dto.BoardDTO;

public interface BoardWriteService extends BoardService {

	public abstract int writeBoard(BoardDTO boardDTO) throws Exception;

}
