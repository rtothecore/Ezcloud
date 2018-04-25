package kr.co.ezinfotech.ezcloud.domain;

import lombok.Data;

@Data
public class GoogleGeoAdressComponent {
    private String long_name;
    private String short_name;
    private String [] types;
}
