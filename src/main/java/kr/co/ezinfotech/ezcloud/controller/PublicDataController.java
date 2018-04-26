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

import kr.co.ezinfotech.ezcloud.domain.PDDomain;
import kr.co.ezinfotech.ezcloud.service.PDService;


// http://ssoonidev.tistory.com/63?category=665815

@RestController
public class PublicDataController {
	private Logger logger = LoggerFactory.getLogger(PublicDataController.class);
	
	@Autowired
	private PDService pdService;

	@GetMapping("/pd/{indate}")
	public List<PDDomain> findByBno(@PathVariable("indate") String indate) throws Exception{
		return pdService.findbyIndate(indate);
	}

	@PostMapping("/pd")
	public List<PDDomain> insertReply(PDDomain pd) {
		logger.info("aaa : "  + pd.toString());
		return pdService.insert(pd);
	}

	@PutMapping("/pd")
	public List<PDDomain> updateReply(PDDomain pd) {
		return pdService.update(pd);
	}

	@PostMapping("/pd/delete")
	public List<PDDomain> deleteReply(PDDomain pd){
		return pdService.delete(pd);
	}

	@GetMapping("/pd/all")
	public List<PDDomain> findAll(){
		return pdService.findAll();
	}

	@GetMapping("/pd/condition")
	public List<PDDomain> findByCondition(){
		return pdService.findByCondition();
	}
	
	@GetMapping("/pd/page/{page}")
	public List<PDDomain> findPage(@PathVariable("page") String page) {
		logger.info("page : "  + page);
		return pdService.findByPage(page);
	}
	
	@GetMapping("/pd/db/stats")
	public CommandResult getDBStats() {
		return pdService.getDbStats();
	}
	
	@GetMapping("/pd/cl")
	public Set<String> getCollectionNames() {
		return pdService.getCollectionNames();
	}
	
	@GetMapping("/pd/cl/{name}/stats")
	public CommandResult getCollectionStats(@PathVariable("name") String name) {
		return pdService.getCollectionStats(name);
	}
	
	@GetMapping("/pd/cl/{name}/keys")
	public Set<String> getCollectionKeys(@PathVariable("name") String name) {
		return pdService.getCollectionKeys(name);
	}
	
	@GetMapping("/pd/key/{key}/{value}/{page}")
	public List<PDDomain> findByKeyValue(@PathVariable("key") String key, @PathVariable("value") String value, @PathVariable("page") String page) {
		logger.info("key : " + key + ", value : " + value + ", page : " + page);
		return pdService.findByKeyValue(key, value, page);
	}
	
	@GetMapping("/pd/key/{key}/{value}/tt")
	public long getTotalCountByKeyValue(@PathVariable("key") String key, @PathVariable("value") String value) {
		return pdService.getTotalCountByKeyValue(key, value);
	}
	
	@GetMapping("/pd/term/{sdate}/{edate}/{page}")
	public List<PDDomain> findByDateTerm(@PathVariable("sdate") String sdate, @PathVariable("edate") String edate, @PathVariable("page") String page) {
		logger.info("sdate : " + sdate + ", edate : " + edate + ", page : " + page);
		return pdService.findByDateTerm(sdate, edate, page);
	}
	
	@GetMapping("/pd/term/{sdate}/{edate}/tt")
	public long getTotalCountByTerm(@PathVariable("sdate") String sdate, @PathVariable("edate") String edate) {
		return pdService.getTotalCountByTerm(sdate, edate);
	}
}
