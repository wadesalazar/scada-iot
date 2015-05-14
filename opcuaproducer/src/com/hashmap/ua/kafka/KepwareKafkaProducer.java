package com.hashmap.ua.kafka;

import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hashmap.ua.Client.IOTMessage;
import com.hashmap.ua.Client.KepwareConnectionClient;
import com.hashmap.ua.kafka.InputJsonParser;

public class KepwareKafkaProducer {
	
	private static final Logger log = LoggerFactory.getLogger(KepwareKafkaProducer.class);
	
	  public static void main(String args[]) {
		    try
		  	{
			    Properties properties = new Properties();
				properties.put("metadata.broker.list", "hun3.hashmap.com:6667");
				properties.put("serializer.class", "kafka.serializer.StringEncoder");
				properties.put("request.required.acks", "0");
				properties.put("producer.type", "async");
				properties.put("batch.num.message", "10");
				
				KepwareConnectionClient kepwareConnectionClient = new KepwareConnectionClient();
				boolean kafkaProduce = true;
				
				while(kafkaProduce) {
				List<IOTMessage> iotMessageList = kepwareConnectionClient.getIOTMessages();
				ProducerConfig producerConfig = new ProducerConfig(properties);
				Producer<String, String> producer = new Producer<String, String>(producerConfig);
							
				for(IOTMessage iotMessageObject:iotMessageList){
					KeyedMessage<String, String> keyedMessage = new KeyedMessage<String, String>("iot_new", InputJsonParser.getJson(iotMessageObject)
							.toString());
					log.info("IOT Message format is "+keyedMessage.toString());
					producer.send(keyedMessage);
				}
				producer.close();
				}
		  } catch(Exception e){
				e.printStackTrace();
			}
	  }	
}
