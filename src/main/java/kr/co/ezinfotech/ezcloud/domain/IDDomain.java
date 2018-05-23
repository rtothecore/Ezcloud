package kr.co.ezinfotech.ezcloud.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="iotData")
public class IDDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String date;
    private String lat;
    private String lng;
    
    private IDData data; 
}
