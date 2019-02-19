package com.glsx.oms.bigdata.admin.bma.salesTask.model;

import com.glsx.oms.bigdata.admin.bma.common.BasePojo;

public class Manager extends BasePojo<Manager>{

	private String managerCode;
	private String managerName;
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
}
