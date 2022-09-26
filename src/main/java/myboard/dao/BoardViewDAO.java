package myboard.dao;

import myboard.dto.BoardDTO;
import myboard.service.BoardViewService;

public interface BoardViewDAO extends BoardViewService {
	
	public abstract BoardDTO viewBoard(int bid) throws Exception;

}
