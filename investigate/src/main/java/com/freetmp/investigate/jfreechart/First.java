package com.freetmp.investigate.jfreechart;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * 首次尝试使用jfreechart
 * @author pin
 */
public class First {

	public static void main(String[] args) {
		// create a dataset
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Category 1", 43.2);
		dataset.setValue("Category 2", 27.9);
		dataset.setValue("Category 3", 79.5);
		// create a chart
		JFreeChart chart = ChartFactory.createPieChart(
				"Sample Pie Chart", 
				dataset, 
				true, true, false);
		PiePlot plot = (PiePlot) chart.getPlot();
		// 设置区域颜色
		plot.setSectionPaint("Category 1", new Color(200, 255, 255));
		plot.setSectionPaint("Category 2", new Color(200, 200, 255));
		plot.setSectionPaint("Category 3", new Color(255, 200, 200));
		// 设置图表的轮廓
		plot.setSectionOutlinesVisible(true);
		plot.setBaseSectionOutlinePaint(new Color(255, 255, 255));
		plot.setBaseSectionOutlineStroke(new BasicStroke(3));
		plot.setSectionOutlinePaint("Category 2", Color.RED);
		plot.setSectionOutlineStroke("Category 2", new BasicStroke(3));
		// 设置忽略Zero，Null
		plot.setIgnoreNullValues(true);
		plot.setIgnoreZeroValues(true);
		// 设置分离的区域
		plot.setExplodePercent("Category 2", 0.3);
		// create and display a frame
		ChartFrame frame = new ChartFrame("Test", chart);
		frame.pack();
		frame.setVisible(true);
	}
}
