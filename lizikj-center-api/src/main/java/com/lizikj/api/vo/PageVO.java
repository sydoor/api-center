package com.lizikj.api.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "分页对象")
public class PageVO<T> {

	/**
	 * 分页页码
	 */
	@ApiModelProperty(value = "分页页码", name = "pageNo", required = true, dataType = "Integer")
	private int pageNo;

	/**
	 * 分页长度
	 */
	@ApiModelProperty(value = "分页长度", name = "pageSize", required = true, dataType = "Integer")
	private int pageSize;

	/**
	 * 总页数
	 */
	@ApiModelProperty(value = "总页数", name = "pages", required = true, dataType = "Integer")
	private int pages;

	/**
	 * 总记录数
	 */
	@ApiModelProperty(value = "总记录数", name = "total", required = true, dataType = "Integer")
	private long total;

	/**
	 * 分页列表数据
	 */
	@ApiModelProperty(value = "分页列表数据", name = "list", required = false, dataType = "Array")
	private List<T> list;

	public PageVO() {
		super();
	}

	public PageVO(int pageNo, int pageSize, int pages, long total) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.pages = pages;
		this.total = total;
	}

	public PageVO(int pageNo, int pageSize, int pages, long total, List<T> list) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.pages = pages;
		this.total = total;
		this.list = list;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
