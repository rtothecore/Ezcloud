package kr.co.ezinfotech.ezcloud.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="publicData")
public class PDDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String indate;
    private String lat;
    private String lng;
    
    private List<String> address = new ArrayList<>();
    
    private Weather weather;
    
    private Airstatus airstatus;
}
