package kr.co.ezinfotech.ezcloud.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection="parkingZone")
public class PZDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String no;
    private String name;
    private String division;
    private String type;
    private String addr_road;
    private String addr_jibun;
    private String total_p;
    private String feed;
    private String buje;
    private String op_date;
    
    private Term w_op;
    private Term s_op;
    private Term h_op;
    
    private String fee_info;
    
    private TF park_base;
    private TF add_term;
    private TF one_day_park;
    
    private String month_fee;
    private String payment;
    private String remarks;
    private String manager;
    private String tel;
    private String lat;
    private String lng;
    private String data_date;
    
    private String manage_flag;
    private String parking_zone_ip;
}
