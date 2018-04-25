package kr.co.ezinfotech.ezcloud.domain;

import lombok.Data;

@Data
public class GoogleGeoCode {
    private String status;
    private GoogleGeoResult [] results;
    private Boolean exclude_from_slo;
    private String error_message;
}