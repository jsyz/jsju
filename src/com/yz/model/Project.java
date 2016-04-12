package com.yz.model;

import java.util.ArrayList;
import java.util.HashSet;
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
 * Project entity.
 * 
 * @author lq 工程项目
 */
@Entity
@Table(name = "project", schema = "dbo", catalog = "jsju")
public class Project implements java.io.Serializable {

	// Fields
	private Integer id;// 项目id
	private Yxarea yxarea;// 所属乡镇分类(0:未选择乡镇,1：宜城，2:环科园,3:开发区，4：丁蜀，5：和桥,6:官林,7:张渚,8:周铁,9:徐舍)
	private Daymanage daymanage;// 日常监管
	private Construction construction;// 文明施工
	private Integer projectType;// 项目分类(0:房地产开发,1：安置房，2:政府投资项目,3:重点项目，4：一般项目)
	private Integer engineeringType;// 工程分类(0:土建,1：装饰，2:市政,3:绿化，4：照明亮化)
	private Integer buildingType;// 建筑分类(0:住宅,1：公共建筑，2:工业建筑)
	private Integer graphicProgress;// 形象进度(0:基础/20%,1：主体/40%，2:装饰/60%，3：完工待验/80%，4：竣工/100%)
	private String buildUnit;// 建设单位
	private String buildUnitPrincipal;// 建设单位负责人
	private String buildUnittelphone;// 建设单位负责人联系电话
	private String name;// 项目名称
	private float buildingArea;// 建筑面积
	private float buildingCost;// 建筑造价
	private String structureLevel;// 结构层次
	private Integer buildingNumber;// 单位工程
	private String startDate;// 开工日期
	private String planendDate;// 计划竣工日期
	private String constructionUnit;// 施工单位?是否就是施工总包单位？
	private String supervisionUnit;// 监理单位
	private String projectManager;// 项目经理
	private String engineeringDirector;// 工程总监
	private String clearPrincipal;// 清欠负责人
	private String clearPrincipalTelphone;// 清欠负责人联系电话?
	private Integer isNewProjectMonth;// 是否本月新开工?为什么显示跨年度？
	private String constructionPermitDate;// 施工许可证发证日期
	private String premarks;// 备注（至此需导入列表内容结束）
	private String supervisor;// 监督员
	private String engineeringPlace;// 工程地点
	private String constructionPermitNumber;// 施工许可证证号
	private String sourcesOfInvestment;// 投资来源
	private String excellenceGoals;// 创优目标
	private String reconnaissanceUnit;// 勘察单位
	private String reconnaissanceUnitPrincipal;// 勘察单位负责人
	private String reconnaissanceUnitPrincipalTelphone;// 勘察单位负责人联系电话
	private String designUnit;// 设计单位
	private String designUnitPrincipal;// 设计单位负责人
	private String designUnitPrincipalTelphone;// 设计单位负责人联系电话
	private String detectionUnit;// 检测机构(这些机构是不是都需要负责人和电话??)
	private String detectionUnitPrincipal;// 检测机构负责人
	private String detectionUnitPrincipalTelphone;// 检测机构负责人联系电话
	private String concreteUnit;// 商品砼生产单位
	private String concreteUnitPrincipal;// 商品砼生产单位负责人
	private String concreteUnitPrincipalTelphone;// 商品砼生产单位负责人联系电话
	private String mortarUnit;// 预拌砂浆生产单位
	private String mortarUnitPrincipal;// 预拌砂浆生产单位负责人
	private String mortarUnitPrincipalTelphone;// 预拌砂浆生产单位负责人联系电话
	private String constructionDrawingUnit;// 施工图审查机构
	private String constructionDrawingUnitPrincipal;// 施工图审查机构负责人
	private String constructionDrawingUnitPrincipalTelphone;// 施工图审查机构联系电话
	private List<Spreadsheet> spreadsheets = new ArrayList<Spreadsheet>();
	private List<Proman> promans = new ArrayList<Proman>();
	private List<Subunit> subunits = new ArrayList<Subunit>();
	private List<Device> devices = new ArrayList<Device>();

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** full constructor */
	public Project(Yxarea yxarea, Daymanage daymanage,
			Construction construction, Integer projectType,
			Integer engineeringType, Integer buildingType,
			Integer graphicProgress, String buildUnit,
			String buildUnitPrincipal, String buildUnittelphone, String name,
			float buildingArea, float buildingCost, String structureLevel,
			Integer buildingNumber, String startDate, String planendDate,
			String constructionUnit, String supervisionUnit,
			String projectManager, String engineeringDirector,
			String clearPrincipal, String clearPrincipalTelphone,
			Integer isNewProjectMonth, String constructionPermitDate,
			String premarks, String supervisor, String engineeringPlace,
			String constructionPermitNumber, String sourcesOfInvestment,
			String excellenceGoals, String reconnaissanceUnit,
			String reconnaissanceUnitPrincipal,
			String reconnaissanceUnitPrincipalTelphone, String designUnit,
			String designUnitPrincipal, String designUnitPrincipalTelphone,
			String detectionUnit, String detectionUnitPrincipal,
			String detectionUnitPrincipalTelphone, String concreteUnit,
			String concreteUnitPrincipal, String concreteUnitPrincipalTelphone,
			String mortarUnit, String mortarUnitPrincipal,
			String mortarUnitPrincipalTelphone, String constructionDrawingUnit,
			String constructionDrawingUnitPrincipal,
			String constructionDrawingUnitPrincipalTelphone,
			List<Spreadsheet> spreadsheets, List<Proman> promans,
			List<Subunit> subunits, List<Device> devices) {
		this.yxarea = yxarea;
		this.daymanage = daymanage;
		this.construction = construction;
		this.projectType = projectType;
		this.engineeringType = engineeringType;
		this.buildingType = buildingType;
		this.graphicProgress = graphicProgress;
		this.buildUnit = buildUnit;
		this.buildUnitPrincipal = buildUnitPrincipal;
		this.buildUnittelphone = buildUnittelphone;
		this.name = name;
		this.buildingArea = buildingArea;
		this.buildingCost = buildingCost;
		this.structureLevel = structureLevel;
		this.buildingNumber = buildingNumber;
		this.startDate = startDate;
		this.planendDate = planendDate;
		this.constructionUnit = constructionUnit;
		this.supervisionUnit = supervisionUnit;
		this.projectManager = projectManager;
		this.engineeringDirector = engineeringDirector;
		this.clearPrincipal = clearPrincipal;
		this.clearPrincipalTelphone = clearPrincipalTelphone;
		this.isNewProjectMonth = isNewProjectMonth;
		this.constructionPermitDate = constructionPermitDate;
		this.premarks = premarks;
		this.supervisor = supervisor;
		this.engineeringPlace = engineeringPlace;
		this.constructionPermitNumber = constructionPermitNumber;
		this.sourcesOfInvestment = sourcesOfInvestment;
		this.excellenceGoals = excellenceGoals;
		this.reconnaissanceUnit = reconnaissanceUnit;
		this.reconnaissanceUnitPrincipal = reconnaissanceUnitPrincipal;
		this.reconnaissanceUnitPrincipalTelphone = reconnaissanceUnitPrincipalTelphone;
		this.designUnit = designUnit;
		this.designUnitPrincipal = designUnitPrincipal;
		this.designUnitPrincipalTelphone = designUnitPrincipalTelphone;
		this.detectionUnit = detectionUnit;
		this.detectionUnitPrincipal = detectionUnitPrincipal;
		this.detectionUnitPrincipalTelphone = detectionUnitPrincipalTelphone;
		this.concreteUnit = concreteUnit;
		this.concreteUnitPrincipal = concreteUnitPrincipal;
		this.concreteUnitPrincipalTelphone = concreteUnitPrincipalTelphone;
		this.mortarUnit = mortarUnit;
		this.mortarUnitPrincipal = mortarUnitPrincipal;
		this.mortarUnitPrincipalTelphone = mortarUnitPrincipalTelphone;
		this.constructionDrawingUnit = constructionDrawingUnit;
		this.constructionDrawingUnitPrincipal = constructionDrawingUnitPrincipal;
		this.constructionDrawingUnitPrincipalTelphone = constructionDrawingUnitPrincipalTelphone;
		this.spreadsheets = spreadsheets;
		this.promans = promans;
		this.subunits = subunits;
		this.devices = devices;
	}

