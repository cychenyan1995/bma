package com.glsx.oms.bigdata.admin.bma.vo;

/**
 * 请求返回对象
 *
 * @Author yangbin
 * @Date 11:20 2018/7/31
 * @Version 1.0
 */
public class RespMessage {

    /**
     * 总页数
     */
    protected long pageTotal;
    /**
     * 返回码
     */
    protected int code;
    /**
     * 返回信息
     */
    protected String message;
    /**
     * 返回数据
     */
    protected Object data;
    /**
     * 发布状态
     */
    protected int publishStatus;
    /**
     * 
     * 当前页数
     */
    protected long pageNum;
    
    public long getPageNum() {
		return pageNum;
	}

	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;
	}

	public int getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(int publishStatus) {
		this.publishStatus = publishStatus;
	}

	public RespMessage() {
        this(null);
    }

    public RespMessage(Object data) {
        this(data, RespStatusEnum.SUCCESS);
    }

    public RespMessage(String data, RespStatusEnum status) {
        this.setCode(status.code());
        this.setMessage(status.message());
        this.setData(data);
    }

    public RespMessage(Object data, RespStatusEnum status) {
        this.setData(data);
        this.setCode(status.code());
        this.setMessage(status.message());
    }

    public RespMessage(int code, String error) {
        this.setCode(code);
        this.setMessage(error);
    }

    public void setStatus(RespStatusEnum status) {
        this.setCode(status.code());
        this.setMessage(status.message());
    }

    public long getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(long pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
