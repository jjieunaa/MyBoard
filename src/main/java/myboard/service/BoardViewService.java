package myboard.service;

import myboard.dto.BoardDTO;

public interface BoardViewService extends BoardService {

	public abstract BoardDTO viewBoard(int bid) throws Exception;
	
}
