package com.chinesejr.vo.util;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeVO {
	// 背景色
	private String backColor;
	// 节点code 非主键 唯一
	private String code;
	// 字体颜色
	private String color;
	// 链接
	private String href;
	// 图标
	private String icon;
	private String menuIcon;
	// 子节点
	private List<TreeNodeVO> nodes = new ArrayList<TreeNodeVO>();
	// 是否可选择 默认为true
	private boolean selectable = true;
	// 选中后图标
	private String selectedIcon;
	private TreeNodeStateVO state;
	private List<String> tags;
	
	// 文本显示内容
	private String text;
	
	private String url;

	public String getBackColor() {
		return backColor;
	}

	public String getCode() {
		return code;
	}

	public String getColor() {
		return color;
	}

	public String getHref() {
		return href;
	}

	public String getIcon() {
		return icon;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public List<TreeNodeVO> getNodes() {
		return nodes;
	}

	public String getSelectedIcon() {
		return selectedIcon;
	}

	public TreeNodeStateVO getState() {
		return state;
	}

	public List<String> getTags() {
		return tags;
	}

	public String getText() {
		return text;
	}

	public String getUrl() {
		return url;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public void setNodes(List<TreeNodeVO> nodes) {
		this.nodes = nodes;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}

	public void setState(TreeNodeStateVO state) {
		this.state = state;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
