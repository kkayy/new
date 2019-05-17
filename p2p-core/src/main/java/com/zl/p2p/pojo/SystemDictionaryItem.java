package com.zl.p2p.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class SystemDictionaryItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long parentId;
	private String title;
	private String tvalue;
	private Integer sequence;
	private String intro;
	
}
