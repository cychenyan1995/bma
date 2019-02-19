package com.glsx.oms.bigdata.admin.bma.salesHistory.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.glsx.oms.bigdata.admin.bma.salesHistory.model.SalesHistory;

@Mapper
public interface SalesHistoryMapper {

	SalesHistory selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SalesHistory record);

	int updateByPrimaryKey(SalesHistory record);

	// 把tbl_sales_statement_monthly的数据查询出来
	public List<SalesHistory> selectAllSalesHistory(SalesHistory salesHistory);

	// 把tbl_sales_statement_monthly的数据跟据同一个月同一商户查询出来
	// 数据颗粒度只到商户这一层 不显示商户下面门店细分数据
	public List<SalesHistory> selectSpSalesHistory(SalesHistory salesHistory);

	// 只通过月份这个条件查询出信息
	public List<SalesHistory> selectSpSalesHistoryByMonth(SalesHistory salesHistory);

	List<SalesHistory> selectByArea();

	List<SalesHistory> selectByLeader();

	public List<SalesHistory> selectSumSalesByMonth(SalesHistory salesHistory);

	public List<SalesHistory> selectSumTaskByMonth(SalesHistory salesHistory);

	public List<SalesHistory> selectSalesBySp(SalesHistory salesHistory);

	public List<SalesHistory> selectTasksBySp(SalesHistory salesHistory);
}