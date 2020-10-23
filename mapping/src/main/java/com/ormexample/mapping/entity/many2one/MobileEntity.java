package com.ormexample.mapping.entity.many2one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MobileEntity")
public class MobileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="MOBILE_ID")
	private int id;
	
	@Column(name="MOBILE_NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserEntity userEntity;

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

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
}
