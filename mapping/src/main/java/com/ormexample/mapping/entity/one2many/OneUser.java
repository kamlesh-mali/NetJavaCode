package com.ormexample.mapping.entity.one2many;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="ONEUSER")
public class OneUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="USER_ID")
	private int id;
	
	@Column(name="USER_NAME")
	private String name;
	
	@OneToMany
	@JoinTable(name="USER_MOBILE",joinColumns = @JoinColumn(name="USER_ID"),inverseJoinColumns = @JoinColumn(name="MOBILE_ID"))
	private Collection<ManyMobile> mobile=new ArrayList<ManyMobile>();

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

	public Collection<ManyMobile> getMobile() {
		return mobile;
	}

	public void setMobile(Collection<ManyMobile> mobile) {
		this.mobile = mobile;
	}
	
}
