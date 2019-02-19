package com.glsx.oms.bigdata.admin.bma.salesHistory.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.oreframework.commons.office.poi.zslin.utils.ExcelUtil;
import org.oreframework.web.ui.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.glsx.oms.bigdata.admin.bma.common.BaseController;
import com.glsx.oms.bigdata.admin.bma.framework.ExportProperty;
import com.glsx.oms.bigdata.admin.bma.salesHistory.model.SalesHistory;
import com.glsx.oms.bigdata.admin.bma.salesHistory.service.SalesHistoryService;
import com.glsx.oms.bigdata.admin.bma.vo.RespMessage;

@RestController
@RequestMapping("/salesHistory")
public class SalesHistoryController extends BaseController<SalesHistory> {

	@Autowired
	// @Resource
	private SalesHistoryService salesHistoryService;

	@Resource
	private ExportProperty exportProperty;

	private final static Logger LOGGER = LoggerFactory.getLogger(SalesHistoryController.class);

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

	@GetMapping("/getListByPage")
	public RespMessage getListByPage(SalesHistory salesHistory, Pagination pagination) {
		RespMessage rm = new RespMessage();
		if (null != salesHistory.getRegionalManager()) {
			String rM = salesHistory.getRegionalManager().replaceAll(" ", "");
			salesHistory.setRegionalManager(rM);
		}
		//现在改为用task表作为主表，所以查询条件都设为task表的
		if(null != salesHistory.getMonth()){
			salesHistory.setTmonth(salesHistory.getMonth());
		}
		PageInfo<SalesHistory> page = salesHistoryService.getSpSalesHistory(pagination, salesHistory);
		if (null != page) {
			List<SalesHistory> list = page.getList();
			// 处理销售量不为0，但任务量为0 这种状况
			for (SalesHistory sales : list) {
				if (0 != sales.getSalesGlDvd() && 0 == sales.getTasknumDvd()) {
					sales.setSalesGlDvdRate("200.00%");
				}
				if (0 != sales.getSalesGlGps() && 0 == sales.getTasknumGps()) {
					sales.setSalesGlGpsRate("200.00%");
				}
				if (0 != sales.getSalesYunjing() && 0 == sales.getTasknumYunjing()) {
					sales.setSalesYunjingRate("200.00%");
				}
				if (0 != sales.getSalesDidihuService() && 0 == sales.getTasknumDidihuService()) {
					sales.setSalesDidihuServiceRate("200.00%");
				}
				if (0 != sales.getSalesWirelessCharge() && 0 == sales.getTasknumWirelessCharge()) {
					sales.setSalesWirelessChargeRate("200.00%");
				}
				if (0 != sales.getSalesRearview() && 0 == sales.getTasknumRearview()) {
					sales.setSalesRearviewRate("200.00%");
				}
				if (0 != sales.getSalesFunctionRearview() && 0 == sales.getTasknumFunctionRearview()) {
					sales.setSalesFunctionRearviewRate("200.00%");
				}
			}
			// 处理跳转商户详情页面时，因某些月份没导入任务，导致商户详细出不来的情况
			/*if (null != salesHistory.getSpCode()) {
				// 是跳转商户详情的操作,通过查询任务数据，将值赋给第一个list
				List<SalesHistory> tasksBySp = salesHistoryService.getTasksBySp(salesHistory);
				SalesHistory taskDetail = tasksBySp.get(0);
				SalesHistory salesDetail = list.get(0);
				salesDetail.setRegionalManager(taskDetail.getRegionalManager());
				salesDetail.setRegionalManagerLeader(taskDetail.getRegionalManagerLeader());
			}*/
			rm.setData(list);
			rm.setPageTotal(page.getTotal());
			rm.setPageNum(page.getPageNum());
		}
		return rm;
	}

	@GetMapping("/getLeaderList")
	public RespMessage getLeaderList(SalesHistory salesHistory, Pagination pagination) {
		RespMessage rm = new RespMessage();
		List<SalesHistory> leader = salesHistoryService.getLeader();
		if (null != leader) {
			rm.setData(leader);
			rm.setPageTotal(leader.size());
		}
		return rm;
	}

