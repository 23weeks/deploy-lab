package asher.demo.mapper;

import org.springframework.stereotype.Repository;

import asher.demo.model.MetaData;

@Repository
public interface StockDataMapper {
	
	/* INSERT Alpha Vantage API - Meta Data */
	void insertMetaData(MetaData metaData);
	
	/* TEST */
	MetaData selectTest();
}
