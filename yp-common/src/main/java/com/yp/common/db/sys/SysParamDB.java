package com.yp.common.db.sys;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 系统参数
 * @author zhiya.chai
 *
 */
public class SysParamDB implements Serializable{

	private static final long serialVersionUID = 4579927038107771697L;
	
	private int id;
	/**
	 * 参数key
	 */
	private String paramKey;
	/**
	 * 参数名称
	 */
	private String paramName;
	/**
	 * 参数值
	 */
	private String paramValue;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 参数是否可用 1=是 0=否
	 */
	private short isUse;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public short getIsUse() {
		return isUse;
	}
	public void setIsUse(short isUse) {
		this.isUse = isUse;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
