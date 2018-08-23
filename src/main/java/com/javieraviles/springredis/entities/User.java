package com.javieraviles.springredis.entities;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -305726463442998985L;

	private String name;

	private String email;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

}
