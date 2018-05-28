package kr.co.ezinfotech.ezcloud.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="parkingData")
public class PKDAggregateField {
	private String indate;
	private String intime;
}
