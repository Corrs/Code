package com.chinesejr.model.form;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.chinesejr.model.BaseEntity;

@Table(name="lp_form")
public class FormModel extends BaseEntity {
	private String createdate;
	
	private String fieldname;
	
	private Integer fieldtype;
	
	@Transient
	private String fieldTypeName;
	
	private String label;
	
	@Transient
	private String oldFieldName;

	private String placeholder;

	private Integer sn;
	
	private String tablename;
	
	public String getCreatedate() {
		return createdate;
	}
	
	public String getFieldname() {
		return fieldname;
	}

	public Integer getFieldtype() {
		return fieldtype;
	}

	public String getFieldTypeName() {
		return fieldTypeName;
	}

	public String getLabel() {
		return label;
	}

	public String getOldFieldName() {
		return oldFieldName;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public Integer getSn() {
		return sn;
	}

	public String getTablename() {
		return tablename;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public void setFieldtype(Integer fieldtype) {
		this.fieldtype = fieldtype;
	}

	public void setFieldTypeName(String fieldTypeName) {
		this.fieldTypeName = fieldTypeName;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setOldFieldName(String oldFieldName) {
		this.oldFieldName = oldFieldName;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
}
