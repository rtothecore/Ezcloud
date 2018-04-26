package kr.co.ezinfotech.ezcloud.service.impl;

import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

import kr.co.ezinfotech.ezcloud.dao.PDRepo;
import kr.co.ezinfotech.ezcloud.domain.PDDomain;
import kr.co.ezinfotech.ezcloud.service.PDService;
import kr.co.ezinfotech.ezcloud.service.PZService;

@Service
public class PDServiceImpl implements PDService{
	private static Logger logger = LoggerFactory.getLogger(PZService.class);
	
	@Autowired
	private PDRepo pdRepo;

	@Autowired
	private MongoTemplate mongoTemplate;
		
	@Cacheable(value="publicDataCache", key="#indate")
	@Override
	public List<PDDomain> findbyIndate(String indate) {
		logger.info("Go Mongo");
		return pdRepo.findByIndate(indate);
	}

	@CachePut(value="publicDataCache", key="#publicData.indate")
	@Override
	public List<PDDomain> insert(PDDomain pd) {
		String indate = pd.getIndate();
		mongoTemplate.insert(pd);
		return pdRepo.findByIndate(indate);
	}

	@CachePut(value = "publicDataCache", key="#publicData.indate")
	@Override
	public List<PDDomain> update(PDDomain pd) {
		String indate = pd.getIndate();
		
		ObjectId id = new ObjectId(pd.getId());
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		
		Update update = new Update();
		update.set("lat", pd.getLat());
		update.set("lng", pd.getLng());
		
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, PDDomain.class);
		logger.info(writeResult.toString());
				
		return pdRepo.findByIndate(indate);
	}
	
	@CacheEvict(value = "publicDataCache", key="")
	@Override
	public List<PDDomain> delete(PDDomain pd) {
		String indate = pd.getIndate();
		
		ObjectId id = new ObjectId(pd.getId());

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		
		mongoTemplate.remove(query, PDDomain.class);
		
		return pdRepo.findByIndate(indate);		
	}

	@Override
	public List<PDDomain> findAll() {
		return pdRepo.findAll();
	}

	@Override
	public List<PDDomain> findByCondition() {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").gt(10));
		return mongoTemplate.find(query, PDDomain.class);
	}
	
	@Override
	public List<PDDomain> findByPage(String page) {
		logger.info("Go Mongo - findByPage:" + page);
		final Pageable pageableRequest = new PageRequest(Integer.parseInt(page), 10);
		Query query = new Query();
		query.with(pageableRequest);
		return mongoTemplate.find(query, PDDomain.class);
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
	public List<PDDomain> findByKeyValue(String key, String value, String page) {
		Query query = new Query();
		final Pageable pageableRequest = new PageRequest(Integer.parseInt(page), 10);
		query.addCriteria(Criteria.where(key).regex(value));
		query.with(pageableRequest);
		
		return mongoTemplate.find(query, PDDomain.class);
	}
	
	@Override
	public long getTotalCountByKeyValue(String key, String value) {
		Query query = new Query();
		query.addCriteria(Criteria.where(key).regex(value));
		
		return mongoTemplate.count(query, PDDomain.class);
	}
	
	@Override
	public List<PDDomain> findByDateTerm(String sdate, String edate, String page) {
		Query query = new Query();
		final Pageable pageableRequest = new PageRequest(Integer.parseInt(page), 10);
		query.addCriteria(Criteria.where("indate").lt(edate).gt(sdate));
		query.with(pageableRequest);
		
		return mongoTemplate.find(query, PDDomain.class);
	}
	
	@Override
	public long getTotalCountByTerm(String sdate, String edate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("indate").lt(edate).gt(sdate));
		
		return mongoTemplate.count(query, PDDomain.class);
	}
}
