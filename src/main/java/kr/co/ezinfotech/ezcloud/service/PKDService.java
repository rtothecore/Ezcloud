package kr.co.ezinfotech.ezcloud.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import com.mongodb.CommandResult;

import kr.co.ezinfotech.ezcloud.domain.PKDAggregateField;
import kr.co.ezinfotech.ezcloud.domain.PKDDomain;

public interface PKDService {
	public List<PKDDomain> findbyNo(String no);
	public boolean insert(PKDDomain pkd);
	public List<PKDDomain> findByNoDate(String pzNo, String startDate, String endDate);
	
	public boolean delete();
	
	public List<PKDDomain> findByNo(String no);
	public List<PKDDomain> findAll();
	public List<PKDDomain> findByCondition();
	public List<PKDDomain> findByPage(String page);
	public List<PKDDomain> findByKeyValue(String key, String value, String page);
	public List<PKDDomain> findByDateTerm(String sdate, String edate, String page);
	
	public List<PKDDomain> update(PKDDomain id);
	public List<PKDDomain> delete(PKDDomain id);
	
	public CommandResult getDbStats();
	public Set<String> getCollectionNames();
	public CommandResult getCollectionStats(String collectionName);
	public Set<String> getCollectionKeys(String collectionName);
	
	public long getTotalCountByKeyValue(String key, String value);
	public long getTotalCountByTerm(String sdate, String edate);
	
	public List<PKDAggregateField> getGroupByDate(String sdate, String edate);
}
