package com.chinesejr.model.msg;

import javax.persistence.Table;

import com.chinesejr.model.BaseEntity;

@Table(name="lp_leave_msg")
public class LeaveMsgModel extends BaseEntity {
	private String address;
	private Integer age;

	private String createdate;

	private String msg;
	
	private String name;
	
	private String phone;
	
	private String qq;
	
	private String wechat;
	
	public String getAddress() {
		return address;
	}
	
	public Integer getAge() {
		return age;
	}

	public String getCreatedate() {
		return createdate;
	}

	public String getMsg() {
		return msg;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getQq() {
		return qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
}
