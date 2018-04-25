package kr.co.ezinfotech.ezcloud.domain;

import lombok.Data;

@Data
public class GoogleGeoResult   {
    private GoogleGeoAdressComponent [] address_components;
    private String formatted_address;
    private GoogleGeoGeometry geometry;
    private Boolean partial_match;
    private String place_id;
    private String [] types;
}  