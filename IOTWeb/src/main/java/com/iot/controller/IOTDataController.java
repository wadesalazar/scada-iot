package com.iot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.model.IOTData;
import com.iot.model.XYData;
import com.iot.service.GraphService;

@Controller
public class IOTDataController {
	
	@RequestMapping("/json/iotData")
    public @ResponseBody String getIotData(){
		
		System.out.println("-------->>>>>>>>> in controller getIotData");
        /*Person person = new Person();
        person.setId(1);
        person.setName("hmk");*/
        return "hello";
    }
	
	@RequestMapping("/json/iotDataList")  	 
	 public @ResponseBody List<IOTData> getIotDataList(@RequestParam String name, @RequestParam long time) {  		 
		 System.out.println("-------->>>>>>>>> in controller getIotDataList");
		 System.out.println("name-------->>>>>>>>>"+name);
		 System.out.println("time-------->>>>>>>>>"+time);
	  List<IOTData> iotDataList = new ArrayList<IOTData>();  
	  XYData XYData = null;
	  try{
		  
		  long endRangeTimeStamp = new Date().getTime();
		  if(time != 0){
			  endRangeTimeStamp = time;
		  }
		  
		  //HBase code start
		  System.out.println("<<<<<<<<<<<<---------Begin HBase Logic-------->>>>>>>>>");
		  GraphService graphService = new GraphService();
		  
		  final String serverName = "Kepware.KEPServerEX.V5";
		  final String serverIp = "opc.tcp://winclienthadoop:49320";
		  final String topic = "Simulation Examples.Functions";
		  
		  //Ramp1 Data update begin
		  List<XYData> ramp1List = graphService.getIotData(serverName, serverIp, topic, "Ramp1", endRangeTimeStamp);		  
		  //Ramp1 Data update end
		  
		  //Ramp2 Data update begin
		  List<XYData> ramp2List = graphService.getIotData(serverName, serverIp, topic, "Ramp2", endRangeTimeStamp);		  
		  //Ramp2 Data update end
		  
		  //Ramp3 Data update begin
		  List<XYData> ramp3List = graphService.getIotData(serverName, serverIp, topic, "Ramp3", endRangeTimeStamp);		  
		  //Ramp3 Data update end
		  
		  //Ramp4 Data update begin
		  List<XYData> ramp4List = graphService.getIotData(serverName, serverIp, topic, "Ramp4", endRangeTimeStamp);		 
		  //Ramp4 Data update end
		  
		  System.out.println("<<<<<<<<<<<<---------End HBase Logic-------->>>>>>>>>");
		  		  
		  //HBase code end 
		  
		  /*// Random data from java begin
		  
		  Random random = new Random();
		    
		  List<XYData> testDataList = new ArrayList<XYData>();
		  testDataList.add(new XYData(1, random.nextInt(60 - 40 + 1) + 40));  
		  testDataList.add(new XYData(2, random.nextInt(60 - 40 + 1) + 40)); 
		  testDataList.add(new XYData(3, random.nextInt(60 - 40 + 1) + 40));  
		  testDataList.add(new XYData(4, random.nextInt(60 - 40 + 1) + 40)); 
		  testDataList.add(new XYData(5, random.nextInt(60 - 40 + 1) + 40)); 
		  testDataList.add(new XYData(6, random.nextInt(60 - 40 + 1) + 40));
		  testDataList.add(new XYData(7, random.nextInt(60 - 40 + 1) + 40));
		  testDataList.add(new XYData(8, random.nextInt(60 - 40 + 1) + 40));
		  testDataList.add(new XYData(9, random.nextInt(60 - 40 + 1) + 40));
		  testDataList.add(new XYData(10, random.nextInt(60 - 40 + 1) + 40));
		  
		  List<XYData> testDataList1 = new ArrayList<XYData>();
		  testDataList1.add(new XYData(1, random.nextInt(40 - 20 + 1) + 20));  
		  testDataList1.add(new XYData(2, random.nextInt(40 - 20 + 1) + 20));   
		  testDataList1.add(new XYData(3, random.nextInt(40 - 20 + 1) + 20));    
		  testDataList1.add(new XYData(4, random.nextInt(40 - 20 + 1) + 20));   
		  testDataList1.add(new XYData(5, random.nextInt(40 - 20 + 1) + 20));    
		  testDataList1.add(new XYData(6, random.nextInt(40 - 20 + 1) + 20));
		  testDataList1.add(new XYData(7, random.nextInt(40 - 20 + 1) + 20));
		  testDataList1.add(new XYData(8, random.nextInt(40 - 20 + 1) + 20));
		  testDataList1.add(new XYData(9, random.nextInt(40 - 20 + 1) + 20));
		  testDataList1.add(new XYData(10, random.nextInt(40 - 20 + 1) + 20));
		  
		  List<XYData> testDataList2 = new ArrayList<XYData>();
		  testDataList2.add(new XYData(1, random.nextInt(20 - 5 + 1) + 5));   
		  testDataList2.add(new XYData(2, random.nextInt(20 - 5 + 1) + 5)); 
		  testDataList2.add(new XYData(3, random.nextInt(20 - 5 + 1) + 5)); 
		  testDataList2.add(new XYData(4, random.nextInt(20 - 5 + 1) + 5));   
		  testDataList2.add(new XYData(5, random.nextInt(20 - 5 + 1) + 5));  
		  testDataList2.add(new XYData(6, random.nextInt(20 - 5 + 1) + 5));
		  testDataList2.add(new XYData(7, random.nextInt(20 - 5 + 1) + 5));
		  testDataList2.add(new XYData(8, random.nextInt(20 - 5 + 1) + 5));
		  testDataList2.add(new XYData(9, random.nextInt(20 - 5 + 1) + 5));
		  testDataList2.add(new XYData(10, random.nextInt(20 - 5 + 1) + 5));
		  
		  List<XYData> testDataList3 = new ArrayList<XYData>();
		  testDataList3.add(new XYData(1, random.nextInt(80 - 60 + 1) + 60));   
		  testDataList3.add(new XYData(2, random.nextInt(80 - 60 + 1) + 60)); 
		  testDataList3.add(new XYData(3, random.nextInt(80 - 60 + 1) + 60)); 
		  testDataList3.add(new XYData(4, random.nextInt(80 - 60 + 1) + 60));   
		  testDataList3.add(new XYData(5, random.nextInt(80 - 60 + 1) + 60));  
		  testDataList3.add(new XYData(6, random.nextInt(80 - 60 + 1) + 60));
		  testDataList3.add(new XYData(7, random.nextInt(80 - 60 + 1) + 60));
		  testDataList3.add(new XYData(8, random.nextInt(80 - 60 + 1) + 60));
		  testDataList3.add(new XYData(9, random.nextInt(80 - 60 + 1) + 60));
		  testDataList3.add(new XYData(10, random.nextInt(80 - 60 + 1) + 60));
		  
		  System.out.println("-------->>>>>>>>>end controller getIotDataList"+testDataList.size());
		  
		  		  
		  IOTData iotData1 = new IOTData();
		  iotData1.setXyDataList(testDataList);
		  iotData1.setTagName("Ramp1");
		  
		  IOTData iotData2 = new IOTData();
		  iotData2.setXyDataList(testDataList1);
		  iotData2.setTagName("Ramp2");
		  
		  IOTData iotData3 = new IOTData();
		  iotData3.setXyDataList(testDataList2);
		  iotData3.setTagName("Ramp3");
		  
		  IOTData iotData4 = new IOTData();
		  iotData4.setXyDataList(testDataList3);
		  iotData4.setTagName("Ramp4");
		  
		  //end */
		  
		  //hbase
		  IOTData iotData1 = new IOTData();
		  iotData1.setXyDataList(ramp1List);
		  iotData1.setTagName("Ramp1");
		  
		  IOTData iotData2 = new IOTData();
		  iotData2.setXyDataList(ramp2List);
		  iotData2.setTagName("Ramp2");
		  
		  IOTData iotData3 = new IOTData();
		  iotData3.setXyDataList(ramp3List);
		  iotData3.setTagName("Ramp3");
		  
		  IOTData iotData4 = new IOTData();
		  iotData4.setXyDataList(ramp4List);
		  iotData4.setTagName("Ramp4");
		  //hbase
	  
		  iotDataList.add(iotData1);
		  iotDataList.add(iotData2);
		  iotDataList.add(iotData3);
		  iotDataList.add(iotData4);
		  
	  }catch(Exception e){
		  e.printStackTrace();
	  }  
	  	return iotDataList;  
	 }  


}
