
package com.xxx.cts_pwd.domain;

import java.io.Serializable;

/** 
 * 类名: App</br> 
 * 包名：com.xxx.cts_pwd.domain </br> 
 * 描述: </br>
 * 发布版本号：</br>
 * 开发人员： 陈泰山</br>
 * 创建时间： 2014-9-9 
 */

public class App implements Serializable {

	/**
	 * 描述:
	 */
	
	private static final long serialVersionUID = 8672094269870846332L;
	
	private int id;
	//网站名称
	private String webname;
	private String username;
	//密码
	private String pwd;
	//备注
	private String content;
	//email
	private String email;
	//网址
	private String websiteAddress;
	
	
	
	public String getWebname() {
		return webname;
	}
	public void setWebname(String webname) {
		this.webname = webname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getWebsiteAddress() {
		return websiteAddress;
	}
	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
