package kr.co.ezinfotech.ezcloud.service.impl;

import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import kr.co.ezinfotech.ezcloud.dao.IDRepo;
import kr.co.ezinfotech.ezcloud.domain.IDAggregateField;
import kr.co.ezinfotech.ezcloud.domain.IDDomain;
import kr.co.ezinfotech.ezcloud.service.IDService;

@Service
public class IDServiceImpl implements IDService {
	private static Logger logger = LoggerFactory.getLogger(IDService.class);
	
	@Autowired
	private IDRepo idRepo;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<IDDomain> findbyDate(String date) {
		return idRepo.findByDate(date);
	}

	@Override
	public List<IDDomain> findAll() {
		return idRepo.findAll();
	}

	@Override
	public List<IDDomain> findByCondition() {
		Query query = new Query();
		query.addCriteria(Criteria.where("date").gt("2018-04-01"));
		return mongoTemplate.find(query, IDDomain.class);
	}

	@Override
	public List<IDDomain> findByPage(String page) {
		logger.info("Go Mongo - findByPage:" + page);
		final Pageable pageableRequest = new PageRequest(Integer.parseInt(page), 10);
		Query query = new Query();
		query.with(pageableRequest);
		return mongoTemplate.find(query, IDDomain.class);
	}

	@Override
	public List<IDDomain> findByKeyValue(String key, String value, String page) {
		Query query = new Query();
		final Pageable pageableRequest = new PageRequest(Integer.parseInt(page), 10);
		query.addCriteria(Criteria.where(key).regex(value));
		query.with(pageableRequest);
		
		return mongoTemplate.find(query, IDDomain.class);
	}

	@Override
	public List<IDDomain> findByDateTerm(String sdate, String edate, String page) {
		Query query = new Query();
		final Pageable pageableRequest = new PageRequest(Integer.parseInt(page), 10);
		query.addCriteria(Criteria.where("date").lt(edate).gt(sdate));
		query.with(pageableRequest);
		
		return mongoTemplate.find(query, IDDomain.class);
	}

	@Override
	public List<IDDomain> insert(IDDomain id) {
		String date = id.getDate();
		mongoTemplate.insert(id);
		return idRepo.findByDate(date);
	}

	@Override
	public List<IDDomain> update(IDDomain id) {
		String date = id.getDate();
		ObjectId oid = new ObjectId(id.getId());
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(oid));
		
		Update update = new Update();
		update.set("lat", id.getLat());
		update.set("lng", id.getLng());
		
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, IDDomain.class);
		logger.info(writeResult.toString());
				
		return idRepo.findByDate(date);
	}

	@Override
	public List<IDDomain> delete(IDDomain id) {
		String date = id.getDate();		
		ObjectId oid = new ObjectId(id.getId());
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(oid));
		
		mongoTemplate.remove(query, IDDomain.class);
		
		return idRepo.findByDate(date);
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
		
		return mongoTemplate.count(query, IDDomain.class);
	}

	@Override
	public long getTotalCountByTerm(String sdate, String edate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("date").lt(edate).gt(sdate));
		
		return mongoTemplate.count(query, IDDomain.class);
	}

	@Override
	public List<IDAggregateField> getGroupByDate(String sdate, String edate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("date").lt(edate).gt(sdate));
		query.with(new Sort(Sort.Direction.ASC, "date"));
		query.fields().include("date");
		query.fields().include("data.temperature");
		query.fields().include("data.humidity");
		
		return mongoTemplate.find(query, IDAggregateField.class);
	}

	@Override
	public List<IDAggregateField> getGroupByKey(String key, String value) {
		Query query = new Query();
		query.addCriteria(Criteria.where(key).regex(value));
		query.with(new Sort(Sort.Direction.ASC, "date"));
		query.fields().include("date");
		query.fields().include("data.temperature");
		query.fields().include("data.humidity");
		
		return mongoTemplate.find(query, IDAggregateField.class);
	}

}
