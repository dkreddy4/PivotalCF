package com.perficient.domain;

public class Fortune {

	public Fortune() {
	}

	public Fortune(Long id, String text) {
		this.id = id;
		this.text = text;
	}

	private Long id;

	private String text;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
