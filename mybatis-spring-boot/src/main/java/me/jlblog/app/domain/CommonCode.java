package me.jlblog.app.domain;

import lombok.Data;

@Data
public class CommonCode {
	private String commCode;
	private String commCodeName;
	private String commCatCode;
	private String commCatCodeName;
	private String description;
	private String useYn; 
	private String regId;
	private String regYmdt;
}
