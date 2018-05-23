package kr.co.ezinfotech.ezcloud.service;

import java.util.List;

import kr.co.ezinfotech.ezcloud.domain.PKDDomain;

public interface PKDService {
	public List<PKDDomain> findbyNo(String no);
	public boolean insert(PKDDomain pkd);
	public List<PKDDomain> findByNoDate(String pzNo, String startDate, String endDate);
	
	public boolean delete();
}
