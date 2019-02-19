package com.glsx.oms.bigdata.admin.bma.salesTask.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.oreframework.web.ui.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.glsx.oms.bigdata.admin.bma.common.BaseService;
import com.glsx.oms.bigdata.admin.bma.salesTask.mapper.SalesTaskMapper;
import com.glsx.oms.bigdata.admin.bma.salesTask.model.Manager;
import com.glsx.oms.bigdata.admin.bma.salesTask.model.MonthSalesTask;
import com.glsx.oms.bigdata.admin.bma.salesTask.model.MonthSalesTaskDetail;
import com.glsx.oms.bigdata.admin.bma.salesTask.model.SalesTask;
import com.glsx.oms.bigdata.admin.bma.salesTask.model.SalesTaskCom;
import com.glsx.oms.bigdata.admin.bma.vo.ResponseResult;

@Service
public class SalesService extends BaseService<SalesTask> {
	@Resource
	private SalesTaskMapper salesTaskMapper;

	private final static Logger LOGGER = LoggerFactory.getLogger(SalesService.class);

	private SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
	Date date = new Date();
	String month = ft.format(date);

	// 大区经理 区域经理的code   name,code
	private Map<String, String> managerCodeMap;
	// 大区经理 区域经理的code   name,code
	private Map<String, String> managerCodeNameMap;

	public PageInfo<SalesTask> getSalesTaskList(Pagination pagination) {
		PageInfo<SalesTask> pageInfo = setPageInfo(pagination);
		Page<SalesTask> page = getPage(pageInfo);

		List<SalesTask> SalesTaskList = salesTaskMapper.getSalesTaskList(month);
		// 查询发布状态
		salesTaskMapper.selectPublishStatus(month);
		setPageInfo(pageInfo, page, SalesTaskList);
		return pageInfo;
	}

	public int updateSalesTask(SalesTaskCom salesTask) {
		salesTask.setMonth(month);
		return salesTaskMapper.updateSalesTask(salesTask);
	}

	public void insertAllSalesTask(List<SalesTask> salesTaskList) {
		salesTaskMapper.insertAllSalesTask(salesTaskList);
	}

	private void getManagerCodeMap() {
		managerCodeMap = new HashMap<String, String>();
		managerCodeNameMap = new HashMap<String, String>();
		List<Manager> managerCodeList = salesTaskMapper.getManagerCode();
		for (Manager manager : managerCodeList) {
			managerCodeMap.put(manager.getManagerName(), manager.getManagerCode());
			managerCodeNameMap.put(manager.getManagerCode(), manager.getManagerName());
		}
	}

	public List<String> getAllManagerLeaderCode() {
		// 转化成code
		// 重新加载code
		getManagerCodeMap();
		List<String> managerLeaderList = salesTaskMapper.getAllManagerLeader(month);
		List<String> managerLeaderCodeList = new ArrayList<String>();
		for (String managerLeader : managerLeaderList) {
			managerLeader = managerCodeMap.get(managerLeader);
			if(managerLeader != null && ! managerLeader.equals("") ) {
				managerLeaderCodeList.add(managerLeader);
			}else{
				LOGGER.info(managerLeader+"没有查询到对应的大区经理的code");
			}
		}
		return managerLeaderCodeList;
	}

	public List<String> getAllManagerCode() {
		// 转化成code
		List<String> managerList = salesTaskMapper.getAllManager(month);
		List<String> managerstemp = new ArrayList<>();
		List<String> managerCodeList = new ArrayList<String>();
		// 区域经理可能有多个
		for (String manager : managerList) {
			if (manager.indexOf('/') != -1) {
				String[] managerSplit = manager.split("/");
				for (String string : managerSplit) {
					managerstemp.add(string);
				}
			} else if(manager != null && ! manager.equals("")){
				managerstemp.add(manager);
			}
		}
		for (String manager : managerstemp) {
			manager = managerCodeMap.get(manager);
			if(manager != null) {
				managerCodeList.add(manager);
			}else{
				LOGGER.info(manager+"没有查询到对应的区域经理的code");
			}
		}
		return managerCodeList;
	}

	public List<String> getAllSpCode() {

		return salesTaskMapper.getAllSpCode(month);
	}

