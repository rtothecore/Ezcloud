package kr.co.ezinfotech.ezcloud.service.impl;

import java.util.Iterator;
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

import kr.co.ezinfotech.ezcloud.dao.PZRepo;
import kr.co.ezinfotech.ezcloud.domain.PZDomain;
import kr.co.ezinfotech.ezcloud.service.PZService;

@Service
public class PZServiceImpl implements PZService{
	private static Logger logger = LoggerFactory.getLogger(PZService.class);
	
	@Autowired
	private PZRepo pzRepo;

	@Autowired
	private MongoTemplate mongoTemplate;
		
	@Cacheable(value="parkingZoneCache", key="#no")
	@Override
	public List<PZDomain> findbyNo(String no) {
		logger.info("Go Mongo");
		return pzRepo.findByNo(no);
	}

	@CachePut(value="parkingZoneCache", key="#parkingZone.no")
	@Override
	public List<PZDomain> insert(PZDomain pz) {
		//int no = pz.getNo();
		String no = pz.getNo();
		mongoTemplate.insert(pz);
		return pzRepo.findByNo(no);
	}

	@CachePut(value = "parkingZoneCache", key="#parkingZone.no")
	@Override
	public List<PZDomain> update(PZDomain pz) {
		//int no = pz.getNo();
		String no = pz.getNo();
		
		ObjectId id = new ObjectId(pz.getId());
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		
		Update update = new Update();
		update.set("name", pz.getName());
		update.set("division", pz.getDivision());
		
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, PZDomain.class);
		logger.info(writeResult.toString());
				
		return pzRepo.findByNo(no);
	}
	@CacheEvict(value = "parkingZoneCache", key="")
	@Override
	public List<PZDomain> delete(PZDomain pz) {
		//int no = pz.getNo();
		String no = pz.getNo();
		
		ObjectId id = new ObjectId(pz.getId());

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		
		mongoTemplate.remove(query, PZDomain.class);
		
		return pzRepo.findByNo(no);		
	}

	@Override
	public List<PZDomain> findAll() {
		return pzRepo.findAll();
	}

	@Override
	public List<PZDomain> findByCondition() {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").gt(10));
		return mongoTemplate.find(query, PZDomain.class);
	}
	
	@Override
	public List<PZDomain> findByPage(String page) {
		logger.info("Go Mongo - findByPage:" + page);
		final Pageable pageableRequest = new PageRequest(Integer.parseInt(page), 10);
		Query query = new Query();
		query.with(pageableRequest);
		return mongoTemplate.find(query, PZDomain.class);
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
		/*
		Iterator<String> it = collectionKeys.iterator();
		while(it.hasNext()) {
			String itVal = it.next();
			logger.info("Go Mongo - collection key : " + itVal);
		}
		*/
		return collectionKeys;
	}
	
	@Override
	public List<PZDomain> findByKeyValue(String key, String value, String page) {
		Query query = new Query();
		final Pageable pageableRequest = new PageRequest(Integer.parseInt(page), 10);
		query.addCriteria(Criteria.where(key).regex(value));
		query.with(pageableRequest);
		
		return mongoTemplate.find(query, PZDomain.class);
	}
	
	@Override
	public List<PZDomain> findByDateTerm(String sdate, String edate, String page) {
		Query query = new Query();
		final Pageable pageableRequest = new PageRequest(Integer.parseInt(page), 10);
		query.addCriteria(Criteria.where("data_date").lt(edate).gt(sdate));
		query.with(pageableRequest);
		
		return mongoTemplate.find(query, PZDomain.class);
	}
}
