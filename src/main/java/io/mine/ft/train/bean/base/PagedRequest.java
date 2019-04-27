package io.mine.ft.train.bean.base;

/**
 * 分页查询请求 Created by machao on 2018/08/22.
 */
public class PagedRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_PAGE_SIZE = 20;
	public static final int DEFAULT_CURRENT_PAGE = 1;

	private Integer page = DEFAULT_CURRENT_PAGE;

	private Integer pageSize = DEFAULT_PAGE_SIZE;

	public Integer getOffset() {
		return pageSize * (page - 1);

	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