	/** minimal constructor */
	public Project(Yxarea yxarea) {
		this.yxarea = yxarea;
	}

	// Property accessors
	@Column(name = "buildingArea")
	public float getBuildingArea() {
		return this.buildingArea;
	}

	@Column(name = "buildingCost")
	public float getBuildingCost() {
		return this.buildingCost;
	}

	@Column(name = "buildingNumber")
	public Integer getBuildingNumber() {
		return this.buildingNumber;
	}

	@Column(name = "buildingType")
	public Integer getBuildingType() {
		return this.buildingType;
	}

	@Column(name = "buildUnit", length = 50)
	public String getBuildUnit() {
		return this.buildUnit;
	}

	@Column(name = "buildUnitPrincipal", length = 30)
	public String getBuildUnitPrincipal() {
		return this.buildUnitPrincipal;
	}

	@Column(name = "buildUnittelphone", length = 30)
	public String getBuildUnittelphone() {
		return this.buildUnittelphone;
	}

	@Column(name = "clearPrincipal", length = 30)
	public String getClearPrincipal() {
		return this.clearPrincipal;
	}

	@Column(name = "clearPrincipalTelphone", length = 30)
	public String getClearPrincipalTelphone() {
		return this.clearPrincipalTelphone;
	}

	@Column(name = "concreteUnit", length = 50)
	public String getConcreteUnit() {
		return this.concreteUnit;
	}

