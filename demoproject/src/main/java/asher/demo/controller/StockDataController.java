package asher.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import asher.demo.model.StockDataVO;
import asher.demo.service.StockDataService;

@CrossOrigin(origins = {"http://localhost:3000", "https://d1edx29o6lkwr1.cloudfront.net"}, allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class StockDataController {
	
	private final StockDataService stockDataService;
	
	public StockDataController(StockDataService stockDataService) {
		this.stockDataService = stockDataService;
	}
	
	@GetMapping("/stocks")
	public ResponseEntity<List<StockDataVO>> getStockData() {
		
		StockDataVO stockDataVO = new StockDataVO();
		//더미 데이터 생성
		stockDataVO.setStockDataVO(
									"9999999999", 
									"2025-12-31 12:10:00.000", 
									"AAPL", 
									"218.275", 
									"218.3997", 
									"217.82", 
									"218.3997", 
									"406641");
		
		//더미 데이터 반환
		return ResponseEntity.ok(List.of(stockDataVO));
	}
	
	@PostMapping("/local/stocks")
	public ResponseEntity<List<StockDataVO>> getLocalStockData(@RequestBody Map<String, String> param) {
		
		//현재 시간
		LocalDateTime now = LocalDateTime.now();
		String formattedTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		System.out.println("REQUEST TIME : " + formattedTime + "\n");
		
		//객체 생성
		StockDataVO stockDataVO = new StockDataVO();
		
		//symbol, rownum
		stockDataVO.setSymbol(param.get("symbol"));
		stockDataVO.setRownum(param.get("rownum"));

		//데이터를 담을 list
		List<StockDataVO> list = stockDataService.selectStockData(stockDataVO);
		
		return ResponseEntity.ok(list);
	}
}
