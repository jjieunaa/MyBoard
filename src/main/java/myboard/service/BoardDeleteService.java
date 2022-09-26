package myboard.service;

public interface BoardDeleteService extends BoardService {

	public abstract int deleteBoard(int bid) throws Exception;
	
}
