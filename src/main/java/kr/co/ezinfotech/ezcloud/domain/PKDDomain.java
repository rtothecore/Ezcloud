package kr.co.ezinfotech.ezcloud.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="parkingData")
public class PKDDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String no;
	private String car_no;
	private String in_time;
	private String out_time;
}
