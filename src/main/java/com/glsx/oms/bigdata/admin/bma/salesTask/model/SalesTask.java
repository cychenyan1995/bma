package com.glsx.oms.bigdata.admin.bma.salesTask.model;

import java.util.Date;


import org.oreframework.commons.office.poi.zslin.utils.ExcelResources;

import com.glsx.oms.bigdata.admin.bma.common.BasePojo;

public class SalesTask extends BasePojo<SalesTask>{
	
    private Integer id;

    private String month;

    private String area;

    private String regionalManagerLeader;

    private String regionalManager;

    private String bizType;
    
    private String spCode;

    private String spName;

    private String tasknumDvd;

    private String tasknumGps;

    private String tasknumYunjing;

    private String tasknumDidihuService;

    private String tasknumWirelessCharge;

    private String tasknumRearview;

    private String tasknumFunctionRearview;

    private Date createTime;

    private Date updateTime;
    
    /**
     * 表格类型
     */
    private String excelType;
    
    /**
     * 推送传值
     */
    private String type;
    private String name;

    /**
     * 
     * 销售数
     */
    private Integer salesGlDvd;

    private Integer salesGlGps;

    private Integer salesYunjing;

    private Integer salesDidihuService;

    private Integer salesWirelessCharge;

    private Integer salesRearview;

    private Integer salesFunctionRearview;
    
    /**
     * 
     * 任务完成率
     */
    private String ratioGlDvd;

    private String ratioGlGps;

    private String ratioYunjing;

    private String ratioDidihuService;

    private String ratioWirelessCharge;

    private String ratioRearview;

    private String ratioFunctionRearview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @ExcelResources(title="区域",order=1)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @ExcelResources(title="大区经理",order=3)
    public String getRegionalManagerLeader() {
        return regionalManagerLeader != null ? regionalManagerLeader.trim() : "";
    }

    public void setRegionalManagerLeader(String regionalManagerLeader) {
        this.regionalManagerLeader = regionalManagerLeader;
    }

    @ExcelResources(title="区域经理",order=4)
    public String getRegionalManager() {
        return regionalManager != null ?regionalManager.trim() : "";
    }

    public void setRegionalManager(String regionalManager) {
        this.regionalManager = regionalManager;
    }

