package com.yz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usero entity.
 * 
 * @author lq
 */
@Entity
@Table(name = "usero", schema = "dbo", catalog = "jsju")
public class Usero implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String telphone;
	private String realname;
	private String jobTitle;
	private Integer userLimit;
	private Integer areaIndex;

	// Constructors

	/** default constructor */
	public Usero() {
	}

	/** full constructor */
	public Usero(String username, String password, String telphone,
			String realname, String jobTitle, Integer userLimit,
			Integer areaIndex) {
		this.username = username;
		this.password = password;
		this.telphone = telphone;
		this.realname = realname;
		this.jobTitle = jobTitle;
		this.userLimit = userLimit;
		this.areaIndex = areaIndex;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "username", length = 30)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 30)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "telphone", length = 30)
	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "realname", length = 30)
	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "jobTitle", length = 30)
	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Column(name = "userLimit")
	public Integer getUserLimit() {
		return this.userLimit;
	}

	public void setUserLimit(Integer userLimit) {
		this.userLimit = userLimit;
	}

	@Column(name = "areaIndex")
	public Integer getAreaIndex() {
		return this.areaIndex;
	}

	public void setAreaIndex(Integer areaIndex) {
		this.areaIndex = areaIndex;
	}

}