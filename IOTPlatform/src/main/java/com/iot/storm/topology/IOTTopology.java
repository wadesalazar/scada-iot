package com.iot.storm.topology;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

import com.iot.storm.bolt.BaseIotBolt;
import com.iot.storm.bolt.HBaseBolt;
import com.iot.storm.scheme.StormIotScheme;

public class IOTTopology extends BaseTopology{
	
	private static final Logger LOG = LoggerFactory.getLogger(IOTTopology.class);

	private static final String KAFKA_SPOUT_ID = "IOT Kafka Spout";
	private static final String HBASE_BOLT_ID = "HBase Bolt";
	private static final String BASE_BOLT_ID = "Base Bolt";
		
	public IOTTopology(String configFileName) throws Exception {
		super(configFileName);
	}

	public static void main(String[] args) throws Exception {
		
		String configFileName = "iot-storm.properties"; 
		IOTTopology iotTopology = new IOTTopology(configFileName);	
		
		if (args != null && args.length > 0) {
			iotTopology.buildAndSubmitTopology(args[0]);
		} else {
			iotTopology.buildAndSubmitTopology(null);
		}
		
	}
	
	private void buildAndSubmitTopology(String topologyName) throws Exception{
		
		TopologyBuilder topologyBuilder = new TopologyBuilder();
		
		//building KafkaSpout and setting it to TopologyBuilder
		configureKafkaSpout(topologyBuilder);
		
		//building BaseIotBolt and setting it to TopologyBuilder
		configureBaseIotBolt(topologyBuilder);
		
		//building HBaseBolt and setting it to TopologyBuilder
		configureHBaseBolt(topologyBuilder);
		
		Config conf = new Config();
		conf.setDebug(false);

		if (StringUtils.isNotBlank(topologyName)) {
			conf.setNumWorkers(6);
			StormSubmitter.submitTopologyWithProgressBar(topologyName, conf, topologyBuilder.createTopology());
		} else {
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("test-iot_toplogy", conf,
					topologyBuilder.createTopology());
			Utils.sleep(10000);
			cluster.killTopology("test-iot_toplogy");
			cluster.shutdown();
		}
	}
	
	private void configureKafkaSpout(TopologyBuilder topologyBuilder){
		
		SpoutConfig spoutConfig = constructSpoutConfig();		
		KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);		
		topologyBuilder.setSpout(KAFKA_SPOUT_ID, kafkaSpout, 10);
	}
	
	private SpoutConfig constructSpoutConfig(){
		BrokerHosts hosts = new ZkHosts(topologyConfig.getProperty("kafka.zookeeper.host.port"));
		String kafkaTopicName = topologyConfig.getProperty("kafka.topic");
		String zkRootPath = topologyConfig.getProperty("kafka.zkRoot");
		String consumerGroupId = "StormSpout";

		SpoutConfig spoutConfig = new SpoutConfig(hosts, kafkaTopicName, zkRootPath, consumerGroupId);

		spoutConfig.scheme = new SchemeAsMultiScheme(new StormIotScheme());		
		spoutConfig.forceFromStart = true;
		
		return spoutConfig;
	}
	
	private void configureBaseIotBolt(TopologyBuilder topologyBuilder){
		BaseIotBolt baseIotBolt = new BaseIotBolt();
		topologyBuilder.setBolt(BASE_BOLT_ID, baseIotBolt, 12).shuffleGrouping(KAFKA_SPOUT_ID);
	}
	
	private void configureHBaseBolt(TopologyBuilder topologyBuilder){
		HBaseBolt hbaseBolt = new HBaseBolt();
		topologyBuilder.setBolt(HBASE_BOLT_ID, hbaseBolt, 12).shuffleGrouping(BASE_BOLT_ID);
	}
}
