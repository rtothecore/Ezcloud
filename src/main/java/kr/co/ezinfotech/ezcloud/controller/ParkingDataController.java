package kr.co.ezinfotech.ezcloud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import kr.co.ezinfotech.ezcloud.domain.GPD;
import kr.co.ezinfotech.ezcloud.domain.GPDRecord;
import kr.co.ezinfotech.ezcloud.domain.PKDDomain;
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
	
	@GetMapping("/{pzNo}/{startDate}/{endDate}")
	public List<PKDDomain> findByNoDate(@PathVariable("pzNo") String pzNo, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws Exception {
		// logger.info("pzNo : " + pzNo + ", startDate : " + startDate + ", endDate : " + endDate);
		return pkdService.findByNoDate(pzNo, startDate, endDate);
	}
}
