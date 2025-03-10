package asher.demo.model;

public class LogVO {
	
	private String codeId;
	private String logContent;
	private String regDate;

	public void setLogVO(String codeId, String logContent, String regDate) {
		this.codeId = codeId;
		this.logContent = logContent;
		this.regDate = regDate;
	}
	
	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
}
