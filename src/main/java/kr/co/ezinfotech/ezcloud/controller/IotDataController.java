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

import kr.co.ezinfotech.ezcloud.domain.IDAggregateField;
import kr.co.ezinfotech.ezcloud.domain.IDDomain;
import kr.co.ezinfotech.ezcloud.service.IDService;

@RestController
public class IotDataController {
	private Logger logger = LoggerFactory.getLogger(IotDataController.class);
	
	@Autowired
	private IDService idService;

	@GetMapping("/id/{date}")
	public List<IDDomain> findByBno(@PathVariable("date") String date) throws Exception{
		return idService.findbyDate(date);
	}

	@PostMapping("/id")
	public List<IDDomain> insertReply(IDDomain id) {
		logger.info("aaa : "  + id.toString());
		return idService.insert(id);
	}

	@PutMapping("/id")
	public List<IDDomain> updateReply(IDDomain id) {
		return idService.update(id);
	}

	@PostMapping("/id/delete")
	public List<IDDomain> deleteReply(IDDomain id){
		return idService.delete(id);
	}

	@GetMapping("/id/all")
	public List<IDDomain> findAll(){
		return idService.findAll();
	}

	@GetMapping("/id/condition")
	public List<IDDomain> findByCondition(){
		return idService.findByCondition();
	}
	
	@GetMapping("/id/page/{page}")
	public List<IDDomain> findPage(@PathVariable("page") String page) {
		logger.info("page : "  + page);
		return idService.findByPage(page);
	}
	
	@GetMapping("/id/db/stats")
	public CommandResult getDBStats() {
		return idService.getDbStats();
	}
	
	@GetMapping("/id/cl")
	public Set<String> getCollectionNames() {
		return idService.getCollectionNames();
	}
	
	@GetMapping("/id/cl/{name}/stats")
	public CommandResult getCollectionStats(@PathVariable("name") String name) {
		return idService.getCollectionStats(name);
	}
	
	@GetMapping("/id/cl/{name}/keys")
	public Set<String> getCollectionKeys(@PathVariable("name") String name) {
		return idService.getCollectionKeys(name);
	}
	
	@GetMapping("/id/key/{key}/{value}/{page}")
	public List<IDDomain> findByKeyValue(@PathVariable("key") String key, @PathVariable("value") String value, @PathVariable("page") String page) {
		logger.info("key : " + key + ", value : " + value + ", page : " + page);
		return idService.findByKeyValue(key, value, page);
	}
	
	@GetMapping("/id/key/{key}/{value}/tt")
	public long getTotalCountByKeyValue(@PathVariable("key") String key, @PathVariable("value") String value) {
		return idService.getTotalCountByKeyValue(key, value);
	}
	
	@GetMapping("/id/term/{sdate}/{edate}/{page}")
	public List<IDDomain> findByDateTerm(@PathVariable("sdate") String sdate, @PathVariable("edate") String edate, @PathVariable("page") String page) {
		logger.info("sdate : " + sdate + ", edate : " + edate + ", page : " + page);
		return idService.findByDateTerm(sdate, edate, page);
	}
	
	@GetMapping("/id/term/{sdate}/{edate}/tt")
	public long getTotalCountByTerm(@PathVariable("sdate") String sdate, @PathVariable("edate") String edate) {
		return idService.getTotalCountByTerm(sdate, edate);
	}
	
	@GetMapping("/id/term/{sdate}/{edate}/ag")
	public List<IDAggregateField> getGroupByIndate(@PathVariable("sdate") String sdate, @PathVariable("edate") String edate) {
		return idService.getGroupByDate(sdate, edate);
	}
	
	@GetMapping("/id/key/{key}/{value}/ag")
	public List<IDAggregateField> getGroupByKey(@PathVariable("key") String key, @PathVariable("value") String value) {
		return idService.getGroupByKey(key, value);
	}
}
