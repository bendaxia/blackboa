package org.blackboa.controller;

import org.blackboa.common.Response;
import org.blackboa.core.bean.DataTableInfo;
import org.blackboa.core.data.DataTableService;
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
	private GenerateHtml generateHtml;

	@RequestMapping(value = "/table", method = RequestMethod.POST, produces = "application/json")
	public String searchSingleRiskEntry(
			@RequestParam(value = "table", required = true) String table,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "tableImplication", required = false) String tableImplication,
			@RequestParam(value = "fileName", required = true) String fileName,
			@RequestParam(value = "author", required = true) String author){
		try {
			DataTableInfo dataTableInfo = dataTableService.getDataTableInfo(table);
			generateHtml.MakeTableHtml(fileName, title,tableImplication,author, dataTableInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.errorInternal(e.getMessage());
		}
		return Response.ok();
	}
}
