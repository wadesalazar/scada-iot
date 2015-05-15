package com.iot.service;

import java.util.List;

import com.iot.dao.IOTGraphDataDao;
import com.iot.model.XYData;

public class GraphService {

	
	public List<XYData> getIotData(String serverName, String serverIp, String topicName, String tagName, long endRangeTimeStamp){
		
		IOTGraphDataDao iotGraphDataDao = new IOTGraphDataDao();
		
		return iotGraphDataDao.getIotGraphData(serverName, serverIp, topicName, tagName, endRangeTimeStamp);
	}
}
