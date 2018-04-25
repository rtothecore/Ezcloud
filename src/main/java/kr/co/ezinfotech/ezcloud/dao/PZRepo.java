package kr.co.ezinfotech.ezcloud.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import kr.co.ezinfotech.ezcloud.domain.PZDomain;


public interface PZRepo extends MongoRepository<PZDomain, Long>{
	public List<PZDomain> findByNo(String no);
	public List<PZDomain> findAll();
}
