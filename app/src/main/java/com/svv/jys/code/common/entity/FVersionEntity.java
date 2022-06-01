package com.svv.jys.code.common.entity;


import com.svv.jys.code.common.base.BaseEntity;

/**
 * 获取版本内容
 *
 * @author Administrator
 *
 */
public class FVersionEntity extends BaseEntity {


	/**
	 * 是否控制更新
	 */
	public Boolean versionControl;
	public String versionCode;
	public String versionName;
	public String downloadLink;
	public String changeLog;
	/**
	 * 是否强制更新
	 */
	public Boolean constraint;
	/**
	 * apk大小
	 */
	public String size;


	public Boolean getVersionControl() {
		return versionControl;
	}

	public void setVersionControl(Boolean versionControl) {
		this.versionControl = versionControl;
	}

	public Boolean getConstraint() {
		return constraint;
	}

	public void setConstraint(Boolean constraint) {
		this.constraint = constraint;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public String getChangeLog() {
		return changeLog;
	}

	public void setChangeLog(String changeLog) {
		this.changeLog = changeLog;
	}


	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
