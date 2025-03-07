package asher.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import asher.demo.model.MetaDataVO;

@Mapper
public interface StockDataMapper {
	
	/* INSERT Alpha Vantage API - Meta Data */
	void insertMetaData(MetaDataVO metaData);
}
