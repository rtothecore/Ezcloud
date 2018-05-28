package kr.co.ezinfotech.ezcloud.service.impl;

import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import kr.co.ezinfotech.ezcloud.dao.PKDRepo;
import kr.co.ezinfotech.ezcloud.domain.PKDDomain;
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
	
	@Override
	public List<PKDDomain> findByNo(String no) {
		return pkdRepo.findByNo(no);
	}

	@Override
	public List<PKDDomain> findAll() {
		return pkdRepo.findAll();
	}

	@Override
	public List<PKDDomain> findByCondition() {
		Query query = new Query();
		query.addCriteria(Criteria.where("in_time").gt("2018-04-01"));
		return mongoTemplate.find(query, PKDDomain.class);
	}

	@Override
	public List<PKDDomain> findByPage(String page) {
		logger.info("Go Mongo - findByPage:" + page);
		final Pageable pageableRequest = new PageRequest(Integer.parseInt(page), 10);
		Query query = new Query();
		query.with(pageableRequest);
		return mongoTemplate.find(query, PKDDomain.class);
	}

	@Override
	public List<PKDDomain> findByKeyValue(String key, String value, String page) {
		Query query = new Query();
		final Pageable pageableRequest = new PageRequest(Integer.parseInt(page), 10);
		query.addCriteria(Criteria.where(key).regex(value));
		query.with(pageableRequest);
		
		return mongoTemplate.find(query, PKDDomain.class);
	}

	@Override
	public List<PKDDomain> findByDateTerm(String sdate, String edate, String page) {
		Query query = new Query();
		final Pageable pageableRequest = new PageRequest(Integer.parseInt(page), 10);
		query.addCriteria(Criteria.where("in_time").lt(edate).gt(sdate));
		query.with(pageableRequest);
		
		return mongoTemplate.find(query, PKDDomain.class);
	}

	@Override
	public List<PKDDomain> update(PKDDomain pkd) {
		String no = pkd.getNo();
		ObjectId oid = new ObjectId(pkd.getId());
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(oid));
		
		Update update = new Update();
		update.set("car_no", pkd.getCar_no());
		
		
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, PKDDomain.class);
		logger.info(writeResult.toString());
				
		return pkdRepo.findByNo(no);
	}

	@Override
	public List<PKDDomain> delete(PKDDomain pkd) {
		//String date = pkd.getIn_time();
		String no = pkd.getNo();
		ObjectId oid = new ObjectId(pkd.getId());
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(oid));
		
		mongoTemplate.remove(query, PKDDomain.class);
		
		return pkdRepo.findByNo(no);
	}

	@Override
	public CommandResult getDbStats() {
		DB db = mongoTemplate.getDb();
		CommandResult resultSet = db.getStats();
		logger.info("Go Mongo - DB stats : " + resultSet);
		
		return resultSet;
	}

	@Override
	public Set<String> getCollectionNames() {
		DB db = mongoTemplate.getDb();
		return db.getCollectionNames();
	}

	@Override
	public CommandResult getCollectionStats(String collectionName) {
		DBCollection dbc = mongoTemplate.getCollection(collectionName);
		CommandResult resultSet = dbc.getStats();
		logger.info("Go Mongo - Collection stats : " + resultSet);
		
		return resultSet;
	}

	@Override
	public Set<String> getCollectionKeys(String collectionName) {
		DBCollection dbc = mongoTemplate.getCollection(collectionName);
		DBObject dbo =  dbc.findOne();
		Set<String> collectionKeys = dbo.keySet();
		return collectionKeys;
	}

	@Override
	public long getTotalCountByKeyValue(String key, String value) {
		Query query = new Query();
		query.addCriteria(Criteria.where(key).regex(value));
		
		return mongoTemplate.count(query, PKDDomain.class);
	}

	@Override
	public long getTotalCountByTerm(String sdate, String edate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("in_time").lt(edate).gt(sdate));
		
		return mongoTemplate.count(query, PKDDomain.class);
	}
}
