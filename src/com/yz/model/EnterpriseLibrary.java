package com.yz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Device entity.
 * 
 * @author sl 企业库
 */
@Entity
@Table(name = "enterpriseLibrary", schema = "dbo", catalog = "jsju")
public class EnterpriseLibrary {
	private Integer id;
	private String name;//企业名称
	private String qualification;//企业资质
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "qualification", length = 300)
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
}
