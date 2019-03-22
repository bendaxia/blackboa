package org.blackboa.core.data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.apache.commons.lang3.StringUtils;
import org.blackboa.core.bean.procedure.DataProcedureColumn;
import org.blackboa.core.bean.procedure.DataProcedureInfo;
import org.blackboa.exceptions.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DataProcedureService {

	@Autowired
	@Qualifier("conn")
	private Connection conn;

	@Autowired
	@Qualifier("dbmd")
	private DatabaseMetaData dbmd;

	@Autowired
	@Qualifier("catalog")
	private String catalog;

	public DataProcedureInfo getDataProcedureInfo(String procedureName) throws Exception {
		if (StringUtils.isEmpty(procedureName)) {
			throw new ParamException("存储过程名为空!");
		}
		DataProcedureInfo dataProcedureInfo = new DataProcedureInfo();
		dataProcedureInfo.setProcedureName(procedureName.toUpperCase());
		this.printProcedureName(dataProcedureInfo);
		this.printProcedureColumns(dataProcedureInfo);
		return dataProcedureInfo;
	}

	public void printProcedureName(DataProcedureInfo dataProcedureInfo) throws Exception {
		ResultSet rs = this.dbmd.getProcedures(catalog, null, dataProcedureInfo.getProcedureName());
		while (rs.next()) {
			String procedureName = rs.getString("PROCEDURE_NAME");
			if (procedureName.equals(dataProcedureInfo.getProcedureName())) {
				dataProcedureInfo.setProcedureName(procedureName);
				dataProcedureInfo.setProcedureCat(StringUtils.isEmpty(rs.getString("PROCEDURE_CAT"))?"":rs.getString("PROCEDURE_CAT"));
				dataProcedureInfo.setProcedureImplication(rs.getString("REMARKS"));
				dataProcedureInfo.setProcedureSchem(rs.getString("PROCEDURE_SCHEM"));
				dataProcedureInfo.setProcedureType(rs.getString("PROCEDURE_TYPE"));
			}
		}
	}

	public void printProcedureColumns(DataProcedureInfo dataProcedureInfo) throws Exception {
		ResultSet rss = this.dbmd.getProcedureColumns(null, getSchema(this.conn), dataProcedureInfo.getProcedureName(),
				null);
		while (rss.next()) {
			DataProcedureColumn dataProcedureColumn = new DataProcedureColumn();
			dataProcedureColumn.setColumnName(rss.getString("COLUMN_NAME"));
			dataProcedureColumn.setColumnTypeName(rss.getString("COLUMN_TYPE"));
			dataProcedureColumn.setColumnType(rss.getString("TYPE_NAME"));
			dataProcedureColumn.setColumnLength(StringUtils.isEmpty(rss.getString("LENGTH"))?"":"("+rss.getString("LENGTH")+")");
			dataProcedureColumn.setColumnImplication(rss.getString("REMARKS"));
			dataProcedureColumn.setColumnIsNull(rss.getString("NULLABLE"));
			dataProcedureInfo.getDataProcedureColumn().add(dataProcedureColumn);
		}
	}

	private static String getSchema(Connection conn) throws Exception {
		String schema;
		schema = conn.getMetaData().getUserName();
		if ((schema == null) || (schema.length() == 0)) {
			throw new Exception("ORACLE数据库模式不允许为空");
		}
		return schema.toUpperCase().toString();
	}
}
