package asher.demo.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import asher.demo.model.Constant;
import asher.demo.model.LogVO;
import asher.demo.model.MetaDataVO;
import asher.demo.model.StockDataVO;
import asher.demo.service.LogService;
import asher.demo.service.StockDataService;

@Service
public class StockDataSchedulerService {
		
	private final StockDataService stockDataService;
	private final LogService logService;
	
	public StockDataSchedulerService(StockDataService stockDataService, LogService logService) {
		this.stockDataService = stockDataService;
		this.logService = logService;
	}

	private static final String API_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=AAPL&interval=5min&apikey=";
	
	@Value("${stock.api.key}")
	private String apiKey;

	@Transactional
	public void fetchAndSaveStockData() {
		
		//SEQ 가져오기(PK로 사용)
		String SEQ = stockDataService.selectSeq();
				
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
			
			//ObjectMapper 생성
			ObjectMapper objectMapper = new ObjectMapper();
			
			//JSON 문자열을 JsonNode 객체로 변환
			JsonNode rootNode = objectMapper.readTree(response);
			
			//Meta Data 유무 확인
			boolean hasMetaData = rootNode.has("Meta Data");
			
			//DTO용 VO 생성
			MetaDataVO metaDataVO = new MetaDataVO();
			StockDataVO stockDataVO = new StockDataVO();
			
			if(hasMetaData) {
				//데이터를 저장할 Map(Meta Data)
				Map<String, String> metaDataMap = new HashMap<>();
				
				//VO에 API 호출시간 담기
				metaDataVO.setReg_date(formattedTime);
				
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
				
				//VO에 담기
				metaDataVO.setMetaDataVO(
										SEQ,
										metaDataMap.get("Information"), 
										metaDataMap.get("Symbol"), 
										metaDataMap.get("Last Refreshed"), 
										metaDataMap.get("Interval"), 
										metaDataMap.get("Output Size"),
										metaDataMap.get("Time Zone"));
				
				//SEQ, Symbol 저장(Stock Data - Time Series)
				stockDataVO.setSeq(SEQ);
				stockDataVO.setSymbol(metaDataMap.get("Symbol"));
				
				//DB에 저장
				stockDataService.insertMetaData(metaDataVO);
				
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
						
						//VO에 담기
						stockDataVO.setStockDataVO(
												timeKey, 
												dataMap.get("open"), 
												dataMap.get("high"), 
												dataMap.get("low"), 
												dataMap.get("close"), 
												dataMap.get("volume"));
						
						//DB에 저장
						stockDataService.insertStockData(stockDataVO);
					}
				}
			}
			
			//로그 등록
			logVO.setLogVO(SEQ, Constant.SUCCESS, "", formattedTime);
			
		} catch (Exception e) {
			//콘솔 확인용
			e.printStackTrace();
			
			//로그 등록
			logVO.setLogVO(SEQ, Constant.API_REQUEST_ERROR, e.getMessage(), formattedTime);
			
		} finally {
			//INSERT LOG
			logService.insertLog(logVO);
		}
	}
}
