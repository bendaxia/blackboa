package org.blackboa.core.bean;

import java.util.ArrayList;
import java.util.List;

public class DataTableInfo {
	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 表说明
	 */
	private String tableImplication;

	/**
	 * 索引
	 */
	private String tableIndex;

	/**
	 * 主键
	 */
	private String tablePrimaryKey;

	/**
	 * 外键
	 */
	private String tableForeignKey;

	/**
	 * 外键所属表
	 */
	private String foreignKeyTableName;
	
	/**
	 * 字段
	 */
	private List<DataTableColumn> dataTableColumns = new ArrayList<>(10);

	public String getForeignKeyTableName() {
		return foreignKeyTableName;
	}

	public void setForeignKeyTableName(String foreignKeyTableName) {
		this.foreignKeyTableName = foreignKeyTableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableImplication() {
		return tableImplication;
	}

	public void setTableImplication(String tableImplication) {
		this.tableImplication = tableImplication;
	}

	public String getTableIndex() {
		return tableIndex;
	}

	public void setTableIndex(String tableIndex) {
		this.tableIndex = tableIndex;
	}

	public String getTablePrimaryKey() {
		return tablePrimaryKey;
	}

	public void setTablePrimaryKey(String tablePrimaryKey) {
		this.tablePrimaryKey = tablePrimaryKey;
	}

	public String getTableForeignKey() {
		return tableForeignKey;
	}

	public void setTableForeignKey(String tableForeignKey) {
		this.tableForeignKey = tableForeignKey;
	}

	public List<DataTableColumn> getDataTableColumns() {
		return dataTableColumns;
	}

	public void setDataTableColumns(List<DataTableColumn> dataTableColumns) {
		this.dataTableColumns = dataTableColumns;
	}

}
