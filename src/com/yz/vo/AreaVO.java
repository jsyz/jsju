package com.yz.vo;

public class AreaVO {

	private int id;
	private int index;// 排序
	private String areaName;// 地区名称
	private int projectNumberTotal;// 项目总数
	private float buildingAreaTotal;// 总面积
	private float buildingCostTotal;// 总造价

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getProjectNumberTotal() {
		return projectNumberTotal;
	}

	public void setProjectNumberTotal(int projectNumberTotal) {
		this.projectNumberTotal = projectNumberTotal;
	}

	public float getBuildingAreaTotal() {
		return buildingAreaTotal;
	}

	public void setBuildingAreaTotal(float buildingAreaTotal) {
		this.buildingAreaTotal = buildingAreaTotal;
	}

	public float getBuildingCostTotal() {
		return buildingCostTotal;
	}

	public void setBuildingCostTotal(float buildingCostTotal) {
		this.buildingCostTotal = buildingCostTotal;
	}

}
