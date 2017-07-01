package com.chinesejr.model.sys;

import javax.persistence.Table;

import com.chinesejr.model.BaseEntity;

@Table(name="lp_sys_catalog")
public class CatalogModel extends BaseEntity {
	private String code;
	
	private String createdate; 
	
	private String name;
	
	private String prop1;
	
	private String prop2;
	
	private String prop3;
	
	private String prop4;
	
	private String remark;
	
	private Integer sn;

	public String getCode() {
		return code;
	}

	public String getCreatedate() {
		return createdate;
	}

	public String getName() {
		return name;
	}

	public String getProp1() {
		return prop1;
	}

	public String getProp2() {
		return prop2;
	}

	public String getProp3() {
		return prop3;
	}

	public String getProp4() {
		return prop4;
	}

	public String getRemark() {
		return remark;
	}

	public Integer getSn() {
		return sn;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}

	public void setProp3(String prop3) {
		this.prop3 = prop3;
	}

	public void setProp4(String prop4) {
		this.prop4 = prop4;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}
}
