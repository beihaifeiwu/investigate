/**
 * 
 */
package com.course.selection.domain;

import java.io.Serializable;

/**
 * @author JiHan
 *
 */
public class TimePoint implements Serializable {
	private static final long serialVersionUID = -44027704073610769L;
	/**
	 * 　　Id：主属性
　　		Weeks：周次
		Period：节次
	 */
	private int ID;
	private int Weeks;
	private short[] Period = new short[7];
	/**
	 * 获得周次的简单描述
	 * @return
	 */
	public String getWeeksDescription(){
		String description = null;
		StringBuilder builder = new StringBuilder();

		int start=0;
		int end=0;
		for(int i=0;i<20;i++){
			if((Weeks&(int)Math.pow(2, i))==(int)Math.pow(2, i)){
				if(start == 0){
					start = i + 1;
				}else{
					end = i + 1;
				}
			}else{
				if(start == 0 && end == 0){
					continue;
				}else{
					builder.append(start);
					builder.append("-");
					builder.append(end);
					builder.append("周");
					start = 0;
					end = 0;
				}
			}
		}
		description = builder.toString();
		return description;
	}
	/**
	 * 获取时间段的简单描述
	 * @return
	 */
	public String getDescription(){
		String description = null;
		StringBuilder builder = new StringBuilder();

		int start=0;
		int end=0;
		for(int i=0;i<20;i++){
			if((Weeks&(int)Math.pow(2, i))==(int)Math.pow(2, i)){
				if(start == 0){
					start = i + 1;
				}else{
					end = i + 1;
				}
			}else{
				if(start == 0 && end == 0){
					continue;
				}else{
					builder.append(start);
					builder.append("-");
					builder.append(end);
					builder.append("周");
					start = 0;
					end = 0;
				}
			}
		}
		start=0;
		end=0;
		for(int i=0;i<7;i++){
			if(this.Period[i]!=0){
				builder.append(";");
				builder.append("周");
				builder.append(i+1);
				builder.append(":");
				for(int j=0;j<11;j++){
					if((this.Period[i]&(int)Math.pow(2, j))==(int)Math.pow(2, j)){
						if(start == 0){
							start = j+1;
						}else{
							end = j + 1;
						}
					}else{
						if(start == 0 && end == 0){
							continue;
						}else{
							builder.append(start);
							builder.append("-");
							builder.append(end);
							builder.append("节");
							start = 0;
							end = 0;
						}
					}
				}
			}
		}
		description = builder.toString();
		return description;
	}
	public void setID(int ID){
		this.ID=ID;
	}
	public int getID(){
		return ID;
	}
	/**
	 * @return weeks
	 */
	public int getWeeks() {
		return Weeks;
	}
	/**
	 * @param weeks 要设置的 weeks
	 */
	public void setWeeks(int weeks) {
		Weeks = weeks;
	}
	/**
	 * @return period
	 */
	public short[] getPeriod() {
		return Period;
	}
	/**
	 * @param period 要设置的 period
	 */
	public void setPeriod(short[] period) {
		Period = period;
	}
	/* （非 Javadoc）
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}
	/* （非 Javadoc）
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimePoint other = (TimePoint) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	

}
