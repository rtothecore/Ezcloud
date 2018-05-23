package kr.co.ezinfotech.ezcloud.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="iotData")
public class IDAggregateField {
	private String date;
	private IDAggregateData data;
}
