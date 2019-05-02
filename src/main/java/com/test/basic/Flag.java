package com.test.basic;

public enum Flag {
	B("Buy", "Outgoing"), S("Sell", "Incoming");

	private String type;
	private String typeDesc;

	private Flag(String type, String typeDesc) {
		this.type = type;
		this.typeDesc = typeDesc;
	}

	public String getType() {
		return type;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

}
