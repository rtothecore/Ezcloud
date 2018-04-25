package kr.co.ezinfotech.ezcloud.domain;

import lombok.Data;

@Data
public class GoogleGeoBounds   {
    private GoogleGeoLatLng northeast;
    private GoogleGeoLatLng southwest;
}