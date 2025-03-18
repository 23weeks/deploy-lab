package asher.demo.service;

import java.util.List;

import asher.demo.model.MetaDataVO;
import asher.demo.model.StockDataVO;

public interface StockDataService {
	
	/* SELECT Meta Data Serial Number */
	String selectSeq();
	
	/* INSERT Alpha Vantage API - Meta Data */
	void insertMetaData(MetaDataVO metaDataVO);
	
	/* INSERT Alpha Vantage API - Time Series */
	void insertStockData(StockDataVO stockDataVO);
	
	/* SELECT Stock Data List */
	List<StockDataVO> selectStockData(StockDataVO stockDataVO);
}
