package org.blackboa.core.bean.function;

public class DataFunctionColumn {

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

	/**
	 * 列默认值(只有sqlserver有)
	 */
	private String columnDef;

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

	public String getColumnTypeName() {
		return columnTypeName;
	}

	public void setColumnTypeName(String columnTypeName) {
		switch (Integer.valueOf(columnTypeName)) {
		case COLUMNTYPENAME_STATE.FUNCTIONCOLUMN_UNKNOWN:
			this.columnTypeName = COLUMNTYPENAME_VALUE.FUNCTIONCOLUMN_UNKNOWN;
			break;
		case COLUMNTYPENAME_STATE.FUNCTIONCOLUMN_IN:
			this.columnTypeName = COLUMNTYPENAME_VALUE.FUNCTIONCOLUMN_IN;
			break;
		case COLUMNTYPENAME_STATE.FUNCTIONCOLUMN_INOUT:
			this.columnTypeName = COLUMNTYPENAME_VALUE.FUNCTIONCOLUMN_INOUT;
			break;
		case COLUMNTYPENAME_STATE.FUNCTIONCOLUMN_OUT:
			this.columnTypeName = COLUMNTYPENAME_VALUE.FUNCTIONCOLUMN_OUT;
			break;
		case COLUMNTYPENAME_STATE.FUNCTION_RETURN:
			this.columnTypeName = COLUMNTYPENAME_VALUE.FUNCTION_RETURN;
			break;
		case COLUMNTYPENAME_STATE.FUNCTIONCOLUMN_RESULT:
			this.columnTypeName = COLUMNTYPENAME_VALUE.FUNCTIONCOLUMN_RESULT;
			break;
		default:
			this.columnTypeName = columnTypeName;
			break;
		}
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
		switch (Integer.valueOf(columnIsNull)) {
		case COLUMNISNULL_STATE.NO_NULLS:
			this.columnIsNull = COLUMNISNULL_VALUE.NO_NULLS;
			break;
		case COLUMNISNULL_STATE.NULL_ABLE:
			this.columnIsNull = COLUMNISNULL_VALUE.NULL_ABLE;
			break;
		case COLUMNISNULL_STATE.UNKNOWN:
			this.columnIsNull = COLUMNISNULL_VALUE.UNKNOWN;
			break;
		default:
			this.columnIsNull = columnIsNull;
			break;
		}
	}

	public String getColumnImplication() {
		return columnImplication;
	}

	public void setColumnImplication(String columnImplication) {
		this.columnImplication = columnImplication;
	}

	public String getColumnDef() {
		return columnDef;
	}

	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}

	private static final class COLUMNTYPENAME_STATE {
		private static final int FUNCTIONCOLUMN_UNKNOWN = 0;
		private static final int FUNCTIONCOLUMN_IN = 1;
		private static final int FUNCTIONCOLUMN_INOUT = 2;
		private static final int FUNCTIONCOLUMN_OUT = 3;
		private static final int FUNCTION_RETURN = 4;
		private static final int FUNCTIONCOLUMN_RESULT = 5;
	}

	private static final class COLUMNTYPENAME_VALUE {
		private static final String FUNCTIONCOLUMN_UNKNOWN = "未知类型";
		private static final String FUNCTIONCOLUMN_IN = "输入参数";
		private static final String FUNCTIONCOLUMN_INOUT = "输入/输出参数";
		private static final String FUNCTIONCOLUMN_OUT = "输出参数";
		private static final String FUNCTION_RETURN = "函数返回值";
		private static final String FUNCTIONCOLUMN_RESULT = "参数或列是结果集中的列";
	}
	
	private static final class COLUMNISNULL_STATE {
		private static final int NO_NULLS = 0; // 可以为null
		private static final int NULL_ABLE = 1; // 不可以为null
		private static final int UNKNOWN = 2; // 不知道可不可以为null
	}

	private static final class COLUMNISNULL_VALUE {
		private static final String NO_NULLS = "N"; // 可以为null
		private static final String NULL_ABLE = "Y"; // 不可以为null
		private static final String UNKNOWN = "UNKNOWN"; // 不知道可不可以为null
	}
}
