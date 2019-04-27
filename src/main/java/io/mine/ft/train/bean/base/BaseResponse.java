package io.mine.ft.train.bean.base;

import java.io.Serializable;

public class BaseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 创建人
	 */
	private String creator;

	/**
	 * 创建时间
	 * 格式：yyyy-MM-dd HH:mm:ss
	 */
	private String gmtCreated;

	/**
	 * 修改人
	 */
	private String modifier;

	/**
	 * 修改时间
	 * 格式：yyyy-MM-dd HH:mm:ss
	 */
	private String gmtModified;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}
}