    @ExcelResources(title="业务类型",order=2)
    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getSpCode() {
		return spCode != null ?spCode.trim() : "";
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	@ExcelResources(title="服务商",order=5)
	public String getSpName() {
        return spName != null ?spName.trim() : "";
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    @ExcelResources(title="DVD任务数",order=6)
    public String getTasknumDvd() {
        return tasknumDvd != null ?tasknumDvd.trim() : "";
    }

    public void setTasknumDvd(String tasknumDvd) {
        this.tasknumDvd = tasknumDvd;
    }

    @ExcelResources(title="GPS任务数",order=7)
    public String getTasknumGps() {
        return tasknumGps != null ?tasknumGps.trim() : "";
    }

    public void setTasknumGps(String tasknumGps) {
        this.tasknumGps = tasknumGps;
    }

    @ExcelResources(title="嘀嘀虎智能云镜任务数",order=8)
    public String getTasknumYunjing() {
        return tasknumYunjing != null ?tasknumYunjing.trim() : "";
    }

    public void setTasknumYunjing(String tasknumYunjing) {
        this.tasknumYunjing = tasknumYunjing;
    }

    @ExcelResources(title="嘀嘀虎车联网服务激活卡-1年版任务数",order=9)
    public String getTasknumDidihuService() {
        return tasknumDidihuService != null ?tasknumDidihuService.trim() : "";
    }

    public void setTasknumDidihuService(String tasknumDidihuService) {
        this.tasknumDidihuService = tasknumDidihuService;
    }

    @ExcelResources(title="无线车充任务数",order=10)
    public String getTasknumWirelessCharge() {
        return tasknumWirelessCharge != null ?tasknumWirelessCharge.trim() : "";
    }

    public void setTasknumWirelessCharge(String tasknumWirelessCharge) {
        this.tasknumWirelessCharge = tasknumWirelessCharge;
    }

    @ExcelResources(title="纯流媒体后视镜任务数",order=11)
    public String getTasknumRearview() {
        return tasknumRearview != null ?tasknumRearview.trim() : "";
    }

    public void setTasknumRearview(String tasknumRearview) {
        this.tasknumRearview = tasknumRearview;
    }

    @ExcelResources(title="全功能_流媒体后视镜任务数",order=12)
    public String getTasknumFunctionRearview() {
        return tasknumFunctionRearview != null ?tasknumFunctionRearview.trim() : "";
    }

    public void setTasknumFunctionRearview(String tasknumFunctionRearview) {
        this.tasknumFunctionRearview = tasknumFunctionRearview;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getExcelType() {
		return excelType;
	}

	public void setExcelType(String excelType) {
		this.excelType = excelType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalesGlDvd() {
		return salesGlDvd;
	}

	public void setSalesGlDvd(Integer salesGlDvd) {
		this.salesGlDvd = (salesGlDvd==null) ? 0: salesGlDvd;
	}

	public Integer getSalesGlGps() {
		return salesGlGps;
	}

	public void setSalesGlGps(Integer salesGlGps) {
		this.salesGlGps = (salesGlGps==null) ? 0: salesGlGps;
	}

	public Integer getSalesYunjing() {
		return salesYunjing;
	}

	public void setSalesYunjing(Integer salesYunjing) {
		this.salesYunjing = (salesYunjing==null) ? 0: salesYunjing;
	}

	public Integer getSalesDidihuService() {
		return salesDidihuService;
	}

	public void setSalesDidihuService(Integer salesDidihuService) {
		this.salesDidihuService = (salesDidihuService==null) ? 0: salesDidihuService;
	}

	public Integer getSalesWirelessCharge() {
		return salesWirelessCharge;
	}

	public void setSalesWirelessCharge(Integer salesWirelessCharge) {
		this.salesWirelessCharge = (salesWirelessCharge==null) ? 0: salesWirelessCharge;
	}

	public Integer getSalesRearview() {
		return salesRearview;
	}

	public void setSalesRearview(Integer salesRearview) {
		this.salesRearview = (salesRearview==null) ? 0: salesRearview;
	}

	public Integer getSalesFunctionRearview() {
		return salesFunctionRearview;
	}

	public void setSalesFunctionRearview(Integer salesFunctionRearview) {
		this.salesFunctionRearview = (salesFunctionRearview==null) ? 0: salesFunctionRearview;
	}



	public String getRatioGlDvd() {
		return ratioGlDvd;
	}

	public void setRatioGlDvd(String ratioGlDvd) {
		this.ratioGlDvd = ratioGlDvd;
	}

	public String getRatioGlGps() {
		return ratioGlGps;
	}

	public void setRatioGlGps(String ratioGlGps) {
		this.ratioGlGps = ratioGlGps;
	}

	public String getRatioYunjing() {
		return ratioYunjing;
	}

	public void setRatioYunjing(String ratioYunjing) {
		this.ratioYunjing = ratioYunjing;
	}

	public String getRatioDidihuService() {
		return ratioDidihuService;
	}

	public void setRatioDidihuService(String ratioDidihuService) {
		this.ratioDidihuService = ratioDidihuService;
	}

	public String getRatioWirelessCharge() {
		return ratioWirelessCharge;
	}

	public void setRatioWirelessCharge(String ratioWirelessCharge) {
		this.ratioWirelessCharge = ratioWirelessCharge;
	}

	public String getRatioRearview() {
		return ratioRearview;
	}

	public void setRatioRearview(String ratioRearview) {
		this.ratioRearview = ratioRearview;
	}

	public String getRatioFunctionRearview() {
		return ratioFunctionRearview;
	}

	public void setRatioFunctionRearview(String ratioFunctionRearview) {
		this.ratioFunctionRearview = ratioFunctionRearview;
	}
	
}