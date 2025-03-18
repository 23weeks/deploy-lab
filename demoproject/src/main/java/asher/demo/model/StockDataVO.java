package asher.demo.model;

public class StockDataVO {
	
	//Time Series Data
	private String seq;				//시퀀스
	private String stock_time;		//시간
	private String symbol;			//종목
	private String open;			//시가
	private String high;			//고가
	private String low;				//저가
	private String close;			//종가
	private String volume;			//거래량
	
	private String rownum;			//rownum
	
	public void setStockDataVO(String stock_time, String open, String high, String low, String close, String volume) {
		this.stock_time = stock_time;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}

	public void setStockDataVO(String seq, String stock_time, String symbol, String open, String high, String low, String close, String volume) {
		this.stock_time = stock_time;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getStock_time() {
		return stock_time;
	}
	public void setStock_time(String stock_time) {
		this.stock_time = stock_time;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getClose() {
		return close;
	}
	public void setClose(String close) {
		this.close = close;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
}
