/**   
 * 文件名：Cts_Application.java</br>
 * 描述： </br>
 * 开发人员：陈泰山</br>
 * 创建时间： 2014-8-11
 */ 

package com.xxx.cts_pwd;

import com.xxx.cts_pwd.utils.Contants;

import android.app.Application;

/** 
 * 类名: Cts_Application</br> 
 * 包名：com.xxx.cts_pwd </br> 
 * 描述: </br>
 * 发布版本号：</br>
 * 开发人员： 陈泰山</br>
 * 创建时间： 2014-8-11 
 */

public class Cts_Application extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
//		初始化数据
		Contants.hashmap.put("position", 1);
	}
	
}
