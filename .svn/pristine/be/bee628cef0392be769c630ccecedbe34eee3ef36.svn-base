package com.glsx.oms.bigdata.admin.bma.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResponseResult<T>  implements Serializable{
	
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public boolean isSucess() {
		return sucess;
	}
	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public ResponseResult(){};
	
	public ResponseResult(T result,boolean isSucess,String errorCode,String errorMsg){
		this.errorCode = errorCode;
		this.result = result;
		this.sucess = isSucess;
		this.errorMsg = errorMsg;
	};
	private T result;
	private boolean sucess;
	private String errorCode;
	private String errorMsg;
	@Override
	public String toString() {
		return "ResponseResult [result=" + result + ", sucess=" + sucess
				+ ", errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
	}
	
	
}