	@Column(name = "concreteUnitPrincipal", length = 30)
	public String getConcreteUnitPrincipal() {
		return this.concreteUnitPrincipal;
	}

	@Column(name = "concreteUnitPrincipalTelphone", length = 30)
	public String getConcreteUnitPrincipalTelphone() {
		return this.concreteUnitPrincipalTelphone;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "conid")
	public Construction getConstruction() {
		return this.construction;
	}

	@Column(name = "constructionDrawingUnit", length = 50)
	public String getConstructionDrawingUnit() {
		return this.constructionDrawingUnit;
	}

	@Column(name = "constructionDrawingUnitPrincipal", length = 30)
	public String getConstructionDrawingUnitPrincipal() {
		return this.constructionDrawingUnitPrincipal;
	}

	@Column(name = "constructionDrawingUnitPrincipalTelphone", length = 30)
	public String getConstructionDrawingUnitPrincipalTelphone() {
		return this.constructionDrawingUnitPrincipalTelphone;
	}

	@Column(name = "constructionPermitDate", length = 50)
	public String getConstructionPermitDate() {
		return this.constructionPermitDate;
	}

	@Column(name = "constructionPermitNumber", length = 100)
	public String getConstructionPermitNumber() {
		return this.constructionPermitNumber;
	}

	@Column(name = "constructionUnit", length = 50)
	public String getConstructionUnit() {
		return this.constructionUnit;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "dayid")
	public Daymanage getDaymanage() {
		return this.daymanage;
	}

	@Column(name = "designUnit", length = 50)
	public String getDesignUnit() {
		return this.designUnit;
	}

	@Column(name = "designUnitPrincipal", length = 30)
	public String getDesignUnitPrincipal() {
		return this.designUnitPrincipal;
	}

	@Column(name = "designUnitPrincipalTelphone", length = 30)
	public String getDesignUnitPrincipalTelphone() {
		return this.designUnitPrincipalTelphone;
	}

	@Column(name = "detectionUnit", length = 50)
	public String getDetectionUnit() {
		return this.detectionUnit;
	}

	@Column(name = "detectionUnitPrincipal", length = 30)
	public String getDetectionUnitPrincipal() {
		return this.detectionUnitPrincipal;
	}

