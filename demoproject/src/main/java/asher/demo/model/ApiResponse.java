package asher.demo.model;

import java.util.Map;

public class ApiResponse {
	
	private MetaData metaData;
	private Map<String, StockData> stockData;
	
	
	public MetaData getMetaData() {
		return metaData;
	}
	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}
	public Map<String, StockData> getStockData() {
		return stockData;
	}
	public void setStockData(Map<String, StockData> stockData) {
		this.stockData = stockData;
	}
}
