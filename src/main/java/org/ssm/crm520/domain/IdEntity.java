package org.ssm.crm520.domain;

import java.io.Serializable;

/**
 * id实体
 * 
 * @author 李璨
 *
 */
public class IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