	@GetMapping("/getAreaList")
	public RespMessage getAreaList(SalesHistory salesHistory, Pagination pagination) {
		RespMessage rm = new RespMessage();
		List<SalesHistory> area = salesHistoryService.getArea();
		if (null != area) {
			rm.setData(area);
			rm.setPageTotal(area.size());
		}
		return rm;
	}

	@GetMapping("/exportMonthSalesHistory")
	public RespMessage exportMonthSalesHistory(SalesHistory salesHistory) {
		RespMessage rm = new RespMessage();
		try {
			List<SalesHistory> list = salesHistoryService.getSpSalesHistoryByMonth(salesHistory);
			for (SalesHistory sales : list) {
				if (0 != sales.getSalesGlDvd() && 0 == sales.getTasknumDvd()) {
					sales.setSalesGlDvdRate("200.00%");
				}
				if (0 != sales.getSalesGlGps() && 0 == sales.getTasknumGps()) {
					sales.setSalesGlGpsRate("200.00%");
				}
				if (0 != sales.getSalesYunjing() && 0 == sales.getTasknumYunjing()) {
					sales.setSalesYunjingRate("200.00%");
				}
				if (0 != sales.getSalesDidihuService() && 0 == sales.getTasknumDidihuService()) {
					sales.setSalesDidihuServiceRate("200.00%");
				}
				if (0 != sales.getSalesWirelessCharge() && 0 == sales.getTasknumWirelessCharge()) {
					sales.setSalesWirelessChargeRate("200.00%");
				}
				if (0 != sales.getSalesRearview() && 0 == sales.getTasknumRearview()) {
					sales.setSalesRearviewRate("200.00%");
				}
				if (0 != sales.getSalesFunctionRearview() && 0 == sales.getTasknumFunctionRearview()) {
					sales.setSalesFunctionRearviewRate("200.00%");
				}
			}
			String url = null;
			if (null != list && list.size() > 0) {
				String fileName = salesHistory.getMonth() + "月商户销售" + "_" + System.currentTimeMillis() / 1000 + ".xlsx";
				// 不需要模版导出
				ExcelUtil.getInstance().exportObj2Excel(exportProperty.getLocation() + fileName, list,
						SalesHistory.class);
				url = exportProperty.getDomain() + fileName;
			}
			rm.setData(url);
			LOGGER.info(salesHistory.getMonth() + "月商户销售表excel的url:" + url);
		} catch (Exception e) {
			rm.setMessage("导出失败");
		}
		return rm;
	}

