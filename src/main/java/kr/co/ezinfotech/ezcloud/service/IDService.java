package kr.co.ezinfotech.ezcloud.service;

import java.util.List;
import java.util.Set;

import com.mongodb.CommandResult;

import kr.co.ezinfotech.ezcloud.domain.IDAggregateField;
import kr.co.ezinfotech.ezcloud.domain.IDDomain;

public interface IDService {
	public List<IDDomain> findbyDate(String date);
	public List<IDDomain> findAll();
	public List<IDDomain> findByCondition();
	public List<IDDomain> findByPage(String page);
	public List<IDDomain> findByKeyValue(String key, String value, String page);
	public List<IDDomain> findByDateTerm(String sdate, String edate, String page);
	
	public List<IDDomain> insert(IDDomain id);
	public List<IDDomain> update(IDDomain id);
	public List<IDDomain> delete(IDDomain id);
	
	public CommandResult getDbStats();
	public Set<String> getCollectionNames();
	public CommandResult getCollectionStats(String collectionName);
	public Set<String> getCollectionKeys(String collectionName);
	
	public long getTotalCountByKeyValue(String key, String value);
	public long getTotalCountByTerm(String sdate, String edate);
	
	public List<IDAggregateField> getGroupByDate(String sdate, String edate);
	public List<IDAggregateField> getGroupByKey(String key, String value);
}
