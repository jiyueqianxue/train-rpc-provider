package io.mine.ft.train.bean.base;

import java.util.List;

/**
 * 分页结果<br/>
 * 构建本结果，请使用PagedResultBuilder类进行构建
 * Created by 马超
 * on 2018/08/22.
 */
public class PagedResult<E> extends Result<List<E>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 总条数
	 */
	private Integer total;

	/**
	 * 总页数
	 */
	private Integer totalPage;

	/**
	 * 当前页数
	 */
	private int currentPage;

	/**
	 * 当前分页大小
	 */
	private int pageSize;

	/**
	 * 构建请使用PagedResult.Builder构建
	 */
	public PagedResult(){

	}

	public Integer getTotal() {
		return total;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setPageInfo(PagedRequest req, int total) {
		this.setTotal(total);
		this.setCurrentPage(req.getPage());
		this.setPageSize(req.getPageSize());
		
		Integer totalPage = total/req.getPageSize() + 1;
		this.setTotalPage(totalPage);
	}
}
