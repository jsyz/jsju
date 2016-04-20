package com.yz.vo;

//项目评价
public class ProjectEvaluateSheet {

	private int badBehaviorNumber;// 工程质量不良行为记录表数量
	private int managerPointNumber;// 项目经理扣分情况记录表数量
	private int administrativeSanctionNumber;// 行政处罚情况记录表数量
	private int rewardsAndPenaltiesNumber;// 奖惩情况记录表数量
	private int workerWageNumber;// 民工工资投诉处理记录表数量
	private int safeProductionNumber;// 安全生产事故情况记录表数量

	public int getBadBehaviorNumber() {
		return badBehaviorNumber;
	}

	public void setBadBehaviorNumber(int badBehaviorNumber) {
		this.badBehaviorNumber = badBehaviorNumber;
	}

	public int getManagerPointNumber() {
		return managerPointNumber;
	}

	public void setManagerPointNumber(int managerPointNumber) {
		this.managerPointNumber = managerPointNumber;
	}

	public int getAdministrativeSanctionNumber() {
		return administrativeSanctionNumber;
	}

	public void setAdministrativeSanctionNumber(int administrativeSanctionNumber) {
		this.administrativeSanctionNumber = administrativeSanctionNumber;
	}

	public int getRewardsAndPenaltiesNumber() {
		return rewardsAndPenaltiesNumber;
	}

	public void setRewardsAndPenaltiesNumber(int rewardsAndPenaltiesNumber) {
		this.rewardsAndPenaltiesNumber = rewardsAndPenaltiesNumber;
	}

	public int getWorkerWageNumber() {
		return workerWageNumber;
	}

	public void setWorkerWageNumber(int workerWageNumber) {
		this.workerWageNumber = workerWageNumber;
	}

	public int getSafeProductionNumber() {
		return safeProductionNumber;
	}

	public void setSafeProductionNumber(int safeProductionNumber) {
		this.safeProductionNumber = safeProductionNumber;
	}

}
