package com.iot.dao;

import org.apache.hadoop.hbase.util.Bytes;

public class IotMetadata {
	
	//HBase table name
	public static final String IOT_DATA_TABLE = "iot_demo";
	
	//HBase column family of the table
	public static final byte[] CF_IOT_DATA = Bytes.toBytes("I");

}
