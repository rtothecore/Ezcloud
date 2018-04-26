package kr.co.ezinfotech.ezcloud.service;

import java.util.List;
import java.util.Set;

import com.mongodb.CommandResult;

import kr.co.ezinfotech.ezcloud.domain.PDDomain;

public interface PDService {
	public List<PDDomain> findbyIndate(String indate);
	public List<PDDomain> findAll();
	public List<PDDomain> findByCondition();
	public List<PDDomain> findByPage(String page);
	public List<PDDomain> findByKeyValue(String key, String value, String page);
	public List<PDDomain> findByDateTerm(String sdate, String edate, String page);
	
	public List<PDDomain> insert(PDDomain pd);
	public List<PDDomain> update(PDDomain pd);
	public List<PDDomain> delete(PDDomain pd);
	
	public CommandResult getDbStats();
	public Set<String> getCollectionNames();
	public CommandResult getCollectionStats(String collectionName);
	public Set<String> getCollectionKeys(String collectionName);
	
	public long getTotalCountByKeyValue(String key, String value);
	public long getTotalCountByTerm(String sdate, String edate);
}
