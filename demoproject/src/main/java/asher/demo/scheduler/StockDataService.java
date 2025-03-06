package asher.demo.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
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

	////////////테스트용
	public void checkMapperFiles() throws Exception {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath:/mapper/**/*.xml");
		
		System.out.println("FOUND MAPPER FILES:");
		for(Resource resource : resources)  {
			System.out.println(resource.getFilename());
		}
	}
	////////////테스트용
	
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
			String information 		= meta_data.get("1. Information").asText();
			String symbol 			= meta_data.get("2. Symbol").asText();
			String lastRefreshed 	= meta_data.get("3. Last Refreshed").asText();
			String interval 		= meta_data.get("4. Interval").asText();
			String outputSize 		= meta_data.get("5. Output Size").asText();
			String timeZone 		= meta_data.get("6. Time Zone").asText();
			
			MetaData metaData = new MetaData(information, symbol, lastRefreshed, interval, outputSize, timeZone);
			
			/*
			System.out.println("information : " + information);
			System.out.println("symbol : " + symbol);
			System.out.println("lastRefreshed : " + lastRefreshed);
			System.out.println("interval : " + interval);
			System.out.println("outputSize : " + outputSize);
			System.out.println("timeZone : " + timeZone);
			*/
			
			//DB에 저장
			//stockDataMapper.insertMetaData(metaData);
			
			//TEST
			stockDataMapper.selectTest();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
