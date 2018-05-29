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
import org.springframework.web.client.RestTemplate;

import com.mongodb.CommandResult;

import kr.co.ezinfotech.ezcloud.domain.GPD;
import kr.co.ezinfotech.ezcloud.domain.IDAggregateField;
import kr.co.ezinfotech.ezcloud.domain.PKDAggregateField;
import kr.co.ezinfotech.ezcloud.domain.PKDDomain;
import kr.co.ezinfotech.ezcloud.domain.PPS;
import kr.co.ezinfotech.ezcloud.domain.PZDomain;
import kr.co.ezinfotech.ezcloud.service.PKDService;
import kr.co.ezinfotech.ezcloud.service.PZService;

@RestController
public class ParkingDataController {
	private Logger logger = LoggerFactory.getLogger(ParkingDataController.class);
	
	@Autowired
	private PZService pzService;
	
	@Autowired
	private PKDService pkdService;
	
	// 제주대학교 딥러닝 서버 호출
	@GetMapping("/pkd/getPredict")
	public PPS getPredict() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://35.231.204.227:3000/PREDICT/NEXTWEEK";
		return restTemplate.getForObject(url, PPS.class);
	}
	
	// 제주대학교 딥러닝 서버에서 호출
	@GetMapping("/pkd/getManage/{startDate}/{endDate}")
	public boolean findByManageFlag(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws Exception {
		// logger.info("findByManageFlag - startDate : " + startDate + ", endDate : " + endDate);
		
		// 1. get parkingZone data with managed flag = 1 
		List<PZDomain> managePZ = pzService.findByManageFlag();
		
		// 2. delete all data in mongoDB parkingData
		pkdService.delete();
		
		// 3. call REST server(each parking zone) to get parkingData
		for(int i = 0; i < managePZ.size(); i++) {
			RestTemplate restTemplate = new RestTemplate();
			// http://192.168.66.233:8081/000-0-000000/2014-01-02/2014-01-03
			String url = "http://" + managePZ.get(i).getParking_zone_ip() + ":8081/" + managePZ.get(i).getNo() + "/" + startDate + "/" + endDate;			
			GPD[] gpd = restTemplate.getForObject(url, GPD[].class);
			
			for(int j = 0; j < gpd.length; j++) {
				// 4. insert to mongoDB with parkingData
				PKDDomain tmpPkdd = new PKDDomain();
				tmpPkdd.setNo(managePZ.get(i).getNo());
				tmpPkdd.setCar_no(gpd[j].getCar_no());
				tmpPkdd.setIn_time(gpd[j].getIn_time());
				tmpPkdd.setOut_time(gpd[j].getOut_time());
				pkdService.insert(tmpPkdd);
			}
		}
		return true;
	}
	
	// 제주대학교 딥러닝 서버에서 호출
	@GetMapping("/{pzNo}/{startDate}/{endDate}")
	public List<PKDDomain> findByNoDate(@PathVariable("pzNo") String pzNo, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws Exception {
		// logger.info("pzNo : " + pzNo + ", startDate : " + startDate + ", endDate : " + endDate);
		return pkdService.findByNoDate(pzNo, startDate, endDate);
	}

	@PutMapping("/pkd")
	public List<PKDDomain> updateReply(PKDDomain pkd) {
		return pkdService.update(pkd);
	}

	@PostMapping("/pkd/delete")
	public List<PKDDomain> deleteReply(PKDDomain pkd){
		return pkdService.delete(pkd);
	}

	@GetMapping("/pkd/all")
	public List<PKDDomain> findAll(){
		return pkdService.findAll();
	}

	@GetMapping("/pkd/condition")
	public List<PKDDomain> findByCondition(){
		return pkdService.findByCondition();
	}
	
	@GetMapping("/pkd/page/{page}")
	public List<PKDDomain> findPage(@PathVariable("page") String page) {
		return pkdService.findByPage(page);
	}
	
	@GetMapping("/pkd/db/stats")
	public CommandResult getDBStats() {
		return pkdService.getDbStats();
	}
	
	@GetMapping("/pkd/cl")
	public Set<String> getCollectionNames() {
		return pkdService.getCollectionNames();
	}
	
	@GetMapping("/pkd/cl/{name}/stats")
	public CommandResult getCollectionStats(@PathVariable("name") String name) {
		return pkdService.getCollectionStats(name);
	}
	
	@GetMapping("/pkd/cl/{name}/keys")
	public Set<String> getCollectionKeys(@PathVariable("name") String name) {
		return pkdService.getCollectionKeys(name);
	}
	
	@GetMapping("/pkd/key/{key}/{value}/{page}")
	public List<PKDDomain> findByKeyValue(@PathVariable("key") String key, @PathVariable("value") String value, @PathVariable("page") String page) {
		return pkdService.findByKeyValue(key, value, page);
	}
	
	@GetMapping("/pkd/key/{key}/{value}/tt")
	public long getTotalCountByKeyValue(@PathVariable("key") String key, @PathVariable("value") String value) {
		return pkdService.getTotalCountByKeyValue(key, value);
	}
	
	@GetMapping("/pkd/term/{sdate}/{edate}/{page}")
	public List<PKDDomain> findByDateTerm(@PathVariable("sdate") String sdate, @PathVariable("edate") String edate, @PathVariable("page") String page) {
		logger.info("sdate : " + sdate + ", edate : " + edate + ", page : " + page);
		return pkdService.findByDateTerm(sdate, edate, page);
	}
	
	@GetMapping("/pkd/term/{sdate}/{edate}/tt")
	public long getTotalCountByTerm(@PathVariable("sdate") String sdate, @PathVariable("edate") String edate) {
		return pkdService.getTotalCountByTerm(sdate, edate);
	}
	
	@GetMapping("/pkd/term/{sdate}/{edate}/ag")
	public List<PKDAggregateField> getGroupByIndate(@PathVariable("sdate") String sdate, @PathVariable("edate") String edate) {
		return pkdService.getGroupByDate(sdate, edate);
	}
}
