package myboard.dto;

import java.io.Serializable;

public class BoardFileDTO implements Serializable {

	public static final long SerialVersionUID = 456789354126L;
	
	private int bfid;
	private String bfcfn;
	private String bfsfn;
	private int bfsize;
	private int bfbid;
	private String bfcontenttype;

	// 기본 생성자
	public BoardFileDTO() {
	}

	// 오버로딩 생성자
	public BoardFileDTO(String bfcfn, String bfsfn, int bfsize, int bfbid) {
		this.bfcfn = bfcfn;
		this.bfsfn = bfsfn;
		this.bfsize = bfsize;
		this.bfbid = bfbid;
	}
	
	public int getBfid() {
		return bfid;
	}

	public void setBfid(int bfid) {
		this.bfid = bfid;
	}

	public String getBfcfn() {
		return bfcfn;
	}

	public void setBfcfn(String bfcfn) {
		this.bfcfn = bfcfn;
	}

	public String getBfsfn() {
		return bfsfn;
	}

	public void setBfsfn(String bfsfn) {
		this.bfsfn = bfsfn;
	}

	public int getBfsize() {
		return bfsize;
	}

	public void setBfsize(int bfsize) {
		this.bfsize = bfsize;
	}

	public int getBfbid() {
		return bfbid;
	}

	public void setBfbid(int bfbid) {
		this.bfbid = bfbid;
	}

	public String getBfcontenttype() {
		return bfcontenttype;
	}

	public void setBfcontenttype(String bfcontenttype) {
		this.bfcontenttype = bfcontenttype;
	}

	@Override
	public String toString() {
		return String.format("BoardFileDTO [bfid=%s, bfcfn=%s, bfsfn=%s, bfsize=%s, bfbid=%s, bfcontenttype=%s]", bfid,
				bfcfn, bfsfn, bfsize, bfbid, bfcontenttype);
	}
	
}
