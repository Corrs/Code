package com.chinesejr.model.sys;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.chinesejr.model.BaseEntity;

/**
 * 菜单Model
 * @author BobyCo
 * @since 2017-06-09 23:27
 */
@Table(name="lp_sys_menu")
public class MenuModel extends BaseEntity {
	private String code;
	
	private String icon;
	
	private String name;
	
	private String path;
	
	private String pcode;
	
	private String prop1;

	private String prop2;

	private String prop3;
	
	private Integer type;
	
	@Transient
	private String typename;
	
	public String getCode() {
		return code;
	}

	public String getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}

	public String getPcode() {
		return pcode;
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

	public Integer getType() {
		return type;
	}

	public String getTypename() {
		return typename;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
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

	public void setType(Integer type) {
		this.type = type;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
	
}
