package org.blackboa.enums;

public enum HtmlTemplatePathEnum {
	TABLE("/htmlTemplate/tableTemplate.html"),
	VIEW("/htmlTemplate/viewTemplate.html"),
	PROCEDURE("/htmlTemplate/procedureTemplate.html"),
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
