package org.blackboa.enums;

public enum HtmlModePathEnum {
	TABLE("/tableModeHtml.html"),
	VIEW("/viewModeHtml.html");

	private String value;

	private HtmlModePathEnum(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(this.value);
	}

}
