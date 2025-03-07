package asher.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import asher.demo.mapper.LogMapper;
import asher.demo.model.LogVO;

@Service
public class ErrorService {
	
	private final LogMapper logMapper;
	
	@Autowired
	public ErrorService(LogMapper logMapper) {
		this.logMapper = logMapper;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)	//Service단에서 @Transactional 선언, 실패 시 독립적인 transaction 처리
	public void logError(LogVO vo) {
		logMapper.insertErrorLog(vo);
	}
}
