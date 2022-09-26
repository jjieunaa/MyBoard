package myboard.dao;

import myboard.service.BoardDeleteService;

public interface BoardDeleteDAO extends BoardDeleteService {

	public abstract int deleteBoard(int bid) throws Exception;
	
}
