/**   
 * 文件名：DbUtils.java</br>
 * 描述： </br>
 * 开发人员：陈泰山</br>
 * 创建时间： 2014-9-9
 */

package com.xxx.cts_pwd.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.xxx.cts_pwd.db.Cts_sqlopenhelper;
import com.xxx.cts_pwd.domain.App;
import com.xxx.cts_pwd.domain.Bank;
import com.xxx.cts_pwd.domain.User;

/**
 * 类名: DbUtils</br> 包名：com.xxx.cts_pwd.utils </br> 描述: </br> 发布版本号：</br> 开发人员：
 * 陈泰山</br> 创建时间： 2014-9-9
 */

public class DbUtils {

	private static String subdbPath = "xxx.db";
	private static Context context;
	public static Cts_sqlopenhelper openHelper;
	public DbUtils(Context context) {
		super();
		this.context = context;

		// getRootPath();
		openHelper = new Cts_sqlopenhelper(context, getRootPath(), null, 1,
				null);
	}
	/**
	 * 
	 * 描述: 获取sd根目录</br> 开发人员：陈泰山</br> 创建时间：2014-9-9</br>
	 * 
	 * @return
	 */
	private String getRootPath() {
		String dbPath = null;
		if (getSDStatus()) {
			dbPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + File.separator + subdbPath;
			// File file = new File(dbPath);
			// if (!file.exists()) {
			// file.mkdirs();
			// }
		} else {
			dbPath = subdbPath;

		}
		return dbPath;
	}
	/**
	 * 
	 * 描述: </br>获取SD的状态 开发人员：陈泰山</br> 创建时间：2014-9-9</br>
	 * 
	 * @return
	 */
	private boolean getSDStatus() {
		// 判断sd卡是否存在
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		return sdCardExist;

	}
	/**
	 * 
	 * 描述: 获取本地内存路径</br> 开发人员：陈泰山</br> 创建时间：2014-9-11</br>
	 * 
	 * @return
	 */
	@SuppressLint("NewApi")
	private boolean getLocalSDStatus() {
		boolean status = Environment.getStorageState(new File("/")).equals(
				android.os.Environment.MEDIA_MOUNTED);
		return status;
	}
	/**
	 * 
	 * 描述: 获取app数据集合</br> 开发人员：陈泰山</br> 创建时间：2014-9-9</br>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<App> getAppsResult() {
		List<App> apps = new ArrayList<App>();
		SQLiteDatabase database = openHelper.getReadableDatabase();
		if (database.isOpen()) {
			Cursor cursor = database.query("App", null, null, null, null, null,
					null);
			while (cursor.moveToNext()) {
				App app = new App();
				// String name =
				// cursor.getColumnName(cursor.getColumnIndex("id"));
				try {
					int id = cursor.getInt(cursor.getColumnIndex("id"));
					
					int web = cursor.getColumnIndex("webname");
					String webname = AESUtil.decrypt(Contants.seed,
							cursor.getString(web));
					String username;
					username = AESUtil
							.decrypt(Contants.seed, cursor.getString(cursor
									.getColumnIndex("username")));
					String pwd = AESUtil.decrypt(Contants.seed,
							cursor.getString(cursor.getColumnIndex("pwd")));
					String content = AESUtil.decrypt(Contants.seed,
							cursor.getString(cursor.getColumnIndex("content")));
					String email = AESUtil.decrypt(Contants.seed,
							cursor.getString(cursor.getColumnIndex("email")));
					String websiteAddress = AESUtil.decrypt(Contants.seed,
							cursor.getString(cursor
									.getColumnIndex("websiteAddress")));
					app.setId(id);
					app.setWebname(webname);
					app.setUsername(username);
					app.setPwd(pwd);
					app.setContent(content);
					app.setEmail(email);
					app.setWebsiteAddress(websiteAddress);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				apps.add(app);
			}
		}
		return apps;
	}
	/**
	 * 
	 * 描述: 获取app详情</br> 开发人员：陈泰山</br> 创建时间：2014-9-14</br>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static App getAppResult(int id) throws Exception {
		App app = new App();
		SQLiteDatabase database = openHelper.getReadableDatabase();
		if (database.isOpen()) {
			Cursor cursor = database.query("App", null, "id=?", new String[]{id
					+ ""}, null, null, null);
			if (cursor.moveToNext()) {
				// String name =
				// cursor.getColumnName(cursor.getColumnIndex("id"));
				String webname = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("webname")));
				String username = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("username")));
				String pwd = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("pwd")));
				String content = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("content")));
				String email = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("email")));
				String websiteAddress = AESUtil.decrypt(Contants.seed, cursor
						.getString(cursor.getColumnIndex("websiteAddress")));
				app.setWebname(webname);
				app.setUsername(username);
				app.setPwd(pwd);
				app.setContent(content);
				app.setEmail(email);
				app.setWebsiteAddress(websiteAddress);
			}
		}
		return app;
	}
	public static void deleteApp(int id) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		db.delete("App", "id=?", new String[]{String.valueOf(id)});
	}
	/**
	 * 
	 * 描述: 修改app数据</br> 开发人员：陈泰山</br> 创建时间：2014-9-9</br>
	 * 
	 * @return
	 * @throws Exception
	 */

