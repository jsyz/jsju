package com.yz.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Construction entity.
 * 
 * @author lq 文明施工
 */
@Entity
@Table(name = "construction", schema = "dbo", catalog = "jsju")
public class Construction implements java.io.Serializable {

	// Fields
	private Integer id;          //文明施工id
	private Project project;     //	所属项目
	private Integer isWashSet;	//	冲洗设备是否设置
	private String washSetImg;  //	冲洗设置图片
	private Integer isWaterClear;//	场内排水是否畅通
	private String waterClearImg;//	排水通畅图片
	private Integer isDoorConform;//	电动伸缩门是否符合要求
	private String doorConformImg;//	电动伸缩门符合图片
	private Integer isGuardConform;//	门卫是否符合要求
	private String guardConformImg;//	门卫符合图片
	private Integer isAdConform;//	围挡及公益广告是否符合要求
	private String adConformImg;//	广告符合图片
	private Integer isRoadHarden;//	主要道路是否硬化
	private String roadHardenImg;//	道路硬化图片
	private Integer isRoadClear;//	主要道路是否通畅
	private String roadClearImg;//道路通畅图片
	private Integer isLabelComplete;//公示标牌内容是否齐全
	private String labelCompleteImg;//	标牌齐全图片
	private Integer isLayoutRational;//作业区、工作区和生活区采取隔离措施，布局是否合理
	private String layoutRationalImg;//布局合理图片
	private Integer isMeetFire;//办公区、生活区临时建筑是否满足防火要求
	private String meetFireImg;//满足防火图片
	private Integer isMeasurePlace;//建筑扬尘防治措施是否到位
	private String measurePlaceImg;//措施到位图片

	// Constructors

	/** default constructor */
	public Construction() {
	}

	/** full constructor */
	public Construction(Project project, Integer isWashSet, String washSetImg,
			Integer isWaterClear, String waterClearImg, Integer isDoorConform,
			String doorConformImg, Integer isGuardConform,
			String guardConformImg, Integer isAdConform, String adConformImg,
			Integer isRoadHarden, String roadHardenImg, Integer isRoadClear,
			String roadClearImg, Integer isLabelComplete,
			String labelCompleteImg, Integer isLayoutRational,
			String layoutRationalImg, Integer isMeetFire, String meetFireImg,
			Integer isMeasurePlace, String measurePlaceImg) {
		this.project = project;
		this.isWashSet = isWashSet;
		this.washSetImg = washSetImg;
		this.isWaterClear = isWaterClear;
		this.waterClearImg = waterClearImg;
		this.isDoorConform = isDoorConform;
		this.doorConformImg = doorConformImg;
		this.isGuardConform = isGuardConform;
		this.guardConformImg = guardConformImg;
		this.isAdConform = isAdConform;
		this.adConformImg = adConformImg;
		this.isRoadHarden = isRoadHarden;
		this.roadHardenImg = roadHardenImg;
		this.isRoadClear = isRoadClear;
		this.roadClearImg = roadClearImg;
		this.isLabelComplete = isLabelComplete;
		this.labelCompleteImg = labelCompleteImg;
		this.isLayoutRational = isLayoutRational;
		this.layoutRationalImg = layoutRationalImg;
		this.isMeetFire = isMeetFire;
		this.meetFireImg = meetFireImg;
		this.isMeasurePlace = isMeasurePlace;
		this.measurePlaceImg = measurePlaceImg;
	}

	// Property accessors
	@Column(name = "adConformImg", length = 100)
	public String getAdConformImg() {
		return this.adConformImg;
	}

	@Column(name = "doorConformImg", length = 100)
	public String getDoorConformImg() {
		return this.doorConformImg;
	}

	@Column(name = "guardConformImg", length = 100)
	public String getGuardConformImg() {
		return this.guardConformImg;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "isAdConform")
	public Integer getIsAdConform() {
		return this.isAdConform;
	}

	@Column(name = "isDoorConform")
	public Integer getIsDoorConform() {
		return this.isDoorConform;
	}

	@Column(name = "isGuardConform")
	public Integer getIsGuardConform() {
		return this.isGuardConform;
	}

	@Column(name = "isLabelComplete")
	public Integer getIsLabelComplete() {
		return this.isLabelComplete;
	}

	@Column(name = "isLayoutRational")
	public Integer getIsLayoutRational() {
		return this.isLayoutRational;
	}

