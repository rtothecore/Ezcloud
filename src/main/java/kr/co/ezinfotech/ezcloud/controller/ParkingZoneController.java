package kr.co.ezinfotech.ezcloud.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.CommandResult;

import kr.co.ezinfotech.ezcloud.domain.PZDomain;
import kr.co.ezinfotech.ezcloud.service.PZService;

// http://ssoonidev.tistory.com/63?category=665815

@RestController
public class ParkingZoneController {
	private Logger logger = LoggerFactory.getLogger(ParkingZoneController.class);
	
	@Autowired
	private PZService pzService;

	@GetMapping("/pz/{no}")
	public List<PZDomain> findByBno(@PathVariable("no") String no) throws Exception{
		return pzService.findbyNo(no);
	}

	@PostMapping("/pz")
	public List<PZDomain> insertReply(PZDomain pz) {
		logger.info("aaa : "  + pz.toString());
		return pzService.insert(pz);
	}

	@PutMapping("/pz")
	public List<PZDomain> updateReply(PZDomain pz) {
		return pzService.update(pz);
	}

	//@PostMapping("/delete")
	@PostMapping("/pz/delete")
	public List<PZDomain> deleteReply(PZDomain pz){
		return pzService.delete(pz);
	}

	@GetMapping("/pz/all")
	public List<PZDomain> findAll(){
		return pzService.findAll();
	}

	@GetMapping("/pz/condition")
	public List<PZDomain> findByCondition(){
		return pzService.findByCondition();
	}
	
	@GetMapping("/pz/page/{page}")
	public List<PZDomain> findPage(@PathVariable("page") String page) {
		logger.info("page : "  + page);
		return pzService.findByPage(page);
	}
	
	@GetMapping("/pz/db/stats")
	public CommandResult getDBStats() {
		return pzService.getDbStats();
	}
	
	@GetMapping("/pz/cl")
	public Set<String> getCollectionNames() {
		return pzService.getCollectionNames();
	}
	
	@GetMapping("/pz/cl/{name}/stats")
	public CommandResult getCollectionStats(@PathVariable("name") String name) {
		return pzService.getCollectionStats(name);
	}
	
	@GetMapping("/pz/cl/{name}/keys")
	public Set<String> getCollectionKeys(@PathVariable("name") String name) {
		return pzService.getCollectionKeys(name);
	}
	
	@GetMapping("/pz/key/{key}/{value}/{page}")
	public List<PZDomain> findByKeyValue(@PathVariable("key") String key, @PathVariable("value") String value, @PathVariable("page") String page) {
		logger.info("key : " + key + ", value : " + value + ", page : " + page);
		return pzService.findByKeyValue(key, value, page);
	}
	
	@GetMapping("/pz/key/{key}/{value}/tt")
	public long getTotalCountByKeyValue(@PathVariable("key") String key, @PathVariable("value") String value) {
		logger.info("getTotalCountByKeyValue-" + "key : " + key + ", value : " + value);
		return pzService.getTotalCountByKeyValue(key, value);
	}
	
	@GetMapping("/pz/term/{sdate}/{edate}/{page}")
	public List<PZDomain> findByDateTerm(@PathVariable("sdate") String sdate, @PathVariable("edate") String edate, @PathVariable("page") String page) {
		logger.info("sdate : " + sdate + ", edate : " + edate + ", page : " + page);
		return pzService.findByDateTerm(sdate, edate, page);
	}
	
	@GetMapping("/pz/term/{sdate}/{edate}/tt")
	public long getTotalCountByTerm(@PathVariable("sdate") String sdate, @PathVariable("edate") String edate) {
		logger.info("getTotalCountByTerm-" + "sdate : " + sdate + ", edate : " + edate);
		return pzService.getTotalCountByTerm(sdate, edate);
	}
	
}
