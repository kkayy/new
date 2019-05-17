package com.zl.p2p.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Userfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer state;
	private String  remark;
	private Date auditTime;
	private Date appluTime;
	private Long auditor_id;
	private Long applier_id;
	private Integer score;
	private String file ;
	private String filetype_id;

}
