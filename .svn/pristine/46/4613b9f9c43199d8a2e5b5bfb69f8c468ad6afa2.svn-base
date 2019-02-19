package com.glsx.oms.bigdata.admin.bma.salesTask.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.glsx.oms.bigdata.admin.bma.salesTask.model.Manager;
import com.glsx.oms.bigdata.admin.bma.salesTask.model.SalesTask;
import com.glsx.oms.bigdata.admin.bma.salesTask.model.SalesTaskCom;

@Mapper
public interface SalesTaskMapper {
	
    List<SalesTask> getSalesTaskList(String month);
	
    int updateSalesTask(SalesTaskCom salesTask);
	 
    int deleteByPrimaryKey(Integer id);

    int insert(SalesTask record);

    int insertSelective(SalesTask record);

    SalesTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SalesTask record);

    int updateByPrimaryKey(SalesTask record);
    
    int insertAllSalesTask(List<SalesTask> salesTaskList);
    
    
    List<String> getAllManagerLeader(String month);

	List<String> getAllManager(String month);

	List<String> getAllSpCode(String month);
	
	List<Manager> getManagerCode();
	
	List<SalesTaskCom> getDetailMessage(Map<String,Object> paraMap);
	
	List<String> getSpCodeList(SalesTaskCom salesTask);
    public String getCodeBySpName(String spName);
    int insertPublishStatus(String month);
    Integer selectPublishStatus(String month);
    
    int deleteByMonth(String month);
    
}