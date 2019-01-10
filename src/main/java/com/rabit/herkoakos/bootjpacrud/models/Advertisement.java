package com.rabit.herkoakos.bootjpacrud.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "advertisements")
public class Advertisement {
	
	private long id;
	private User user;
	private String title;
	
	public Advertisement() {
		// TODO Auto-generated constructor stub
	}
	
	public Advertisement(long id, User user, String title) {
		this.id = id;
		this.user = user;
		this.title = title;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToOne
	@JoinColumn(name = "userid")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Advertisement [id=" + this.id + ", title=" + this.title + ", user:" + this.user;
	}
}
