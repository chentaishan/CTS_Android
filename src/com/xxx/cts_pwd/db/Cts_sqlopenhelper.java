/**   
 * 文件名：Cts_sqlopenhelper.java</br>
 * 描述： </br>
 * 开发人员：陈泰山</br>
 * 创建时间： 2014-8-11
 */ 

package com.xxx.cts_pwd.db;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/** 
 * 类名: Cts_sqlopenhelper</br> 
 * 包名：com.xxx.cts_pwd.db </br> 
 * 描述: </br>
 * 发布版本号：</br>
 * 开发人员： 陈泰山</br>
 * 创建时间： 2014-8-11 
 */

public class Cts_sqlopenhelper extends SQLiteOpenHelper {


	/** 
	 * 描述: </br>
	 * 开发人员：陈泰山</br>
	 * 创建时间：2014-8-11</br>
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @param errorHandler
	 */ 
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public Cts_sqlopenhelper(Context context, String name,
			CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 * 开发人员：陈泰山
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table if not exists App("
				+ "id integer primary key,"
				+ "webname varchar,"
				+ "username varchar,"
				+ "email varchar,"
				+ "websiteAddress varchar,"
				+ "pwd varchar,"
				+ "content varchar)");  
		db.execSQL("create table if not exists Bank("
				+ "id integer primary key,"
				+ "name varchar,"
				+ "BankNO varchar,"
				+ "webpwd varchar,"
				+ "mobilePwd varchar,"
				+ "email varchar,"
				+ "content varchar)");  
		db.execSQL("create table if not exists User("
				+ "id integer primary key,"
				+ "name varchar,"
				+ "time varchar,"
				+ "address varchar,"
				+ "content varchar)");  
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 * 开发人员：陈泰山
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