	@Column(name = "isMeasurePlace")
	public Integer getIsMeasurePlace() {
		return this.isMeasurePlace;
	}

	@Column(name = "isMeetFire")
	public Integer getIsMeetFire() {
		return this.isMeetFire;
	}

	@Column(name = "isRoadClear")
	public Integer getIsRoadClear() {
		return this.isRoadClear;
	}

	@Column(name = "isRoadHarden")
	public Integer getIsRoadHarden() {
		return this.isRoadHarden;
	}

	@Column(name = "isWashSet")
	public Integer getIsWashSet() {
		return this.isWashSet;
	}

	@Column(name = "isWaterClear")
	public Integer getIsWaterClear() {
		return this.isWaterClear;
	}

	@Column(name = "labelCompleteImg", length = 100)
	public String getLabelCompleteImg() {
		return this.labelCompleteImg;
	}

	@Column(name = "layoutRationalImg", length = 100)
	public String getLayoutRationalImg() {
		return this.layoutRationalImg;
	}

	@Column(name = "measurePlaceImg", length = 100)
	public String getMeasurePlaceImg() {
		return this.measurePlaceImg;
	}

	@Column(name = "meetFireImg", length = 100)
	public String getMeetFireImg() {
		return this.meetFireImg;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "construction")
	public Project getProject() {
		return this.project;
	}

	@Column(name = "roadClearImg", length = 100)
	public String getRoadClearImg() {
		return this.roadClearImg;
	}

	@Column(name = "roadHardenImg", length = 100)
	public String getRoadHardenImg() {
		return this.roadHardenImg;
	}

	@Column(name = "washSetImg", length = 100)
	public String getWashSetImg() {
		return this.washSetImg;
	}

	@Column(name = "waterClearImg", length = 100)
	public String getWaterClearImg() {
		return this.waterClearImg;
	}

	@Column(name = "adConformImg", length = 100)
	public void setAdConformImg(String adConformImg) {
		this.adConformImg = adConformImg;
	}

	public void setDoorConformImg(String doorConformImg) {
		this.doorConformImg = doorConformImg;
	}

	public void setGuardConformImg(String guardConformImg) {
		this.guardConformImg = guardConformImg;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsAdConform(Integer isAdConform) {
		this.isAdConform = isAdConform;
	}

	public void setIsDoorConform(Integer isDoorConform) {
		this.isDoorConform = isDoorConform;
	}

	public void setIsGuardConform(Integer isGuardConform) {
		this.isGuardConform = isGuardConform;
	}

	public void setIsLabelComplete(Integer isLabelComplete) {
		this.isLabelComplete = isLabelComplete;
	}

	public void setIsLayoutRational(Integer isLayoutRational) {
		this.isLayoutRational = isLayoutRational;
	}

	public void setIsMeasurePlace(Integer isMeasurePlace) {
		this.isMeasurePlace = isMeasurePlace;
	}

	public void setIsMeetFire(Integer isMeetFire) {
		this.isMeetFire = isMeetFire;
	}

	public void setIsRoadClear(Integer isRoadClear) {
		this.isRoadClear = isRoadClear;
	}

	public void setIsRoadHarden(Integer isRoadHarden) {
		this.isRoadHarden = isRoadHarden;
	}

	public void setIsWashSet(Integer isWashSet) {
		this.isWashSet = isWashSet;
	}

	public void setIsWaterClear(Integer isWaterClear) {
		this.isWaterClear = isWaterClear;
	}

	public void setLabelCompleteImg(String labelCompleteImg) {
		this.labelCompleteImg = labelCompleteImg;
	}

	public void setLayoutRationalImg(String layoutRationalImg) {
		this.layoutRationalImg = layoutRationalImg;
	}

	public void setMeasurePlaceImg(String measurePlaceImg) {
		this.measurePlaceImg = measurePlaceImg;
	}

	public void setMeetFireImg(String meetFireImg) {
		this.meetFireImg = meetFireImg;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setRoadClearImg(String roadClearImg) {
		this.roadClearImg = roadClearImg;
	}

	public void setRoadHardenImg(String roadHardenImg) {
		this.roadHardenImg = roadHardenImg;
	}

	public void setWashSetImg(String washSetImg) {
		this.washSetImg = washSetImg;
	}

	public void setWaterClearImg(String waterClearImg) {
		this.waterClearImg = waterClearImg;
	}

}