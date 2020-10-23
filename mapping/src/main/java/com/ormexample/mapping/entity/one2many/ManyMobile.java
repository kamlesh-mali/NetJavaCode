package com.ormexample.mapping.entity.one2many;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MANYMOBILE")
public class ManyMobile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="MOBILE_ID")
	private int id;
	
	@Column(name="MOBILE_NAME")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
