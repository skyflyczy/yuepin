package com.yp.common.enums.user;
/**
 * 性别
 * @author zhiya.chai
 *
 */
public enum SexType {

	女((short)0),
	男((short)1);
	
	private final short type;
	
	private SexType (short type) {
		this.type = type;
	}
	
	public short getType() {
		return this.type;
	}
}
