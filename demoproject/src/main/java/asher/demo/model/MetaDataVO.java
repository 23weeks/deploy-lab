package asher.demo.model;

public class MetaDataVO {
	
	//Meta Data
	private String seq;				//시퀀스
	private String information;		//정보
	private String symbol;			//종목
	private String last_refreshed;	//최종수정시간
	private String time_frame;		//간격
	private String output_size;		//출력크기
	private String time_zone;		//시간대
	private String reg_date;		//생성시간
	
	public void setMetaDataVO(String seq, String information, String symbol, String last_refreshed, String time_frame, String output_size, String time_zone) {
		this.seq = seq;
		this.information = information;
		this.symbol = symbol;
		this.last_refreshed = last_refreshed;
		this.time_frame = time_frame;
		this.output_size = output_size;
		this.time_zone = time_zone;
	}

	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
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
		return last_refreshed;
	}
	public void setLast_refreshed(String last_refreshed) {
		this.last_refreshed = last_refreshed;
	}
	public String getTime_frame() {
		return time_frame;
	}
	public void setTime_frame(String time_frame) {
		this.time_frame = time_frame;
	}
	public String getOutput_size() {
		return output_size;
	}
	public void setOutput_size(String output_size) {
		this.output_size = output_size;
	}
	public String getTime_zone() {
		return time_zone;
	}
	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
}
