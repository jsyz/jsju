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
 * Subunit entity.
 * 
 * @author lq 分包单位
 */
@Entity
@Table(name = "subunit", schema = "dbo", catalog = "jsju")
public class Subunit implements java.io.Serializable {

	// Fields
	private Integer id;//分包单位id
	private Project project;//所属项目
	private String unitName;//单位名称
	private String unitLeader;//单位负责人
	private String leaderPhone;//负责人联系电话
	private Integer isInCity;//是否办理入市登记核验

	// Constructors

	/** default constructor */
	public Subunit() {
	}

	/** full constructor */
	public Subunit(Project project, String unitName, String unitLeader,
			String leaderPhone, Integer isInCity) {
		this.project = project;
		this.unitName = unitName;
		this.unitLeader = unitLeader;
		this.leaderPhone = leaderPhone;
		this.isInCity = isInCity;
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
	@JoinColumn(name = "projectId")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "unitName", length = 50)
	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Column(name = "unitLeader", length = 30)
	public String getUnitLeader() {
		return this.unitLeader;
	}

	public void setUnitLeader(String unitLeader) {
		this.unitLeader = unitLeader;
	}

	@Column(name = "leaderPhone", length = 30)
	public String getLeaderPhone() {
		return this.leaderPhone;
	}

	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}

	@Column(name = "isInCity")
	public Integer getIsInCity() {
		return this.isInCity;
	}

	public void setIsInCity(Integer isInCity) {
		this.isInCity = isInCity;
	}

}