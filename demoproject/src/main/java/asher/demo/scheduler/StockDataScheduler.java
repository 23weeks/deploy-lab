package asher.demo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockDataScheduler {
	
	private final StockDataSchedulerService stockDataService;
	
	public StockDataScheduler(StockDataSchedulerService stockDataService) {
		this.stockDataService = stockDataService;
	}
	
	/* 5분 주기로 주식 데이터 저장 
	 * 1일 최대 25회 제한으로 1시간 주기로 변경
	 * */
	@Scheduled(cron = "0 0 * * * *")
	//@Scheduled(fixedRate = 300000)
	public void fetchStockData() {
		stockDataService.fetchAndSaveStockData();
	}
}
