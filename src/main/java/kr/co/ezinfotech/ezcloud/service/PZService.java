package kr.co.ezinfotech.ezcloud.service;

import java.util.List;
import java.util.Set;

import com.mongodb.CommandResult;

import kr.co.ezinfotech.ezcloud.domain.PZDomain;
import kr.co.ezinfotech.ezcloud.domain.PZDomainShort;

public interface PZService {
	public List<PZDomain> findbyNo(String no);
	public List<PZDomain> findAll();
	public List<PZDomain> findByCondition();
	public List<PZDomain> findByPage(String page);
	public List<PZDomain> findByKeyValue(String key, String value, String page);
	public List<PZDomain> findByDateTerm(String sdate, String edate, String page);
	public List<PZDomain> findByManageFlag();
	public List<PZDomainShort> findByManageFlag2();
	
	public List<PZDomain> insert(PZDomain pz);
	public List<PZDomain> update(PZDomain pz);
	public List<PZDomain> delete(PZDomain pz);
	
	public CommandResult getDbStats();
	public Set<String> getCollectionNames();
	public CommandResult getCollectionStats(String collectionName);
	public Set<String> getCollectionKeys(String collectionName);
	
	public long getTotalCountByKeyValue(String key, String value);
	public long getTotalCountByTerm(String sdate, String edate);
}
