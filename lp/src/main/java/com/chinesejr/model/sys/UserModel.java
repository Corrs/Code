package com.chinesejr.model.sys;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.chinesejr.model.BaseEntity;

/**
 * 
 * @author BobyCo
 * @since 2017-06-12 11:01
 * 用户model
 *
 */
@Table(name="lp_sys_user")
public class UserModel extends BaseEntity {
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 行业
	 */
	private Integer business;
	/**
	 * 确认密码
	 */
	@Transient
	private String confirmPwd;
	
	/**
	 * 用户创建时间 必填
	 */
	private String createdate;
	/**
	 * 学历
	 */
	private Integer education;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 头像路径
	 */
	private String image;
	/**
	 * 婚否
	 */
	private Integer marry;
	/**
	 * 手机号 必填
	 */
	private String mobile;
	/**
	 * 月收入
	 */
	private Integer monthincome;
	/**
	 * 密码 必填
	 */
	private String password;
	/**
	 * 身份证
	 */
	private String personcode;
	/**
	 * 备用字段
	 */
	private String prop1;
	/**
	 * 备用字段
	 */
	private String prop2;
	/**
	 * 备用字段
	 */
	private String prop3;
	/**
	 * 备用字段
	 */
	private String prop4;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 真实姓名
	 */
	private String truename;
	/**
	 * 用户名 必填
	 */
	private String username;
	public String getBirthday() {
		return birthday;
	}
	
	public Integer getBusiness() {
		return business;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public String getCreatedate() {
		return createdate;
	}

	public Integer getEducation() {
		return education;
	}

	public String getEmail() {
		return email;
	}

	public String getImage() {
		return image;
	}

	public Integer getMarry() {
		return marry;
	}

	public String getMobile() {
		return mobile;
	}

	public Integer getMonthincome() {
		return monthincome;
	}

	public String getPassword() {
		return password;
	}

	public String getPersoncode() {
		return personcode;
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

	public Integer getSex() {
		return sex;
	}

	public String getTruename() {
		return truename;
	}

	public String getUsername() {
		return username;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setBusiness(Integer business) {
		this.business = business;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setMarry(Integer marry) {
		this.marry = marry;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setMonthincome(Integer monthincome) {
		this.monthincome = monthincome;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPersoncode(String personcode) {
		this.personcode = personcode;
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

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
