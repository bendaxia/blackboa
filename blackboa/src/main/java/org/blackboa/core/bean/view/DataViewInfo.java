package org.blackboa.core.bean.view;

import java.util.ArrayList;
import java.util.List;

public class DataViewInfo {

	/**
	 * 视图名
	 */
	private String viewName;
	
	/**
	 * 视图字段信息
	 */
	private List<DataViewColumn> dataViewColumns = new ArrayList<>();

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public List<DataViewColumn> getDataViewColumns() {
		return dataViewColumns;
	}

	public void setDataViewColumns(List<DataViewColumn> dataViewColumns) {
		this.dataViewColumns = dataViewColumns;
	}
	
}
