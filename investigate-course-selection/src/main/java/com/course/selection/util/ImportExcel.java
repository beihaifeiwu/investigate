package com.course.selection.util;

import com.course.selection.domain.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ImportExcel {
	// 记录类的输出信息
	static Log log = LogFactory.getLog(ImportExcel.class);
	
	/**
	 * 解析Excel表格，填充数据库，生成教师列表
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<Teacher> importTeacherByExcel(File file) throws Exception {
		// 创建对Excel工作簿文件的引用
		HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(file));
		// 在Excel文档中，第一张工作表的缺省索引是0
		// 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFSheet sheet = wookbook.getSheet("Sheet1");
		// 获取到Excel文件中的所有行数
		int rows = sheet.getPhysicalNumberOfRows();
		List<Teacher> list = new ArrayList<>();
		HSSFDataFormatter format = new HSSFDataFormatter();
		// 遍历行
		for (int i = 1; i < rows; i++) {
			// 读取左上端单元格
			HSSFRow row = sheet.getRow(i);
			// 行不为空
			if (row != null) {
				// 获取到Excel文件中的所有的列
				int cells = row.getPhysicalNumberOfCells();
				StringBuilder value = new StringBuilder();
				// 遍历列
				for (int j = 0; j < cells; j++) {
					// 获取到列的值
					HSSFCell cell = row.getCell(j);
					if (cell != null) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_FORMULA:
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							value.append(format.formatCellValue(cell)).append(",");
							break;
						case HSSFCell.CELL_TYPE_STRING:
							value.append(cell.getRichStringCellValue().toString()).append(",");
							break;
						default:
							value.append("0").append(",");
							break;
						}
					}
				}
				value.deleteCharAt(value.length() - 1);
				String[] values = value.toString().split(",");
				if(values.length == 4){
					Teacher teacher = new Teacher();
					//获取老师的账户
					teacher.setAccount(values[0]);
					//设置老师的默认账户
					teacher.setPassword("123");
					//设置老师的职称
					teacher.setTitle(values[2]);
					//设置老师的信息
					Profile profile = new Profile();
					profile.setName(values[1]);
					profile.setDepartment(values[3]);
					teacher.setProfile(profile);
					list.add(teacher);
				}
				
			}
		}
		return list;
	}
	/**
	 * 解析Excel表格，填充数据库，生成教室列表
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<ClassRoom> importClassRoomByExcel(File file) throws Exception {
		// 创建对Excel工作簿文件的引用
		HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(file));
		// 在Excel文档中，第一张工作表的缺省索引是0
		// 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFSheet sheet = wookbook.getSheet("Sheet1");
		// 获取到Excel文件中的所有行数
		int rows = sheet.getPhysicalNumberOfRows();
		List<ClassRoom> list = new ArrayList<>();
		HSSFDataFormatter format = new HSSFDataFormatter();
		// 遍历行
		for (int i = 1; i < rows; i++) {
			// 读取左上端单元格
			HSSFRow row = sheet.getRow(i);
			// 行不为空
			if (row != null) {
				// 获取到Excel文件中的所有的列
				int cells = row.getPhysicalNumberOfCells();
				StringBuilder value = new StringBuilder();
				// 遍历列
				for (int j = 0; j < cells; j++) {
					// 获取到列的值
					HSSFCell cell = row.getCell(j);
					if (cell != null) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_FORMULA:
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							value.append(format.formatCellValue(cell)).append(",");
							break;
						case HSSFCell.CELL_TYPE_STRING:
							value.append(cell.getRichStringCellValue().toString()).append(",");
							break;
						default:
							value.append("0").append(",");
							break;
						}
					}
				}
				value.deleteCharAt(value.length() - 1);
				String[] values = value.toString().split(",");
				if(values.length == 2){
					ClassRoom classRoom = new ClassRoom();
					//获取教室编名
					classRoom.setRoomName(values[0]);
					//获取教室容量
					classRoom.setCapacity(Integer.parseInt(values[1]));
					list.add(classRoom);
				}
				
			}
		}
		return list;
	}
	/**
	 * 解析excel表格，填充数据库,生成课程列表
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<Course> importCourseByExcel(File file) throws Exception {
		// 创建对Excel工作簿文件的引用
		HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(file));
		// 在Excel文档中，第一张工作表的缺省索引是0
		// 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFSheet sheet = wookbook.getSheet("Sheet1");
		// 获取到Excel文件中的所有行数
		int rows = sheet.getPhysicalNumberOfRows();
		
		List<Course> list = new ArrayList<Course>();
		
		HSSFDataFormatter format = new HSSFDataFormatter();
		// 遍历行
		for (int i = 1; i < rows; i++) {
			// 读取左上端单元格
			HSSFRow row = sheet.getRow(i);
			// 行不为空
			if (row != null) {
				// 获取到Excel文件中的所有的列
				int cells = row.getPhysicalNumberOfCells();
				StringBuilder value = new StringBuilder();
				// 遍历列
				for (int j = 0; j < cells; j++) {
					// 获取到列的值
					HSSFCell cell = row.getCell(j);
					if (cell != null) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_FORMULA:
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							value.append(format.formatCellValue(cell)).append(",");
							break;
						case HSSFCell.CELL_TYPE_STRING:
							value.append(cell.getRichStringCellValue().toString()).append(",");
							break;
						default:
							value.append("0").append(",");
							break;
						}
					}
				}
				value.deleteCharAt(value.length() - 1);
				String[] values = value.toString().split(",");
				if(values.length == 18){
					Course course = new Course();
					//获取课程号
					course.setCourseNumber(values[0]);
					//获取课序号
					course.setClassNumber(values[1]);
					//获取课程名
					course.setCourseName(values[2]);
					
					//获取任课教师信息
					Teacher teacher = new Teacher();
					teacher.setAccount(values[3]);
					course.setTeacher(teacher);
					
					//获取课程院系
					course.setDepartment(values[5]);
					//获取课程属性
					course.setAttribute(values[6]);
					//获取课程容量
					course.setCapacity(Integer.parseInt(values[7]));
					//获取学分
					course.setCredit(Double.parseDouble(values[8]));
					
					//获取上课时间信息
					TimePointBuilder tpb = new TimePointBuilder();
					for(int j = 9 ; j <= 15; j++){
						if(! values[j].equals("0") && !values[j].trim().equals("")){
							Integer[] courseRange = parseRange(values[j]);
							tpb.addPeriod(j-8, courseRange);
						}
					}
					tpb.addWeekRange(parseRange(values[17]));
					course.setTimepoint(tpb.parseToTimePoint());
					
					//获取上课地点信息
					ClassRoom cr = new ClassRoom();
					cr.setRoomName(parseRoom(values[16]));
					course.setClassroom(cr);
					
					//设置课程状态为新建
					CourseState cs = new CourseState();
					cs.setStateType(CourseStateType.CREATE);
					cs.setStudentNumber(0);
					list.add(course);
				}

			}
		}
		
		return list;
	}
	
	private static Integer[] parseRange(String numRange){
		Integer[] array = new Integer[2];
		if(numRange != null){
			String[] ranges = numRange.split("-");
			array[0] = Integer.parseInt(ranges[0]);
			array[1] = Integer.parseInt(ranges[1]);
		}
		return array;
	}
	
	private static String parseRoom(String room){
		if(room != null){
			return room.replaceAll("-", "");
		}
		return null;
	}
}