package asher.demo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import asher.demo.service.impl.StockDataServiceImpl;

@Component
public class StockDataScheduler {
	
	private final StockDataServiceImpl stockDataService;
	
	public StockDataScheduler(StockDataServiceImpl stockDataService) {
		this.stockDataService = stockDataService;
	}
	
	//5분 주기로 주식 데이터 저장
	@Scheduled(cron = "0 0/5 * * * *")
	public void fetchStockData() {
		stockDataService.fetchAndSaveStockData();
	}
}
