package asher.demo.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import asher.demo.model.StockData;
import asher.demo.repository.StockDataRepository;
import asher.demo.service.StockDataService;

@Service
public class StockDataServiceImpl implements StockDataService {
	
	private static final String API_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=AAPL&interval=5min&apikey=";
	
	@Value("${stock.api.key}")
	private String apiKey;
	
	private final StockDataRepository stockDataRepository;
	
	@Autowired
	public StockDataServiceImpl(StockDataRepository stockDataRepository) {
		this.stockDataRepository = stockDataRepository;
	}
	
	public void fetchAndSaveStockData() {
		
		//현재 시간
		LocalDateTime now = LocalDateTime.now();
		String formattedTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS"));
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("API CALL TIME : " + formattedTime);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		String url = API_URL + apiKey;
		
		RestTemplate restTemplate = new RestTemplate();
		Object response = restTemplate.getForObject(url, Object.class);
		
		System.out.println(response);
		
		//객체 생성
		StockData stockData = new StockData("AAPL", "Apple", "150.25", "1000", "", "", "");
		
		//DB에 저장
		//stockDataRepository.save(stockData);
	}
}
