package com.iot.storm.bolt;

import java.io.IOException;
import java.text.ParseException;
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

import com.iot.dao.IotDataDao;
import com.iot.model.IotData;
import com.iot.storm.scheme.StormIotScheme;

public class HBaseBolt implements IRichBolt {	
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(HBaseBolt.class);
	
	private OutputCollector outputCollector;
	private IotData iotData = null;
	private IotDataDao iotDataDao;

	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		iotDataDao = new IotDataDao();
		this.outputCollector = collector;
		
	}

	public void execute(Tuple inputTuple) {
		
		LOG.debug("<<<<<<<-----------Start HBaseBolt.execute()----------------->>>>>>>>>>>>");
		
		try {
			
			iotData = (IotData) inputTuple.getValueByField(StormIotScheme.IOT_DATA);
			
			//persist data to hbase table
			iotDataDao.putIotData(iotData);			
			
			outputCollector.emit(inputTuple, new Values(iotData));
			outputCollector.ack(inputTuple);
			
			LOG.debug("<<<<<<<-----------End HBaseBolt.execute()----------------->>>>>>>>>>>>");
			
		} catch (ParseException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
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
