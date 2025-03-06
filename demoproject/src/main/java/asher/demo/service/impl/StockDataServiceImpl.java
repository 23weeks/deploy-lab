package asher.demo.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import asher.demo.model.MetaData;
import asher.demo.model.StockData;
import asher.demo.service.StockDataService;

@Service
public class StockDataServiceImpl implements StockDataService {
	
	private static final String API_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=AAPL&interval=5min&apikey=";
	
	@Value("${stock.api.key}")
	private String apiKey;
	
	public void fetchAndSaveStockData() {
		
		//현재 시간
		LocalDateTime now = LocalDateTime.now();
		String formattedTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS"));
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("API REQUEST TIME : " + formattedTime);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		String url = API_URL + apiKey;
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.getForObject(url, String.class);
			
			//ObjectMapper 생성
			ObjectMapper objectMapper = new ObjectMapper();
			
			//JSON 문자열을 JsonNode 객체로 변환
			JsonNode jsonNode = objectMapper.readTree(response);
			
			//임시
			String data = jsonNode.toString();
			System.out.println(data);
			
			//객체 생성
			JsonNode meta_data = jsonNode.get("Meta Data");
			String information = meta_data.get("Information").asText();
			String symbol = meta_data.get("Symbol").asText();
			String lastRefreshed = meta_data.get("Last Refreshed").asText();
			String interval = meta_data.get("Interval").asText();
			String outputSize = meta_data.get("Output Size").asText();
			String timeZone = meta_data.get("Time Zone").asText();
			
			MetaData metaData = new MetaData(information, symbol, lastRefreshed, interval, outputSize, timeZone);
			//StockData stockData = new StockData();
			
			//DB에 저장
			//stockDataRepository.save(stockData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
