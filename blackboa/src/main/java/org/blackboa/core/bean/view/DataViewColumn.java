package org.blackboa.core.bean.view;

public class DataViewColumn {
	/**
	 * 字段名称
	 */
	private String columnName;
	/**
	 * 字段类型
	 */
	private String columnType;
	/**
	 * 字段长度
	 */
	private String columnLength;
	/**
	 * 字段是否为空
	 */
	private String columnIsNull;
	/**
	 * 字段含义
	 */
	private String columnImplication;
	/**
	 * 字段默认值
	 */
	private String columnDefault;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnLength() {
		return columnLength;
	}

	public void setColumnLength(String columnLength) {
		this.columnLength = columnLength;
	}

	public String getColumnIsNull() {
		return columnIsNull;
	}

	public void setColumnIsNull(String columnIsNull) {
		this.columnIsNull = columnIsNull;
	}

	public String getColumnImplication() {
		return columnImplication;
	}

	public void setColumnImplication(String columnImplication) {
		this.columnImplication = columnImplication;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

}