	/*@GetMapping("/getSumSalesHistoryByType")
	public RespMessage getSumSalesHistoryByType(String taskType) {
		RespMessage rm = new RespMessage();
		List<SalesHistory> sumSaleByMonth = salesHistoryService.getSumSalesByMonth();
		List<SalesHistory> sumTaskByMonth = salesHistoryService.getSumTaskByMonth();
		// 取近10个月的数据进行展示
		List<String> monthList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < 10; i++) {
			String lastMonth = sdf.format(cal.getTime());
			cal.add(cal.MONTH, -1);
			monthList.add(lastMonth);
		}
		Collections.reverse(monthList);

		List<Integer> saleList = new ArrayList<Integer>();
		List<Integer> taskList = new ArrayList<Integer>();
		List<Float> rateList = new ArrayList<Float>();
		// 任务完成数
		for (int i = 0; i < 10; i++) {
			Integer sumSale = 0;
			for (SalesHistory sale : sumSaleByMonth) {
				if (monthList.get(i).equals(sale.getMonth())) {
					switch (taskType) {
					case "sum":
						sumSale = sale.getSalesGlDvd() + sale.getSalesGlGps() + sale.getSalesYunjing()
								+ sale.getSalesDidihuService() + sale.getSalesWirelessCharge() + sale.getSalesRearview()
								+ sale.getSalesFunctionRearview();
						break;
					case "dvd":
						sumSale = sale.getSalesGlDvd();
						break;
					case "gps":
						sumSale = sale.getSalesGlGps();
						break;
					case "yunjing":
						sumSale = sale.getSalesYunjing();
						break;
					case "didihuService":
						sumSale = sale.getSalesDidihuService();
						break;
					case "wirelessCharge":
						sumSale = sale.getSalesWirelessCharge();
						break;
					case "rearview":
						sumSale = sale.getSalesRearview();
						break;
					case "functionRearview":
						sumSale = sale.getSalesFunctionRearview();
						break;
					}
					break;
				} else {
					sumSale = 0;
				}
			}
			saleList.add(sumSale);

		}
		// 任务计划数
		for (int i = 0; i < 10; i++) {
			Integer sumTask = 0;
			for (SalesHistory task : sumTaskByMonth) {
				if (monthList.get(i).equals(task.getMonth())) {
					switch (taskType) {
					case "sum":
						sumTask = task.getTasknumDvd() + task.getTasknumGps() + task.getTasknumYunjing()
								+ task.getTasknumDidihuService() + task.getTasknumWirelessCharge()
								+ task.getTasknumRearview() + task.getTasknumFunctionRearview();
						break;
					case "dvd":
						sumTask = task.getTasknumDvd();
						break;
					case "gps":
						sumTask = task.getTasknumGps();
						break;
					case "yunjing":
						sumTask = task.getTasknumYunjing();
						break;
					case "didihuService":
						sumTask = task.getTasknumDidihuService();
						break;
					case "wirelessCharge":
						sumTask = task.getTasknumWirelessCharge();
						break;
					case "rearview":
						sumTask = task.getTasknumRearview();
						break;
					case "functionRearview":
						sumTask = task.getTasknumFunctionRearview();
						break;
					}
					break;
				} else {
					sumTask = 0;
				}
			}
			taskList.add(sumTask);
		}
		// 任务完成率
		for (int i = 0; i < 10; i++) {
			float rate = 0;
			if (0 != saleList.get(i) && 0 != taskList.get(i)) {
				float quotient = (float) saleList.get(i) * 100 / (float) taskList.get(i);
				DecimalFormat df = new DecimalFormat("0.00");// 格式化小数，不足的补0
				String s = df.format(quotient);// 返回的是String类型的
				rate = Float.parseFloat(s);
			} else if (0 != saleList.get(i) && 0 == taskList.get(i)) {
				rate = Float.parseFloat("200.00");
			} else {
				rate = 0;
			}
			rateList.add(rate);
		}
		LOGGER.info(taskType + "图标信息查询成功！");
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("monthList", monthList);
		resultMap.put("saleList", saleList);
		resultMap.put("taskList", taskList);
		resultMap.put("rateList", rateList);
		rm.setData(resultMap);
		return rm;
	}*/
	
	//实际销售历史页面的图表
	@GetMapping("/getSumSalesHistoryByType")
	public RespMessage getSumSalesHistoryByType(SalesHistory salesHistory) {
		RespMessage rm = new RespMessage();
		List<SalesHistory> sumSaleByMonth = salesHistoryService.getSumSalesByMonth(salesHistory);
		List<SalesHistory> sumTaskByMonth = salesHistoryService.getSumTaskByMonth(salesHistory);
		// 取近10个月的数据进行展示
		List<String> monthList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < 10; i++) {
			String lastMonth = sdf.format(cal.getTime());
			cal.add(cal.MONTH, -1);
			monthList.add(lastMonth);
		}
		Collections.reverse(monthList);

