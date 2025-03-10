package asher.demo.model;

public class LogVO {
	
	private String seq;
	private String code_id;
	private String log_content;
	private String reg_date;

	public void setLogVO(String seq, String code_id, String log_content, String reg_date) {
		this.seq = seq;
		this.code_id = code_id;
		this.log_content = log_content;
		this.reg_date = reg_date;
	}

	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCode_id() {
		return code_id;
	}
	public void setCode_id(String code_id) {
		this.code_id = code_id;
	}
	public String getLog_content() {
		return log_content;
	}
	public void setLog_content(String log_content) {
		this.log_content = log_content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
}
