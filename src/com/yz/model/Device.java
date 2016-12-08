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
 * Device entity.
 * 
 * @author lq 设备
 */
@Entity
@Table(name = "device", schema = "dbo", catalog = "jsju")
public class Device implements java.io.Serializable {

	// Fields
	private Integer id;
	private Project project;// 所属项目
	private String name;// 设备名称
	private String propertyCardNumber;// 产权证号
	private String installTime;// 安装告知时间
	private String checkTime;// 检测时间
	private Integer isDealUsecard;// 是否办理使用登记证
	private String usecardExpireTime;// 登记证到期时间
	private String removeTime;// 拆卸告知日期
	private Integer devType;// 设备类型 (0:其他设备 1：摄像头设备)
	private String number;// 设备编号

	// Constructors

	/** default constructor */
	public Device() {
	}

	/** full constructor */
	public Device(Project project, String name, String propertyCardNumber,
			String installTime, String checkTime, Integer isDealUsecard,
			String usecardExpireTime, String removeTime, Integer devType,
			String number) {
		this.project = project;
		this.name = name;
		this.propertyCardNumber = propertyCardNumber;
		this.installTime = installTime;
		this.checkTime = checkTime;
		this.isDealUsecard = isDealUsecard;
		this.usecardExpireTime = usecardExpireTime;
		this.removeTime = removeTime;
		this.devType = devType;
		this.number = number;
	}

	// Property accessors
	@Column(name = "checkTime", length = 50)
	public String getCheckTime() {
		return this.checkTime;
	}

	@Column(name = "devType")
	public Integer getDevType() {
		return devType;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "installTime", length = 50)
	public String getInstallTime() {
		return this.installTime;
	}

	@Column(name = "isDealUsecard")
	public Integer getIsDealUsecard() {
		return this.isDealUsecard;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	@Column(name = "number", length = 50)
	public String getNumber() {
		return number;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId")
	public Project getProject() {
		return this.project;
	}

	@Column(name = "propertyCardNumber", length = 100)
	public String getPropertyCardNumber() {
		return this.propertyCardNumber;
	}

	@Column(name = "removeTime", length = 50)
	public String getRemoveTime() {
		return this.removeTime;
	}

	@Column(name = "usecardExpireTime", length = 50)
	public String getUsecardExpireTime() {
		return this.usecardExpireTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public void setDevType(Integer devType) {
		this.devType = devType;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}

	public void setIsDealUsecard(Integer isDealUsecard) {
		this.isDealUsecard = isDealUsecard;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setPropertyCardNumber(String propertyCardNumber) {
		this.propertyCardNumber = propertyCardNumber;
	}

	public void setRemoveTime(String removeTime) {
		this.removeTime = removeTime;
	}

	public void setUsecardExpireTime(String usecardExpireTime) {
		this.usecardExpireTime = usecardExpireTime;
	}

}