package org.blackboa.enums;

public enum HtmlTemplatePathEnum {
	/**
	 * 表
	 */
	TABLE("/htmlTemplate/tableTemplate.html"),
	/**
	 * 视图
	 */
	VIEW("/htmlTemplate/viewTemplate.html"),
	/**
	 * 存储过程
	 */
	PROCEDURE("/htmlTemplate/procedureTemplate.html"),
	/**
	 * 函数
	 */
	FUNCTIOPN("/htmlTemplate/functionTemplate.html");

	private String value;

	private HtmlTemplatePathEnum(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(this.value);
	}

}