	public static void updateApp(int id, String webname, String username,
			String pwd, String content, String email, String websiteAddress)
			throws Exception {
		SQLiteDatabase database = openHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("webname", AESUtil.encrypt(Contants.seed, webname));
		values.put("username", AESUtil.encrypt(Contants.seed, username));
		values.put("pwd", AESUtil.encrypt(Contants.seed, pwd));
		values.put("content", AESUtil.encrypt(Contants.seed, content));
		values.put("email", AESUtil.encrypt(Contants.seed, email));
		values.put("websiteAddress",
				AESUtil.encrypt(Contants.seed, websiteAddress));
		database.update("App", values, "id=?", new String[]{id + ""});
	}
	/**
	 * 
	 * 描述: 保存app数据</br> 开发人员：陈泰山</br> 创建时间：2014-9-9</br>
	 * 
	 * @return
	 * @throws Exception
	 */

	public static void saveApp(String webname, String username, String pwd,
			String content, String email, String websiteAddress)
			throws Exception {
		SQLiteDatabase database = openHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

//		values.put("id",id);
		values.put("webname", AESUtil.encrypt(Contants.seed, webname));
		values.put("username", AESUtil.encrypt(Contants.seed, username));
		values.put("pwd", AESUtil.encrypt(Contants.seed, pwd));
		values.put("content", AESUtil.encrypt(Contants.seed, content));
		values.put("email", AESUtil.encrypt(Contants.seed, email));
		values.put("websiteAddress",
				AESUtil.encrypt(Contants.seed, websiteAddress));
		database.insert("App", null, values);
	}
	/**
	 * 
	 * 描述: 设置密码</br> 开发人员：陈泰山</br> 创建时间：2014-9-11</br>
	 * 
	 * @param pwd
	 */
	public void settingMainPWD(String pwd) {
		SharedPreferences.Editor editor = context.getSharedPreferences(
				"main_pwd", 0).edit();
		editor.putString("pwd", pwd).commit();
	}
	/**
	 * 
	 * 描述: 添加银行类数据</br> 开发人员：陈泰山</br> 创建时间：2014-9-18</br>
	 * 
	 * @throws Exception
	 */
	public static void addBankResult(Bank bank) throws Exception {
		SQLiteDatabase database = openHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("name", AESUtil.encrypt(Contants.seed, bank.getName()));
		values.put("BankNO", AESUtil.encrypt(Contants.seed, bank.getBankNO()));
		values.put("webpwd", AESUtil.encrypt(Contants.seed, bank.getWebpwd()));
		values.put("mobilePwd",
				AESUtil.encrypt(Contants.seed, bank.getMobilePwd()));
		values.put("email", AESUtil.encrypt(Contants.seed, bank.getEmail()));
		values.put("content", AESUtil.encrypt(Contants.seed, bank.getContent()));
		database.insert("Bank", null, values);
	}

