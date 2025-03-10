package asher.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import asher.demo.model.MetaDataVO;
import asher.demo.model.StockDataVO;

@Mapper
public interface StockDataMapper {
	
	/* SELECT Meta Data Serial Number */
	String selectSeq();
	
	/* INSERT Alpha Vantage API - Meta Data */
	void insertMetaData(MetaDataVO metaDataVO);
	
	/* INSERT Alpha Vantage API - Time Series */
	void insertStockData(StockDataVO stockDataVO);
}
