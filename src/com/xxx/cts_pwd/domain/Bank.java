 
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

public class Bank implements Serializable{

	/**
	 * 描述:
	 */
	
	private static final long serialVersionUID = -2843484422071002705L;
	private int id;
	//银行名称
	private String name;
	//银行名称
	private String BankNO;
	//网站密码
	private String webpwd;
	//备注
	private String content;
	//手机密码
	private String mobilePwd;
	//邮箱
	private String email;
	
	
	
	public String getWebpwd() {
		return webpwd;
	}
	public void setWebpwd(String webpwd) {
		this.webpwd = webpwd;
	}
	public String getBankNO() {
		return BankNO;
	}
	public void setBankNO(String bankNO) {
		BankNO = bankNO;
	}
	 
	public String getMobilePwd() {
		return mobilePwd;
	}
	public void setMobilePwd(String mobilePwd) {
		this.mobilePwd = mobilePwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
