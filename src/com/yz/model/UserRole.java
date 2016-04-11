package com.yz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User entity. 角色（用户）
 * 
 * @author lq
 */
@Entity
@Table(name = "userRole", schema = "dbo", catalog = "dchc")
public class UserRole implements java.io.Serializable {

	// Fields
	private Integer id;
	private String username;// 用户名（登陆名）
	private String password;// 密码
	private String telphone;// 电话
	private String number;// 警员编号
	private String realname;// 真实姓名
	private String photo;// 照片
	private Integer userLimit;// 用户权限
	private Integer sex;// 性别
	private String beforeLoginTime;
	private String currentLoginTime;

	// Constructors

	public UserRole(Integer id, String username, String password,
			String telphone, String number, String realname, String photo,
			Integer userLimit, Integer sex, String beforeLoginTime,
			String currentLoginTime) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.telphone = telphone;
		this.number = number;
		this.realname = realname;
		this.photo = photo;
		this.userLimit = userLimit;
		this.sex = sex;
		this.beforeLoginTime = beforeLoginTime;
		this.currentLoginTime = currentLoginTime;
	}

	/** default constructor */
	public UserRole() {
	}

	/** full constructor */

	@Column(name = "beforeLoginTime", length = 50)
	public String getBeforeLoginTime() {
		return beforeLoginTime;
	}

	@Column(name = "currentLoginTime", length = 50)
	public String getCurrentLoginTime() {
		return currentLoginTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "number", length = 30)
	public String getNumber() {
		return this.number;
	}

	@Column(name = "password", length = 30)
	public String getPassword() {
		return this.password;
	}

	@Column(name = "photo", length = 30)
	public String getPhoto() {
		return this.photo;
	}

	@Column(name = "realname", length = 30)
	public String getRealname() {
		return this.realname;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return sex;
	}

	@Column(name = "telphone", length = 30)
	public String getTelphone() {
		return this.telphone;
	}

	@Column(name = "userLimit")
	public Integer getUserLimit() {
		return userLimit;
	}

	@Column(name = "username", length = 30)
	public String getUsername() {
		return this.username;
	}

	public void setBeforeLoginTime(String beforeLoginTime) {
		this.beforeLoginTime = beforeLoginTime;
	}

	public void setCurrentLoginTime(String currentLoginTime) {
		this.currentLoginTime = currentLoginTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public void setUserLimit(Integer userLimit) {
		this.userLimit = userLimit;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}