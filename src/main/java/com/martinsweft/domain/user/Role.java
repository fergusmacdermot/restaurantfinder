package com.martinsweft.domain.user;

public enum Role {
	SUPER_USER("ROLE_SUPER_USER"),ADMIN("ROLE_ADMIN"), VIEWER("ROLE_VIEWER"), MANAGER("ROLE_MANAGER"), REPORT("ROLE_REPORT");
	
	private String value;

	private Role(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
