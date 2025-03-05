package asher.demo.model;

public class MetaData {
	
	//Meta Data
	private String information;		//정보
	private String symbol;			//종목
	private String last_refreshed;	//최종수정시간
	private String interval;		//간격
	private String output_size;		//출력크기
	
	
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getLast_refreshed() {
		return last_refreshed;
	}
	public void setLast_refreshed(String last_refreshed) {
		this.last_refreshed = last_refreshed;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public String getOutput_size() {
		return output_size;
	}
	public void setOutput_size(String output_size) {
		this.output_size = output_size;
	}
	
}
