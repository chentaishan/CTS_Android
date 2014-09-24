/**   
 * 文件名：Contants.java</br>
 * 描述： </br>
 * 开发人员：陈泰山</br>
 * 创建时间： 2014-8-11
 */

package com.xxx.cts_pwd.utils;

import java.util.HashMap;

/**
 * 类名: Contants</br> 包名：com.xxx.cts_pwd.utils </br> 描述: </br> 发布版本号：</br> 开发人员：
 * 陈泰山</br> 创建时间： 2014-8-11
 */

public class Contants {

	public static final int REQUEST_CODE = 11;
	public static final int RESULT_CODE = 12;

	public static final String seed = "CTS";

	//add data of apppwd
	public static final int ISADD_APP_DATA = 0;
	//update data of apppwd
	public static final int ISUPTE_APP_DATA = 1;

	//addorupdate
	//position
	//type 0=网站 ;1= 银行 ; 2 = 个人
	public static HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
	
}
