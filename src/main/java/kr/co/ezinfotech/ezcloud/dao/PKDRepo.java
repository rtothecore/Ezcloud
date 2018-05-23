package kr.co.ezinfotech.ezcloud.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import kr.co.ezinfotech.ezcloud.domain.PKDDomain;


public interface PKDRepo extends MongoRepository<PKDDomain, Long>{
	public List<PKDDomain> findByNo(String no);
	public List<PKDDomain> findAll();
}
