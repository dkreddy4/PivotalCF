package com.perficient;

public class DemoModel {
	
	private final String content;

	public DemoModel(long id, String content) {
		this.id = id;
		this.content = content;

	}

	private final long id;

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	

}
