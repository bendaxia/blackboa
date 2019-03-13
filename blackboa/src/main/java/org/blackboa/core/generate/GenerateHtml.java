package org.blackboa.core.generate;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.blackboa.core.bean.DataTableInfo;
import org.blackboa.enums.HtmlModePathEnum;
import org.blackboa.utils.time.TimeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class GenerateHtml {
	
	@Value("${generate.table.html}")
	private String tablePath;
	
	
	public void MakeTableHtml(String fileName,String title,String tableImplication,String author,DataTableInfo dataTableInfo) {
		try {
			String templateContent = "";
			FileInputStream fileinputstream = new FileInputStream(HtmlModePathEnum.TABLE.toString());// 读取模板文件
			int lenght = fileinputstream.available();
			byte bytes[] = new byte[lenght];
			fileinputstream.read(bytes);
			fileinputstream.close();
			templateContent = new String(bytes);
			templateContent = templateContent.replaceAll("###title###", title);
			templateContent = templateContent.replaceAll("###tableExplain###", title);
			templateContent = templateContent.replaceAll("###tableName###", dataTableInfo.getTableName());
			templateContent = templateContent.replaceAll("###introduce###", tableImplication);
			templateContent = templateContent.replaceAll("###tablePrimaryKey###", dataTableInfo.getTablePrimaryKey());
			templateContent = templateContent.replaceAll("###tableIndex###", dataTableInfo.getTableIndex());
			templateContent = templateContent.replaceAll("###author###", author);
			templateContent = templateContent.replaceAll("###dateTime###", TimeUtils.getCurrentDay("yyyy-MM-dd hh:mm"));
			StringBuilder tr = new StringBuilder();
			for(int i = 1; i<dataTableInfo.getDataTableColumns().size();i++) {
				tr.append("<tr>\n");
				tr.append("<td class=\"ziduan_td\"><b>"+i+"</b></td>\n");
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
			FileOutputStream fileoutputstream = new FileOutputStream(fileame);// 建立文件输出流
			byte tag_bytes[] = templateContent.getBytes();
			fileoutputstream.write(tag_bytes);
			fileoutputstream.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.toString());
		}
	}
}
