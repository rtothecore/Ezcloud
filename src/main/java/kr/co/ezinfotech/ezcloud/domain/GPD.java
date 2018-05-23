package kr.co.ezinfotech.ezcloud.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GPD {
	private String car_no;
	private String in_time;
	private String out_time;
}