	@Column(name = "detectionUnitPrincipalTelphone", length = 30)
	public String getDetectionUnitPrincipalTelphone() {
		return this.detectionUnitPrincipalTelphone;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public List<Device> getDevices() {
		return this.devices;
	}

	@Column(name = "engineeringDirector", length = 50)
	public String getEngineeringDirector() {
		return this.engineeringDirector;
	}

	@Column(name = "engineeringPlace", length = 50)
	public String getEngineeringPlace() {
		return this.engineeringPlace;
	}

	@Column(name = "engineeringType")
	public Integer getEngineeringType() {
		return this.engineeringType;
	}

	@Column(name = "excellenceGoals", length = 100)
	public String getExcellenceGoals() {
		return this.excellenceGoals;
	}

	@Column(name = "graphicProgress")
	public Integer getGraphicProgress() {
		return this.graphicProgress;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "isNewProjectMonth")
	public Integer getIsNewProjectMonth() {
		return this.isNewProjectMonth;
	}

	@Column(name = "mortarUnit", length = 50)
	public String getMortarUnit() {
		return this.mortarUnit;
	}

	@Column(name = "mortarUnitPrincipal", length = 30)
	public String getMortarUnitPrincipal() {
		return this.mortarUnitPrincipal;
	}

	@Column(name = "mortarUnitPrincipalTelphone", length = 30)
	public String getMortarUnitPrincipalTelphone() {
		return this.mortarUnitPrincipalTelphone;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	@Column(name = "planendDate", length = 50)
	public String getPlanendDate() {
		return this.planendDate;
	}

	@Column(name = "premarks")
	public String getPremarks() {
		return this.premarks;
	}

	@Column(name = "projectManager", length = 50)
	public String getProjectManager() {
		return this.projectManager;
	}

	@Column(name = "projectType")
	public Integer getProjectType() {
		return this.projectType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public List<Proman> getPromans() {
		return this.promans;
	}

	@Column(name = "reconnaissanceUnit", length = 50)
	public String getReconnaissanceUnit() {
		return this.reconnaissanceUnit;
	}

	@Column(name = "reconnaissanceUnitPrincipal", length = 30)
	public String getReconnaissanceUnitPrincipal() {
		return this.reconnaissanceUnitPrincipal;
	}

	@Column(name = "reconnaissanceUnitPrincipalTelphone", length = 30)
	public String getReconnaissanceUnitPrincipalTelphone() {
		return this.reconnaissanceUnitPrincipalTelphone;
	}

	@Column(name = "sourcesOfInvestment", length = 100)
	public String getSourcesOfInvestment() {
		return this.sourcesOfInvestment;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public List<Spreadsheet> getSpreadsheets() {
		return this.spreadsheets;
	}

	@Column(name = "startDate", length = 50)
	public String getStartDate() {
		return this.startDate;
	}

	@Column(name = "structureLevel", length = 30)
	public String getStructureLevel() {
		return this.structureLevel;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public List<Subunit> getSubunits() {
		return this.subunits;
	}

	@Column(name = "supervisionUnit", length = 50)
	public String getSupervisionUnit() {
		return this.supervisionUnit;
	}

	@Column(name = "supervisor", length = 50)
	public String getSupervisor() {
		return this.supervisor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aid", nullable = false)
	public Yxarea getYxarea() {
		return this.yxarea;
	}

	public void setBuildingArea(float buildingArea) {
		this.buildingArea = buildingArea;
	}

	public void setBuildingCost(float buildingCost) {
		this.buildingCost = buildingCost;
	}

	public void setBuildingNumber(Integer buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public void setBuildingType(Integer buildingType) {
		this.buildingType = buildingType;
	}

	public void setBuildUnit(String buildUnit) {
		this.buildUnit = buildUnit;
	}

	public void setBuildUnitPrincipal(String buildUnitPrincipal) {
		this.buildUnitPrincipal = buildUnitPrincipal;
	}

	public void setBuildUnittelphone(String buildUnittelphone) {
		this.buildUnittelphone = buildUnittelphone;
	}

	public void setClearPrincipal(String clearPrincipal) {
		this.clearPrincipal = clearPrincipal;
	}

	public void setClearPrincipalTelphone(String clearPrincipalTelphone) {
		this.clearPrincipalTelphone = clearPrincipalTelphone;
	}

	public void setConcreteUnit(String concreteUnit) {
		this.concreteUnit = concreteUnit;
	}

	public void setConcreteUnitPrincipal(String concreteUnitPrincipal) {
		this.concreteUnitPrincipal = concreteUnitPrincipal;
	}

	public void setConcreteUnitPrincipalTelphone(
			String concreteUnitPrincipalTelphone) {
		this.concreteUnitPrincipalTelphone = concreteUnitPrincipalTelphone;
	}

	public void setConstruction(Construction construction) {
		this.construction = construction;
	}

	public void setConstructionDrawingUnit(String constructionDrawingUnit) {
		this.constructionDrawingUnit = constructionDrawingUnit;
	}

	public void setConstructionDrawingUnitPrincipal(
			String constructionDrawingUnitPrincipal) {
		this.constructionDrawingUnitPrincipal = constructionDrawingUnitPrincipal;
	}

	public void setConstructionDrawingUnitPrincipalTelphone(
			String constructionDrawingUnitPrincipalTelphone) {
		this.constructionDrawingUnitPrincipalTelphone = constructionDrawingUnitPrincipalTelphone;
	}

	public void setConstructionPermitDate(String constructionPermitDate) {
		this.constructionPermitDate = constructionPermitDate;
	}

	public void setConstructionPermitNumber(String constructionPermitNumber) {
		this.constructionPermitNumber = constructionPermitNumber;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	public void setDaymanage(Daymanage daymanage) {
		this.daymanage = daymanage;
	}

	public void setDesignUnit(String designUnit) {
		this.designUnit = designUnit;
	}

	public void setDesignUnitPrincipal(String designUnitPrincipal) {
		this.designUnitPrincipal = designUnitPrincipal;
	}

	public void setDesignUnitPrincipalTelphone(
			String designUnitPrincipalTelphone) {
		this.designUnitPrincipalTelphone = designUnitPrincipalTelphone;
	}

	public void setDetectionUnit(String detectionUnit) {
		this.detectionUnit = detectionUnit;
	}

	public void setDetectionUnitPrincipal(String detectionUnitPrincipal) {
		this.detectionUnitPrincipal = detectionUnitPrincipal;
	}

	public void setDetectionUnitPrincipalTelphone(
			String detectionUnitPrincipalTelphone) {
		this.detectionUnitPrincipalTelphone = detectionUnitPrincipalTelphone;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public void setEngineeringDirector(String engineeringDirector) {
		this.engineeringDirector = engineeringDirector;
	}

	public void setEngineeringPlace(String engineeringPlace) {
		this.engineeringPlace = engineeringPlace;
	}

	public void setEngineeringType(Integer engineeringType) {
		this.engineeringType = engineeringType;
	}

	public void setExcellenceGoals(String excellenceGoals) {
		this.excellenceGoals = excellenceGoals;
	}

	public void setGraphicProgress(Integer graphicProgress) {
		this.graphicProgress = graphicProgress;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsNewProjectMonth(Integer isNewProjectMonth) {
		this.isNewProjectMonth = isNewProjectMonth;
	}

	public void setMortarUnit(String mortarUnit) {
		this.mortarUnit = mortarUnit;
	}

	public void setMortarUnitPrincipal(String mortarUnitPrincipal) {
		this.mortarUnitPrincipal = mortarUnitPrincipal;
	}

	public void setMortarUnitPrincipalTelphone(
			String mortarUnitPrincipalTelphone) {
		this.mortarUnitPrincipalTelphone = mortarUnitPrincipalTelphone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlanendDate(String planendDate) {
		this.planendDate = planendDate;
	}

	public void setPremarks(String premarks) {
		this.premarks = premarks;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	public void setPromans(List<Proman> promans) {
		this.promans = promans;
	}

	public void setReconnaissanceUnit(String reconnaissanceUnit) {
		this.reconnaissanceUnit = reconnaissanceUnit;
	}

	public void setReconnaissanceUnitPrincipal(
			String reconnaissanceUnitPrincipal) {
		this.reconnaissanceUnitPrincipal = reconnaissanceUnitPrincipal;
	}

	public void setReconnaissanceUnitPrincipalTelphone(
			String reconnaissanceUnitPrincipalTelphone) {
		this.reconnaissanceUnitPrincipalTelphone = reconnaissanceUnitPrincipalTelphone;
	}

	public void setSourcesOfInvestment(String sourcesOfInvestment) {
		this.sourcesOfInvestment = sourcesOfInvestment;
	}

	public void setSpreadsheets(List<Spreadsheet> spreadsheets) {
		this.spreadsheets = spreadsheets;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setStructureLevel(String structureLevel) {
		this.structureLevel = structureLevel;
	}

	public void setSubunits(List<Subunit> subunits) {
		this.subunits = subunits;
	}

	public void setSupervisionUnit(String supervisionUnit) {
		this.supervisionUnit = supervisionUnit;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public void setYxarea(Yxarea yxarea) {
		this.yxarea = yxarea;
	}

}