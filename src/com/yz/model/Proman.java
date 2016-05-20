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
 * Proman entity.
 * 
 * @author 工程人员 五大员 项目经理
 */
@Entity
@Table(name = "proman", schema = "dbo", catalog = "jsju")
public class Proman implements java.io.Serializable {

	// Fields
	private Integer id;//人员id
	private Project project;//所属工程
	private String name;//人员姓名
	private String jobTitle;//职务职称
	private String telphone;//联系电话
	private String certificate;//证书信息

	// Constructors

	/** default constructor */
	public Proman() {
	}

	/** full constructor */
	public Proman(Project project, String name, String jobTitle,
			String telphone, String certificate) {
		this.project = project;
		this.name = name;
		this.jobTitle = jobTitle;
		this.telphone = telphone;
		this.certificate = certificate;
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

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "jobTitle", length = 30)
	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Column(name = "telphone", length = 30)
	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "certificate", length = 100)
	public String getCertificate() {
		return this.certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

}