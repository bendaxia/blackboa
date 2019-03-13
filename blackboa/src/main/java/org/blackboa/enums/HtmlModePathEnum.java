package org.blackboa.enums;

public enum HtmlModePathEnum {
	TABLE("src/main/resources//tableModeHtml.html");

	private String value;

	private HtmlModePathEnum(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(this.value);
	}

}
