package com.iot.dao;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;

public class HBaseConnection {

	private static HConnection connection = null;
	private static Object obj = new Object();

	private HBaseConnection() {
		synchronized (obj) {
			if (connection == null) {
				Configuration config = HBaseConfiguration.create();
				config.set("hbase.zookeeper.quorum", "dravidian1,hun1,hun2");
				config.set("hbase.zookeeper.property.clientPort","2181");
				config.set("zookeeper.znode.parent", "/hbase-unsecure");
				try {
					
					//HBaseAdmin.checkHBaseAvailable(config);
					connection = HConnectionManager.createConnection(config);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static HTableInterface getInstance(String tablename)
			throws IOException {
		if (connection == null) {
			synchronized (obj) {
				new HBaseConnection();
			}
		}
		HTableInterface table = connection.getTable(tablename);
		table.setAutoFlushTo(false);
		return table;
	}

	public static void main(String[] args) throws Exception {
		HBaseConnection.getInstance("iot_demo");
	}
}
