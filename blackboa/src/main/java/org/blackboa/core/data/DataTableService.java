package org.blackboa.core.data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.blackboa.core.bean.table.DataTableColumn;
import org.blackboa.core.bean.table.DataTableInfo;
import org.blackboa.exceptions.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DataTableService {
	
	@Autowired
	@Qualifier("conn")
	private Connection conn;
	
	@Autowired
	@Qualifier("dbmd")
	private DatabaseMetaData dbmd;
	
	@Autowired
	@Qualifier("catalog")
	private String catalog;
	
	/**
	 * 获取表信息
	 * 
	 * @Function: Data.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @author: ben
	 * @date: 2019年3月13日 下午12:54:15
	 */
	public DataTableInfo getDataTableInfo(String table) throws Exception {
		try {
			if (StringUtils.isEmpty(table)) {
				throw new ParamException("表名为空!");
			}
			DataTableInfo dataTableInfo = new DataTableInfo();
			dataTableInfo.setTableName(table);
			this.printTableName(dataTableInfo);
			this.printColumns(dataTableInfo);
			this.printPrimaryKey(dataTableInfo);
			this.printForeignKey(dataTableInfo);
			this.printIndex(dataTableInfo);
			return dataTableInfo;
		} finally {
//			this.conn.close();  工具自始至终只有一个连接，无需关闭.
		}
	}

	/**
	 * 其他数据库不需要这个方法 oracle和db2需要
	 * 
	 * @Function: Data.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @author: ben
	 * @date: 2019年3月13日 下午12:49:19
	 */
	private static String getSchema(Connection conn) throws Exception {
		String schema;
		schema = conn.getMetaData().getUserName();
		if ((schema == null) || (schema.length() == 0)) {
			throw new Exception("ORACLE数据库模式不允许为空");
		}
		return schema.toUpperCase().toString();

	}

	/**
	 * 获取表名
	 * 
	 * @Function: Data.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @author: ben
	 * @throws Exception
	 * @throws SQLException
	 * @date: 2019年3月13日 下午12:49:02
	 */
	private void printTableName(DataTableInfo dataTableInfo) throws Exception {
		ResultSet resultSet = this.dbmd.getTables(null, "%", dataTableInfo.getTableName(), new String[] { "TABLE" });
		while (resultSet.next()) {
			String tableName = resultSet.getString("TABLE_NAME");
			if (tableName.equals(dataTableInfo.getTableName())) {
				dataTableInfo.setTableName(tableName);
				dataTableInfo.setTableImplication(resultSet.getString("Remarks"));
			}
		}
	}

	/**
	 * 获取字段信息
	 * 
	 * @Function: Data.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @author: ben
	 * @date: 2019年3月13日 下午12:52:26
	 */
	private void printColumns(DataTableInfo dataTableInfo) throws Exception {
		ResultSet rs = this.dbmd.getColumns(null, getSchema(this.conn), dataTableInfo.getTableName().toUpperCase(),null);
		while (rs.next()) {
			DataTableColumn dataTableColumn = new DataTableColumn();
			dataTableColumn.setColumnName(rs.getString("COLUMN_NAME"));
			dataTableColumn.setColumnImplication(rs.getString("REMARKS"));
			dataTableColumn.setColumnType(rs.getString("TYPE_NAME"));
			dataTableColumn.setColumnLength(StringUtils.isEmpty((Integer.valueOf(rs.getString("COLUMN_SIZE")) > 0 ?(StringUtils.isEmpty(rs.getString("DECIMAL_DIGITS"))||(Integer.valueOf(rs.getString("DECIMAL_DIGITS"))==0?true:false)?rs.getString("COLUMN_SIZE"):(rs.getString("COLUMN_SIZE")+","+rs.getString("DECIMAL_DIGITS"))):""))?"":"("+(Integer.valueOf(rs.getString("COLUMN_SIZE")) > 0 ?(StringUtils.isEmpty(rs.getString("DECIMAL_DIGITS"))||(Integer.valueOf(rs.getString("DECIMAL_DIGITS"))==0?true:false)?rs.getString("COLUMN_SIZE"):(rs.getString("COLUMN_SIZE")+","+rs.getString("DECIMAL_DIGITS"))):"")+")");
			dataTableColumn.setColumnDefault(rs.getString("COLUMN_DEF"));
			dataTableColumn.setColumnIsNull(Integer.valueOf(rs.getString("NULLABLE"))==1?"Y":"N");
			dataTableInfo.getDataTableColumns().add(dataTableColumn);
		}
	}

	/**
	 * 主键
	 * 
	 * @Function: Data.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @author: ben
	 * @date: 2019年3月13日 下午12:53:21
	 */
	private void printPrimaryKey(DataTableInfo dataTableInfo) throws Exception {
		ResultSet primaryKeyResultSet = this.dbmd.getPrimaryKeys(this.catalog, null,
				dataTableInfo.getTableName().toUpperCase());
		StringBuilder str = new StringBuilder();
		HashSet<String> strs = new HashSet<>();
		while (primaryKeyResultSet.next()) {
			if(!StringUtils.isEmpty(primaryKeyResultSet.getString("COLUMN_NAME"))) {
				strs.add(primaryKeyResultSet.getString("COLUMN_NAME")+",");
			}
		}
		for(String s : strs) {
			str.append(s);
		}
		dataTableInfo.setTablePrimaryKey(str.toString());
	}

	/**
	 * 外键
	 * 
	 * @Function: Data.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @author: ben
	 * @date: 2019年3月13日 下午12:54:52
	 */
	private void printForeignKey(DataTableInfo dataTableInfo) throws Exception {
		ResultSet foreignKeyResultSet = this.dbmd.getImportedKeys(this.catalog, null,
				dataTableInfo.getTableName().toUpperCase());
		StringBuilder str = new StringBuilder();
		HashSet<String> strs = new HashSet<>();
		while (foreignKeyResultSet.next()) {
			if(!StringUtils.isEmpty(foreignKeyResultSet.getString("FKCOLUMN_NAME"))) {
				strs.add(foreignKeyResultSet.getString("FKCOLUMN_NAME")+",");
			}
		}
		for(String s : strs) {
			str.append(s);
		}
		dataTableInfo.setTableForeignKey(str.toString());
	}
	
	/**
	 * 索引
	* @Function: Data.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @author: ben
	* @date: 2019年3月13日 下午1:23:46
	 */
	private void printIndex(DataTableInfo dataTableInfo) throws Exception {
		ResultSet indexResultSet = this.dbmd.getIndexInfo(null,null,dataTableInfo.getTableName().toUpperCase(),false,false);
		StringBuilder str = new StringBuilder();
		HashSet<String> strs = new HashSet<>();
		while (indexResultSet.next()) {
			if(!StringUtils.isEmpty(indexResultSet.getString("COLUMN_NAME"))) {
				int index = indexResultSet.getInt("TYPE");
				String indexstr = "";
				switch(index){
					case 0:{
						indexstr="没有索引";
						break;
					}
					case 1:{
						indexstr="聚集索引";
						break;
					}
					case 2:{
						indexstr="哈希表索引";
						break;
					}
					case 3:{
						indexstr="其它索引";
						break;
					}
				}
				strs.add(indexResultSet.getString("COLUMN_NAME")+":"+indexstr+",");
			}
		}
		for(String s : strs) {
			str.append(s);
		}
		dataTableInfo.setTableIndex(str.toString());
	}
}