	public static void deleteBankResult(int id) {
		SQLiteDatabase database = openHelper.getWritableDatabase();
		database.delete("Bank", "id=?", new String[]{String.valueOf(id)});

	}
	public static void updateBankResult(Bank bank, int id) throws Exception {
		SQLiteDatabase database = openHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("name", AESUtil.encrypt(Contants.seed, bank.getName()));
		values.put("BankNO", AESUtil.encrypt(Contants.seed, bank.getBankNO()));
		values.put("webpwd", AESUtil.encrypt(Contants.seed, bank.getWebpwd()));
		values.put("mobilePwd",
				AESUtil.encrypt(Contants.seed, bank.getMobilePwd()));
		values.put("email", AESUtil.encrypt(Contants.seed, bank.getEmail()));
		values.put("content", AESUtil.encrypt(Contants.seed, bank.getContent()));
		database.update("Bank", values, "id=?", new String[]{id + ""});
	}
	public static List<Bank> getBanksResult() throws Exception {
		List<Bank> banks = new ArrayList<Bank>();
		SQLiteDatabase database = openHelper.getReadableDatabase();
		if (database.isOpen()) {
			Cursor cursor = database.query("Bank", null, null, null, null,
					null, null);
			while (cursor.moveToNext()) {
				Bank bank = new Bank();
				// String name =
				// cursor.getColumnName(cursor.getColumnIndex("id"));
				String name = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("name")));
				String BankNO = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("BankNO")));
				String webpwd = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("webpwd")));
				String mobilePwd = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("mobilePwd")));
				String email = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("email")));
				String content = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("content")));
				bank.setName(name);
				bank.setBankNO(BankNO);
				bank.setWebpwd(webpwd);
				bank.setMobilePwd(mobilePwd);
				bank.setEmail(email);
				bank.setContent(content);

				banks.add(bank);
			}
		}
		return banks;
	}
	public static Bank getBankDetail(int id) throws Exception {
		SQLiteDatabase database = openHelper.getReadableDatabase();
		Bank bank = new Bank();
		if (database.isOpen()) {
			Cursor cursor = database.query("Bank", null, "id=?",
					new String[]{id + ""}, null, null, null);
			if (cursor.moveToNext()) {
				String name = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("name")));
				String BankNO = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("BankNO")));
				String webpwd = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("webpwd")));
				String mobilePwd = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("mobilePwd")));
				String email = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("email")));
				String content = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("content")));
				bank.setName(name);
				bank.setBankNO(BankNO);
				bank.setWebpwd(webpwd);
				bank.setMobilePwd(mobilePwd);
				bank.setEmail(email);
				bank.setContent(content);

			}
		}
		return bank;
	}

	/**
	 * 
	 * 描述:获取密码 </br> 开发人员：陈泰山</br> 创建时间：2014-9-11</br>
	 * 
	 * @return
	 */
	public String getPWD() {
		SharedPreferences editor = context.getSharedPreferences("main_pwd", 0);
		String pwd = editor.getString("pwd", "-1");
		return pwd;
	}
	public boolean comparedPP(String input) {
		if (input.equals(getPWD())) {
			return true;
		} else {

			return false;
		}
	}
	public static void addUser(User user) throws Exception {
		SQLiteDatabase database = openHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("name", AESUtil.encrypt(Contants.seed, user.getName()));
		values.put("time", AESUtil.encrypt(Contants.seed, user.getTime()));
		values.put("address", AESUtil.encrypt(Contants.seed, user.getAddress()));
		values.put("content", AESUtil.encrypt(Contants.seed, user.getContent()));
		database.insert("User", null, values);
	}
	public static void deleteUser(int id) {
		SQLiteDatabase database = openHelper.getWritableDatabase();
		database.delete("User", "id=?", new String[]{String.valueOf(id)});
	}
	public static void updateUser(User user, int id) throws Exception {
		SQLiteDatabase database = openHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("name", AESUtil.encrypt(Contants.seed, user.getName()));
		values.put("time", AESUtil.encrypt(Contants.seed, user.getTime()));
		values.put("address", AESUtil.encrypt(Contants.seed, user.getAddress()));
		values.put("content", AESUtil.encrypt(Contants.seed, user.getContent()));
		database.update("User", values, "id=?", new String[]{id + ""});
	}
	public static User getUserinfo(int id) throws Exception {

		User user = new User();
		SQLiteDatabase database = openHelper.getReadableDatabase();
		if (database.isOpen()) {
			Cursor cursor = database.query("User", null, "id=?",
					new String[]{id + ""}, null, null, null);
			if (cursor.moveToNext()) {
				// String name =
				// cursor.getColumnName(cursor.getColumnIndex("id"));
				String name = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("name")));
				String time = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("time")));
				String address = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("address")));
				String content = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("content")));
				user.setName(name);
				user.setTime(time);
				user.setAddress(address);
				user.setContent(content);
			}
		}
		return user;

	}
	public static List<User> getUsers() throws Exception {

		List<User> users = new ArrayList<User>();
		SQLiteDatabase database = openHelper.getReadableDatabase();
		if (database.isOpen()) {
			Cursor cursor = database.query("User", null, null, null, null,
					null, null);

			while (cursor.moveToNext()) {
				User user = new User();
				String name = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("name")));
				String time = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("time")));
				String address = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("address")));
				String content = AESUtil.decrypt(Contants.seed,
						cursor.getString(cursor.getColumnIndex("content")));
				user.setName(name);
				user.setTime(time);
				user.setAddress(address);
				user.setContent(content);
				users.add(user);
			}
		}
		return users;
	}

}
