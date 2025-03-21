package asher.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import asher.demo.mapper.StockDataMapper;
import asher.demo.model.MetaDataVO;
import asher.demo.model.StockDataVO;
import asher.demo.service.StockDataService;

@Service
public class StockDataServiceImpl implements StockDataService {

	private final StockDataMapper stockDataMapper;
	
	public StockDataServiceImpl(StockDataMapper stockDataMapper) {
		this.stockDataMapper = stockDataMapper;
	}

	@Override
	public String selectSeq() {
		return stockDataMapper.selectSeq();
	}

	@Override
	public void insertMetaData(MetaDataVO metaDataVO) {
		stockDataMapper.insertMetaData(metaDataVO);
	}

	@Override
	public void insertStockData(StockDataVO stockDataVO) {
		stockDataMapper.insertStockData(stockDataVO);
	}

	@Override
	public List<StockDataVO> selectStockData(StockDataVO stockDataVO) {
		return stockDataMapper.selectStockData(stockDataVO);
	}
	
	
}