		List<Integer> saleList = new ArrayList<Integer>();
		List<Integer> taskList = new ArrayList<Integer>();
		List<Float> rateList = new ArrayList<Float>();
		String taskType = salesHistory.getTaskType();
		// 任务完成数
		for (int i = 0; i < 10; i++) {
			Integer sumSale = 0;
			
			for (SalesHistory sale : sumSaleByMonth) {
				if (monthList.get(i).equals(sale.getMonth())) {
					switch (taskType) {
					case "sum":
						sumSale = sale.getSalesGlDvd() + sale.getSalesGlGps() + sale.getSalesYunjing()
								+ sale.getSalesDidihuService() + sale.getSalesWirelessCharge() + sale.getSalesRearview()
								+ sale.getSalesFunctionRearview();
						break;
					case "dvd":
						sumSale = sale.getSalesGlDvd();
						break;
					case "gps":
						sumSale = sale.getSalesGlGps();
						break;
					case "yunjing":
						sumSale = sale.getSalesYunjing();
						break;
					case "didihuService":
						sumSale = sale.getSalesDidihuService();
						break;
					case "wirelessCharge":
						sumSale = sale.getSalesWirelessCharge();
						break;
					case "rearview":
						sumSale = sale.getSalesRearview();
						break;
					case "functionRearview":
						sumSale = sale.getSalesFunctionRearview();
						break;
					}
					break;
				} else {
					sumSale = 0;
				}
			}
			saleList.add(sumSale);

		}
		// 任务计划数
		for (int i = 0; i < 10; i++) {
			Integer sumTask = 0;
			for (SalesHistory task : sumTaskByMonth) {
				if (monthList.get(i).equals(task.getMonth())) {
					switch (taskType) {
					case "sum":
						sumTask = task.getTasknumDvd() + task.getTasknumGps() + task.getTasknumYunjing()
								+ task.getTasknumDidihuService() + task.getTasknumWirelessCharge()
								+ task.getTasknumRearview() + task.getTasknumFunctionRearview();
						break;
					case "dvd":
						sumTask = task.getTasknumDvd();
						break;
					case "gps":
						sumTask = task.getTasknumGps();
						break;
					case "yunjing":
						sumTask = task.getTasknumYunjing();
						break;
					case "didihuService":
						sumTask = task.getTasknumDidihuService();
						break;
					case "wirelessCharge":
						sumTask = task.getTasknumWirelessCharge();
						break;
					case "rearview":
						sumTask = task.getTasknumRearview();
						break;
					case "functionRearview":
						sumTask = task.getTasknumFunctionRearview();
						break;
					}
					break;
				} else {
					sumTask = 0;
				}
			}
			taskList.add(sumTask);
		}
		// 任务完成率
		for (int i = 0; i < 10; i++) {
			float rate = 0;
			if (0 != saleList.get(i) && 0 != taskList.get(i)) {
				float quotient = (float) saleList.get(i) * 100 / (float) taskList.get(i);
				DecimalFormat df = new DecimalFormat("0.00");// 格式化小数，不足的补0
				String s = df.format(quotient);// 返回的是String类型的
				rate = Float.parseFloat(s);
			} else if (0 != saleList.get(i) && 0 == taskList.get(i)) {
				rate = Float.parseFloat("200.00");
			} else {
				rate = 0;
			}
			rateList.add(rate);
		}
		LOGGER.info(taskType + "图标信息查询成功！");
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("monthList", monthList);
		resultMap.put("saleList", saleList);
		resultMap.put("taskList", taskList);
		resultMap.put("rateList", rateList);
		rm.setData(resultMap);
		return rm;
	}

	//商户详情里的图表
	@GetMapping("/getChartListBySp")
	public RespMessage getChartListBySp(String spCode, String area, Pagination pagination, String taskType) {
		RespMessage rm = new RespMessage();
		SalesHistory salesHistory = new SalesHistory();
		salesHistory.setSpCode(spCode);
		salesHistory.setArea(area);
		List<SalesHistory> saleBySp = salesHistoryService.getSalesBySp(salesHistory);
		List<SalesHistory> taskBySp = salesHistoryService.getTasksBySp(salesHistory);

		// 取近10个月的数据进行展示
		List<String> monthList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < 10; i++) {
			String lastMonth = sdf.format(cal.getTime());
			cal.add(cal.MONTH, -1);
			monthList.add(lastMonth);
		}
		Collections.reverse(monthList);

		List<Integer> saleList = new ArrayList<Integer>();
		List<Integer> taskList = new ArrayList<Integer>();
		List<Float> rateList = new ArrayList<Float>();

		// 因为存在没有销售数，但有任务数的情况 所以需要分开来查询
		// 任务完成数
		for (int i = 0; i < 10; i++) {
			Integer sumSale = 0;
			for (SalesHistory sale : saleBySp) {
				if (monthList.get(i).equals(sale.getMonth())) {
					switch (taskType) {
					case "sum":
						sumSale = sale.getSalesGlDvd() + sale.getSalesGlGps() + sale.getSalesYunjing()
								+ sale.getSalesDidihuService() + sale.getSalesWirelessCharge() + sale.getSalesRearview()
								+ sale.getSalesFunctionRearview();
						break;
					case "dvd":
						sumSale = sale.getSalesGlDvd();
						break;
					case "gps":
						sumSale = sale.getSalesGlGps();
						break;
					case "yunjing":
						sumSale = sale.getSalesYunjing();
						break;
					case "didihuService":
						sumSale = sale.getSalesDidihuService();
						break;
					case "wirelessCharge":
						sumSale = sale.getSalesWirelessCharge();
						break;
					case "rearview":
						sumSale = sale.getSalesRearview();
						break;
					case "functionRearview":
						sumSale = sale.getSalesFunctionRearview();
						break;
					}
					break;
				} else {
					sumSale = 0;
				}
			}
			saleList.add(sumSale);
		}

		// 任务计划数
		for (int i = 0; i < 10; i++) {
			Integer sumTask = 0;
			for (SalesHistory task : taskBySp) {
				if (monthList.get(i).equals(task.getMonth())) {
					if (null != task.getMonth()) {
						switch (taskType) {
						case "sum":
							sumTask = task.getTasknumDvd() + task.getTasknumGps() + task.getTasknumYunjing()
									+ task.getTasknumDidihuService() + task.getTasknumWirelessCharge()
									+ task.getTasknumRearview() + task.getTasknumFunctionRearview();
							break;
						case "dvd":
							sumTask = task.getTasknumDvd();
							break;
						case "gps":
							sumTask = task.getTasknumGps();
							break;
						case "yunjing":
							sumTask = task.getTasknumYunjing();
							break;
						case "didihuService":
							sumTask = task.getTasknumDidihuService();
							break;
						case "wirelessCharge":
							sumTask = task.getTasknumWirelessCharge();
							break;
						case "rearview":
							sumTask = task.getTasknumRearview();
							break;
						case "functionRearview":
							sumTask = task.getTasknumFunctionRearview();
							break;
						}
						break;
					} else {
						LOGGER.info(monthList.get(i) + "没有导入销售任务");
						sumTask = 0;
					}
				} else {
					sumTask = 0;
				}
			}
			taskList.add(sumTask);
		}
		// 任务完成率
		for (int i = 0; i < 10; i++) {
			float rate = 0;
			if (0 != saleList.get(i) && 0 != taskList.get(i)) {
				float quotient = (float) saleList.get(i) * 100 / (float) taskList.get(i);
				DecimalFormat df = new DecimalFormat("0.00");// 格式化小数，不足的补0
				String s = df.format(quotient);// 返回的是String类型的
				rate = Float.parseFloat(s);
			} else if (0 != saleList.get(i) && 0 == taskList.get(i)) {
				rate = Float.parseFloat("200.00");
			} else {
				rate = 0;
			}
			rateList.add(rate);
		}
		LOGGER.info(taskType + "图标信息查询成功！");
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("monthList", monthList);
		resultMap.put("saleList", saleList);
		resultMap.put("taskList", taskList);
		resultMap.put("rateList", rateList);
		rm.setData(resultMap);
		return rm;
	}

}
