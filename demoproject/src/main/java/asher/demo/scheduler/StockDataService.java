package asher.demo.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import asher.demo.mapper.StockDataMapper;
import asher.demo.model.MetaData;

@Service
public class StockDataService {
		
	private final StockDataMapper stockDataMapper;
	
	@Autowired
	public StockDataService(StockDataMapper stockDataMapper) {
		this.stockDataMapper = stockDataMapper;
	}

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
			//String response = restTemplate.getForObject(url, String.class);
			
			//임시
			String response = "{ \"Meta Data\": { \"1. Information\": \"Intraday (5min) open, high, low, close prices and volume\", \"2. Symbol\": \"IBM\", \"3. Last Refreshed\": \"2025-03-05 19:55:00\", \"4. Interval\": \"5min\", \"5. Output Size\": \"Compact\", \"6. Time Zone\": \"US/Eastern\" }, \"Time Series (5min)\": { \"2025-03-05 19:55:00\": { \"1. open\": \"250.7200\", \"2. high\": \"250.7200\", \"3. low\": \"250.7200\", \"4. close\": \"250.7200\", \"5. volume\": \"41\" }, \"2025-03-05 19:50:00\": { \"1. open\": \"250.6000\", \"2. high\": \"250.6000\", \"3. low\": \"250.6000\", \"4. close\": \"250.6000\", \"5. volume\": \"10\" } } }";
			
			//ObjectMapper 생성
			ObjectMapper objectMapper = new ObjectMapper();
			
			//JSON 문자열을 JsonNode 객체로 변환
			JsonNode rootNode = objectMapper.readTree(response);
			
			//임시
			/*
			String data = rootNode.toString();
			System.out.println("===================TEMP===================");
			System.out.println(data);
			System.out.println("===================TEMP===================");
			*/
			
			//Meta Data 유무 확인
			boolean hasMetaData = rootNode.has("Meta Data");
			
			if(hasMetaData) {
				//데이터를 저장할 Map(Meta Data)
				Map<String, String> metaDataMap = new HashMap<>();
				
				//객체 생성(Meta Data)
				JsonNode metaDataNode = rootNode.get("Meta Data");
				
				if(metaDataNode != null && metaDataNode.isObject()) {
					Iterator<String> metaKeys = metaDataNode.fieldNames();
					
					while(metaKeys.hasNext()) {
						String key = metaKeys.next();
						String value = metaDataNode.get(key).asText();
						
						metaDataMap.put(key, value);
					}
				}
				System.out.println(metaDataMap);
				//DB에 저장
				//stockDataMapper.insertMetaData(metaData);
			}
			
			//Time Series (~) Key 찾기
			String timeSeriesKey = null;
			for(Iterator<String> it = rootNode.fieldNames(); it.hasNext(); ) {
				String key = it.next();
				if(key.startsWith("Time Series")) {
					timeSeriesKey = key;
					break;
				}
			}
			
			//Time Series 유무 확인
			if(timeSeriesKey != null) {
				//데이터를 저장할 Map(Time Series)
				Map<String,Map<String, String>> timeSeriesMap = new HashMap<>();
				
				//객체 생성(Time Series)
				JsonNode timeSeriesNode = rootNode.get(timeSeriesKey);
				
				if(timeSeriesNode != null && timeSeriesNode.isObject()) {
					Iterator<String> timeKeys = timeSeriesNode.fieldNames();
					
					while(timeKeys.hasNext()) {
						String timeKey = timeKeys.next();
						JsonNode timeDataNode = timeSeriesNode.get(timeKey);
						
						Map<String, String> dataMap = new HashMap<>();
						Iterator<String> dataKeys = timeDataNode.fieldNames();
						
						while(dataKeys.hasNext()) {
							String dataKey = dataKeys.next();
							String dataValue = timeDataNode.get(dataKey).asText();
							
							dataMap.put(dataKey, dataValue);
						}
						
						timeSeriesMap.put(timeKey, dataMap);
					}
				}
				System.out.println(timeSeriesMap);
				//DB에 저장
				//stockDataMapper.insertMetaData(metaData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
