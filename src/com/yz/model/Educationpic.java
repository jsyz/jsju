package com.yz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Educationpic entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "educationpic", schema = "dbo", catalog = "jsju")
public class Educationpic implements java.io.Serializable {

	// Fields

	private Integer id;
	private Daymanage daymanage;
	private String picName;
	private String picDir;

	// Constructors

	/** default constructor */
	public Educationpic() {
	}

	/** full constructor */
	public Educationpic(Daymanage daymanage, String picName, String picDir) {
		this.daymanage = daymanage;
		this.picName = picName;
		this.picDir = picDir;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dayid")
	public Daymanage getDaymanage() {
		return this.daymanage;
	}

	public void setDaymanage(Daymanage daymanage) {
		this.daymanage = daymanage;
	}

	@Column(name = "picName", length = 50)
	public String getPicName() {
		return this.picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	@Column(name = "picDir", length = 50)
	public String getPicDir() {
		return this.picDir;
	}

	public void setPicDir(String picDir) {
		this.picDir = picDir;
	}

}