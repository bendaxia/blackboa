package org.blackboa.controller;

import org.blackboa.common.Response;
import org.blackboa.core.bean.function.DataFunctionInfo;
import org.blackboa.core.bean.procedure.DataProcedureInfo;
import org.blackboa.core.bean.table.DataTableInfo;
import org.blackboa.core.bean.view.DataViewInfo;
import org.blackboa.core.data.DataFunctionService;
import org.blackboa.core.data.DataProcedureService;
import org.blackboa.core.data.DataTableService;
import org.blackboa.core.data.DataViewService;
import org.blackboa.core.generate.GenerateHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackboa/data")
public class DataController {
	@Autowired
	private DataTableService dataTableService;
	@Autowired
	private DataViewService dataViewService;
	@Autowired
	private DataProcedureService dataProcedureService;
	@Autowired
	private DataFunctionService dataFunctionService;
	@Autowired
	private GenerateHtml generateHtml;

	@RequestMapping(value = "/table", method = RequestMethod.POST, produces = "application/json")
	public String table(@RequestParam(value = "table", required = true) String table,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "tableImplication", required = false) String tableImplication,
			@RequestParam(value = "fileName", required = true) String fileName,
			@RequestParam(value = "author", required = true) String author) {
		try {
			DataTableInfo dataTableInfo = dataTableService.getDataTableInfo(table);
			generateHtml.MakeTableHtml(fileName, title, tableImplication, author, dataTableInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.errorInternal(e.getMessage());
		}
		return Response.ok();
	}

	@RequestMapping(value = "/view", method = RequestMethod.POST, produces = "application/json")
	public String view(@RequestParam(value = "view", required = true) String view,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "viewImplication", required = false) String viewImplication,
			@RequestParam(value = "fileName", required = true) String fileName,
			@RequestParam(value = "author", required = true) String author) {
		try {
			DataViewInfo dataViewInfo = dataViewService.getDataViewInfo(view);
			generateHtml.MakeViewHtml(fileName, title, viewImplication, author, dataViewInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.errorInternal(e.getMessage());
		}
		return Response.ok();
	}

	@RequestMapping(value = "/procedure", method = RequestMethod.POST, produces = "application/json")
	public String procedure(@RequestParam(value = "procedure", required = true) String procedure,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "procedureImplication", required = false) String procedureImplication,
			@RequestParam(value = "fileName", required = true) String fileName,
			@RequestParam(value = "author", required = true) String author) {
		try {
			DataProcedureInfo dataProcedureInfo = dataProcedureService.getDataProcedureInfo(procedure);
			generateHtml.MakeProcedureHtml(fileName, title, procedureImplication, author, dataProcedureInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.errorInternal(e.getMessage());
		}
		return Response.ok();
	}
	
	@RequestMapping(value = "/function", method = RequestMethod.POST, produces = "application/json")
	public String function(@RequestParam(value = "function", required = true) String function,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "functionImplication", required = false) String functionImplication,
			@RequestParam(value = "fileName", required = true) String fileName,
			@RequestParam(value = "author", required = true) String author) {
		try {
			DataFunctionInfo dataFunctionInfo = this.dataFunctionService.getDataFunctionInfo(function);
			generateHtml.MakeFunctionHtml(fileName, title, functionImplication, author, dataFunctionInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.errorInternal(e.getMessage());
		}
		return Response.ok();
	}
}
