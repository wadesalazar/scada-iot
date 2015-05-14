package com.hashmap.ua.kafka;

import java.io.StringWriter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.hashmap.ua.Client.IOTMessage;

public class InputJsonParser {

	private static final Logger LOG = LoggerFactory
			.getLogger(InputJsonParser.class);


	public static IOTMessage parseJson(byte[] jsonAsByteArray) {
		try {

			LOG.info("Inside parseJson :" + jsonAsByteArray);

			// create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();
			
			objectMapper.readValue(jsonAsByteArray,IOTMessage.class);
			

			// convert json string to object
			IOTMessage iotData = objectMapper.readValue(
					jsonAsByteArray, IOTMessage.class);

			LOG.debug("Successfully parsed JSON binary to Java Obj: "
					+ iotData);

			return iotData;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	
	
	public static String getJson(IOTMessage iotMessage) {
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();		
		
		//configure Object mapper for pretty print
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        
        //writing to console, can write to any output stream such as file
        String jsonAsString = new String();
        
        try {
        	jsonAsString = objectMapper.writeValueAsString(iotMessage);
        	return jsonAsString;        	
        } catch (Exception ex) {
        	throw new RuntimeException(ex);
        }
	}
}
