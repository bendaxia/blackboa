package org.blackboa.core.bean.procedure;

public class DataProcedureColumn {

	/**
	 * 列/参数名称
	 */
	private String columnName;
	/**
	 * 列/参数种类
	 */
	private String columnType;
	/**
	 * 列/参数种类名
	 */
	private String columnTypeName;
	/**
	 * 列/参数长度
	 */
	private String columnLength;
	/**
	 * 列/参数是否为空
	 */
	private String columnIsNull;
	/**
	 * 列/参数含义
	 */
	private String columnImplication;

	public String getColumnTypeName() {
		return columnTypeName;
	}

	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}

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

}
