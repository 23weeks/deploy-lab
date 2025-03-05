package asher.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import asher.demo.model.StockData;

@Repository
public class StockDataRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public StockDataRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void save(StockData stockData) {
		String sql = "INSERT INTO STOCK_DATA_TB (STICK_TIME, SYMBOL, OPEN, HIGH, LOW, CLOSE, VOLUME) VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql,
							stockData.getStock_time(),
							stockData.getSymbol(),
							stockData.getOpen(),
							stockData.getHigh(),
							stockData.getLow(),
							stockData.getClose(),
							stockData.getVolume()
				);
	}
}
