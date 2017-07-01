package com.chinesejr.vo.util;

public class TreeNodeStateVO {
	// 是否打勾
	private boolean checked;
	// 是否禁用
	private boolean disabled;
	// 展开
	private boolean expanded;
	// 选择
	private boolean selected;

	public boolean isChecked() {
		return checked;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
