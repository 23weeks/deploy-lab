package asher.demo.model;

public class StockDataVO {
	
	//Time Series Data
	private String stockTime;		//시간
	private String symbol;			//종목
	private String open;			//시가
	private String high;			//고가
	private String low;				//저가
	private String close;			//종가
	private String volume;			//거래량
	
	//생성자
	public StockDataVO(String stockTime, String symbol, String open, String high, String low, String close, String volume) {
		this.stockTime = stockTime;
		this.symbol = symbol;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}
	
	public String getStockTime() {
		return stockTime;
	}
	public void setStockTime(String stockTime) {
		this.stockTime = stockTime;
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
}
