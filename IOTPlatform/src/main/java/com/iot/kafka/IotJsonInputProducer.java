package com.iot.kafka;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iot.model.IotData;
import com.iot.util.JsonToJavaUtil;

public class IotJsonInputProducer {
	
	private static final Logger LOG = LoggerFactory.getLogger(IotJsonInputProducer.class);	
	
	private String jsonFile;
	private String brokerListString;
	private String topicString;
	private static int MAX_STREAMERS = 7;
	
	public IotJsonInputProducer(String jsonFile) {
		this.jsonFile = jsonFile;
	}
	
	public static void main(String[] args){
		try{
			LOG.info("START ------->>>>>>>>>> IotJsonInputProducer");

			IotJsonInputProducer producer = new IotJsonInputProducer(args[0]);
			producer.init();
			producer.publish();
			producer.waitForCompletion();
			
			LOG.info("End ------->>>>>>>>>> IotJsonInputProducer");
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public void init() throws Exception {
		Properties properties = new Properties();
		properties.load(IotJsonInputProducer.class.getClassLoader().getResourceAsStream("kafka-cluster.properties"));

		brokerListString = (String) properties.get("kafka.broker.list");
		topicString = (String) properties.get("kafka.topic.to.publish.to");
		
	}
	
	private Thread[] threads = new Thread[MAX_STREAMERS];
	
	public void publish() {
		for (int i = 0; i < MAX_STREAMERS; i++) {
			IotRunnable runnable = new IotRunnable(jsonFile, i + 1);
			threads[i] = new Thread(runnable);
			threads[i].start();
		}
	}
	
	private class IotRunnable implements Runnable{


		private String inputFileContainingJson;
		private int equipmentNumber;

		public IotRunnable(String inputFileContainingJson,
				int equipmentNumber) {
			this.inputFileContainingJson = inputFileContainingJson;
			this.equipmentNumber = equipmentNumber;
		}

		public void run() {			
			int shotNumber = 1;
			String jsonStream;

			try {
				jsonStream = IotJsonInputProducer.readFile(inputFileContainingJson, "UTF-8");
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}

			IotData iotData = JsonToJavaUtil.parseJsonToJava(jsonStream.getBytes());
		    
			Producer producer = getProducer();

			long counter = 1;
			while (true) {				
				
				/*iotData.setEquipmentId("E"+equipmentNumber);				
				
				// Random number between 1 and 10
			    int randomNum = new Random().nextInt((100 - 1) + 1) + 1;
			    
			    iotData.setTimeStamp(System.currentTimeMillis());
				
				
				 * Set the random values for tag name
				 
				iotData.setTagName("T"+randomNum);
				
				iotData.setValue(new Random().nextInt(100-10) + 10);	*/	    

				KeyedMessage<String, String> message = new KeyedMessage<String, String>(
						topicString, JsonToJavaUtil.parseJavaToJson(iotData).toString());
				producer.send(message);

				System.out.println("Message Sent # : " + counter++ + " "
						+ new Date());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	
	}
	
	static String readFile(String path, String string) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, string);
	}
	
	private Producer<String, String> getProducer() {
		Properties props = new Properties();
		props.put("metadata.broker.list", brokerListString);
		// props.put("zk.connect", zkCconnect);
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("request.required.acks", "0");

		props.put("producer.type", "async");
		// props.put("squeue.enqueue.timeout.ms", "-1");
		props.put("batch.num.messages", "1");
		// props.put("compression.codec", "1");

		ProducerConfig config = new ProducerConfig(props);
		Producer<String, String> producer = new Producer<String, String>(config);
		return producer;
	}
	
	public void waitForCompletion() {
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


}
