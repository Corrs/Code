package com.chinesejr.model.contact;

import javax.persistence.Table;

import com.chinesejr.model.BaseEntity;

@Table(name="lp_contact")
public class ContactModel extends BaseEntity {
	private Integer type;
	
	private String contact;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
}
