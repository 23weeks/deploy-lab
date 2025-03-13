package asher.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import asher.demo.model.StockDataVO;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class StockDataController {
	
	@GetMapping("/stocks")
	public ResponseEntity<List<StockDataVO>> getStockData() {
		StockDataVO stockDataVO = new StockDataVO();
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
}