	public String getCodeBySpName(String spName) {
		return salesTaskMapper.getCodeBySpName(spName);
	}

	public int insertPublishStatus(String month) {
		return salesTaskMapper.insertPublishStatus(month);
	}

	public Integer selectPublishStatus(String month) {
		return salesTaskMapper.selectPublishStatus(month);
	}

	public List<MonthSalesTask> getSendMessage(SalesTaskCom salesTask)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> paraMap = new HashMap<>();
		List<MonthSalesTask> spInfoList = new ArrayList<MonthSalesTask>();
		List<MonthSalesTask> resList = new ArrayList<MonthSalesTask>();
		Map<String, SalesTaskCom> areaMap = new HashMap<>();
		List<String> spCodeList = new ArrayList<>();
		List<SalesTaskCom> salesList = new ArrayList<>();
		//得到经理的对应关系
		getManagerCodeMap();
		// 详情表的list

		String type = salesTask.getType();
		String name = salesTask.getName();
		// salesTask.setMonth(month);

		if (type.equals("sp")) {
			spCodeList.add(name);
			paraMap.put("spCodeList", spCodeList);
			paraMap.put("month", salesTask.getMonth());
			// 1.2得到各个服务商的7个产品销售数量和任务数
			LOGGER.info("得到各个服务商的7个产品销售数量和任务数参数：" + paraMap);
			salesList = salesTaskMapper.getDetailMessage(paraMap);
			LOGGER.info("得到各个服务商的7个产品销售数量和任务数记录数：" + salesList.size());
			salesTask.setSpName(name);
			for (SalesTaskCom sales : salesList) {
				MonthSalesTask monthSalesTask = new MonthSalesTask();
				monthSalesTask.setManager(sales.getSpName());
				// 7个产品销售数量 任务数和 将任务数赋值到sales中 7个产品和
				computeMonthSales(sales, monthSalesTask);
				// 把详细表数据也计算出来 详细列表的数据********
				List<MonthSalesTaskDetail> monthSalesTaskDetailList = operateProsRatio(sales);
				monthSalesTask.setMonthSalesTaskDetailList(monthSalesTaskDetailList);
				resList.add(monthSalesTask);

			}
		} else {
			if (type.equals("managerLeader")) {
				//根据code得到name
				String regionalManagerLeader =   managerCodeNameMap.get(name);
				salesTask.setRegionalManagerLeader(regionalManagerLeader);
				spCodeList = salesTaskMapper.getSpCodeList(salesTask);
				if (spCodeList.size() > 0) {
					paraMap.put("spCodeList", spCodeList);
					paraMap.put("month", salesTask.getMonth());
					// 1.2得到各个服务商的7个产品销售数量和任务数
					salesList = salesTaskMapper.getDetailMessage(paraMap);
					// 1.3根据任务数 销售数计算
					for (SalesTaskCom salesTemp : salesList) {
						SalesTaskCom sales = new SalesTaskCom();
						BeanUtils.copyProperties(sales, salesTemp);
						MonthSalesTask monthSalesTask = new MonthSalesTask();
						monthSalesTask.setManager(sales.getSpName());
						// 7个产品销售数量 任务数和 将任务数赋值到sales中 7个产品和
						computeMonthSales(sales, monthSalesTask);
						// 把详细表数据也计算出来 详细列表的数据 ********
						List<MonthSalesTaskDetail> monthSalesTaskDetailList = operateProsRatio(sales);
						monthSalesTask.setMonthSalesTaskDetailList(monthSalesTaskDetailList);
						// 各个服务商的数据放到list集合中
						spInfoList.add(monthSalesTask);
						if (areaMap.get(sales.getArea()) != null) {
							SalesTaskCom salesTaskBefore = areaMap.get(sales.getArea());
							// salesTaskBefore,sales 7个产品的销量和任务数分别相加
							operateProducts(salesTaskBefore, sales);
							areaMap.put(sales.getArea(), salesTaskBefore);
						} else {
							areaMap.put(sales.getArea(), sales);
						}

					}
				}
				
			} else if (type.equals("manager")) {
				//根据code得到name
				String manager = managerCodeNameMap.get(name);
				salesTask.setRegionalManager(manager);
				spCodeList = salesTaskMapper.getSpCodeList(salesTask);
				if (spCodeList.size() > 0) {
					paraMap.put("spCodeList", spCodeList);
					paraMap.put("month", salesTask.getMonth());
					// 1.2得到各个服务商的7个产品销售数量和任务数
					salesList = salesTaskMapper.getDetailMessage(paraMap);
					// 1.3计算
					for (SalesTaskCom salesTemp : salesList) {
						MonthSalesTask monthSalesTask = new MonthSalesTask();
						SalesTaskCom sales = new SalesTaskCom();
						BeanUtils.copyProperties(sales, salesTemp);
						monthSalesTask.setManager(sales.getSpName());
						// 7个产品销售数量 任务数和 将任务数赋值到sales中 7个产品和
						computeMonthSales(sales, monthSalesTask);
						// 把详细表数据也计算出来 详细列表的数据 ********
						List<MonthSalesTaskDetail> monthSalesTaskDetailList = operateProsRatio(sales);
						monthSalesTask.setMonthSalesTaskDetailList(monthSalesTaskDetailList);
						// 各个服务商的数据放到list集合中
						spInfoList.add(monthSalesTask);
					}
				}
			}

			// 2.1 大区经理 区域经理的数据 各个服务商的销量和 任务数和
			int totalTaskNum = 0;
			int totalSalesNum = 0;
			for (MonthSalesTask monthSalesTask : spInfoList) {
				totalTaskNum += monthSalesTask.getTotalTaskNum();
				totalSalesNum += monthSalesTask.getTotalSaleNum();
			}
			MonthSalesTask monthSalesTask = new MonthSalesTask();
			if (type.equals("manager")) {
				monthSalesTask.setManager(salesTask.getRegionalManager());
			} else {
				monthSalesTask.setManager(salesTask.getRegionalManagerLeader());
			}
			monthSalesTask.setTotalSaleNum(totalSalesNum);
			monthSalesTask.setTotalTaskNum(totalTaskNum);
			monthSalesTask.setTotalRatio(computedRatio(totalTaskNum, totalSalesNum));
			
			List<MonthSalesTaskDetail> monthSalesTaskDetailList = new ArrayList<>();

			// 计算大区经理 区域经理 对应的详情表数据********
			SalesTaskCom salesTaskDetail = new SalesTaskCom();
			if(salesList != null && salesList.size()>0){
				BeanUtils.copyProperties(salesTaskDetail, salesList.get(0));
				for (int i = 1; i < salesList.size(); i++) {
					operateProducts(salesTaskDetail, salesList.get(i));
				}
				monthSalesTaskDetailList = operateProsRatio(salesTaskDetail);
			}
			monthSalesTask.setMonthSalesTaskDetailList(monthSalesTaskDetailList);
			// 2.2加到list集合
			resList.add(monthSalesTask);

			if (salesTask.getType().equals("managerLeader")) {
				// 3.2大区经理下的区域计算
				for (Entry<String, SalesTaskCom> entry : areaMap.entrySet()) {
					SalesTaskCom areaSalesTask = entry.getValue();
					String area = entry.getKey();
					MonthSalesTask monthSalesTaskArea = new MonthSalesTask();
					monthSalesTaskArea.setManager(area);
					computeMonthSales(areaSalesTask, monthSalesTaskArea);
					// 把详细表数据也计算出来 详细列表的数据 ********
					monthSalesTaskDetailList = operateProsRatio(areaSalesTask);
					monthSalesTaskArea.setMonthSalesTaskDetailList(monthSalesTaskDetailList);
					resList.add(monthSalesTaskArea);
				}
			}

			// 4将服务商的计算信息保存到leaderList中
			for (MonthSalesTask spInfo : spInfoList) {
				resList.add(spInfo);
			}
		}

