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
	private Integer rowid;// 行号
	private Construction construction;
	private String picDir1;
	private String picDir2;
	private String picDir3;

	// Constructors

	/** default constructor */
	public Constructionpic() {
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conid")
	public Construction getConstruction() {
		return construction;
	}

	// P roperty accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "picDir1", length = 100)
	public String getPicDir1() {
		return picDir1;
	}

	@Column(name = "picDir2", length = 100)
	public String getPicDir2() {
		return picDir2;
	}

	@Column(name = "rowid", length = 11)
	public Integer getRowid() {
		return rowid;
	}

	public void setRowid(Integer rowid) {
		this.rowid = rowid;
	}

	@Column(name = "picDir3", length = 100)
	public String getPicDir3() {
		return picDir3;
	}

	public void setConstruction(Construction construction) {
		this.construction = construction;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPicDir1(String picDir1) {
		this.picDir1 = picDir1;
	}

	public void setPicDir2(String picDir2) {
		this.picDir2 = picDir2;
	}

	public void setPicDir3(String picDir3) {
		this.picDir3 = picDir3;
	}

}