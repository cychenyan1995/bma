package com.glsx.oms.bigdata.admin.bma.salesHistory.model;

import java.util.Date;

import org.oreframework.commons.office.poi.zslin.utils.ExcelResources;

import com.glsx.oms.bigdata.admin.bma.common.BasePojo;

public class SalesHistory extends BasePojo<SalesHistory> {
	private Integer id;

	private String month;

	private String tmonth;

	private String monthRegion;

	private String area;

	private String bizType;

	private String regionalManagerLeader;

	private String regionalManager;

	private String spCode;

	private String spName;

	private String shopCode;

	private String shopName;
	
	private String taskType; //页面上选择的任务类型

	// 任务数
	private Integer tasknumDvd;

	private Integer tasknumGps;

	private Integer tasknumYunjing;

	private Integer tasknumDidihuService;

	private Integer tasknumWirelessCharge;

	private Integer tasknumRearview;

	private Integer tasknumFunctionRearview;

	// 销售数
	private Integer salesGlDvd;

	private Integer salesGlGps;

	private Integer salesYunjing;

	private Integer salesDidihuService;

	private Integer salesWirelessCharge;

	private Integer salesRearview;

	private Integer salesFunctionRearview;

	// 完成率
	private String salesGlDvdRate;

	private String salesGlGpsRate;

	private String salesYunjingRate;

	private String salesDidihuServiceRate;

	private String salesWirelessChargeRate;

	private String salesRearviewRate;

	private String salesFunctionRearviewRate;

	private Integer salesGlTotal;

	private Integer salesDvdInterLargescreen;

	private Integer salesDvdInterOriginalscreen;

	private Integer salesScreenChange;

	private Integer salesReflectionUpgrade;

	private Integer salesBigScreenPromotion;

	private Float totalChainRatio;

	private Float glDvdChainRatio;

	private Float glGpsChainRatio;

	private Float yunjingChainRatio;

	private Float didihuServiceChainRatio;

	private Float wirelessChargeChainRatio;

	private Float rearviewChainRatio;

	private Float funRearviewChainRatio;

	private Float ratioInstallGlDvd;

	private Float ratioGlDvd;

	private Float ratioGlGps;

	private Float costDvd;

	private Float costGps;

	private Float costYunjing;

	private Float costDidihuService;

	private Float costWirelessCharge;

	private Float costRearview;

	private Float costFunctionRearview;

	private Float costTotal;

	private Float costDvdInterLargescreen;

	private Float costDvdInterOriginalscreen;

	private Float costScreenChange;

	private Float costReflectionUpgrade;

	private Float costBigScreenPromotion;

	private Integer totalDeliver;

	private Integer totalSales;

	private Integer totalInventory;

	private Date createTime;

	private Date updateTime;

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

	public String getTmonth() {
		return tmonth;
	}

	public void setTmonth(String tmonth) {
		this.tmonth = tmonth;
	}

	public String getMonthRegion() {
		return monthRegion;
	}

	public void setMonthRegion(String monthRegion) {
		this.monthRegion = monthRegion;
	}

	@ExcelResources(title = "区域", order = 1)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@ExcelResources(title = "业务类型", order = 2)
	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	@ExcelResources(title = "大区经理", order = 3)
	public String getRegionalManagerLeader() {
		return regionalManagerLeader;
	}

	public void setRegionalManagerLeader(String regionalManagerLeader) {
		this.regionalManagerLeader = regionalManagerLeader;
	}

	@ExcelResources(title = "区域经理", order = 4)
	public String getRegionalManager() {
		return regionalManager;
	}

