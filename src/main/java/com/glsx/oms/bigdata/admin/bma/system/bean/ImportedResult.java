package com.glsx.oms.bigdata.admin.bma.system.bean;

import java.io.Serializable;
import java.util.List;

import com.glsx.oms.bigdata.admin.bma.salesTask.model.SalesTask;
import com.glsx.oms.bigdata.admin.bma.vo.ResponseResult;

public class ImportedResult implements Serializable {

	private static final long serialVersionUID = -1813546946702344859L;
	
	// 导入的总行数
	private long totalCount;
	
	// 导入成功的行数
	private long successCount;
	
	// 导入失败的行数
	private long failCount;
	
	// 是否导入成功 1：成功，0：失败
	private int isImported;
	
	// 失败的原因
	private String cause;
	
	// 失败文件的url
	private String url;
	
	//导入返回的字符串
	private String msg;
	
	//返回失败列表
	private List<ResponseResult<SalesTask>> failList;
	

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(long successCount) {
		this.successCount = successCount;
	}

	public long getFailCount() {
		return failCount;
	}

	public void setFailCount(long failCount) {
		this.failCount = failCount;
	}

	public int getIsImported() {
		return isImported;
	}

	public void setIsImported(int isImported) {
		this.isImported = isImported;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

	public List<ResponseResult<SalesTask>> getFailList() {
		return failList;
	}

	public void setFailList(List<ResponseResult<SalesTask>> failList) {
		this.failList = failList;
	}

	@Override
	public String toString() {
		return "ImportedResult [totalCount=" + totalCount + ", successCount="
				+ successCount + ", failCount=" + failCount + ", isImported="
				+ isImported + ", cause=" + cause + "]";
	}

}
