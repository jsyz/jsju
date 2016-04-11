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
 * @author lq 宜兴区域
 */
@Entity
@Table(name = "yxarea", schema = "dbo", catalog = "jsju")
public class Yxarea implements java.io.Serializable {

	// Fields
	private Integer id;//片区id
	private String areaname;//片区名称
	private Integer index;//排列序号
	private List<Project> projects = new ArrayList<Project>(0);
	private List<Usero> useros = new ArrayList<Usero>(0);
	// Constructors

	/** default constructor */
	public Yxarea() {
	}

	/** full constructor */
	public Yxarea(String areaname, Integer index, List<Project> projects) {
		this.areaname = areaname;
		this.index = index;
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

	@Column(name = "index")
	public Integer getIndex() {
		return this.index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "yxarea")
	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "yxarea")
	public List<Usero> getUseros() {
		return useros;
	}

	public void setUseros(List<Usero> useros) {
		this.useros = useros;
	}
	
	

}