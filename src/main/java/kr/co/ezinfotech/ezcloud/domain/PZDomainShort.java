package kr.co.ezinfotech.ezcloud.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="parkingZone")
public class PZDomainShort {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String no;
    private String name;
    private String addr_road;
}
