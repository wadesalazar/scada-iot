package com.iot.model;

import java.util.Date;
import java.util.List;

public class IOTData {
	
	private String ServerName;
    private String ServerIP;
    private String topic;
    private String tagName;
    private String tagDataType;
    private String tagValue;
    private Date serverReportTimestamp;
    private Date acquistionTimestamp;
    private String quality;	
	private List<XYData> xyDataList;
	
	public String getServerName() {
		return ServerName;
	}

	public void setServerName(String serverName) {
		ServerName = serverName;
	}

	public String getServerIP() {
		return ServerIP;
	}

	public void setServerIP(String serverIP) {
		ServerIP = serverIP;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagDataType() {
		return tagDataType;
	}

	public void setTagDataType(String tagDataType) {
		this.tagDataType = tagDataType;
	}

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	public Date getServerReportTimestamp() {
		return serverReportTimestamp;
	}

	public void setServerReportTimestamp(Date serverReportTimestamp) {
		this.serverReportTimestamp = serverReportTimestamp;
	}

	public Date getAcquistionTimestamp() {
		return acquistionTimestamp;
	}

	public void setAcquistionTimestamp(Date acquistionTimestamp) {
		this.acquistionTimestamp = acquistionTimestamp;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public List<XYData> getXyDataList() {
		return xyDataList;
	}

	public void setXyDataList(List<XYData> xyDataList) {
		this.xyDataList = xyDataList;
	}
	
	

}
