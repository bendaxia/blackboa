package org.blackboa.core.generate;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import org.blackboa.core.bean.table.DataTableInfo;
import org.blackboa.core.bean.view.DataViewInfo;
import org.blackboa.enums.HtmlModePathEnum;
import org.blackboa.utils.time.TimeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class GenerateHtml {
	
	@Value("${generate.table.html}")
	private String tablePath;
	
	
	public void MakeTableHtml(String fileName,String title,String tableImplication,String author,DataTableInfo dataTableInfo) throws Exception {
			String templateContent = "";
			InputStream fileinputstream = this.getClass().getResourceAsStream(HtmlModePathEnum.TABLE.toString());
			int lenght = fileinputstream.available();
			byte bytes[] = new byte[lenght];
			fileinputstream.read(bytes);
			fileinputstream.close();
			templateContent = new String(bytes,"UTF-8");
			templateContent = templateContent.replaceAll("###title###", title);
			templateContent = templateContent.replaceAll("###tableExplain###", title);
			templateContent = templateContent.replaceAll("###tableName###", dataTableInfo.getTableName());
			templateContent = templateContent.replaceAll("###introduce###", tableImplication);
			templateContent = templateContent.replaceAll("###tablePrimaryKey###", dataTableInfo.getTablePrimaryKey());
			templateContent = templateContent.replaceAll("###tableIndex###", dataTableInfo.getTableIndex());
			templateContent = templateContent.replaceAll("###author###", author);
			templateContent = templateContent.replaceAll("###dateTime###", TimeUtils.getCurrentDay("yyyy-MM-dd hh:mm"));
			StringBuilder tr = new StringBuilder();
			for(int i = 0; i<dataTableInfo.getDataTableColumns().size();i++) {
				tr.append("<tr>\n");
				tr.append("<td class=\"ziduan_td\"><b>"+(i+1)+"</b></td>\n");
				tr.append("<td class=\"ziduan_td\"><b>"+dataTableInfo.getDataTableColumns().get(i).getColumnName()+"</b></td>\n");
				tr.append("<td class=\"ziduan_td\"><b>"+dataTableInfo.getDataTableColumns().get(i).getColumnImplication()+"</b></td>\n");
				tr.append("<td class=\"ziduan_td\"><b>"+dataTableInfo.getDataTableColumns().get(i).getColumnType()+dataTableInfo.getDataTableColumns().get(i).getColumnLength()+"</b></td>\n");
				tr.append("<td class=\"ziduan_td\"><b>"+dataTableInfo.getDataTableColumns().get(i).getColumnIsNull()+"</b></td>\n");
				tr.append("<td class=\"ziduan_td\"><b>"+dataTableInfo.getDataTableColumns().get(i).getColumnDefault()+"</b></td>\n");
				tr.append("<td class=\"ziduan_td\"><b></b></td>\n");
				tr.append("<tr>\n");
			}
			templateContent = templateContent.replaceAll("###tr###", tr.toString());
			String fileame = fileName + ".html";
			fileame = tablePath + "/" + fileame;// 生成的html文件保存路径。
			OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(fileame), "UTF-8");
			oStreamWriter.append(templateContent);
			oStreamWriter.close();
			
	}
	
	
	public void MakeViewHtml(String fileName,String title,String viewImplication,String author,DataViewInfo dataViewInfo) throws Exception {
		String templateContent = "";
		InputStream fileinputstream = this.getClass().getResourceAsStream(HtmlModePathEnum.VIEW.toString());
		int lenght = fileinputstream.available();
		byte bytes[] = new byte[lenght];
		fileinputstream.read(bytes);
		fileinputstream.close();
		templateContent = new String(bytes,"UTF-8");
		templateContent = templateContent.replaceAll("###title###", title);
		templateContent = templateContent.replaceAll("###viewExplain###", title);
		templateContent = templateContent.replaceAll("###viewName###", dataViewInfo.getViewName());
		templateContent = templateContent.replaceAll("###introduce###", viewImplication);
		templateContent = templateContent.replaceAll("###viewPrimaryKey###", dataViewInfo.getViewPrimaryKey());
		templateContent = templateContent.replaceAll("###author###", author);
		templateContent = templateContent.replaceAll("###dateTime###", TimeUtils.getCurrentDay("yyyy-MM-dd hh:mm"));
		StringBuilder tr = new StringBuilder();
		for(int i = 0; i<dataViewInfo.getDataViewColumns().size();i++) {
			tr.append("<tr>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+(i+1)+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataViewInfo.getDataViewColumns().get(i).getColumnName()+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataViewInfo.getDataViewColumns().get(i).getColumnImplication()+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataViewInfo.getDataViewColumns().get(i).getColumnType()+dataViewInfo.getDataViewColumns().get(i).getColumnLength()+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataViewInfo.getDataViewColumns().get(i).getColumnIsNull()+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataViewInfo.getDataViewColumns().get(i).getColumnDefault()+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b></b></td>\n");
			tr.append("<tr>\n");
		}
		templateContent = templateContent.replaceAll("###tr###", tr.toString());
		String fileame = fileName + ".html";
		fileame = tablePath + "/" + fileame;// 生成的html文件保存路径。
		OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(fileame), "UTF-8");
		oStreamWriter.append(templateContent);
		oStreamWriter.close();
		
	}
}
