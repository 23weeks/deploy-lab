package asher.demo.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockDataMapper {
	
	/* INSERT Alpha Vantage API - Meta Data */
	void insertMetaData(Map<String, String> metaData);
	
	/* INSERT Alpha Vantage API - Time Series */
	void insertStockData(Map<String, String> stockData);
}
