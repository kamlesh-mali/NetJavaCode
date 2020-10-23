package com.ormexample.mapping.entity.many2many;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ManyMobile")
public class ManyMobile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="MOBILE_ID")
	private int id;
	
	@Column(name="MOBILE_NAME")
	private String name;
	
	@ManyToMany(mappedBy = "ManyMobile")
	private Collection<ManyUser> manyUsers=new ArrayList<ManyUser>();

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

	public Collection<ManyUser> getManyUsers() {
		return manyUsers;
	}

	public void setManyUsers(Collection<ManyUser> manyUsers) {
		this.manyUsers = manyUsers;
	}
	
}
