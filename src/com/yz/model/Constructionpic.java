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
 * Constructionpic entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "constructionpic", schema = "dbo", catalog = "jsju")
public class Constructionpic implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer row;
	private Construction construction;
	private String picName;
	private String picDir;

	// Constructors

	/** default constructor */
	public Constructionpic() {
	}

	/** full constructor */
	public Constructionpic(int row, Construction construction, String picName,
			String picDir) {
		this.row = row;
		this.construction = construction;
		this.picName = picName;
		this.picDir = picDir;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conid")
	public Construction getConstruction() {
		return construction;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "picDir", length = 50)
	public String getPicDir() {
		return this.picDir;
	}

	@Column(name = "picName", length = 50)
	public String getPicName() {
		return this.picName;
	}

	@Column(name = "row")
	public Integer getRow() {
		return row;
	}

	public void setConstruction(Construction construction) {
		this.construction = construction;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPicDir(String picDir) {
		this.picDir = picDir;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

}