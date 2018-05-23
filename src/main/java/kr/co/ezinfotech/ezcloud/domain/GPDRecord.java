package kr.co.ezinfotech.ezcloud.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GPDRecord {
	private List<GPD> gpds = new ArrayList<>();
}
