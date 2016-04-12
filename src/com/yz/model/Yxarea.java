package com.yz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Yxarea entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "yxarea", schema = "dbo", catalog = "jsju")
public class Yxarea implements java.io.Serializable {

	// Fields

	private Integer id;
	private String areaname;
	private Integer areaIndex;
	private List<Project> projects = new ArrayList<Project>(0);

	// Constructors

	/** default constructor */
	public Yxarea() {
	}

	/** full constructor */
	public Yxarea(String areaname, Integer areaIndex, List<Project> projects) {
		this.areaname = areaname;
		this.areaIndex = areaIndex;
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

	@Column(name = "areaname", length = 30)
	public String getAreaname() {
		return this.areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	@Column(name = "areaIndex")
	public Integer getAreaIndex() {
		return this.areaIndex;
	}

	public void setAreaIndex(Integer areaIndex) {
		this.areaIndex = areaIndex;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "yxarea")
	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}