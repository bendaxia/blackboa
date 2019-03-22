package org.blackboa.core.bean.function;

import java.util.ArrayList;
import java.util.List;

public class DataFunctionInfo {

	/**
	 * 函数名
	 */
	private String functionName;

	/**
	 * 函数说明
	 */
	private String functionImplication;

	/**
	 * 函数类型
	 */
	private String functionType;

	/**
	 * 函数所在数据库
	 */
	private String functionCat;

	/**
	 * 函数架构
	 */
	private String functionSchem;

	private List<DataFunctionColumn> dataFunctionColumn = new ArrayList<DataFunctionColumn>();

	public List<DataFunctionColumn> getDataFunctionColumn() {
		return dataFunctionColumn;
	}

	public void setDataFunctionColumn(List<DataFunctionColumn> dataFunctionColumn) {
		this.dataFunctionColumn = dataFunctionColumn;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionImplication() {
		return functionImplication;
	}

	public void setFunctionImplication(String functionImplication) {
		this.functionImplication = functionImplication;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		switch (Integer.valueOf(functionType)) {
		case FUNCTIONTYPE_STATE.UNKNOWN_FUNCTIONTYPE:
			this.functionType = FUNCTIONTYPE_VALUE.UNKNOWN_FUNCTIONTYPE;
			break;
		case FUNCTIONTYPE_STATE.PROCEDURE:
			this.functionType = FUNCTIONTYPE_VALUE.PROCEDURE;
			break;
		case FUNCTIONTYPE_STATE.FUNCTION:
			this.functionType = FUNCTIONTYPE_VALUE.FUNCTION;
			break;
		default:
			this.functionType = functionType;
			break;
		}
	}

	public String getFunctionCat() {
		return functionCat;
	}

	public void setFunctionCat(String functionCat) {
		this.functionCat = functionCat;
	}

	public String getFunctionSchem() {
		return functionSchem;
	}

	public void setFunctionSchem(String functionSchem) {
		this.functionSchem = functionSchem;
	}

	private static final class FUNCTIONTYPE_STATE {
		private static final int UNKNOWN_FUNCTIONTYPE = 0;
		private static final int PROCEDURE = 1;
		private static final int FUNCTION = 2;
	}

	private static final class FUNCTIONTYPE_VALUE {
		private static final String UNKNOWN_FUNCTIONTYPE = "无法得知";
		private static final String PROCEDURE = "不返回结果";
		private static final String FUNCTION = "返回结果";
	}
}
