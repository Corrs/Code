package com.chinesejr.model.sys;

import javax.persistence.Table;

import com.chinesejr.model.BaseEntity;

@Table(name="lp_sys_content")
public class SysContentModel extends BaseEntity {
	/**
	 * 网站主题背景色
	 */
	private String bgcolor;
	
	/**
	 * 购买信息
	 */
	private String buy;
	
	/**
	 * 底部区域背景色
	 */
	private String footerbgcolor;
	
	/**
	 * 底部区域内容
	 */
	private String footercontent;

	/**
	 * 是否启用  启用的将在前台展示
	 */
	private Integer inuse;

	/**
	 * 获奖（优惠）信息
	 */
	private String offer;
	
	private String prop1;
	
	private String prop2;
	
	private String prop3;
	
	private String prop4;
	
	/**
	 * 网站标题
	 */
	private String title;
	
	public String getBgcolor() {
		return bgcolor;
	}
	
	public String getBuy() {
		return buy;
	}

	public String getFooterbgcolor() {
		return footerbgcolor;
	}

	public String getFootercontent() {
		return footercontent;
	}

	public Integer getInuse() {
		return inuse;
	}

	public String getOffer() {
		return offer;
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

	public String getTitle() {
		return title;
	}

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	public void setBuy(String buy) {
		this.buy = buy;
	}

	public void setFooterbgcolor(String footerbgcolor) {
		this.footerbgcolor = footerbgcolor;
	}

	public void setFootercontent(String footercontent) {
		this.footercontent = footercontent;
	}

	public void setInuse(Integer inuse) {
		this.inuse = inuse;
	}

	public void setOffer(String offer) {
		this.offer = offer;
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

	public void setTitle(String title) {
		this.title = title;
	}
}
