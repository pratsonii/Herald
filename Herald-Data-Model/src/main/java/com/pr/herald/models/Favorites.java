package com.pr.herald.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_H_Favourite")
public class Favorites {

	private Long id;
	private Devices device;
	private Categories category;

	
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "device")
	public Devices getDevice() {
		return device;
	}
	public void setDevice(Devices device) {
		this.device = device;
	}
	
	@Column(name = "category")
	public Categories getCategory() {
		return category;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
}