	public void setRegionalManager(String regionalManager) {
		this.regionalManager = regionalManager;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	@ExcelResources(title = "服务商", order = 5)
	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	@ExcelResources(title = "广联DVD任务数", order = 6)
	public Integer getTasknumDvd() {
		return (null == tasknumDvd) ? 0 : tasknumDvd;
	}

	public void setTasknumDvd(Integer tasknumDvd) {
		this.tasknumDvd = tasknumDvd;
	}

	@ExcelResources(title = "广联DVD销售数", order = 7)
	public Integer getSalesGlDvd() {
		return (null == salesGlDvd) ? 0 : salesGlDvd;
	}

	public void setSalesGlDvd(Integer salesGlDvd) {
		this.salesGlDvd = salesGlDvd;
	}

	@ExcelResources(title = "广联DVD完成率", order = 8)
	public String getSalesGlDvdRate() {
		return salesGlDvdRate;
	}

	public void setSalesGlDvdRate(String salesGlDvdRate) {
		this.salesGlDvdRate = salesGlDvdRate;
	}

	@ExcelResources(title = "广联GPS任务数", order = 9)
	public Integer getTasknumGps() {
		return (null == tasknumGps) ? 0 : tasknumGps;
	}

	public void setTasknumGps(Integer tasknumGps) {
		this.tasknumGps = tasknumGps;
	}

	@ExcelResources(title = "广联GPS销售数", order = 10)
	public Integer getSalesGlGps() {
		return (null == salesGlGps) ? 0 : salesGlGps;
	}

	public void setSalesGlGps(Integer salesGlGps) {
		this.salesGlGps = salesGlGps;
	}

	@ExcelResources(title = "广联GPS完成率", order = 11)
	public String getSalesGlGpsRate() {
		return salesGlGpsRate;
	}

	public void setSalesGlGpsRate(String salesGlGpsRate) {
		this.salesGlGpsRate = salesGlGpsRate;
	}

	@ExcelResources(title = "嘀嘀虎智能云镜任务数", order = 12)
	public Integer getTasknumYunjing() {
		return (null == tasknumYunjing) ? 0 : tasknumYunjing;
	}

	public void setTasknumYunjing(Integer tasknumYunjing) {
		this.tasknumYunjing = tasknumYunjing;
	}

	@ExcelResources(title = "嘀嘀虎智能云镜销售数", order = 13)
	public Integer getSalesYunjing() {
		return (salesYunjing == null) ? 0 : salesYunjing;
	}

	public void setSalesYunjing(Integer salesYunjing) {
		this.salesYunjing = salesYunjing;
	}

	@ExcelResources(title = "嘀嘀虎智能云镜完成率", order = 14)
	public String getSalesYunjingRate() {
		return salesYunjingRate;
	}

	public void setSalesYunjingRate(String salesYunjingRate) {
		this.salesYunjingRate = salesYunjingRate;
	}

	@ExcelResources(title = "嘀嘀虎车联网服务激活卡-1年版任务数", order = 15)
	public Integer getTasknumDidihuService() {
		return (null == tasknumDidihuService) ? 0 : tasknumDidihuService;
	}

	public void setTasknumDidihuService(Integer tasknumDidihuService) {
		this.tasknumDidihuService = tasknumDidihuService;
	}

	@ExcelResources(title = "嘀嘀虎车联网服务激活卡-1年版销售数", order = 16)
	public Integer getSalesDidihuService() {
		return (salesDidihuService == null) ? 0 : salesDidihuService;
	}

	public void setSalesDidihuService(Integer salesDidihuService) {
		this.salesDidihuService = salesDidihuService;
	}

	@ExcelResources(title = "嘀嘀虎车联网服务激活卡-1年版完成率", order = 17)
	public String getSalesDidihuServiceRate() {
		return salesDidihuServiceRate;
	}

	public void setSalesDidihuServiceRate(String salesDidihuServiceRate) {
		this.salesDidihuServiceRate = salesDidihuServiceRate;
	}

	@ExcelResources(title = "无线车充任务数", order = 18)
	public Integer getTasknumWirelessCharge() {
		return (null == tasknumWirelessCharge) ? 0 : tasknumWirelessCharge;
	}

	public void setTasknumWirelessCharge(Integer tasknumWirelessCharge) {
		this.tasknumWirelessCharge = tasknumWirelessCharge;
	}

	@ExcelResources(title = "无线车充任务数销售数", order = 19)
	public Integer getSalesWirelessCharge() {
		return (salesWirelessCharge == null) ? 0 : salesWirelessCharge;
	}

	public void setSalesWirelessCharge(Integer salesWirelessCharge) {
		this.salesWirelessCharge = salesWirelessCharge;
	}

	@ExcelResources(title = "无线车充任务数完成率", order = 20)
	public String getSalesWirelessChargeRate() {
		return salesWirelessChargeRate;
	}

	public void setSalesWirelessChargeRate(String salesWirelessChargeRate) {
		this.salesWirelessChargeRate = salesWirelessChargeRate;
	}

	@ExcelResources(title = "纯流媒体后视镜任务数", order = 21)
	public Integer getTasknumRearview() {
		return (null == tasknumRearview) ? 0 : tasknumRearview;
	}

	public void setTasknumRearview(Integer tasknumRearview) {
		this.tasknumRearview = tasknumRearview;
	}

	@ExcelResources(title = "纯流媒体后视镜销售数", order = 22)
	public Integer getSalesRearview() {
		return (salesRearview == null) ? 0 : salesRearview;
	}

	public void setSalesRearview(Integer salesRearview) {
		this.salesRearview = salesRearview;
	}

	@ExcelResources(title = "纯流媒体后视镜完成率", order = 23)
	public String getSalesRearviewRate() {
		return salesRearviewRate;
	}

	public void setSalesRearviewRate(String salesRearviewRate) {
		this.salesRearviewRate = salesRearviewRate;
	}

	@ExcelResources(title = "全功能_流媒体后视镜任务数", order = 24)
	public Integer getTasknumFunctionRearview() {
		return (null == tasknumFunctionRearview) ? 0 : tasknumFunctionRearview;
	}

	public void setTasknumFunctionRearview(Integer tasknumFunctionRearview) {
		this.tasknumFunctionRearview = tasknumFunctionRearview;
	}

	@ExcelResources(title = "全功能_流媒体后视镜销售数", order = 25)
	public Integer getSalesFunctionRearview() {
		return (salesFunctionRearview == null) ? 0 : salesFunctionRearview;
	}

	public void setSalesFunctionRearview(Integer salesFunctionRearview) {
		this.salesFunctionRearview = salesFunctionRearview;
	}

	@ExcelResources(title = "全功能_流媒体后视镜完成率", order = 26)
	public String getSalesFunctionRearviewRate() {
		return salesFunctionRearviewRate;
	}

	public void setSalesFunctionRearviewRate(String salesFunctionRearviewRate) {
		this.salesFunctionRearviewRate = salesFunctionRearviewRate;
	}

	public Integer getSalesGlTotal() {
		return salesGlTotal;
	}

	public void setSalesGlTotal(Integer salesGlTotal) {
		this.salesGlTotal = salesGlTotal;
	}

	public Integer getSalesDvdInterLargescreen() {
		return salesDvdInterLargescreen;
	}

	public void setSalesDvdInterLargescreen(Integer salesDvdInterLargescreen) {
		this.salesDvdInterLargescreen = salesDvdInterLargescreen;
	}

	public Integer getSalesDvdInterOriginalscreen() {
		return salesDvdInterOriginalscreen;
	}

	public void setSalesDvdInterOriginalscreen(Integer salesDvdInterOriginalscreen) {
		this.salesDvdInterOriginalscreen = salesDvdInterOriginalscreen;
	}

	public Integer getSalesScreenChange() {
		return salesScreenChange;
	}

	public void setSalesScreenChange(Integer salesScreenChange) {
		this.salesScreenChange = salesScreenChange;
	}

	public Integer getSalesReflectionUpgrade() {
		return salesReflectionUpgrade;
	}

	public void setSalesReflectionUpgrade(Integer salesReflectionUpgrade) {
		this.salesReflectionUpgrade = salesReflectionUpgrade;
	}

	public Integer getSalesBigScreenPromotion() {
		return salesBigScreenPromotion;
	}

	public void setSalesBigScreenPromotion(Integer salesBigScreenPromotion) {
		this.salesBigScreenPromotion = salesBigScreenPromotion;
	}

	public Float getTotalChainRatio() {
		return totalChainRatio;
	}

	public void setTotalChainRatio(Float totalChainRatio) {
		this.totalChainRatio = totalChainRatio;
	}

	public Float getGlDvdChainRatio() {
		return glDvdChainRatio;
	}

	public void setGlDvdChainRatio(Float glDvdChainRatio) {
		this.glDvdChainRatio = glDvdChainRatio;
	}

	public Float getGlGpsChainRatio() {
		return glGpsChainRatio;
	}

	public void setGlGpsChainRatio(Float glGpsChainRatio) {
		this.glGpsChainRatio = glGpsChainRatio;
	}

	public Float getYunjingChainRatio() {
		return yunjingChainRatio;
	}

	public void setYunjingChainRatio(Float yunjingChainRatio) {
		this.yunjingChainRatio = yunjingChainRatio;
	}

	public Float getDidihuServiceChainRatio() {
		return didihuServiceChainRatio;
	}

	public void setDidihuServiceChainRatio(Float didihuServiceChainRatio) {
		this.didihuServiceChainRatio = didihuServiceChainRatio;
	}

	public Float getWirelessChargeChainRatio() {
		return wirelessChargeChainRatio;
	}

	public void setWirelessChargeChainRatio(Float wirelessChargeChainRatio) {
		this.wirelessChargeChainRatio = wirelessChargeChainRatio;
	}

	public Float getRearviewChainRatio() {
		return rearviewChainRatio;
	}

	public void setRearviewChainRatio(Float rearviewChainRatio) {
		this.rearviewChainRatio = rearviewChainRatio;
	}

	public Float getFunRearviewChainRatio() {
		return funRearviewChainRatio;
	}

	public void setFunRearviewChainRatio(Float funRearviewChainRatio) {
		this.funRearviewChainRatio = funRearviewChainRatio;
	}

	public Float getRatioInstallGlDvd() {
		return ratioInstallGlDvd;
	}

	public void setRatioInstallGlDvd(Float ratioInstallGlDvd) {
		this.ratioInstallGlDvd = ratioInstallGlDvd;
	}

	public Float getRatioGlDvd() {
		return ratioGlDvd;
	}

	public void setRatioGlDvd(Float ratioGlDvd) {
		this.ratioGlDvd = ratioGlDvd;
	}

	public Float getRatioGlGps() {
		return ratioGlGps;
	}

	public void setRatioGlGps(Float ratioGlGps) {
		this.ratioGlGps = ratioGlGps;
	}

	public Float getCostDvd() {
		return costDvd;
	}

	public void setCostDvd(Float costDvd) {
		this.costDvd = costDvd;
	}

	public Float getCostGps() {
		return costGps;
	}

	public void setCostGps(Float costGps) {
		this.costGps = costGps;
	}

	public Float getCostYunjing() {
		return costYunjing;
	}

	public void setCostYunjing(Float costYunjing) {
		this.costYunjing = costYunjing;
	}

	public Float getCostDidihuService() {
		return costDidihuService;
	}

	public void setCostDidihuService(Float costDidihuService) {
		this.costDidihuService = costDidihuService;
	}

	public Float getCostWirelessCharge() {
		return costWirelessCharge;
	}

	public void setCostWirelessCharge(Float costWirelessCharge) {
		this.costWirelessCharge = costWirelessCharge;
	}

	public Float getCostRearview() {
		return costRearview;
	}

	public void setCostRearview(Float costRearview) {
		this.costRearview = costRearview;
	}

	public Float getCostFunctionRearview() {
		return costFunctionRearview;
	}

	public void setCostFunctionRearview(Float costFunctionRearview) {
		this.costFunctionRearview = costFunctionRearview;
	}

	public Float getCostTotal() {
		return costTotal;
	}

	public void setCostTotal(Float costTotal) {
		this.costTotal = costTotal;
	}

	public Float getCostDvdInterLargescreen() {
		return costDvdInterLargescreen;
	}

	public void setCostDvdInterLargescreen(Float costDvdInterLargescreen) {
		this.costDvdInterLargescreen = costDvdInterLargescreen;
	}

	public Float getCostDvdInterOriginalscreen() {
		return costDvdInterOriginalscreen;
	}

	public void setCostDvdInterOriginalscreen(Float costDvdInterOriginalscreen) {
		this.costDvdInterOriginalscreen = costDvdInterOriginalscreen;
	}

	public Float getCostScreenChange() {
		return costScreenChange;
	}

	public void setCostScreenChange(Float costScreenChange) {
		this.costScreenChange = costScreenChange;
	}

	public Float getCostReflectionUpgrade() {
		return costReflectionUpgrade;
	}

	public void setCostReflectionUpgrade(Float costReflectionUpgrade) {
		this.costReflectionUpgrade = costReflectionUpgrade;
	}

	public Float getCostBigScreenPromotion() {
		return costBigScreenPromotion;
	}

	public void setCostBigScreenPromotion(Float costBigScreenPromotion) {
		this.costBigScreenPromotion = costBigScreenPromotion;
	}

	public Integer getTotalDeliver() {
		return totalDeliver;
	}

	public void setTotalDeliver(Integer totalDeliver) {
		this.totalDeliver = totalDeliver;
	}

	public Integer getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(Integer totalSales) {
		this.totalSales = totalSales;
	}

	public Integer getTotalInventory() {
		return totalInventory;
	}

	public void setTotalInventory(Integer totalInventory) {
		this.totalInventory = totalInventory;
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

}