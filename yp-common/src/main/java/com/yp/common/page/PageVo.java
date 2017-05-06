package com.yp.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * page分页对象
 * @author zhiya.chai
 * 2016年9月4日 上午1:38:48
 */
public class PageVo<E> implements Serializable{
	
	/**
	 * 2016年9月4日 上午1:44:10
	 */
	private static final long serialVersionUID = 1037077544829819722L;

	private static int DEFAULT_PAGE_SIZE = 20;

	private int currentPage = -1;//当前页
	private int pageSize = -1;//
	private int total;//总数
	
	private List<E> voList;//集合
	
	public PageVo() {
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.currentPage = -1;
    }

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<E> getVoList() {
		return voList;
	}

	public void setVoList(List<E> voList) {
		this.voList = voList;
	}
	
}
