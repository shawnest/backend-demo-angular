package com.rabit.herkoakos.bootjpacrud.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	private long id;
	private String name;
	//private List<Advertisement> advertisementList;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		public long getId() {
			return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "User [id=" + this.id + ", name=" + this.name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

//	@OneToMany(mappedBy = "refUser")
//	public List<Advertisement> getAdvertisementList() {
//		return advertisementList;
//	}
//
//	public void setAdvertisementList(List<Advertisement> advertisementList) {
//		this.advertisementList = advertisementList;
//	}
//	
	
	
	
}
