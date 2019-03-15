package org.blackboa.core.data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.apache.commons.lang3.StringUtils;
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

	public void getDataViewInfo(String view) throws Exception {
		try {
			if (StringUtils.isEmpty(view)) {
				throw new ParamException("视图名为空!");
			}
			DataViewInfo dataViewInfo = new DataViewInfo();
			dataViewInfo.setViewName(view);
			this.printViewName(dataViewInfo);
		}finally {
			this.conn.close();
		}
	}
	
	private void printViewName(DataViewInfo dataViewInfo) throws Exception {
		ResultSet resultSet = this.dbmd.getTables(null, "%", dataViewInfo.getViewName(), new String[] { "VIEW" });
		while (resultSet.next()) {
			String viewName = resultSet.getString("TABLE_NAME");
			if (viewName.equals(dataViewInfo.getViewName())) {
				System.out.println(viewName);
			}
		}
	}

}
