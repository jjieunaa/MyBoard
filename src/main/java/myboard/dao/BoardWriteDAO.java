package myboard.dao;

import myboard.dto.BoardDTO;
import myboard.service.BoardWriteService;

public interface BoardWriteDAO extends BoardWriteService {

	public abstract int writeBoard(BoardDTO boardDTO) throws Exception;
	
}
