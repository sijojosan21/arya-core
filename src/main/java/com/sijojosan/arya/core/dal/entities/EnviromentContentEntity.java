package com.sijojosan.arya.core.dal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="Enviroment_Content")
public class EnviromentContentEntity {

	@Id
	@Column(name = "key", unique = true, nullable = false)
	private String key;
	@Column(name = "value", nullable = false)
    private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
        
}
