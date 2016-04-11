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
	private Integer id;//id
	private Project project;//所属项目
	private Integer isFiveSigned;//五方责任书是否签订(0:否，1：是)
	private Integer isMassSafeNotify;//质量安全是否告知(0:否，1：是)
	private Integer isEducationLaunch;//三级教育开展情况(0:否，1：是)
	private String launchContent;//开展方式（0：纸质1:图片2：VCR）
	private String launchImg8;//开展图片8
	private String launchImg7;//开展图片7
	private String launchImg6;//开展图片6
	private String launchImg5;//开展图片5
	private String launchImg4;//开展图片4
	private String launchImg3;//开展图片3
	private String launchImg2;//开展图片2
	private String launchImg1;//	开展图片1
	private Integer isDangerArgument;//超过一定规模危险性较大分部分项工程专家论证情况(0:否，1：是)
	private Integer isNameplateInstall;//永久性铭牌安装落实情况(0:否，1：是)
	private String installTime;//落实时间
	private Integer isMortarQualified;//预拌砂浆用量是否达标(0:否，1：是)
	private Integer isCompleted;//竣工验收情况(0:否，1：是)
	private String completedTime;//竣工时间
	private List<Dangerargument> dangerarguments = new ArrayList<Dangerargument>();//论证情况

	// Constructors

	/** default constructor */
	public Daymanage() {
	}

	/** full constructor */
	public Daymanage(Integer isFiveSigned, Integer isMassSafeNotify,
			Integer isEducationLaunch, String launchContent, String launchImg8,
			String launchImg7, String launchImg6, String launchImg5,
			String launchImg4, String launchImg3, String launchImg2,
			String launchImg1, Integer isDangerArgument,
			Integer isNameplateInstall, String installTime,
			Integer isMortarQualified, Integer isCompleted,
			String completedTime, Project projects, List<Dangerargument> dangerarguments) {
		this.isFiveSigned = isFiveSigned;
		this.isMassSafeNotify = isMassSafeNotify;
		this.isEducationLaunch = isEducationLaunch;
		this.launchContent = launchContent;
		this.launchImg8 = launchImg8;
		this.launchImg7 = launchImg7;
		this.launchImg6 = launchImg6;
		this.launchImg5 = launchImg5;
		this.launchImg4 = launchImg4;
		this.launchImg3 = launchImg3;
		this.launchImg2 = launchImg2;
		this.launchImg1 = launchImg1;
		this.isDangerArgument = isDangerArgument;
		this.isNameplateInstall = isNameplateInstall;
		this.installTime = installTime;
		this.isMortarQualified = isMortarQualified;
		this.isCompleted = isCompleted;
		this.completedTime = completedTime;
		this.project = project;
		this.dangerarguments = dangerarguments;
	}

	// Property accessors
	@Column(name = "completedTime", length = 50)
	public String getCompletedTime() {
		return this.completedTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "daymanage")
	public List<Dangerargument> getDangerarguments() {
		return dangerarguments;
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

	@Column(name = "launchImg1", length = 50)
	public String getLaunchImg1() {
		return this.launchImg1;
	}

	@Column(name = "launchImg2", length = 50)
	public String getLaunchImg2() {
		return this.launchImg2;
	}

	@Column(name = "launchImg3", length = 50)
	public String getLaunchImg3() {
		return this.launchImg3;
	}

	@Column(name = "launchImg4", length = 50)
	public String getLaunchImg4() {
		return this.launchImg4;
	}

	@Column(name = "launchImg5", length = 50)
	public String getLaunchImg5() {
		return this.launchImg5;
	}

	@Column(name = "launchImg6", length = 50)
	public String getLaunchImg6() {
		return this.launchImg6;
	}

	@Column(name = "launchImg7", length = 50)
	public String getLaunchImg7() {
		return this.launchImg7;
	}

	@Column(name = "launchImg8", length = 50)
	public String getLaunchImg8() {
		return this.launchImg8;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "daymanage")
	public Project getProject() {
		return project;
	}

	public void setCompletedTime(String completedTime) {
		this.completedTime = completedTime;
	}

	public void setDangerarguments(List<Dangerargument> dangerarguments) {
		this.dangerarguments = dangerarguments;
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

	public void setLaunchImg1(String launchImg1) {
		this.launchImg1 = launchImg1;
	}

	public void setLaunchImg2(String launchImg2) {
		this.launchImg2 = launchImg2;
	}

	public void setLaunchImg3(String launchImg3) {
		this.launchImg3 = launchImg3;
	}

	public void setLaunchImg4(String launchImg4) {
		this.launchImg4 = launchImg4;
	}

	public void setLaunchImg5(String launchImg5) {
		this.launchImg5 = launchImg5;
	}

	public void setLaunchImg6(String launchImg6) {
		this.launchImg6 = launchImg6;
	}

	public void setLaunchImg7(String launchImg7) {
		this.launchImg7 = launchImg7;
	}

	public void setLaunchImg8(String launchImg8) {
		this.launchImg8 = launchImg8;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	

}