package asher.demo.mapper;

import java.util.List;

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
	
	/* SELECT Stock Data List */
	List<StockDataVO> selectStockData(StockDataVO stockDataVO);
}
