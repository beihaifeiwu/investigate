/**
 * 
 */
package com.course.selection.util;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.course.selection.domain.Course;

/**
 * @author JiHan
 *
 */
public class CourseTable extends AbstractMap<String, String> implements Serializable {
	private static final long serialVersionUID = 1321131764593931419L;
	/**
	 * �洢�γ̵Ķ�ά�����б�Table[�ܴ�][�ڴ�]
	 */
	private CourseGroup table[][]= new CourseGroup[7][11];
	/**
	 * �洢��ͻ�γ̼���λ��
	 */
	private Map<String,CourseGroup> conflictMap = new HashMap<>();
	/**
	 * @return table
	 */
	public CourseGroup[][] getTable() {
		return table;
	}
	/**
	 * @param table Ҫ���õ� table
	 */
	public void setTable(CourseGroup table[][]) {
		this.table = table;
	}

	/**
	 * �ж������γ����ܴ����Ƿ��ͻ
	 * @param c1
	 * @param c2
	 * @return�г�ͻ����false,�޳�ͻ����true
	 */
	public static boolean isWeeksConflict(Course c1,Course c2){
		if((c1.getTimepoint().getWeeks()!=0)&&(c2.getTimepoint().getWeeks()!=0)){
			for(int i=0;i<20;i++){
				int temp1 = (c1.getTimepoint().getWeeks()&(int)Math.pow(2, i));
				int temp2 = (c2.getTimepoint().getWeeks()&(int)Math.pow(2, i));
				if(temp1==temp2&&temp1*temp2!=0){
					return false;
				}	
			}
		}
		return true;	
	}
	/**
	 * �ж������γ���һ���ڵĽڴ����Ƿ��ͻ
	 * @param c1
	 * @param c2
	 * @return�г�ͻ����false,�޳�ͻ����true
	 */
	public static boolean isPeriodConflict(Course c1,Course c2){
		for(int i=0;i<7;i++){
			if((c1.getTimepoint().getPeriod()[i]!=0&&c1.getTimepoint().getPeriod()[i]!=0))
				for(int j=0;j<11;j++){
					int temp1=c1.getTimepoint().getPeriod()[i]&(int)Math.pow(2, j);
					int temp2 = c2.getTimepoint().getPeriod()[i]&(int)Math.pow(2, j);
					if(temp1==temp2&&temp1*temp2!=0)
						return false;
				}
		}
		return true;
	}
	/**
	 * ��������γ���ʱ�����Ƿ��ͻ
	 * @param c1
	 * @param c2
	 * @return�����γ̳�ͻ����false������ͻ����true
	 */
	public static boolean isConflict(Course c1,Course c2){
		if(isWeeksConflict(c1, c2))
			return true;
		else{
			if(isPeriodConflict(c1, c2))
				return true;
		}	
		return false;
	}
	/**
	 * ���α������ָ���γ�
	 * @param course ��Ҫ����ӵ�ָ���γ�
	 * @return ��ӿγ̵Ľ������ӳɹ�Ϊtrue�����ʧ��Ϊfalse
	 */
	public boolean addCourse(Course course){
		for(int i=0;i<7;i++){
			for(int j=0;j<11;j++){
				if(this.table[i][j]!=null){
					for(Course c : this.table[i][j]){
						if(!isConflict(course, c)){
							//System.out.println("���ʧ��");
							String key = (i+1) + "," + (j+1);
							CourseGroup cg = this.getConflictMap().get(key);
							if(cg == null){
								cg = new CourseGroup();
							}
							cg.add(course);
							//System.out.println(course);
							this.getConflictMap().put(key, cg);
							return false;
						}
					}
				}
			}
		}
		for(int i=0;i<7;i++){
			if(course.getTimepoint().getPeriod()[i]!=0){
				for(int j=0;j<11;j++){
					if(((course.getTimepoint().getPeriod()[i])&(int)Math.pow(2, j))==(int)Math.pow(2, j)){

						if(table[i][j] == null){
							this.table[i][j] = new CourseGroup();
						}
						this.table[i][j].add(course);
						//System.out.println("��ӳɹ�");
					}
				}
			}
		}
		return true;
	}
	/**
	 * �ӿα���ɾ���γ�
	 * @param course
	 */
	public void rmCourse(Course course){
		for(int i=0;i<7;i++){
			for(int j=0;j<11;j++){
				if(table[i][j]!=null){
					int count =0;
					for(Course c : this.table[i][j]){
						if(c==course){
							this.table[i][j].remove(count);
							count--;
						}	
						count++;
					}
				}
			}
		}
	}
	@Override
	public Set<Entry<String, String>> entrySet() {
		Set<Entry<String, String>> set = new HashSet<>();
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 11; j++){
				if(table[i][j] != null){
					set.add(new CourseTableEntry(i, j));
				}
			}
		}
		return set;
	}
	
	@Override
	public boolean isEmpty() {
		boolean empty = true;
		
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 11; j++){
				if(table[i][j] != null) empty = false;
			}
		}
		
		return empty;
	}
	@Override
	public boolean containsKey(Object key) {
		
		boolean res = false;
		
		String str = (String) key;
		
		String[] array = str.split(",");
		
		if(array.length == 2){
			
			int i = Integer.parseInt(array[0]) - 1;
			
			int j = Integer.parseInt(array[1]) - 1;
			
			if(i >= 0 && i <= 6 && j >= 0 && j <= 11){
				res = true;
			}
			
		}
		
		return res;
	}
	@Override
	public String get(Object key) {

		String res = null;
		
		String str = (String) key;
		
		String[] array = str.split(",");
		
		if(array.length == 2){
			
			int i = Integer.parseInt(array[0]) - 1;
			
			int j = Integer.parseInt(array[1]) - 1;
			
			if(i >= 0 && i <= 6 && j >= 0 && j <= 11){
				
				CourseGroup cg = table[i][j];

				if(cg != null) res = cg.getDescription();
			}
			
		}
		
		return res;
	}

	public Map<String,CourseGroup> getConflictMap() {
		return conflictMap;
	}
	public void setConflictMap(Map<String,CourseGroup> conflictMap) {
		this.conflictMap = conflictMap;
	}

	public class CourseTableEntry implements Entry<String, String>{

		private int i;
		
		private int j;
		
		@Override
		public String getKey() {
			return (i+1) + "," + (j+1);
		}

		@Override
		public String getValue() {
			return table[i][j].getDescription();
		}

		@Override
		public String setValue(String value) {
			return null;
		}

		public CourseTableEntry(int i, int j) {
			this.i = i;
			this.j = j;
		}

	}
}
