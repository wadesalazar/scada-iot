package com.iot.storm.bolt;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.iot.model.IotData;
import com.iot.storm.scheme.StormIotScheme;
import com.iot.util.JsonToJavaUtil;


public class BaseIotBolt implements IRichBolt{

	private static final long serialVersionUID = -6115041482989864870L;
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseIotBolt.class);
	private static int baseBoltMsgCount = 0;
	
	
	private OutputCollector outputCollector;
	private IotData iotData = null;

	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.outputCollector = collector;
		
	}

	public void execute(Tuple inputTuple) {
		try{
			baseBoltMsgCount++;
			LOG.debug("<<<<<<<-----------Start BaseIotBolt.execute()----------------->>>>>>>>>>>>");
			System.out.println("inputTuple.getStringByField(StormIotScheme.IN_MSG)--------------->>>>>>>>>>>>>>>>"+inputTuple.getStringByField(StormIotScheme.IN_MSG));
			LOG.info("inputTuple.getStringByField(StormIotScheme.IN_MSG)--------------->>>>>>>>>>>>>>>>"+inputTuple.getStringByField(StormIotScheme.IN_MSG));
			iotData = JsonToJavaUtil.parseJsonToJava((inputTuple.getStringByField(StormIotScheme.IN_MSG)).getBytes());
			
			outputCollector.emit(inputTuple, new Values(iotData));
			outputCollector.ack(inputTuple);
			
			System.out.println("iotdata tostring-------------->>>>>>>>>>>>>>>>"+iotData.toString()+"-- counter"+baseBoltMsgCount);
			
			LOG.debug("<<<<<<<-----------End BaseIotBolt.execute()----------------->>>>>>>>>>>>");
		}catch(Exception e){
			e.printStackTrace();
		}		
	}

	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

	public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
		outputFieldsDeclarer.declare(new Fields(StormIotScheme.IOT_DATA));
		
	}

	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
