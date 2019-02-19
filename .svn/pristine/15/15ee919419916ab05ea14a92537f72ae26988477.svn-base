package com.glsx.oms.bigdata.admin.bma.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glsx.oms.bigdata.admin.bma.salesTask.model.SalesTask;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author yangbin
 * @Date 2018/10/29 11:23
 * @Version 1.0
 */
public class BaseController<T extends BasePojo> {

    /***
     * 结束时间加一天（sql用的between)
     * @Author yangbin
     * @Date 10:52 2018/10/29
     * @Param [firstActivePoint]
     * @Return void
     */
	
	private Map<String,SalesTask> taskInfoMap = new HashMap<String,SalesTask>();

	public Map<String,SalesTask> setTaskInfoDict(List<SalesTask> salesTaskList){
		taskInfoMap = new HashMap<String,SalesTask>();
		for (SalesTask salesTask : salesTaskList) {
			taskInfoMap.put(salesTask.getSpCode(), salesTask);
		}
		return taskInfoMap;
	}
	
	public Map<String,SalesTask> getTaskInfoDict(){
		return taskInfoMap;
	}
}
