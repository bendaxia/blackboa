package org.blackboa.core.bean.view;

import java.util.ArrayList;
import java.util.List;

public class DataViewInfo {

	/**
	 * 视图名
	 */
	private String viewName;

	/**
	 * 视图注释
	 */
	private String viewImplication;

	/**
	 * 视图字段信息
	 */
	private List<DataViewColumn> dataViewColumns = new ArrayList<>();

	/**
	 * 索引
	 */
	private String viewIndex;

	/**
	 * 主键
	 */
	private String viewPrimaryKey;

	/**
	 * 外键
	 */
	private String viewForeignKey;

	/**
	 * 外键所属表
	 */
	private String foreignKeyViewName;

	public String getViewIndex() {
		return viewIndex;
	}

	public void setViewIndex(String viewIndex) {
		this.viewIndex = viewIndex;
	}

	public String getViewPrimaryKey() {
		return viewPrimaryKey;
	}

	public void setViewPrimaryKey(String viewPrimaryKey) {
		this.viewPrimaryKey = viewPrimaryKey;
	}

	public String getViewForeignKey() {
		return viewForeignKey;
	}

	public void setViewForeignKey(String viewForeignKey) {
		this.viewForeignKey = viewForeignKey;
	}

	public String getForeignKeyViewName() {
		return foreignKeyViewName;
	}

	public void setForeignKeyViewName(String foreignKeyViewName) {
		this.foreignKeyViewName = foreignKeyViewName;
	}

	public String getViewImplication() {
		return viewImplication;
	}

	public void setViewImplication(String viewImplication) {
		this.viewImplication = viewImplication;
	}

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
