package com.masai.blogApp.models;

public enum Category {

	//Technology, Art, Sports
	
	TECHNOLOGY("technology"),
	ART("art"),
	SPORTS("sports");
	
	private final String value;
	
	private Category(String value) {
		this.value=value;
	}
	public String getValue() {
		return value;
	}
}
