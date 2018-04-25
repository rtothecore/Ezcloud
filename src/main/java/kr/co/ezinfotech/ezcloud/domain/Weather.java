package kr.co.ezinfotech.ezcloud.domain;

import lombok.Data;

@Data
public class Weather {
	private String basedate;
    private String basetime;
    private String t1h;
    private String reh;
    private String rn1;
    private String pty;
}