		return resList;
	}

	/*
	 * public List<MonthSalesTask> getManagerDetailMessage(SalesTask salesTask){
	 * List<MonthSalesTask> managerList = new ArrayList<>();
	 * List<MonthSalesTask> spInfoList = new ArrayList<MonthSalesTask>();
	 * Map<String,Object> paraMap = new HashMap<>(); //1.1得到区域经理对应的spCodeList
	 * List<String> spCodeList = salesTaskMapper.getSpCodeList(salesTask);
	 * //1.2得到区域经理对应服务商的的销量 任务数 paraMap.put("spCodeList", spCodeList);
	 * paraMap.put("month", month); List<SalesTask> salesList =
	 * salesTaskMapper.getDetailMessage(paraMap); //1.3计算 for (SalesTask sales :
	 * salesList) { MonthSalesTask monthSalesTask = new MonthSalesTask();
	 * monthSalesTask.setManager(sales.getSpName()); //7个产品销售数量 任务数和
	 * 将任务数赋值到sales中 7个产品和 computeMonthSales(sales,monthSalesTask);
	 * //各个服务商的数据放到list集合中 spInfoList.add(monthSalesTask); }
	 * 
	 * //2.1区域经理的数据 各个服务商的销量和 任务数和 int totalTaskNum = 0; int totalSalesNum = 0;
	 * for (MonthSalesTask monthSalesTask : spInfoList) { totalTaskNum +=
	 * monthSalesTask.getTotalTaskNum(); totalSalesNum +=
	 * monthSalesTask.getTotalSaleNum(); } MonthSalesTask monthSalesTask = new
	 * MonthSalesTask();
	 * monthSalesTask.setManager(salesTask.getRegionalManagerLeader());
	 * monthSalesTask.setTotalSaleNum(totalSalesNum);
	 * monthSalesTask.setTotalTaskNum(totalTaskNum);
	 * monthSalesTask.setTotalRatio(totalSalesNum / totalTaskNum * 100 + "%");
	 * //2.2加到list集合 managerList.add(monthSalesTask);
	 * 
	 * //3将服务商添加到managerList for (MonthSalesTask spInfo : spInfoList) {
	 * managerList.add(spInfo); }
	 * 
	 * return managerList; }
	 */

	/*
	 * public List<MonthSalesTaskDetail> getDetailMessage(SalesTask salesTask){
	 * List<MonthSalesTaskDetail> MonthSalesTaskDetailList = new ArrayList<>();
	 * List<String> spCodeList = new ArrayList<>(); String type =
	 * salesTask.getType(); String name = salesTask.getName();
	 * 
	 * Map<String,Object> paraMap = new HashMap<>();
	 * 
	 * if(type.equals("sp")){ spCodeList.add(name); }else{ spCodeList =
	 * salesTaskMapper.getSpCodeList(salesTask); }
	 * 
	 * paraMap.put("spCodeList", spCodeList); paraMap.put("month", month);
	 * 
	 * //1.2得到各个服务商的7个产品销售数量和任务数 List<SalesTask> salesList =
	 * salesTaskMapper.getDetailMessage(paraMap); if(salesList.size() > 0){
	 * if(type.equals("sp")){ //一个商户只对应一个 SalesTask salesAndTask =
	 * salesList.get(0);
	 * //operateProsRatio(salesAndTask,MonthSalesTaskDetailList); }else{
	 * 
	 * } }
	 * 
	 * return MonthSalesTaskDetailList; }
	 */

	public List<String> getSpCodeList(SalesTaskCom salesTask) {
		return salesTaskMapper.getSpCodeList(salesTask);
	}

	private void computeMonthSales(SalesTaskCom salesTask, MonthSalesTask monthSalesTask) {
		int totalSalesNum = 0;
		int totalTaskNum = 0;

		if (salesTask.getSalesGlDvd() != null) {
			totalSalesNum += salesTask.getSalesGlDvd();
		}
		if (salesTask.getSalesGlGps() != null) {
			totalSalesNum += salesTask.getSalesGlGps();
		}
		if (salesTask.getSalesYunjing() != null) {
			totalSalesNum += salesTask.getSalesYunjing();
		}
		if (salesTask.getSalesWirelessCharge() != null) {
			totalSalesNum += salesTask.getSalesWirelessCharge();
		}
		if (salesTask.getSalesDidihuService() != null) {
			totalSalesNum += salesTask.getSalesDidihuService();
		}
		if (salesTask.getSalesRearview() != null) {
			totalSalesNum += salesTask.getSalesRearview();
		}
		if (salesTask.getSalesFunctionRearview() != null) {
			totalSalesNum += salesTask.getSalesFunctionRearview();
		}

		if (salesTask.getTasknumDvd() != null) {
			totalTaskNum += salesTask.getTasknumDvd();
		}
		if (salesTask.getTasknumGps() != null) {
			totalTaskNum += salesTask.getTasknumGps();
		}
		if (salesTask.getTasknumYunjing() != null) {
			totalTaskNum += salesTask.getTasknumYunjing();
		}
		if (salesTask.getTasknumWirelessCharge() != null) {
			totalTaskNum += salesTask.getTasknumWirelessCharge();
		}
		if (salesTask.getTasknumDidihuService() != null) {
			totalTaskNum += salesTask.getTasknumDidihuService();
		}
		if (salesTask.getTasknumRearview() != null) {
			totalTaskNum += salesTask.getTasknumRearview();
		}
		if (salesTask.getTasknumFunctionRearview() != null) {
			totalTaskNum += salesTask.getTasknumFunctionRearview();
		}
		monthSalesTask.setTotalSaleNum(totalSalesNum);
		monthSalesTask.setTotalTaskNum(totalTaskNum);
		monthSalesTask.setTotalRatio(computedRatio(totalTaskNum, totalSalesNum));

	}

	private void operateProducts(SalesTaskCom salesTaskBefore, SalesTaskCom salesTask) {
		salesTaskBefore.setSalesGlDvd(salesTaskBefore.getSalesGlDvd() + salesTask.getSalesGlDvd());
		salesTaskBefore.setSalesGlGps(salesTaskBefore.getSalesGlGps() + salesTask.getSalesGlGps());
		salesTaskBefore.setSalesYunjing(salesTaskBefore.getSalesYunjing() + salesTask.getSalesYunjing());
		salesTaskBefore
				.setSalesWirelessCharge(salesTaskBefore.getSalesWirelessCharge() + salesTask.getSalesWirelessCharge());
		salesTaskBefore
				.setSalesDidihuService(salesTaskBefore.getSalesDidihuService() + salesTask.getSalesDidihuService());
		salesTaskBefore.setSalesRearview(salesTaskBefore.getSalesRearview() + salesTask.getSalesRearview());
		salesTaskBefore.setSalesFunctionRearview(
				salesTaskBefore.getSalesFunctionRearview() + salesTask.getSalesFunctionRearview());

		salesTaskBefore.setTasknumDvd(salesTaskBefore.getTasknumDvd() + salesTask.getTasknumDvd());
		salesTaskBefore.setTasknumGps(salesTaskBefore.getTasknumGps() + salesTask.getTasknumGps());
		salesTaskBefore.setTasknumYunjing(salesTaskBefore.getTasknumYunjing() + salesTask.getTasknumYunjing());
		salesTaskBefore.setTasknumWirelessCharge(
				salesTaskBefore.getTasknumWirelessCharge() + salesTask.getTasknumWirelessCharge());
		salesTaskBefore.setTasknumDidihuService(
				salesTaskBefore.getTasknumDidihuService() + salesTask.getTasknumDidihuService());
		salesTaskBefore.setTasknumRearview(salesTaskBefore.getTasknumRearview() + salesTask.getTasknumRearview());
		salesTaskBefore.setTasknumFunctionRearview(
				salesTaskBefore.getTasknumFunctionRearview() + salesTask.getTasknumFunctionRearview());
		
	}

	private List<MonthSalesTaskDetail> operateProsRatio(SalesTaskCom salesTask) {
		List<MonthSalesTaskDetail> MonthSalesTaskDetailList = new ArrayList<>();
		salesTask.setRatioGlDvd(devide(salesTask.getSalesGlDvd(), salesTask.getTasknumDvd()));
		salesTask.setRatioGlGps(devide(salesTask.getSalesGlGps(), salesTask.getTasknumGps()));
		salesTask.setRatioYunjing(devide(salesTask.getSalesYunjing(), salesTask.getTasknumYunjing()));
		salesTask.setRatioDidihuService(devide(salesTask.getSalesDidihuService(), salesTask.getTasknumDidihuService()));
		salesTask.setRatioWirelessCharge(
				devide(salesTask.getSalesWirelessCharge(), salesTask.getTasknumWirelessCharge()));
		salesTask.setRatioRearview(devide(salesTask.getSalesRearview(), salesTask.getTasknumRearview()));
		salesTask.setRatioFunctionRearview(
				devide(salesTask.getSalesFunctionRearview(), salesTask.getTasknumFunctionRearview()));

		MonthSalesTaskDetail pro1 = new MonthSalesTaskDetail();
		pro1.setTaskType("广联DVD");
		pro1.setSaleNum(salesTask.getSalesGlDvd());
		pro1.setTaskNum(salesTask.getTasknumDvd());
		pro1.setRatio(salesTask.getRatioGlDvd());
		MonthSalesTaskDetailList.add(pro1);

		MonthSalesTaskDetail pro2 = new MonthSalesTaskDetail();
		pro2.setTaskType("广联GPS");
		pro2.setSaleNum(salesTask.getSalesGlGps());
		pro2.setTaskNum(salesTask.getTasknumGps());
		pro2.setRatio(salesTask.getRatioGlGps());
		MonthSalesTaskDetailList.add(pro2);

		MonthSalesTaskDetail pro4 = new MonthSalesTaskDetail();
		pro4.setTaskType("无线车充");
		pro4.setSaleNum(salesTask.getSalesWirelessCharge());
		pro4.setTaskNum(salesTask.getTasknumWirelessCharge());
		pro4.setRatio(salesTask.getRatioWirelessCharge());
		MonthSalesTaskDetailList.add(pro4);

		MonthSalesTaskDetail pro3 = new MonthSalesTaskDetail();
		pro3.setTaskType("嘀嘀虎智能云镜");
		pro3.setSaleNum(salesTask.getSalesYunjing());
		pro3.setTaskNum(salesTask.getTasknumYunjing());
		pro3.setRatio(salesTask.getRatioYunjing());
		MonthSalesTaskDetailList.add(pro3);

		MonthSalesTaskDetail pro5 = new MonthSalesTaskDetail();
		pro5.setTaskType("嘀嘀虎车联网服务激活卡-1年版");
		pro5.setSaleNum(salesTask.getSalesDidihuService());
		pro5.setTaskNum(salesTask.getTasknumDidihuService());
		pro5.setRatio(salesTask.getRatioDidihuService());
		MonthSalesTaskDetailList.add(pro5);

		MonthSalesTaskDetail pro6 = new MonthSalesTaskDetail();
		pro6.setTaskType("纯流媒体后视镜");
		pro6.setSaleNum(salesTask.getSalesRearview());
		pro6.setTaskNum(salesTask.getTasknumRearview());
		pro6.setRatio(salesTask.getRatioRearview());
		MonthSalesTaskDetailList.add(pro6);

		MonthSalesTaskDetail pro7 = new MonthSalesTaskDetail();
		pro7.setTaskType("全功能_流媒体后视镜");
		pro7.setSaleNum(salesTask.getSalesFunctionRearview());
		pro7.setTaskNum(salesTask.getTasknumFunctionRearview());
		pro7.setRatio(salesTask.getRatioFunctionRearview());
		MonthSalesTaskDetailList.add(pro7);

		MonthSalesTaskDetail total = new MonthSalesTaskDetail();
		total.setTaskType("合计");

		MonthSalesTask monthSalesTask = new MonthSalesTask();
		computeMonthSales(salesTask, monthSalesTask);

		total.setSaleNum(monthSalesTask.getTotalSaleNum());
		total.setTaskNum(monthSalesTask.getTotalTaskNum());
		total.setRatio(monthSalesTask.getTotalRatio());
		MonthSalesTaskDetailList.add(total);

		return MonthSalesTaskDetailList;
	}

	private String devide(int sale, int task) {
		String res = "";
		if (task != 0) {
			res = Math.round((sale * 100.0 / task) * 100) / 100.0 + "%";
		}
		return res;

	}

	private String computedRatio(int task, int sale) {
		String res = Math.round((sale * 100.0 / task) * 100) / 100.0 + "%";
		return res;
	}

	// 判断这个月的数据是否存在
	public Integer isThisMonthDataExit(String month) {
		Integer exit = 0;
		List<SalesTask> salesTaskList = salesTaskMapper.getSalesTaskList(month);
		if (null != salesTaskList && salesTaskList.size() != 0) {
			exit = 1;
		}
		return exit;
	}

	public void deleteByMonth(String month) {
		salesTaskMapper.deleteByMonth(month);
	}

	public List<ResponseResult<SalesTask>> judgeTaskIsCorrect(List<Object> objectList) {
		List<ResponseResult<SalesTask>> result = new ArrayList<ResponseResult<SalesTask>>();
		Set<String> wholeNameList = new HashSet<String>();
		int correct = 1;// 1:正确，0：存在错误,2:一直都最后都是空的
		for (Object object : objectList) {
			SalesTask task = (SalesTask) object;

			// 1.若商户名和区域都不存在，这条数据作废
			// 3.若存在商户名和区域都一样的情况，则为重复
			String wholeName = task.getArea() + "-" + task.getSpName();
			if (wholeName.equals("-")) {
				correct = 2;
				LOGGER.info("taskInvalid,商户名和区域都不存在，这条数据作废");
				continue;
			} else if (wholeNameList.contains(wholeName)) {
				correct = 0;
				result.add(new ResponseResult<SalesTask>(task, false, "taskHasRepeat", "存在任务商户名和区域都一样的情况，请修改"));
			} else {
				wholeNameList.add(wholeName);
			}

			// 1.任务数不能为负数与小数
			Boolean tasknumDvd = task.getTasknumDvd().matches("[0-9]+.0|0.0");
			Boolean tasknumGps = task.getTasknumGps().matches("[0-9]+.0|0.0");
			Boolean tasknumYunjing = task.getTasknumYunjing().matches("[0-9]+.0|0.0");
			Boolean tasknumDidihuService = task.getTasknumDidihuService().matches("[0-9]+.0|0.0");
			Boolean tasknumWirelessCharge = task.getTasknumWirelessCharge().matches("[0-9]+.0|0.0");
			Boolean tasknumRearview = task.getTasknumRearview().matches("[0-9]+.0|0.0");
			Boolean tasknumFunctionRearview = task.getTasknumFunctionRearview().matches("[0-9]+.0|0.0");

			if (!tasknumDvd || !tasknumGps || !tasknumYunjing || !tasknumDidihuService || !tasknumWirelessCharge
					|| !tasknumRearview || !tasknumFunctionRearview) {
				correct = 0;
				result.add(new ResponseResult<SalesTask>(task, false, "taskHasNegative", "存在任务数为负数或小数或空数据，请修改"));
			}

			// 2.但任务数若全为0，则这个任务无效
			Boolean zeroDvd = task.getTasknumDvd().matches("0.0");
			Boolean zeroGps = task.getTasknumGps().matches("0.0");
			Boolean zeroYunjing = task.getTasknumYunjing().matches("0.0");
			Boolean zeroDidihuService = task.getTasknumDidihuService().matches("0.0");
			Boolean zeroWirelessCharge = task.getTasknumWirelessCharge().matches("0.0");
			Boolean zeroRearview = task.getTasknumRearview().matches("0.0");
			Boolean zeroFunctionRearview = task.getTasknumFunctionRearview().matches("0.0");
			if (zeroDvd && zeroGps && zeroYunjing && zeroDidihuService && zeroWirelessCharge && zeroRearview
					&& zeroFunctionRearview) {
				correct = 0;
				result.add(new ResponseResult<SalesTask>(task, false, "taskAllZero", "任务数全为0，请修改"));
			}
			// 3.商户不存在的数据
			String spCode = salesTaskMapper.getCodeBySpName(task.getSpName());
			if (spCode == null || spCode.equals("")) {
				result.add(new ResponseResult<SalesTask>(task, false, "spNotExist", "这个服务商不存在，请修改"));
			}
		}
		// 一般若存在空行的话，都在最下面
		if (correct == 2) {
			SalesTask task = new SalesTask();
			task.setSpName("");
			result.add(new ResponseResult<SalesTask>(task, false, "taskHasNull",
					"excel表存在数据空行/商户与区域皆空数据，建议修改或删除该行而不是清除内容"));
		}
		return result;
	}

}
