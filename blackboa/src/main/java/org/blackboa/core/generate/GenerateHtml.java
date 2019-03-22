package org.blackboa.core.generate;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.lang3.StringUtils;
import org.blackboa.core.bean.function.DataFunctionInfo;
import org.blackboa.core.bean.procedure.DataProcedureInfo;
import org.blackboa.core.bean.table.DataTableInfo;
import org.blackboa.core.bean.view.DataViewInfo;
import org.blackboa.enums.HtmlTemplatePathEnum;
import org.blackboa.utils.time.TimeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class GenerateHtml {
	
	@Value("${generate.path}")
	private String path;
	
	
	public void MakeTableHtml(String fileName,String title,String tableImplication,String author,DataTableInfo dataTableInfo) throws Exception {
			String templateContent = "";
			InputStream fileinputstream = this.getClass().getResourceAsStream(HtmlTemplatePathEnum.TABLE.toString());
			int lenght = fileinputstream.available();
			byte bytes[] = new byte[lenght];
			fileinputstream.read(bytes);
			fileinputstream.close();
			templateContent = new String(bytes,"UTF-8");
			templateContent = templateContent.replaceAll("###title###", title);
			templateContent = templateContent.replaceAll("###tableExplain###", title);
			templateContent = templateContent.replaceAll("###tableName###", dataTableInfo.getTableName());
			templateContent = templateContent.replaceAll("###introduce###", !StringUtils.isEmpty(tableImplication)?tableImplication:dataTableInfo.getTableImplication());
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
			fileame = path + "/" + fileame;// 生成的html文件保存路径。
			OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(fileame), "UTF-8");
			oStreamWriter.append(templateContent);
			oStreamWriter.close();
	}
	
	
	public void MakeViewHtml(String fileName,String title,String viewImplication,String author,DataViewInfo dataViewInfo) throws Exception {
		String templateContent = "";
		InputStream fileinputstream = this.getClass().getResourceAsStream(HtmlTemplatePathEnum.VIEW.toString());
		int lenght = fileinputstream.available();
		byte bytes[] = new byte[lenght];
		fileinputstream.read(bytes);
		fileinputstream.close();
		templateContent = new String(bytes,"UTF-8");
		templateContent = templateContent.replaceAll("###title###", title);
		templateContent = templateContent.replaceAll("###viewExplain###", title);
		templateContent = templateContent.replaceAll("###viewName###", dataViewInfo.getViewName());
		templateContent = templateContent.replaceAll("###introduce###", !StringUtils.isEmpty(viewImplication)?viewImplication:dataViewInfo.getViewImplication());
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
		fileame = path + "/" + fileame;// 生成的html文件保存路径。
		OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(fileame), "UTF-8");
		oStreamWriter.append(templateContent);
		oStreamWriter.close();
	}
	
	
	public void MakeProcedureHtml(String fileName,String title,String procedureImplication,String author,DataProcedureInfo dataProcedureInfo) throws Exception {
		String templateContent = "";
		InputStream fileinputstream = this.getClass().getResourceAsStream(HtmlTemplatePathEnum.PROCEDURE.toString());
		int lenght = fileinputstream.available();
		byte bytes[] = new byte[lenght];
		fileinputstream.read(bytes);
		fileinputstream.close();
		templateContent = new String(bytes,"UTF-8");
		templateContent = templateContent.replaceAll("###title###", title);
		templateContent = templateContent.replaceAll("###procedureName###", dataProcedureInfo.getProcedureName());
		templateContent = templateContent.replaceAll("###procedureExplain###", title);
		templateContent = templateContent.replaceAll("###procedureCat###", dataProcedureInfo.getProcedureCat());
		templateContent = templateContent.replaceAll("###procedureSchem###", dataProcedureInfo.getProcedureSchem());
		templateContent = templateContent.replaceAll("###procedureType###", dataProcedureInfo.getProcedureType());
		templateContent = templateContent.replaceAll("###introduce###", !StringUtils.isEmpty(procedureImplication)?procedureImplication:dataProcedureInfo.getProcedureImplication());
		templateContent = templateContent.replaceAll("###author###", author);
		templateContent = templateContent.replaceAll("###dateTime###", TimeUtils.getCurrentDay("yyyy-MM-dd hh:mm"));
		StringBuilder tr = new StringBuilder();
		for(int i = 0; i<dataProcedureInfo.getDataProcedureColumn().size();i++) {
			tr.append("<tr>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+(i+1)+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataProcedureInfo.getDataProcedureColumn().get(i).getColumnName()+"("+dataProcedureInfo.getDataProcedureColumn().get(i).getColumnTypeName()+")"+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataProcedureInfo.getDataProcedureColumn().get(i).getColumnImplication()+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataProcedureInfo.getDataProcedureColumn().get(i).getColumnType()+dataProcedureInfo.getDataProcedureColumn().get(i).getColumnLength()+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataProcedureInfo.getDataProcedureColumn().get(i).getColumnIsNull()+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b></b></td>\n");
			tr.append("<tr>\n");
		}
		templateContent = templateContent.replaceAll("###tr###", tr.toString());
		String fileame = fileName + ".html";
		fileame = path + "/" + fileame;// 生成的html文件保存路径。
		OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(fileame), "UTF-8");
		oStreamWriter.append(templateContent);
		oStreamWriter.close();
	}
	
	
	public void MakeFunctionHtml(String fileName,String title,String functionImplication,String author,DataFunctionInfo dataFunctionInfo) throws Exception {
		String templateContent = "";
		InputStream fileinputstream = this.getClass().getResourceAsStream(HtmlTemplatePathEnum.FUNCTIOPN.toString());
		int lenght = fileinputstream.available();
		byte bytes[] = new byte[lenght];
		fileinputstream.read(bytes);
		fileinputstream.close();
		templateContent = new String(bytes,"UTF-8");
		templateContent = templateContent.replaceAll("###title###", title);
		templateContent = templateContent.replaceAll("###functionName###", dataFunctionInfo.getFunctionName());
		templateContent = templateContent.replaceAll("###functionExplain###", title);
		templateContent = templateContent.replaceAll("###functionCat###", dataFunctionInfo.getFunctionCat());
		templateContent = templateContent.replaceAll("###functionSchem###", dataFunctionInfo.getFunctionSchem());
		templateContent = templateContent.replaceAll("###functionType###", dataFunctionInfo.getFunctionType());
		templateContent = templateContent.replaceAll("###introduce###", !StringUtils.isEmpty(functionImplication)?functionImplication:dataFunctionInfo.getFunctionImplication());
		templateContent = templateContent.replaceAll("###author###", author);
		templateContent = templateContent.replaceAll("###dateTime###", TimeUtils.getCurrentDay("yyyy-MM-dd hh:mm"));
		StringBuilder tr = new StringBuilder();
		for(int i = 0; i<dataFunctionInfo.getDataFunctionColumn().size();i++) {
			tr.append("<tr>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+(i+1)+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataFunctionInfo.getDataFunctionColumn().get(i).getColumnName()+"("+dataFunctionInfo.getDataFunctionColumn().get(i).getColumnTypeName()+")"+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataFunctionInfo.getDataFunctionColumn().get(i).getColumnImplication()+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataFunctionInfo.getDataFunctionColumn().get(i).getColumnType()+dataFunctionInfo.getDataFunctionColumn().get(i).getColumnLength()+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataFunctionInfo.getDataFunctionColumn().get(i).getColumnIsNull()+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b>"+dataFunctionInfo.getDataFunctionColumn().get(i).getColumnDef()+"</b></td>\n");
			tr.append("<td class=\"ziduan_td\"><b></b></td>\n");
			tr.append("<tr>\n");
		}
		templateContent = templateContent.replaceAll("###tr###", tr.toString());
		String fileame = fileName + ".html";
		fileame = path + "/" + fileame;// 生成的html文件保存路径。
		OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(fileame), "UTF-8");
		oStreamWriter.append(templateContent);
		oStreamWriter.close();
	}
}
