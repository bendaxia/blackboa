package org.blackboa.core.data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.blackboa.core.bean.view.DataViewColumn;
import org.blackboa.core.bean.view.DataViewInfo;
import org.blackboa.exceptions.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DataViewService {

	@Autowired
	@Qualifier("conn")
	private Connection conn;

	@Autowired
	@Qualifier("dbmd")
	private DatabaseMetaData dbmd;

	@Autowired
	@Qualifier("catalog")
	private String catalog;

	public DataViewInfo getDataViewInfo(String view) throws Exception {
		try {
			if (StringUtils.isEmpty(view)) {
				throw new ParamException("视图名为空!");
			}
			DataViewInfo dataViewInfo = new DataViewInfo();
			dataViewInfo.setViewName(view);
			this.printViewName(dataViewInfo);
			this.printColumns(dataViewInfo);
			this.printPrimaryKey(dataViewInfo);
			this.printForeignKey(dataViewInfo);
			this.printIndex(dataViewInfo);
			return dataViewInfo;
		}finally {
			this.conn.close();
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
	
	private void printViewName(DataViewInfo dataViewInfo) throws Exception {
		ResultSet resultSet = this.dbmd.getTables(null, "%", dataViewInfo.getViewName(), new String[] { "VIEW" });
		while (resultSet.next()) {
			String viewName = resultSet.getString("TABLE_NAME");
			if (viewName.equals(dataViewInfo.getViewName())) {
				dataViewInfo.setViewName(viewName);
				dataViewInfo.setViewImplication(resultSet.getString("Remarks"));
			}
		}
	}
	
	private void printColumns(DataViewInfo dataViewInfo) throws Exception {
		ResultSet rs = this.dbmd.getColumns(null, getSchema(this.conn), dataViewInfo.getViewName().toUpperCase(),null);
		while (rs.next()) {
			DataViewColumn dataViewColumn = new DataViewColumn();
			dataViewColumn.setColumnName(rs.getString("COLUMN_NAME"));
			dataViewColumn.setColumnImplication(rs.getString("REMARKS"));
			dataViewColumn.setColumnType(rs.getString("TYPE_NAME"));
			dataViewColumn.setColumnLength(StringUtils.isEmpty((Integer.valueOf(rs.getString("COLUMN_SIZE")) > 0 ?(StringUtils.isEmpty(rs.getString("DECIMAL_DIGITS"))||(Integer.valueOf(rs.getString("DECIMAL_DIGITS"))==0?true:false)?rs.getString("COLUMN_SIZE"):(rs.getString("COLUMN_SIZE")+","+rs.getString("DECIMAL_DIGITS"))):""))?"":"("+(Integer.valueOf(rs.getString("COLUMN_SIZE")) > 0 ?(StringUtils.isEmpty(rs.getString("DECIMAL_DIGITS"))||(Integer.valueOf(rs.getString("DECIMAL_DIGITS"))==0?true:false)?rs.getString("COLUMN_SIZE"):(rs.getString("COLUMN_SIZE")+","+rs.getString("DECIMAL_DIGITS"))):"")+")");
			dataViewColumn.setColumnDefault(rs.getString("COLUMN_DEF"));
			dataViewColumn.setColumnIsNull(Integer.valueOf(rs.getString("NULLABLE"))==1?"Y":"N");
			dataViewInfo.getDataViewColumns().add(dataViewColumn);
		}
	}
	
	/**
	 * 
	* @Function: DataViewService.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @author: ben
	* @date: 2019年3月18日 上午9:47:24
	 */
	private void printPrimaryKey(DataViewInfo dataViewInfo) throws Exception {
		ResultSet primaryKeyResultSet = this.dbmd.getPrimaryKeys(this.catalog, null,
				dataViewInfo.getViewName().toUpperCase());
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
		dataViewInfo.setViewPrimaryKey(str.toString());
	}
	
	/**
	 * 外键
	* @Function: DataViewService.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @author: ben
	* @date: 2019年3月18日 上午10:33:58
	 */
	private void printForeignKey(DataViewInfo dataViewInfo) throws Exception {
		ResultSet foreignKeyResultSet = this.dbmd.getImportedKeys(this.catalog, null,
				dataViewInfo.getViewName().toUpperCase());
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
		dataViewInfo.setViewForeignKey(str.toString());
	}
	
	/**
	 * 索引
	* @Function: DataViewService.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @author: ben
	* @date: 2019年3月18日 上午10:34:47
	 */
	private void printIndex(DataViewInfo dataViewInfo) throws Exception {
		ResultSet indexResultSet = this.dbmd.getIndexInfo(null,null,dataViewInfo.getViewName().toUpperCase(),false,false);
		StringBuilder str = new StringBuilder();
		HashSet<String> strs = new HashSet<>();
		while (indexResultSet.next()) {
			if(!StringUtils.isEmpty(indexResultSet.getString("COLUMN_NAME"))) {
				strs.add(indexResultSet.getString("COLUMN_NAME")+",");
			}
		}
		for(String s : strs) {
			str.append(s);
		}
		dataViewInfo.setViewIndex(str.toString());
	}
}
