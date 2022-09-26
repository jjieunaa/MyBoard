package myboard.dao.impl;

import java.util.List;

import myboard.dto.BoardFileDTO;
import myboard.service.BoardFileService;

public abstract class AbstractBoardFileDAO extends BoardDAOImpl implements BoardFileService {

	@Override
	public List<BoardFileDTO> listBoardFile(int bid) throws Exception {
		return null;
	}

	@Override
	public BoardFileDTO viewBoardFile(int bfid) throws Exception {
		return null;
	}

	@Override
	public int writeBoardFile(int bid, BoardFileDTO boardFileDTO) throws Exception {
		return 0;
	}

	@Override
	public int deleteBoardFile(int bfid) throws Exception {
		return 0;
	}
	
	@Override
	public int deleteBoardFiles(int bid) throws Exception {
		return 0;
	}
	
	@Override
	public int updateBoardFiles(int bid, List<BoardFileDTO> list) throws Exception {
		return 0;
	}

}
