package com.iot.model;

public class XYData {
	
		private long x;
		private long y;
		
		public XYData(long x, long y){
			this.x = x;
			this.y = y;
		}
		
		public XYData(){
			//empty constructor
		}
		
		public long getX() {
			return x;
		}
		public void setX(long x) {
			this.x = x;
		}
		public long getY() {
			return y;
		}
		public void setY(long y) {
			this.y = y;
		}		
}
