package asher.demo.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockDataScheduler {
	
	private final StockDataService stockDataService;
	
	@Autowired
	public StockDataScheduler(StockDataService stockDataService) {
		this.stockDataService = stockDataService;
	}
	
	//5분 주기로 주식 데이터 저장
	//@Scheduled(cron = "0 0/5 * * * *")
	@Scheduled(fixedRate = 300000)
	public void fetchStockData() {
		
		//xml list 확인용(테스트)
		try {
			stockDataService.checkMapperFiles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stockDataService.fetchAndSaveStockData();
	}
}
