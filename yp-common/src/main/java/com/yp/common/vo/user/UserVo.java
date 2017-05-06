package com.yp.common.vo.user;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户传输类
 * @author zhiya.chai
 * 2016年9月3日 下午6:38:43
 */
public class UserVo implements Serializable{
	/**
	 * 2016年9月3日 下午6:38:54
	 */
	private static final long serialVersionUID = -1916277547201084730L;
	
	/**
	 * 主键ID
	 */
	private java.lang.Integer id;
	/**
	 * guid 查询用
	 */
	private java.lang.String userGuid;
	/**
	 * 手机号（加密）
	 */
	private java.lang.String phoneNo;
	/**
	 * 密码盐
	 */
	private String salt;
	/**
	 * 登录密码（加密）
	 */
	private java.lang.String password;
	/**
	 * 用户名
	 */
	private java.lang.String userName;
	/**
	 * 头像Id
	 */
	private int headPicId;
	/**
	 * 性别（0：女 1：男）
	 */
	private short sex;
	/**
	 * 出生年
	 */
	private String bornYear;
	/**
	 * 出生月
	 */
	private String bornMonth;
	/**
	 * 学历
	 */
	private short degree;
	/**
	 * 个性签名
	 */
	private java.lang.String signature;
	/**
	 * 所属省份
	 */
	private java.lang.Integer provinceId;
	/**
	 * 城市Id
	 */
	private java.lang.Integer cityId;
	/**
	 * districtId
	 */
	private java.lang.Integer districtId;
	/**
	 * 级别Id
	 */
	private java.lang.Integer levelId;
	/**
	 * score
	 */
	private java.lang.Integer score;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;
	/**
	 * 版本号
	 */
	private java.lang.Long versionNo;
	
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.String getUserGuid() {
		return userGuid;
	}
	public void setUserGuid(java.lang.String userGuid) {
		this.userGuid = userGuid;
	}
	public java.lang.String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(java.lang.String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public java.lang.String getPassword() {
		return password;
	}
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	public java.lang.String getUserName() {
		return userName;
	}
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	public int getHeadPicId() {
		return headPicId;
	}
	public void setHeadPicId(int headPicId) {
		this.headPicId = headPicId;
	}
	public short getSex() {
		return sex;
	}
	public void setSex(short sex) {
		this.sex = sex;
	}
	public String getBornYear() {
		return bornYear;
	}
	public void setBornYear(String bornYear) {
		this.bornYear = bornYear;
	}
	public String getBornMonth() {
		return bornMonth;
	}
	public void setBornMonth(String bornMonth) {
		this.bornMonth = bornMonth;
	}
	public short getDegree() {
		return degree;
	}
	public void setDegree(short degree) {
		this.degree = degree;
	}
	public java.lang.String getSignature() {
		return signature;
	}
	public void setSignature(java.lang.String signature) {
		this.signature = signature;
	}
	public java.lang.Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(java.lang.Integer provinceId) {
		this.provinceId = provinceId;
	}
	public java.lang.Integer getCityId() {
		return cityId;
	}
	public void setCityId(java.lang.Integer cityId) {
		this.cityId = cityId;
	}
	public java.lang.Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(java.lang.Integer districtId) {
		this.districtId = districtId;
	}
	public java.lang.Integer getLevelId() {
		return levelId;
	}
	public void setLevelId(java.lang.Integer levelId) {
		this.levelId = levelId;
	}
	public java.lang.Integer getScore() {
		return score;
	}
	public void setScore(java.lang.Integer score) {
		this.score = score;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public java.lang.Long getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(java.lang.Long versionNo) {
		this.versionNo = versionNo;
	}

}
