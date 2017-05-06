package com.yp.common.enums;

public enum YesOrNo {
	
	是((short)1),
	否((short)0);
	
	private final short value;
	
	private YesOrNo(short value) {
		this.value = value;
	}
	
	public short getValue() {
		return this.value;
	} 

}
