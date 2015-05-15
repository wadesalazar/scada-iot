package com.iot.util;

import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.iot.model.IotData;
import com.iot.storm.scheme.StormIotScheme;


public class JsonToJavaUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(JsonToJavaUtil.class);
	
	public static IotData parseJsonToJava(byte[] jsonAsByteArray){
		
		try{
			
			LOG.debug("<<<<<<<<<--------Start parseJsonToJava--------->>>>>>>>>>>>>>");
			
			// create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();
			System.out.println("in json to java -------------->>>>>>>>>>>>>>>>");
			LOG.info("in json to java -------------->>>>>>>>>>>>>>>>");
			
			// convert json string to java object
			IotData iotData = objectMapper.readValue(jsonAsByteArray, IotData.class);
			
			System.out.println("in json to java ----end---------->>>>>>>>>>>>>>>>");
			LOG.info("in json to java ----end---------->>>>>>>>>>>>>>>>");

			LOG.debug("<<<<<<<<<--------End parseJsonToJava--------->>>>>>>>>>>>>>");

			return iotData;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}				
	}
	
	public static StringWriter parseJavaToJson(IotData iotData){
		
		//create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();		
		
		//configure Object mapper for pretty print
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        
        //writing to console, can write to any output stream such as file
        StringWriter jsonAsString = new StringWriter();
        
        try {
        	
        	objectMapper.writeValue(jsonAsString, iotData);        	
        	return jsonAsString;        	
        } catch (Exception ex) {
        	throw new RuntimeException(ex);
        }
	}

}
