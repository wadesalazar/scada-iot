package com.iot.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import com.iot.model.XYData;
import com.iot.util.IOTMetaData;

public class IOTGraphDataDao {
	
	private HTableInterface hTableInterface = null;
	private int cnt = 0;
    private final int flushfrequency = 10;
    
	public IOTGraphDataDao(){
		try {
			hTableInterface = HBaseConnection.getInstance(IOTMetaData.IOT_DATA_TABLE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<XYData> getIotGraphData(String serverName, String serverIp, String topicName, String tagName, long endRangeTimeStamp){		
		List<XYData> timeValueList = new ArrayList<XYData>();
		try{
			
			System.out.println("<<<<<<<<<<<<---------Inside getIotGraphData-------->>>>>>>>>");
			String rowKeyString = serverName+"|"+serverIp+"|"+topicName;
			System.out.println("Inside getIotGraphData-------->>>>>>>>>"+rowKeyString);
			
			FilterList filterList = new FilterList();
			
			Filter filter = new PrefixFilter(Bytes.toBytes(rowKeyString));
			filterList.addFilter(filter);
			
			Filter columnFilter = new ColumnPrefixFilter(Bytes.toBytes(tagName));
			filterList.addFilter(columnFilter);		
			
		    //Scan scan = new Scan();
		    
		   /* //logic to get date range begin
		    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");         
            //Date endRangeDate = dateFormat.parse("05/06/2015 12:59:30");
            Date endRangeDate = new Date();
            Date startRangeDate = getStartRangeDate(endRangeDate, 300);
            System.out.println("endRangeDate: -------------%%%%%%%%%"+endRangeDate);
            System.out.println("startRangeDate: -------------%%%%%%%%%"+startRangeDate);
            //System.out.println("startRangeDate timeStamp: -------------%%%%%%%%%"+endRangeDate.getTime());
            //System.out.println("startRangeDate timeStamp: -------------%%%%%%%%%"+startRangeDate.getTime());
		    //logic to get date range end*/    
			
			/*String startRow = serverName+"|"+serverIp+"|"+topicName+"|"+(startRangeDate.getTime());
		    String endRow = serverName+"|"+serverIp+"|"+topicName+"|"+(endRangeDate.getTime());*/
			
			long startRangeTimestamp = getStartRangeTimeStamp(endRangeTimeStamp, 300);
			
			String startRow = serverName+"|"+serverIp+"|"+topicName+"|"+endRangeTimeStamp;
		    String endRow = serverName+"|"+serverIp+"|"+topicName+"|"+startRangeTimestamp;
            
		    
		    
		    //String startRow = serverName+"|"+serverIp+"|"+topicName+"|1430916719000";
		    //String endRow = serverName+"|"+serverIp+"|"+topicName+"|1430917526000";
		    
		    System.out.println("startRow: -------------%%%%%%%%%"+startRow);
		    System.out.println("endRow: -------------%%%%%%%%%"+endRow);
		    
		    //Scan scan = new Scan(Bytes.toBytes(startRow),Bytes.toBytes(endRow)); 	
		    Scan scan = new Scan();
		    scan.setStartRow(Bytes.toBytes(startRow));
		    scan.setStopRow(Bytes.toBytes(endRow));
		    
		    scan.setFilter(filterList);
		    ResultScanner resultScanner = hTableInterface.getScanner(scan);
		    
		    timeValueList = populateGraphDataList(resultScanner, startRangeTimestamp, endRangeTimeStamp);
		    
		    /*Random random = new Random();
		    long timeScale = 0;		 
		    int cellCount = 1;
		    for (Result result : resultScanner) {		
		    	int i = 1;
		        for (Cell cell : result.rawCells()) {
		        	cellCount++;
		        	XYData xyData = new XYData();
		        	Float floatValue = new Float(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
		        	long value = floatValue.longValue();
		        	//long value = Long.valueOf(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
		        	//value = value+(random.nextInt(100 - 2 + 1) + 2);
		        	long timeStamp = cell.getTimestamp();		        	
		        	xyData.setX(timeScale*5);
		        	xyData.setY(value);			        	
		        	
		        	if(i<6){
		        		timeValueList.add(xyData);
		        		i++;
		        	}
		        	
		        	
		        	//System.out.println("tagName-------->>>>>>>>>>>>>>>>>>>>>>>"+tagName+"--timeStamp: "+timeStamp);
		        	//System.out.println("value-------->>>>>>>>>>>>>>"+value);
		        	//System.out.println("timeStamp-------->>>>>>>>>>"+timeScale*5);
		        	
		          System.out.println("Cell:----->>>> " + cell + ", Value:---->>> " +
		            Bytes.toString(cell.getValueArray(), cell.getValueOffset(),
		              cell.getValueLength()) + ", Timestamp:---->>>"+cell.getTimestamp());
		        	timeScale = timeScale+1;
		        }
		        
		      }*/
		    
		    //printRows(resultScanner);
		    
		    resultScanner.close();
		    //System.out.println("tagName cellCount--------##########>>>>>>>>>>"+tagName+"---"+cellCount);
		    System.out.println("timeValueList--------###########>>>>>>>>>>"+timeValueList.size());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				hTableInterface.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}		
		return timeValueList;
	}
	
	public List<XYData> populateGraphDataList(ResultScanner resultScanner, long startTimeStamp, long endTimeStamp){
		List<XYData> xyDataList = new LinkedList<XYData>();
		System.out.println("<<<<<<<<<------------Inside populateGraphDataList ------------->>>>>>>>>>>>");
		try{
			if(resultScanner != null){
				long currentTimeStamp = startTimeStamp;
				long nextTimeStamp = startTimeStamp+(30*1000);
				long prevValue = 0;
				long value = 0;
				int timeScale = 0;
				int dataPointCount = 1;				
				
				for(Result result : resultScanner){		
					
					for(Cell cell : result.rawCells()){
						Float floatValue = new Float(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));								        	
			        	long timeStamp = cell.getTimestamp();
			        	
			        	prevValue = value;
			        	value = floatValue.longValue();	  	
			        	
			        	if(timeStamp>=nextTimeStamp){
			        		XYData xyData = new XYData();
			        		//xyData.setX(timeScale*30);
			        		xyData.setX(timeScale);
			        		xyData.setY(prevValue);
			        		
			        		if(dataPointCount < 11){
			        			xyDataList.add(xyData);
			        		}
			        		
			        		System.out.println("nextTimeStamp: ------------->>>>>>>>>>>>"+nextTimeStamp);
			        		System.out.println("timeStamp: ------------->>>>>>>>>>>>"+timeStamp);
			        		System.out.println("prevValue: ------------->>>>>>>>>>>>"+prevValue);
			        		System.out.println("value: ------------->>>>>>>>>>>>"+value);
			        		System.out.println("timeScale: ------------->>>>>>>>>>>>"+timeScale);
			        		System.out.println("dataPointCount: ------------->>>>>>>>>>>>"+dataPointCount);
			        		System.out.println("timeScale: ------------->>>>>>>>>>>>"+timeScale);			        		
			        		
			        		timeScale++;
			        		dataPointCount++;
			        		nextTimeStamp = nextTimeStamp+(30*1000);			        		
		        		}
			        	
			        	if(nextTimeStamp == endTimeStamp){
			        		System.out.println("<<<<<<<<<---------nextTimeStamp: ------endTimeStamp------->>>>>>>>>>>>");
			        		
			        		XYData xyData = new XYData();
			        		//xyData.setX(timeScale*30);
			        		xyData.setX(timeScale);
			        		xyData.setY(value);
			        		
			        		if(dataPointCount < 11){
			        			xyDataList.add(xyData);
			        		}
			        		
			        		System.out.println("nextTimeStamp: ------------->>>>>>>>>>>>"+nextTimeStamp);
			        		System.out.println("timeStamp: ------------->>>>>>>>>>>>"+timeStamp);
			        		System.out.println("prevValue: ------------->>>>>>>>>>>>"+prevValue);
			        		System.out.println("value: ------------->>>>>>>>>>>>"+value);
			        		System.out.println("timeScale: ------------->>>>>>>>>>>>"+timeScale);
			        		System.out.println("dataPointCount: ------------->>>>>>>>>>>>"+dataPointCount);
			        		System.out.println("timeScale: ------------->>>>>>>>>>>>"+timeScale);			        		
			        		
			        		timeScale++;
			        		dataPointCount++;
			        	}			        	
			        	
					}
				}
				
				Random random = new Random();
				
				while(dataPointCount < 11){
					System.out.println("<<<<<<<<<---------IN RANDOM------endTimeStamp------->>>>>>>>>>>>");
	        		
	        		XYData xyData = new XYData();
	        		//xyData.setX(timeScale*30);
	        		xyData.setX(timeScale);
	        		
	        		long randomValue = 0;
	        		if(xyDataList.size()>0){
	        			randomValue = xyDataList.get(0).getY()+(random.nextInt(50 - 2 + 1) + 2);;
		        		xyData.setY(randomValue); 
	        		}else{
	        			randomValue =(random.nextInt(100 - 20 + 1) + 20);;
		        		xyData.setY(randomValue);
	        		}
	        		       		
	        		
        			xyDataList.add(xyData);
	        		
	        		
	        		System.out.println("nextTimeStamp: ------------->>>>>>>>>>>>"+nextTimeStamp);	        		
	        		System.out.println("prevValue: ------------->>>>>>>>>>>>"+prevValue);
	        		System.out.println("value: ------------->>>>>>>>>>>>"+randomValue);
	        		System.out.println("timeScale: ------------->>>>>>>>>>>>"+timeScale);
	        		System.out.println("dataPointCount: ------------->>>>>>>>>>>>"+dataPointCount);
	        		System.out.println("timeScale: ------------->>>>>>>>>>>>"+timeScale);			        		
	        		
	        		timeScale++;
	        		dataPointCount++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return xyDataList;
	}
	
	public Date getStartRangeDate(Date endRangeDate, int seconds){
        Date startRangeDate = null;
        try{
              Long nextTimeStamp = endRangeDate.getTime()-(seconds*1000);
              startRangeDate = new Date(nextTimeStamp);            
        }catch(Exception e){
              e.printStackTrace();
        }          
        return startRangeDate;
	}
	
	public long getStartRangeTimeStamp(long endRangeTimeStamp, int seconds){
        long startRangeTimeStamp = 0;
        try{
        	endRangeTimeStamp = endRangeTimeStamp-(seconds*1000);                        
        }catch(Exception e){
              e.printStackTrace();
        }          
        return endRangeTimeStamp;
	}
	
	

}
