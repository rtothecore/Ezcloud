package kr.co.ezinfotech.ezcloud.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PP {
	private String day;
	private String cars;
}
