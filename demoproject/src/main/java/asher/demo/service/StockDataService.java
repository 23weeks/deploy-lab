package asher.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import asher.demo.mapper.StockDataMapper;
import asher.demo.model.Constant;
import asher.demo.model.LogVO;

@Service
public class StockDataService {
		
	private final StockDataMapper stockDataMapper;
	private final LogService logService;
	
	@Autowired
	public StockDataService(StockDataMapper stockDataMapper, LogService logService) {
		this.stockDataMapper = stockDataMapper;
		this.logService = logService;
	}

	private static final String API_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=AAPL&interval=5min&apikey=";
	
	@Value("${stock.api.key}")
	private String apiKey;

	@Transactional
	public void fetchAndSaveStockData() {
		
		//현재 시간
		LocalDateTime now = LocalDateTime.now();
		String formattedTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("API REQUEST TIME : " + formattedTime);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		String url = API_URL + apiKey;
		
		LogVO logVO = new LogVO();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.getForObject(url, String.class);
			
			//테스트용 데이터
			//String response = "{ \"Meta Data\": { \"1. Information\": \"Intraday (5min) open, high, low, close prices and volume\", \"2. Symbol\": \"IBM\", \"3. Last Refreshed\": \"2025-03-05 19:55:00\", \"4. Interval\": \"5min\", \"5. Output Size\": \"Compact\", \"6. Time Zone\": \"US/Eastern\" }, \"Time Series (5min)\": { \"2025-03-05 19:55:00\": { \"1. open\": \"250.7200\", \"2. high\": \"250.7200\", \"3. low\": \"250.7200\", \"4. close\": \"250.7200\", \"5. volume\": \"41\" }, \"2025-03-05 19:50:00\": { \"1. open\": \"250.6000\", \"2. high\": \"250.6000\", \"3. low\": \"250.6000\", \"4. close\": \"250.6000\", \"5. volume\": \"10\" } } }";
			
			//ObjectMapper 생성
			ObjectMapper objectMapper = new ObjectMapper();
			
			//JSON 문자열을 JsonNode 객체로 변환
			JsonNode rootNode = objectMapper.readTree(response);
			
			//Meta Data 유무 확인
			boolean hasMetaData = rootNode.has("Meta Data");
			
			//Symbol 초기화
			String Symbol = null;
			
			if(hasMetaData) {
				//데이터를 저장할 Map(Meta Data)
				Map<String, String> metaDataMap = new HashMap<>();
				//현재 시간 입력
				metaDataMap.put("REG_DATE", formattedTime);
				
				//객체 생성(Meta Data)
				JsonNode metaDataNode = rootNode.get("Meta Data");
				
				if(metaDataNode != null && metaDataNode.isObject()) {
					Iterator<String> metaKeys = metaDataNode.fieldNames();
					
					while(metaKeys.hasNext()) {
						String key = metaKeys.next();
						String value = metaDataNode.get(key).asText();
						
						String replaceKey = key.replaceAll("^\\d+\\.\\s+", "");
						metaDataMap.put(replaceKey, value);
					}
				}
				
				//symbol 저장(timeSeries에 사용)
				Symbol = metaDataMap.get("Symbol");
				
				//DB에 저장
				stockDataMapper.insertMetaData(metaDataMap);
				
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
							
							String replaceDataKey = dataKey.replaceAll("^\\d+\\.\\s+", "");	//앞에 붙은 "숫자. " 제거
							dataMap.put(replaceDataKey, dataValue);
						}
						
						dataMap.put("STOCK_TIME", timeKey);
						dataMap.put("Symbol", Symbol);
						stockDataMapper.insertStockData(dataMap);
					}
				}
			}
			
			//성공 로그 등록
			logVO.setLogVO(Constant.SUCCESS, "", formattedTime);
			
		} catch (Exception e) {
			//콘솔 확인용
			e.printStackTrace();
			//DB 저장용
			logVO.setLogVO(Constant.API_REQUEST_ERROR, e.getMessage(), formattedTime);
			
		} finally {
			//INSERT LOG
			logService.insertLog(logVO);
		}
	}
}
