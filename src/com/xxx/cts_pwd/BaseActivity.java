package com.xxx.cts_pwd;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 
 * 类名: BaseActivity</br> 
 * 包名：com.xxx.cts_pwd </br> 
 * 描述:基类 </br>
 * 发布版本号：</br>
 * 开发人员： 陈泰山</br>
 * 创建时间： 2014-8-11
 */
public class BaseActivity extends  Activity {

	protected TextView left,right,title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);

	}
	protected void initTitleView(Activity context) {
		left = (TextView)context.findViewById(R.id.left);
		right = (TextView)context.findViewById(R.id.right);
		title = (TextView)context.findViewById(R.id.title);
	}
}
