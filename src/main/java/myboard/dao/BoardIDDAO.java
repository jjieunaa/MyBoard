package myboard.dao;

import myboard.service.BoardIDService;

public interface BoardIDDAO extends BoardIDService {
	
	public abstract int getBID() throws Exception;

}
