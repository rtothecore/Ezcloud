package kr.co.ezinfotech.ezcloud.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.WriteConcern;

import kr.co.ezinfotech.ezcloud.dao.PKDRepo;
import kr.co.ezinfotech.ezcloud.domain.PKDDomain;
import kr.co.ezinfotech.ezcloud.domain.PZDomain;
import kr.co.ezinfotech.ezcloud.service.PKDService;

@Service
public class PKDServiceImpl implements PKDService{
	private static Logger logger = LoggerFactory.getLogger(PKDService.class);
	
	@Autowired
	private PKDRepo pkdRepo;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Cacheable(value="parkingDataCache", key="#no")
	@Override
	public List<PKDDomain> findbyNo(String no) {
		logger.info("Go Mongo");
		return pkdRepo.findByNo(no);
	}

	@Cacheable(value="parkingDataCache", key="#no")
	@Override
	public List<PKDDomain> findByNoDate(String pzNo, String startDate, String endDate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("no").is(pzNo));
		query.addCriteria(Criteria.where("in_time").lte(endDate).gte(startDate));
		
		return mongoTemplate.find(query, PKDDomain.class);
	}
	
	@Override
	public boolean insert(PKDDomain pkd) {
		String no = pkd.getNo();
		mongoTemplate.insert(pkd);
		return true;
	}
	
	@CachePut(value="parkingDataCache", key="#parkingData.no")
	@Override
	public boolean delete() {
		Query query = new Query();
		mongoTemplate.remove(query, PKDDomain.class);
		
		return true;
	}
}
