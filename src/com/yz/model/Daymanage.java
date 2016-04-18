package com.yz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Daymanage entity.
 * 
 * @author lq 日常监管
 */
@Entity
@Table(name = "daymanage", schema = "dbo", catalog = "jsju")
public class Daymanage implements java.io.Serializable {

	// Fields
	private Integer id;// id
	private Project project;// 所属项目
	private Integer isFiveSigned;// 五方责任书是否签订(0:否，1：是)
	private String signTime;// 签到日期
	private Integer isMassSafeNotify;// 质量安全是否告知(0:否，1：是)
	private String notifyTime;// 告知时间
	private Integer isEducationLaunch;// 三级教育开展情况(0:否，1：是)
	private String launchContent;// 开展方式（0：纸质1:图片2：VCR）
	private Integer isDangerArgument;// 超过一定规模危险性较大分部分项工程专家论证情况(0:否，1：是)
	private Integer isNameplateInstall;// 永久性铭牌安装落实情况(0:否，1：是)
	private String installTime;// 落实时间
	private Integer isMortarQualified;// 预拌砂浆用量是否达标(0:否，1：是)
	private Integer isCompleted;// 竣工验收情况(0:否，1：是)
	private String completedTime;// 竣工时间
	private List<Dangerargument> dangerarguments = new ArrayList<Dangerargument>();// 论证情况
	private List<Educationpic> educationpics = new ArrayList<Educationpic>();// 教育图片

	/** default constructor */
	public Daymanage() {
	}

	// Constructors
	public Daymanage(Integer id, Project project, Integer isFiveSigned,
			String signTime, Integer isMassSafeNotify, String notifyTime,
			Integer isEducationLaunch, String launchContent, String launchImg8,
			Integer isDangerArgument, Integer isNameplateInstall,
			String installTime, Integer isMortarQualified, Integer isCompleted,
			String completedTime, List<Dangerargument> dangerarguments,
			List<Educationpic> educationpics) {
		this.id = id;
		this.project = project;
		this.isFiveSigned = isFiveSigned;
		this.signTime = signTime;
		this.isMassSafeNotify = isMassSafeNotify;
		this.notifyTime = notifyTime;
		this.isEducationLaunch = isEducationLaunch;
		this.launchContent = launchContent;
		this.isDangerArgument = isDangerArgument;
		this.isNameplateInstall = isNameplateInstall;
		this.installTime = installTime;
		this.isMortarQualified = isMortarQualified;
		this.isCompleted = isCompleted;
		this.completedTime = completedTime;
		this.dangerarguments = dangerarguments;
		this.educationpics = educationpics;
	}

	/** full constructor */

	// Property accessors
	@Column(name = "completedTime", length = 50)
	public String getCompletedTime() {
		return this.completedTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "daymanage")
	public List<Dangerargument> getDangerarguments() {
		return dangerarguments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "daymanage")
	public List<Educationpic> getEducationpics() {
		return educationpics;
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

	@Column(name = "isCompleted")
	public Integer getIsCompleted() {
		return this.isCompleted;
	}

	@Column(name = "isDangerArgument")
	public Integer getIsDangerArgument() {
		return this.isDangerArgument;
	}

	@Column(name = "isEducationLaunch")
	public Integer getIsEducationLaunch() {
		return this.isEducationLaunch;
	}

	@Column(name = "isFiveSigned")
	public Integer getIsFiveSigned() {
		return this.isFiveSigned;
	}

	@Column(name = "isMassSafeNotify")
	public Integer getIsMassSafeNotify() {
		return this.isMassSafeNotify;
	}

	@Column(name = "isMortarQualified")
	public Integer getIsMortarQualified() {
		return this.isMortarQualified;
	}

	@Column(name = "isNameplateInstall")
	public Integer getIsNameplateInstall() {
		return this.isNameplateInstall;
	}

	@Column(name = "launchContent", length = 30)
	public String getLaunchContent() {
		return this.launchContent;
	}

	@Column(name = "notifyTime", length = 50)
	public String getNotifyTime() {
		return notifyTime;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "daymanage")
	public Project getProject() {
		return project;
	}

	@Column(name = "signTime", length = 50)
	public String getSignTime() {
		return signTime;
	}

	public void setCompletedTime(String completedTime) {
		this.completedTime = completedTime;
	}

	public void setDangerarguments(List<Dangerargument> dangerarguments) {
		this.dangerarguments = dangerarguments;
	}

	public void setEducationpics(List<Educationpic> educationpics) {
		this.educationpics = educationpics;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}

	public void setIsCompleted(Integer isCompleted) {
		this.isCompleted = isCompleted;
	}

	public void setIsDangerArgument(Integer isDangerArgument) {
		this.isDangerArgument = isDangerArgument;
	}

	public void setIsEducationLaunch(Integer isEducationLaunch) {
		this.isEducationLaunch = isEducationLaunch;
	}

	public void setIsFiveSigned(Integer isFiveSigned) {
		this.isFiveSigned = isFiveSigned;
	}

	public void setIsMassSafeNotify(Integer isMassSafeNotify) {
		this.isMassSafeNotify = isMassSafeNotify;
	}

	public void setIsMortarQualified(Integer isMortarQualified) {
		this.isMortarQualified = isMortarQualified;
	}

	public void setIsNameplateInstall(Integer isNameplateInstall) {
		this.isNameplateInstall = isNameplateInstall;
	}

	public void setLaunchContent(String launchContent) {
		this.launchContent = launchContent;
	}

	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:" + this.id + ",isFiveSigned:" + this.isFiveSigned
				+ ",signTime:" + this.signTime + ",isMassSafeNotify:"
				+ this.isMassSafeNotify;
	}

}