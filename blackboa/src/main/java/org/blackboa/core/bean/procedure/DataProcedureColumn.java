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
		switch (Integer.valueOf(columnTypeName)) {
		case COLUMNTYPENAME_STATE.PROCEDURECOLUMN_UNKNOWN:
			this.columnTypeName = COLUMNTYPENAME_VALUE.PROCEDURECOLUMN_UNKNOWN;
			break;
		case COLUMNTYPENAME_STATE.PROCEDURECOLUMN_IN:
			this.columnTypeName = COLUMNTYPENAME_VALUE.PROCEDURECOLUMN_IN;
			break;
		case COLUMNTYPENAME_STATE.PROCEDURECOLUMN_INOUT:
			this.columnTypeName = COLUMNTYPENAME_VALUE.PROCEDURECOLUMN_INOUT;
			break;
		case COLUMNTYPENAME_STATE.PROCEDURECOLUMN_OUT:
			this.columnTypeName = COLUMNTYPENAME_VALUE.PROCEDURECOLUMN_OUT;
			break;
		case COLUMNTYPENAME_STATE.PROCEDURECOLUMN_RETURN:
			this.columnTypeName = COLUMNTYPENAME_VALUE.PROCEDURECOLUMN_RETURN;
			break;
		case COLUMNTYPENAME_STATE.PROCEDURECOLUMN_RESULT:
			this.columnTypeName = COLUMNTYPENAME_VALUE.PROCEDURECOLUMN_RESULT;
			break;
		default:
			this.columnTypeName = columnTypeName;
			break;
		}
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
	
	private static final class COLUMNTYPENAME_STATE{
		private static final int PROCEDURECOLUMN_UNKNOWN  = 0; 
		private static final int PROCEDURECOLUMN_IN  = 1; 
		private static final int PROCEDURECOLUMN_INOUT   = 2; 
		private static final int PROCEDURECOLUMN_RESULT  = 3; 
		private static final int PROCEDURECOLUMN_OUT   = 4; 
		private static final int PROCEDURECOLUMN_RETURN   = 5; 
	}
	
	private static final class COLUMNTYPENAME_VALUE{
		private static final String PROCEDURECOLUMN_UNKNOWN  = "没人知道"; 
		private static final String PROCEDURECOLUMN_IN  = "IN参数"; 
		private static final String PROCEDURECOLUMN_INOUT   = "INOUT参数"; 
		private static final String PROCEDURECOLUMN_RESULT  = "过程结果"; 
		private static final String PROCEDURECOLUMN_OUT   = "OUT参数"; 
		private static final String PROCEDURECOLUMN_RETURN   = "过程返回值"; 
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
