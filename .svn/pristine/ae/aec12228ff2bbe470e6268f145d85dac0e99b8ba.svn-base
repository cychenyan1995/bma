package com.glsx.oms.bigdata.admin.bma.framework;


import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("importExcel")

public class ImportExcelProperty {

	/**
	 * 上传路径
	 */
	private String uploadPath;

    /**
     * 文件服务器域名
     */
    private String domain;

	/**
	 * 导出路径
	 */
	private String download;
	
	/**
	 * 消息推送链接
	 */
	private String sendUrl;
	
	private String testSps;

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getSendUrl() {
		return sendUrl;
	}

	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl;
	}

	public String getTestSps() {
		return testSps;
	}

	public void setTestSps(String testSps) {
		this.testSps = testSps;
	}
	
	
}
