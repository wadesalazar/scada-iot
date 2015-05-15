package com.iot.dao;

import java.io.IOException;
import java.text.ParseException;

import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import com.iot.model.IotData;


public class IotDataDao {
	
	private HTableInterface hTableInterface = null;
	private int cnt = 0;
    private final int flushfrequency = 10;
	
	public IotDataDao(){
		try {
			hTableInterface = HBaseConnection.getInstance(IotMetadata.IOT_DATA_TABLE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] putIotData(IotData iotData) throws ParseException, IOException {
		
		byte[] rowKey = constructRowKey(iotData);
		Put put = new Put(rowKey);
		
		byte[] columnQualifier = constructColumnQualifier(iotData);
		
		put.add(IotMetadata.CF_IOT_DATA, columnQualifier, iotData.getServerReportTimestamp(), Bytes.toBytes(iotData.getTagValue()));
		
		hTableInterface.put(put);
		
		if (cnt++ % flushfrequency == 0 ) {
			hTableInterface.flushCommits();
        }

		hTableInterface.close();
		
		return rowKey;
	}
	
	
	private byte[] constructRowKey(IotData iotData){
		
		//String rowKey = iotData.getEquipmentId()+"|"+iotData.getTimeStamp();
		String rowKey = iotData.getServerName()+"|"+iotData.getServerIp()+"|"+iotData.getTopic()+"|"+iotData.getAcquistionTimestamp();
		
		return Bytes.toBytes(rowKey);
		
	}
	
	private byte[] constructColumnQualifier(IotData iotData){
		
		String columnQualifier = iotData.getTagName()+"_"+iotData.getTagDataType()+"_"+iotData.getQuality();
		
		return Bytes.toBytes(columnQualifier);
	}

}
