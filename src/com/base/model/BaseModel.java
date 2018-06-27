package com.base.model;

import com.base.utils.RequestHandler;

public class BaseModel {
	/** 取数据起始行，用于分页 **/
	private int rowStart;
	/** 取数据行数，用于分页 **/
	private int rowCount;
	/** 当前页码，用于分页 **/
	private int pageNo;
	/** 排序 **/
	private String sortColumn;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public int getRowStart() {
		int from = RequestHandler.getFromByPage(pageNo, rowCount);
		this.setRowStart(from);
		return rowStart;
	}

	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

}
