package com.yz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Construction entity.
 * 
 * @author sl 混凝土和砂浆合同备案
 */
@Entity
@Table(name = "concreterecord", schema = "dbo", catalog = "jsju")
public class Concreterecord implements java.io.Serializable {
	private Integer id;
	private Integer concreterecordType; // 0:混凝土 1：砂浆
	private String name;
	private String contractRecordForm; // 项目合同备案表
	private String liabilityUndertaking; // 责任承诺书
	private String letterOfAttorney; // 法定代表人授权书
	private String representativeIdCard; // 法人身份证复印件
	private String projectLeaderIdCatd; // 项目负责人身份证复印件
	private String contract; // 合同复印件

	@Column(name = "concreterecordType")
	public Integer getConcreterecordType() {
		return concreterecordType;
	}

	@Column(name = "contract", length = 300)
	public String getContract() {
		return contract;
	}

	@Column(name = "contractRecordForm", length = 300)
	public String getContractRecordForm() {
		return contractRecordForm;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(name = "letterOfAttorney", length = 300)
	public String getLetterOfAttorney() {
		return letterOfAttorney;
	}

	@Column(name = "liabilityUndertaking", length = 300)
	public String getLiabilityUndertaking() {
		return liabilityUndertaking;
	}

	@Column(name = "projectLeaderIdCatd", length = 300)
	public String getProjectLeaderIdCatd() {
		return projectLeaderIdCatd;
	}

	@Column(name = "representativeIdCard", length = 300)
	public String getRepresentativeIdCard() {
		return representativeIdCard;
	}

	public void setConcreterecordType(Integer concreterecordType) {
		this.concreterecordType = concreterecordType;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public void setContractRecordForm(String contractRecordForm) {
		this.contractRecordForm = contractRecordForm;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLetterOfAttorney(String letterOfAttorney) {
		this.letterOfAttorney = letterOfAttorney;
	}

	public void setLiabilityUndertaking(String liabilityUndertaking) {
		this.liabilityUndertaking = liabilityUndertaking;
	}

	public void setProjectLeaderIdCatd(String projectLeaderIdCatd) {
		this.projectLeaderIdCatd = projectLeaderIdCatd;
	}

	public void setRepresentativeIdCard(String representativeIdCard) {
		this.representativeIdCard = representativeIdCard;
	}
	@Column(name = "name", length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
