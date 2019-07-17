package cn.big.model;

public class BeanExamResult {

	private int sid;
	private String type;
	private String content;
	private String aanswer;
	private String banswer;
	private String canswer;
	private String rightanswer;
	private int score;
	private String youranswer;
	private int question_id;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAanswer() {
		return aanswer;
	}
	public void setAanswer(String aanswer) {
		this.aanswer = aanswer;
	}
	public String getBanswer() {
		return banswer;
	}
	public void setBanswer(String banswer) {
		this.banswer = banswer;
	}
	public String getCanswer() {
		return canswer;
	}
	public void setCanswer(String canswer) {
		this.canswer = canswer;
	}
	public String getRightanswer() {
		return rightanswer;
	}
	public void setRightanswer(String rightanswer) {
		this.rightanswer = rightanswer;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getYouranswer() {
		return youranswer;
	}
	public void setYouranswer(String youranswer) {
		this.youranswer = youranswer;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
}
