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
 * Dangerargument entity.
 * 
 * @author lq  论证情况
 */
@Entity
@Table(name = "dangerargument", schema = "dbo", catalog = "jsju")
public class Dangerargument implements java.io.Serializable {

	// Fields
	private Integer id;//id
	private Daymanage daymanage;//所属日常监管
	private String argTypeName;//认证类型名称
	private String argTime;//认证时间
	private String implTime;//实施时间
	private String finishTime;//完成时间

	// Constructors

	/** default constructor */
	public Dangerargument() {
	}

	/** full constructor */
	public Dangerargument(Daymanage daymanage, String argTypeName,
			String argTime, String implTime, String finishTime) {
		this.daymanage = daymanage;
		this.argTypeName = argTypeName;
		this.argTime = argTime;
		this.implTime = implTime;
		this.finishTime = finishTime;
	}

	// Property accessors

	@Column(name = "argTime", length = 50)
	public String getArgTime() {
		return this.argTime;
	}

	@Column(name = "argTypeName", length = 30)
	public String getArgTypeName() {
		return this.argTypeName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dayid")
	public Daymanage getDaymanage() {
		return this.daymanage;
	}

	@Column(name = "finishTime", length = 50)
	public String getFinishTime() {
		return this.finishTime;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "implTime", length = 50)
	public String getImplTime() {
		return this.implTime;
	}

	public void setArgTime(String argTime) {
		this.argTime = argTime;
	}

	public void setArgTypeName(String argTypeName) {
		this.argTypeName = argTypeName;
	}

	public void setDaymanage(Daymanage daymanage) {
		this.daymanage = daymanage;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImplTime(String implTime) {
		this.implTime = implTime;
	}

}