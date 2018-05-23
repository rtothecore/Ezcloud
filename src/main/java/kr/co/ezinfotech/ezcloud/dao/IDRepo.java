package kr.co.ezinfotech.ezcloud.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import kr.co.ezinfotech.ezcloud.domain.IDDomain;
import kr.co.ezinfotech.ezcloud.domain.PDDomain;



public interface IDRepo extends MongoRepository<IDDomain, Long>{
	public List<IDDomain> findByDate(String date);
	public List<IDDomain> findAll();
}
