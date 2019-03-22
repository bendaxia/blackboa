package org.blackboa.core.bean.table;

public class DataTableColumn {
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

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
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
	
	public static final class COLUMNINDEX_STATE {
		public static final int UNKNOWN_INDEX = 0; 
		public static final int JJ_INDEX = 1; 
		public static final int HASH_INDEX = 2; 
		public static final int QT_INDEX = 3; 
	}
	
	public static final class COLUMNINDEX_VALUE {
		public static final String UNKNOWN_INDEX = "没有索引"; 
		public static final String JJ_INDEX = "聚集索引"; 
		public static final String HASH_INDEX = "哈希索引"; 
		public static final String QT_INDEX = "其他索引"; 
	}
}
