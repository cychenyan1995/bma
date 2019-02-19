package com.glsx.oms.bigdata.admin.bma.salesTask.controller;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.oreframework.web.ui.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.glsx.oms.bigdata.admin.bma.common.BaseController;
import com.glsx.oms.bigdata.admin.bma.framework.ImportExcelProperty;
import com.glsx.oms.bigdata.admin.bma.salesTask.model.MonthSalesTask;
import com.glsx.oms.bigdata.admin.bma.salesTask.model.SalesTask;
import com.glsx.oms.bigdata.admin.bma.salesTask.model.SalesTaskCom;
import com.glsx.oms.bigdata.admin.bma.salesTask.service.SalesService;
import com.glsx.oms.bigdata.admin.bma.system.bean.ImportedResult;
import com.glsx.oms.bigdata.admin.bma.util.ExcelUtils;
import com.glsx.oms.bigdata.admin.bma.vo.RespMessage;
import com.glsx.oms.bigdata.admin.bma.vo.RespStatusEnum;
import com.glsx.oms.bigdata.admin.bma.vo.ResponseResult;

import cn.com.glsx.club.user.model.User;
import cn.com.glsx.club.user.remote.UserRemote;
import cn.com.glsx.club.wechat.enums.WechatNoticeSetTypeEnum;
import cn.com.glsx.club.wechat.remote.WechatMessageRemote;
import cn.com.glsx.framework.core.beans.rpc.RpcResponse;

@RestController
@RequestMapping("/salesTask")

public class SalesTaskController extends BaseController<SalesTask> {

	private final static Logger LOGGER = LoggerFactory.getLogger(SalesTaskController.class);
	
	private SimpleDateFormat ft = new SimpleDateFormat("yyyy年MM月");
	private SimpleDateFormat ftd = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat ftt = new SimpleDateFormat("yyyy-MM");
	/**
	 * 静态变量配置类
	 */
	@Autowired
	private ImportExcelProperty importExcelProperty;

	@Resource
	private SalesService salesService;

	@Reference(timeout=30000)
	private WechatMessageRemote wechatMessageRemote;

	@Reference(timeout=30000)
	private UserRemote userRemote;

	@GetMapping("/getSalesTaskList")
	public RespMessage getSalesTaskList(SalesTask salesTask, Pagination pagination) {
		RespMessage rm = new RespMessage();
		PageInfo<SalesTask> page = salesService.getSalesTaskList(pagination);
		// 查询发布状态
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String month = ft.format(date);
		Integer publishStatus = salesService.selectPublishStatus(month);
		if (null != page) {
			rm.setData(page.getList());
			rm.setPageTotal(page.getTotal());
			if (publishStatus == null) {
				publishStatus = 0;
			}
			rm.setPublishStatus(publishStatus);
			rm.setPageNum(page.getPageNum());
			LOGGER.info("getSalesTaskList记录条数：" + page.getTotal());
		}
		return rm;
	}

	@PostMapping("/updateTask")
	public RespMessage updateSalesTask(@RequestBody SalesTaskCom salesTaskRow) {
		RespMessage rm = new RespMessage();
		int res = salesService.updateSalesTask(salesTaskRow);

		if (res <= 0) {
			rm.setStatus(RespStatusEnum.FAIL);
		} else {
			Pagination pagination = new Pagination();
			pagination.setCurrentPage(salesTaskRow.getCurrentPage());
			pagination.setPageSize(salesTaskRow.getPageSize());
			PageInfo<SalesTask> page = salesService.getSalesTaskList(pagination);
			if (null != page) {
				rm.setData(page.getList());
				rm.setPageTotal(page.getTotal());
				LOGGER.info("getSalesTaskList记录条数：" + page.getTotal());
			}
		}
		return rm;
	}

