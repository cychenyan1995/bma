package com.glsx.oms.bigdata.admin.bma.salesTask.model;

import java.util.List;

public class MonthSalesTask {

	private String manager;
	private Integer totalTaskNum;
	private Integer totalSaleNum;
	private String totalRatio;
	private List<MonthSalesTaskDetail> MonthSalesTaskDetailList;
	
	public List<MonthSalesTaskDetail> getMonthSalesTaskDetailList() {
		return MonthSalesTaskDetailList;
	}
	public void setMonthSalesTaskDetailList(List<MonthSalesTaskDetail> monthSalesTaskDetailList) {
		MonthSalesTaskDetailList = monthSalesTaskDetailList;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public Integer getTotalTaskNum() {
		return totalTaskNum;
	}
	public void setTotalTaskNum(Integer totalTaskNum) {
		this.totalTaskNum = totalTaskNum;
	}
	public Integer getTotalSaleNum() {
		return totalSaleNum;
	}
	public void setTotalSaleNum(Integer totalSaleNum) {
		this.totalSaleNum = totalSaleNum;
	}
	public String getTotalRatio() {
		return totalRatio;
	}
	public void setTotalRatio(String totalRatio) {
		this.totalRatio = totalRatio;
	}
	
	
}
