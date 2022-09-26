package myboard.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class BoardDTO implements Serializable{
	
	public static final long serialVersionUID = 328495761L;
	
	private int bid;
	private String btitle;
	private String bcontent;
	private Timestamp bwdate;
	private String bdomain;
	private String bwriterid;
	
	public BoardDTO() {
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public Timestamp getBwdate() {
		return bwdate;
	}

	public void setBwdate(Timestamp bwdate) {
		this.bwdate = bwdate;
	}

	public String getBdomain() {
		return bdomain;
	}

	public void setBdomain(String bdomain) {
		this.bdomain = bdomain;
	}

	public String getBwriterid() {
		return bwriterid;
	}

	public void setBwriterid(String bwriterid) {
		this.bwriterid = bwriterid;
	}

	@Override
	public String toString() {
		return String.format("BoardDTO [bid=%s, btitle=%s, bcontent=%s, bwdate=%s, bdomain=%s, bwriterid=%s]", bid,
				btitle, bcontent, bwdate, bdomain, bwriterid);
	}
	
} // class