	// 只推送任务数 /send/sendMessage/:taskType/:time/:type/:name
	/* time要生成的月份的数据 推送的月份都是当前月 taskType === 1 显示完成度 taskType === 0 不显示完成度 */
	@GetMapping("/sendMessage")
	public RespMessage sendMessage() {
		RespMessage rm = new RespMessage();
		// 查询到所有的大区经理，区域经理，sp
		List<String> leaders = salesService.getAllManagerLeaderCode();
		List<String> managers = salesService.getAllManagerCode();
		List<String> sps = salesService.getAllSpCode();

		// 服务商的推送
		LOGGER.info("服务商调用消息推送接口....");
		operateSendMessage(sps,"sp");
		// 大区经理的推送
		LOGGER.info("大区经理调用消息推送接口....");
		operateSendMessage(leaders,"managerLeader");
		// 区域经理的推送
		LOGGER.info("区域经理调用消息推送接口....");
		operateSendMessage(managers,"manager");

		// 修改发布状态
		Date date = new Date();
		SimpleDateFormat ftt = new SimpleDateFormat("yyyy-MM");
		String time = ftt.format(date);
		int res = salesService.insertPublishStatus(time);
		if (res <= 0) {
			rm.setStatus(RespStatusEnum.FAIL);
			rm.setMessage("发布状态更新失败");
			LOGGER.info("发布状态更新失败");
		}
		return rm;
	}

	@GetMapping("/querySendInfo")
	public RespMessage getSendMessage(SalesTaskCom salesTask) throws IllegalAccessException, InvocationTargetException {
		RespMessage rm = new RespMessage();
		LOGGER.info("推送给" + salesTask.getType() + ":" + salesTask.getName()+"月份："+salesTask.getMonth());
		List<MonthSalesTask> salesSendData = new ArrayList<>();

		salesSendData = salesService.getSendMessage(salesTask);

		// 大区经理，区域经理查出对应的spCode 多个区域经理 模糊查询

		LOGGER.info("推送的报表数据记录数：" + salesSendData.size());
		rm.setData(salesSendData);
		return rm;
	}

