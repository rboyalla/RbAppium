package org.boyalla.util;

public enum SysConstants {

	userHome("user.home"), userDir("user.dir");

	private final String __value;

	private SysConstants(String value) {
		this.__value = value;
	}

	public String value() {
		return __value;
	}

	@Override
	public String toString() {
		return __value;
	}
}
