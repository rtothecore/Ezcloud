package kr.co.ezinfotech.ezcloud.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="publicData")
public class PDAggregateField {
	private String indate;
	private PDAggregateWeather weather;
}
