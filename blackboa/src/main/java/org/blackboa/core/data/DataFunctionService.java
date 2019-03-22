package org.blackboa.core.data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.apache.commons.lang3.StringUtils;
import org.blackboa.core.bean.function.DataFunctionColumn;
import org.blackboa.core.bean.function.DataFunctionInfo;
import org.blackboa.exceptions.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DataFunctionService {

	@Autowired
	@Qualifier("conn")
	private Connection conn;

	@Autowired
	@Qualifier("dbmd")
	private DatabaseMetaData dbmd;

	@Autowired
	@Qualifier("catalog")
	private String catalog;

	public DataFunctionInfo getDataFunctionInfo(String functionName) throws Exception {
		if (StringUtils.isEmpty(functionName)) {
			throw new ParamException("函数名为空!");
		}
		DataFunctionInfo dataFunctionInfo = new DataFunctionInfo();
		dataFunctionInfo.setFunctionName(functionName.toUpperCase());
		this.printFunctionName(dataFunctionInfo);
		this.printFunctionColumns(dataFunctionInfo);
		return dataFunctionInfo;
	}
	
	public void printFunctionName(DataFunctionInfo dataFunctionInfo) throws Exception {
		ResultSet rs = this.dbmd.getFunctions(catalog, null, dataFunctionInfo.getFunctionName());
		while (rs.next()) {
			String functionName = rs.getString("FUNCTION_NAME");
			if (functionName.equals(dataFunctionInfo.getFunctionName())) {
				dataFunctionInfo.setFunctionName(functionName);
				dataFunctionInfo.setFunctionCat(StringUtils.isEmpty(rs.getString("FUNCTION_CAT"))?"":rs.getString("FUNCTION_CAT"));
				dataFunctionInfo.setFunctionImplication(rs.getString("REMARKS"));
				dataFunctionInfo.setFunctionSchem(rs.getString("FUNCTION_SCHEM"));
				dataFunctionInfo.setFunctionType(rs.getString("FUNCTION_TYPE"));
			}
		}
	}
	
	public void printFunctionColumns(DataFunctionInfo dataFunctionInfo) throws Exception {
		ResultSet rss = this.dbmd.getFunctionColumns(null, getSchema(this.conn), dataFunctionInfo.getFunctionName(),
				null);
		while (rss.next()) {
			DataFunctionColumn dataFunctionColumn = new DataFunctionColumn();
			dataFunctionColumn.setColumnName(rss.getString("COLUMN_NAME"));
			dataFunctionColumn.setColumnTypeName(rss.getString("COLUMN_TYPE"));
			dataFunctionColumn.setColumnType(rss.getString("TYPE_NAME"));
			dataFunctionColumn.setColumnLength(StringUtils.isEmpty(rss.getString("LENGTH"))?"":"("+rss.getString("LENGTH")+")");
			dataFunctionColumn.setColumnImplication(rss.getString("REMARKS"));
			dataFunctionColumn.setColumnIsNull(rss.getString("NULLABLE"));
			dataFunctionColumn.setColumnDef(StringUtils.isEmpty(rss.getString("COLUMN_DEF"))?"":rss.getString("COLUMN_DEF"));
			dataFunctionInfo.getDataFunctionColumn().add(dataFunctionColumn);
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
