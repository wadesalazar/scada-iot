package com.iot.util;

import java.text.SimpleDateFormat;

public class IOTUtil {
	
	public static String convertTimestampToString(long timeStamp){
		String timeString = "";
		try{
			timeString = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(timeStamp);
		}catch(Exception e){
			e.printStackTrace();
		}		
		return timeString;
	}

}
