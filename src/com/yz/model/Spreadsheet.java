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
 * Spreadsheet entity.
 * 
 * @author 各类电子表格
 */
@Entity
@Table(name = "spreadsheet", schema = "dbo", catalog = "jsju")
public class Spreadsheet implements java.io.Serializable {

	// Fields
	private Integer id;//表格id
	private Project project;//	所属项目
	/*
	 * 表格类型（行为监督抽查：1工程质量行为资料监督抽查记录，2：施工单位安全生产行为监督检查表.doc ,5：监理单位安全生产行为监督检查表 6：建设单位安全生产行为监督检查表）
		日常巡查：3：工程质量监督抽查（巡查）记录 4：建设工程安全生产监督抽查记录表 
			    7：检查及整改表格
		抽测抽查：8：混凝土 9：钢筋 10：现浇楼 11：导线 12：市政
		项目评价：13：工程质量不良行为记录表 14：项目经理扣分情况记录表 15：行政处罚情况记录表 16：奖惩情况记录表 17：民工工资投诉处理记录表 18：安全生产事故情况记录表
	 */
	private Integer sheetType;
	private String sheetName;//表格名称
	private String updateTime;//更新时间(修改时间)
	private String sheetImg;//表格的上传图片
	private String checkTime;//检查日期（检查及整改表格特有）
	private String expireTime;//整改到期时间
	private Integer isClose;//是否闭合
	private String closeTime;//闭合时间
	private String sheetImg1;//表格的上传图片
	private String sheetImg2;//表格的上传图片

	// Constructors

	/** default constructor */
	public Spreadsheet() {
	}

	/** full constructor */
	public Spreadsheet(Project project, Integer sheetType, String sheetName,
			String updateTime, String sheetImg,String sheetImg1,String sheetImg2, String checkTime,
			String expireTime, Integer isClose, String closeTime) {
		this.project = project;
		this.sheetType = sheetType;
		this.sheetName = sheetName;
		this.updateTime = updateTime;
		this.sheetImg = sheetImg;
		this.sheetImg1 = sheetImg1;
		this.sheetImg2 = sheetImg2;
		this.checkTime = checkTime;
		this.expireTime = expireTime;
		this.isClose = isClose;
		this.closeTime = closeTime;
	}

	@Column(name = "checkTime", length = 50)
	public String getCheckTime() {
		return this.checkTime;
	}

	@Column(name = "closeTime", length = 50)
	public String getCloseTime() {
		return this.closeTime;
	}

	@Column(name = "expireTime", length = 50)
	public String getExpireTime() {
		return this.expireTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
	
	

	@Column(name = "isClose")
	public Integer getIsClose() {
		return this.isClose;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId")
	public Project getProject() {
		return this.project;
	}

	@Column(name = "sheetImg", length = 100)
	public String getSheetImg() {
		return this.sheetImg;
	}

	@Column(name = "sheetImg1", length = 100)
	public String getSheetImg1() {
		return sheetImg1;
	}

	@Column(name = "sheetImg2", length = 100)
	public String getSheetImg2() {
		return sheetImg2;
	}

	@Column(name = "sheetName", length = 50)
	public String getSheetName() {
		return this.sheetName;
	}

	@Column(name = "sheetType")
	public Integer getSheetType() {
		return this.sheetType;
	}

	@Column(name = "updateTime", length = 50)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsClose(Integer isClose) {
		this.isClose = isClose;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setSheetImg(String sheetImg) {
		this.sheetImg = sheetImg;
	}

	public void setSheetImg1(String sheetImg1) {
		this.sheetImg1 = sheetImg1;
	}

	public void setSheetImg2(String sheetImg2) {
		this.sheetImg2 = sheetImg2;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public void setSheetType(Integer sheetType) {
		this.sheetType = sheetType;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}