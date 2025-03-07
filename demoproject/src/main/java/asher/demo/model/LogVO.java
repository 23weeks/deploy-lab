package asher.demo.model;

public class LogVO {
	
	private String codeId;
	private String logContent;

	public void setLogVO(String codeId, String logContent) {
		this.codeId = codeId;
		this.logContent = logContent;
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
}
