package com.yz.vo;

public class ProjectClassify {

	private String totalClassifyName;
	private String classifyName;
	private int projectNumberTotal;// 项目总数
	private float buildingAreaTotal;// 总面积
	private float buildingCostTotal;// 总造价

	public String getTotalClassifyName() {
		return totalClassifyName;
	}

	public void setTotalClassifyName(String totalClassifyName) {
		this.totalClassifyName = totalClassifyName;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
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
