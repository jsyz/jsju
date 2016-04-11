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
 * Usero entity.
 * 
 * @author lq 用户
 */
@Entity
@Table(name = "usero", schema = "dbo", catalog = "jsju")
public class Usero implements java.io.Serializable {

	// Fields
	private Integer id;//用户id
	private Yxarea yxarea;//权限不为最强权限时,用户会存在所在管辖区域
	private String username;//用户姓名
	private String password;//用户密码
	private String telphone;//联系电话
	private String realname;//真实姓名
	private String number;//工号
	private Integer userLimit;//用户权限(0:最高权限 1:高级权限 2：一般权限)
	private String unitname;//所属部门
	private List<Project> projects = new ArrayList<Project>();

	// Constructors

	/** default constructor */
	public Usero() {
	}

	/** full constructor */
	public Usero(Yxarea yxarea,String username, String password, String telphone,
			String realname, String number, Integer userLimit, String unitname,
			List<Project> projects) {
		this.yxarea = yxarea;
		this.username = username;
		this.password = password;
		this.telphone = telphone;
		this.realname = realname;
		this.number = number;
		this.userLimit = userLimit;
		this.unitname = unitname;
		this.projects = projects;
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

	@Column(name = "number", length = 30)
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "userLimit")
	public Integer getUserLimit() {
		return this.userLimit;
	}

	public void setUserLimit(Integer userLimit) {
		this.userLimit = userLimit;
	}

	@Column(name = "unitname", length = 30)
	public String getUnitname() {
		return this.unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usero")
	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aid")
	public Yxarea getYxarea() {
		return yxarea;
	}

	
	public void setYxarea(Yxarea yxarea) {
		this.yxarea = yxarea;
	}
	
	

}