package cn.big.model;

public class BeanExamPaper {

	public static int curScore = 0;
	private int eid;
	private String econtent;
	private String eanswera;
	private String eanswerb;
	private String eanswerc;
	private String erightanswer;
	private String etype;
	private int emark;
	//private int escore;
	
	public int getEmark() {
		return emark;
	}
	public void setEmark(int emark) {
		this.emark = emark;
	}
//	public int getEscore() {
//		return escore;
//	}
//	public void setEscore(int escore) {
//		this.escore = escore;
//	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEcontent() {
		return econtent;
	}
	public void setEcontent(String econtent) {
		this.econtent = econtent;
	}
	public String getEanswera() {
		return eanswera;
	}
	public void setEanswera(String eanswera) {
		this.eanswera = eanswera;
	}
	public String getEanswerb() {
		return eanswerb;
	}
	public void setEanswerb(String eanswerb) {
		this.eanswerb = eanswerb;
	}
	public String getEanswerc() {
		return eanswerc;
	}
	public void setEanswerc(String eanswerc) {
		this.eanswerc = eanswerc;
	}
	public String getErightanswer() {
		return erightanswer;
	}
	public void setErightanswer(String erightanswer) {
		this.erightanswer = erightanswer;
	}
	public String getEtype() {
		return etype;
	}
	public void setEtype(String etype) {
		this.etype = etype;
	}
	
	
}
