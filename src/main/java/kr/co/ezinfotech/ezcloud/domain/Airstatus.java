package kr.co.ezinfotech.ezcloud.domain;

import lombok.Data;

@Data
public class Airstatus {
	private String dataTime;
    private String so2Value;
    private String coValue;
    private String o3Value;
    private String no2Value;
    private String pm10Value;
    private String pm25Value;
}
