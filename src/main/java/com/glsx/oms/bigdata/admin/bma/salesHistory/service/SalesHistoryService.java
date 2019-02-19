package com.glsx.oms.bigdata.admin.bma.salesHistory.service;

import java.util.List;

import javax.annotation.Resource;

import org.oreframework.web.ui.Pagination;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.glsx.oms.bigdata.admin.bma.common.BaseService;
import com.glsx.oms.bigdata.admin.bma.salesHistory.mapper.SalesHistoryMapper;
import com.glsx.oms.bigdata.admin.bma.salesHistory.model.SalesHistory;

@Service
public class SalesHistoryService extends BaseService<SalesHistory>{
	
	@Resource
	private SalesHistoryMapper salesHistoryMapper;
	
	public PageInfo<SalesHistory> getAllSalesHistory(Pagination pagination,SalesHistory salesHistory){
		PageInfo<SalesHistory> pageInfo = setPageInfo(pagination);
		Page<SalesHistory> page = getPage(pageInfo);
		List<SalesHistory> list = salesHistoryMapper.selectAllSalesHistory(salesHistory);
		setPageInfo(pageInfo, page, list);
		return pageInfo;
	}
	
	public PageInfo<SalesHistory> getSpSalesHistory(Pagination pagination,SalesHistory salesHistory){
		PageInfo<SalesHistory> pageInfo = setPageInfo(pagination);
		Page<SalesHistory> page = getPage(pageInfo);
		List<SalesHistory> list = salesHistoryMapper.selectSpSalesHistory(salesHistory);
		setPageInfo(pageInfo, page, list);
		return pageInfo;
	}
	
	public List<SalesHistory> getSpSalesHistoryByMonth(SalesHistory salesHistory){
		List<SalesHistory> list = salesHistoryMapper.selectSpSalesHistoryByMonth(salesHistory);
		return list;
	}
	
	public List<SalesHistory> getArea(){
		return salesHistoryMapper.selectByArea();
	}
	
	public List<SalesHistory> getLeader(){
		return salesHistoryMapper.selectByLeader();
	}
	
	public List<SalesHistory> getSumSalesByMonth(SalesHistory salesHistory){
		return salesHistoryMapper.selectSumSalesByMonth(salesHistory);
	}
	
	public List<SalesHistory> getSumTaskByMonth(SalesHistory salesHistory){
		return salesHistoryMapper.selectSumTaskByMonth(salesHistory);
	}
	
	public List<SalesHistory> getSalesBySp(SalesHistory salesHistory){
		return salesHistoryMapper.selectSalesBySp(salesHistory);
	}
	
	public List<SalesHistory> getTasksBySp(SalesHistory salesHistory){
		return salesHistoryMapper.selectTasksBySp(salesHistory);
	}

}