	@PostMapping(value = "/importSalesTask", produces = { "application/json" })
	public RespMessage importSalesTask(@RequestParam("file") MultipartFile file) {
		RespMessage rm = new RespMessage();
		List<ImportedResult> importListRes = new ArrayList<ImportedResult>();
		ImportedResult importedResult = new ImportedResult();
		importedResult.setIsImported(0);
		//importedResult.setCause("导入成功");

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String month = ft.format(date);

		if (file != null) {
			try {
				String fileName = file.getOriginalFilename();

				// 判断是否有文件
				if (null != fileName && !"".equalsIgnoreCase(fileName.trim())
						&& (FilenameUtils.getExtension(fileName.trim()).equals("xls")
								|| FilenameUtils.getExtension(fileName.trim()).equals("xlsx"))) {

					// 可以选择把文件保存到服务器,创建输出文件对象
					String onlyName = fileName.replace("." + FilenameUtils.getExtension(fileName), "");
					String name = onlyName + "_" + UUID.randomUUID().toString() + "."
							+ FilenameUtils.getExtension(fileName);
					// 得到文件上传的URL
					String fileUrl = importExcelProperty.getUploadPath() + name;
					String downloadUrl = importExcelProperty.getDomain() + "upload/" + name;
					File outFile = new File(fileUrl);
					LOGGER.info("downloadUrl:" + downloadUrl);

					// 文件到输出文件对象
					FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);

					// 获得输入流
					InputStream input = null;
					input = file.getInputStream();

					List<Object> importList = null;
					try {
						// 读取基础数据
						LOGGER.info("读取基础数据..........");
						// 读excel数据
						importList = ExcelUtils.getInstance().readExcel2Objs(input, SalesTask.class, 0, 0);
						List<SalesTask> salesTaskList = new ArrayList<SalesTask>();
						if (importList != null && importList.size() > 0) {
							// 1.先检查excel中有没有错误的数据，若存在，输出错误的数据与原因，不导入数据，等待下一次数据全部成功后再导入
							List<ResponseResult<SalesTask>> judgeCorrect = salesService.judgeTaskIsCorrect(importList);
							//1.1 不存在不正确的数据 可以进行下一步判断
							if (judgeCorrect.size() == 0) {
								// 2.1若这个月已存在数据，先清除掉
								Integer exit = salesService.isThisMonthDataExit(month);
								if (exit == 1) {
									salesService.deleteByMonth(month);
									LOGGER.info(month + "月已存在的数据已删除完成");
								}
								// 2.2查出新的数据
								for (Object object : importList) {
									SalesTask salesTask = (SalesTask) object;
									String spCode = salesService.getCodeBySpName(salesTask.getSpName());
									//if(spCode != null && !spCode.equals("")){
									salesTask.setSpCode(spCode);
									salesTaskList.add(salesTask);
//									}else{
//										LOGGER.info(salesTask.getSpName()+"对应的spCode为空");
//									}
								}
								// 2.3批量将数据插入数据库
								salesService.insertAllSalesTask(salesTaskList);
								LOGGER.info("导入数据【" + salesTaskList.size() + "】条，全部成功！！！");
							}else{
								importedResult.setIsImported(4);
								rm.setMessage("导入失败");
								importedResult.setCause("存在错误信息，请修改后再导入");
								importedResult.setFailList(judgeCorrect);
								//3.导出错误信息及原因
								LOGGER.info(judgeCorrect.size() + "条数据错误信息");
							}
						}else{
							//导入数据为空
							LOGGER.error("导入的Excel表数据为空");
							importedResult.setIsImported(3);
							importedResult.setCause("导入的Excel表数据为空");
						}

					} catch (Exception e) {
						LOGGER.error("导入Excel数据失败:" + e);
						importedResult.setIsImported(2);
						importedResult.setCause("导入Excel数据失败");
					}
				} else {
					importedResult.setCause("上传文件只支持.xls与.xlsx格式，请另存为兼容格式Excel再上传");
					LOGGER.warn("上传文件不为.xls或.xlsx格式");
					importedResult.setIsImported(1);
					importListRes.add(importedResult);
					rm.setData(importListRes);
					return rm;
				}
				rm.setCode(1000);
			} catch (Exception e) {
				LOGGER.warn("catch an exception in importTasks", e);
			}
		} else {
			LOGGER.warn("file is null");
		}
		importListRes.add(importedResult);
		rm.setData(importListRes);
		return rm;
	}
	
	void operateSendMessage(List<String> mechartCodeist, String type){
		// 1.推送的模板
		Date date = new Date();
		String month = ft.format(date);
		String monthDay = ftd.format(date);
		String time = ftt.format(date);
		String first = "您好，您的" + month + "销售任务目标已生成";
		String keyword1 = month + "销售任务";
		String keyword2 = monthDay;
		String regex = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		
		if(null != mechartCodeist && mechartCodeist.size() > 0){
			LOGGER.info("要推送的消息数："+type+"个数" + mechartCodeist.size());
			for (int i=0;i<mechartCodeist.size();i++) {
				String mechartCode = mechartCodeist.get(i);
				LOGGER.info("商户"+i+":"+mechartCode);
				// 2.url
				String url = importExcelProperty.getSendUrl() + "/0/" + time +"/" + type +"/" + mechartCode;
				// 3.根据sp_code返回用户id
				if(mechartCode != null && ! mechartCode.equals("")){
					RpcResponse<User> user = null;
					if(Pattern.matches(regex, mechartCode)){
						LOGGER.info("商户"+i+":"+mechartCode+"是手机号");
						user = userRemote.getByPhone(mechartCode);
					}else{
						Integer spTemp = Integer.parseInt(mechartCode);
						user = userRemote.getMerchant(spTemp);
					}
					if (user.getResult() != null) {
						Integer userId = user.getResult().getId();
						// 23
						RpcResponse<Integer> result = wechatMessageRemote.sendMessage(WechatNoticeSetTypeEnum.GH_SALE_DETAIL,
								userId, url, first, keyword1, keyword2);
						LOGGER.info(mechartCode + "推送结果的状态：" + result.getResult() + "，消息：" + result.getMessage());
					} else {
						LOGGER.info(mechartCode+"对应的user为空");
						// rm.setStatus(RespStatusEnum.FAIL);
					}
				}else{
					LOGGER.info("spCode为空");
				}
			}
		}

	}

}
