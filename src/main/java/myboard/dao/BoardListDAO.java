package myboard.dao;

import java.util.List;
import java.util.Map;

import myboard.dto.BoardDTO;
import myboard.service.BoardListService;

public interface BoardListDAO extends BoardListService {
	
	public abstract List<BoardDTO> listBoard(String bdomain, Map<String, String> searchCriteria) throws Exception;
	
	@Override
	public abstract int countAttachFiles(int bid) throws Exception;
	
}
