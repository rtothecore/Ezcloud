package kr.co.ezinfotech.ezcloud.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import kr.co.ezinfotech.ezcloud.domain.PDDomain;



public interface PDRepo extends MongoRepository<PDDomain, Long>{
	public List<PDDomain> findByIndate(String indate);
	public List<PDDomain> findAll();
}
