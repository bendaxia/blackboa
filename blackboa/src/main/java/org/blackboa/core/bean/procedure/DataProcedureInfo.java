package org.blackboa.core.bean.procedure;

import java.util.ArrayList;
import java.util.List;

public class DataProcedureInfo {
	/**
	 * 存储过程名
	 */
	private String procedureName;

	/**
	 * 存储过程说明
	 */
	private String procedureImplication;

	/**
	 * 存储过程类别
	 */
	private String procedureCat;

	/**
	 * 存储过程模式
	 */
	private String procedureSchem;

	/**
	 * 存储过程种类
	 */
	private String procedureType;

	private List<DataProcedureColumn> dataProcedureColumn = new ArrayList<>(10);
	
	public List<DataProcedureColumn> getDataProcedureColumn() {
		return dataProcedureColumn;
	}

	public void setDataProcedureColumn(List<DataProcedureColumn> dataProcedureColumn) {
		this.dataProcedureColumn = dataProcedureColumn;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getProcedureImplication() {
		return procedureImplication;
	}

	public void setProcedureImplication(String procedureImplication) {
		this.procedureImplication = procedureImplication;
	}

	public String getProcedureCat() {
		return procedureCat;
	}

	public void setProcedureCat(String procedureCat) {
		this.procedureCat = procedureCat;
	}

	public String getProcedureSchem() {
		return procedureSchem;
	}

	public void setProcedureSchem(String procedureSchem) {
		this.procedureSchem = procedureSchem;
	}

	public String getProcedureType() {
		return procedureType;
	}

	public void setProcedureType(String procedureType) {
		this.procedureType = procedureType;
	}

}
