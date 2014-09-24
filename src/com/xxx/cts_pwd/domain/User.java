/**   
 * 文件名：User.java</br>
 * 描述： </br>
 * 开发人员：陈泰山</br>
 * 创建时间： 2014-9-9
 */

package com.xxx.cts_pwd.domain;

import java.io.Serializable;

/**
 * 类名: User</br> 包名：com.xxx.cts_pwd.domain </br> 描述: </br> 发布版本号：</br> 开发人员：
 * 陈泰山</br> 创建时间： 2014-9-9
 */

public class User implements Serializable {
	
	private static final long serialVersionUID = 4956131396669745670L;
	private int id;
	// 名称
	private String name;
	private String time;

	private String address;
	private String content;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
