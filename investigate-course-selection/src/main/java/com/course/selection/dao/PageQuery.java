package com.course.selection.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询的信息类
 * @author Administrator
 *
 * @param <T> 分页查询所承载的域类
 */
public class PageQuery<T> implements Serializable{

	private static final long serialVersionUID = 3933355967786922435L;
	
	/**
	 * 页面当前显示的索引
	 */
	private int currentIndex;
	/**
	 * 符合条件的总记录数
	 */
	private long totalNum;
	/**
	 * 页面索引的总数
	 */
	private int totalIndex;
	/**
	 * 当前索引所代表的数据
	 */
	private List<T> datas;

	/**
	 * 每个索引页面所包含的条数
	 */
	private int pageContentNum = 20;
	
	/**
	 * 获取当前页在查询结果中的位置
	 * @return
	 */
	public int getCurrentNum(){
		return (getCurrentIndex()-1) * getPageContentNum();
	}
	
	/**
	 * 跳转到下一页，返回下一页的索引
	 */
	public int getNextPage(){
		int res = getCurrentIndex();
		if(getCurrentIndex() < getTotalIndex()){
			res = getCurrentIndex() + 1;
		}
		return res;
	}
	
	/**
	 * 跳转到上一页，返回上一页的索引
	 */
	public int getPreviousPage(){
		int res = 1;
		if(getCurrentIndex() > 1){
			res  = getCurrentIndex() - 1;
		}
		return res;
	}
	
	/**
	 * 跳转到最后一页，返回最后一页的索引
	 */
	public int getLastPage(){
		return getTotalIndex();
	}
	/**
	 * 跳转到第一页,返回第一页的索引
	 */
	public int getFirstPage(){
		return 1;
	}	
	
	/**
	 * 跳转到指定页
	 * @param page
	 */
	public PageQuery<T> getSpecificPage(int page){
		if(page >= 1 && page <= getTotalIndex()){
			setCurrentIndex(page);
		}
		return this;
	}
	
	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currnetIndex) {
		this.currentIndex = currnetIndex;
	}

	public int getTotalIndex() {
		return totalIndex;
	}

	public void setTotalIndex(int totalIndex) {
		this.totalIndex = totalIndex;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public int getPageContentNum() {
		return pageContentNum;
	}

	public void setPageContentNum(int pageContentNum) {
		this.pageContentNum = pageContentNum;
	}

	public PageQuery(int currnetIndex, int pageContentNum) {
		this.currentIndex = currnetIndex;
		this.pageContentNum = pageContentNum;
	}

	public PageQuery() {
		this.currentIndex = 1;
		this.pageContentNum = 20;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long total) {
		this.totalNum = total;
		setTotalIndex((int)Math.ceil((double)total / getPageContentNum()));
	}

	public boolean isFirst(){
		if(this.currentIndex == 1){
			return true;
		}
		return false;
	}
}
