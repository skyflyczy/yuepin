package com.yp.common.enums.user;
/**
 * 学历
 * @author zhiya.chai
 *
 */
public enum Degree {

	高中或中专((short)1),
	大专((short)2),
	本科((short)3),
	研究生((short)4),
	硕士((short)5),
	博士((short)6),
	博士后((short)7);
	
	private final short value;
	
	private Degree(short value) {
		this.value = value;
	}
	
	public short getValue(){
		return this.value;
	}
	
	
}
