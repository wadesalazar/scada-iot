package com.iot.storm.scheme;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class StormIotScheme  implements Scheme {

	private static final long serialVersionUID = 1L;

	public static final String IN_MSG = "inMsg";	
	public static final String IOT_DATA = "iotData";

	private static final Logger LOG = Logger.getLogger(StormIotScheme.class);

	public List<Object> deserialize(byte[] bytes) {
		
		LOG.debug("------->>>>>>>>Begin StormScheme");
			String inMsg = null;
			try {
				inMsg = new String(bytes, "UTF-8");
				System.out.println("inMsg--------------->>>>>>>>>>>>>>>>"+inMsg);
				LOG.info("inMsg--------------->>>>>>>>>>>>>>>>"+inMsg);
			} catch (UnsupportedEncodingException e) {				
				e.printStackTrace();
			}
			
			LOG.debug("------->>>>>>>>Returning StormScheme");
			
			return new Values(inMsg);		
	}

	public Fields getOutputFields() {
		return new Fields(IN_MSG);
	}

}