package com.course.selection.util;

import com.course.selection.domain.TimePoint;

public class TimePointBuilder {
	
	private int weeks;
	private short[] period = new short[7];
	
	/**
	 * 添加周次
	 * @param weekArray
	 */
	public TimePointBuilder addWeekRange(Integer[] weekArray){
		if(weekArray != null && weekArray.length == 2 && weekArray[0] <= 20 && weekArray[1] <= 20
				&& weekArray[0] >= 1 && weekArray[1] >= 2 && weekArray[0] <= weekArray[1]){
			setWeeks(0);
			for(int i = weekArray[0] ; i <= weekArray[1] ; i++){
				setWeeks(getWeeks() | (int)Math.pow(2, i-1));
			}
			
		}
		return this;
	}
	/**
	 * 添加节次
	 * @param workDay 工作日
	 * @param period 节次
	 * @return
	 */
	public TimePointBuilder addPeriod(int workDay,int period){
		if(workDay >= 1 && workDay <= 7 && period >= 1 && period <= 11){
			getPeriod()[workDay -1] |= ((int)Math.pow(2, period-1));
			
		}
		return this;
	}
	/**
	 * 添加节次
	 * @param workDay 第几个工作日
	 * @param courseRange 节次范围
	 */
	public TimePointBuilder addPeriod(int workDay,Integer[] courseRange){
		if(workDay >= 1 && workDay <= 7 && courseRange != null){
			if(courseRange[0] >= 1 && courseRange[0] <= 11 && courseRange[1] >= 1 && courseRange[1] <= 11
					&& courseRange[0] <= courseRange[1]){
				short periodTime = 0;
				for(int i = courseRange[0]; i <= courseRange[1]; i++){
					periodTime |= ((int)Math.pow(2, i-1));
				}
				getPeriod()[workDay-1] = periodTime;
			}
		}
		return this;
	}
	
	/**
	 * 将所有信息封装进TimePoint
	 * @return
	 */
	public TimePoint parseToTimePoint(){
		TimePoint tp = new TimePoint();
		tp.setWeeks(getWeeks());
		tp.setPeriod(getPeriod());
		return tp;
	}
	public int getWeeks() {
		return weeks;
	}

	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}

	public short[] getPeriod() {
		return period;
	}

	public void setPeriod(short[] period) {
		this.period = period;
	}

}
