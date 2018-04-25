package kr.co.ezinfotech.ezcloud.domain;

import lombok.Data;

@Data
public class GoogleGeoGeometry {
    private GoogleGeoBounds bounds;
    private GoogleGeoLatLng location;
    private String location_type;
    private GoogleGeoBounds viewport;
}