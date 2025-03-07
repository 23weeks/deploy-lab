package asher.demo.model;

public class MetaDataVO {
	
	//Meta Data
	private String information;		//정보
	private String symbol;			//종목
	private String lastRefreshed;	//최종수정시간
	private String timeFrame;		//간격
	private String outputSize;		//출력크기
	private String timeZone;		//시간대
	
	//생성자
	public MetaDataVO(String information, String symbol, String last_refreshed, String time_frame, String output_size, String time_zone) {
		this.information = information;
		this.symbol = symbol;
		this.lastRefreshed = last_refreshed;
		this.timeFrame = time_frame;
		this.outputSize = output_size;
		this.timeZone = time_zone;
	}
	
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
		return lastRefreshed;
	}
	public void setLast_refreshed(String last_refreshed) {
		this.lastRefreshed = last_refreshed;
	}
	public String getTimeFrame() {
		return timeFrame;
	}
	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}
	public String getOutput_size() {
		return outputSize;
	}
	public void setOutput_size(String output_size) {
		this.outputSize = output_size;
	}
	public String getTime_zone() {
		return timeZone;
	}
	public void setTime_zone(String time_zone) {
		this.timeZone = time_zone;
	}
}
