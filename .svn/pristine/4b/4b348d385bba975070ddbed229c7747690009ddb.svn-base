package com.glsx.oms.bigdata.admin.bma.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.oreframework.web.ui.Pagination;

import java.util.List;

/**
 * @ClassName BaseService
 * @Description TODO
 * @Author yangbin
 * @Date 2018/10/29 11:23
 * @Version 1.0
 */
public class BaseService<T> {

    private static final int pageSize = 10;

    public PageInfo<T> setPageInfo(Pagination pagination) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pagination.getCurrentPage());
        pageInfo.setPageSize(pagination.getPageSize());
        return pageInfo;
    }

    public Page<T> getPage(PageInfo<T> pageInfo) {
        return PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    public void setPageInfo(PageInfo<T> pageInfo, Page<T> page, List<T> list) {
        long count = page.getTotal();
        pageInfo.setList(list);
        pageInfo.setTotal(count);
    }
}
