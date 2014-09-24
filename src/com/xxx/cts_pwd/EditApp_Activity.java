/**   
 * 文件名：EditApp_Activity.java</br>
 * 描述： </br>
 * 开发人员：陈泰山</br>
 * 创建时间： 2014-9-9
 */

package com.xxx.cts_pwd;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.xxx.cts_pwd.domain.App;
import com.xxx.cts_pwd.domain.Bank;
import com.xxx.cts_pwd.domain.User;
import com.xxx.cts_pwd.utils.Contants;
import com.xxx.cts_pwd.utils.DbUtils;

/**
 * 类名: EditApp_Activity</br> 包名：com.xxx.cts_pwd </br> 描述: </br> 发布版本号：</br>
 * 开发人员： 陈泰山</br> 创建时间： 2014-9-9
 */

public class EditApp_Activity extends BaseActivity {

	private EditText webnameEdit, usernameEdit, pwdEdit, contentEdit,
			emailEdit, websiteEdit;
	Button button;
	boolean isSaved = false;
	// 实体id
	int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editapp);

		initTitleView(this);
		left.setVisibility(View.GONE);
		right.setVisibility(View.GONE);
		title.setText("编辑网站密码");

		webnameEdit = (EditText) findViewById(R.id.edit_webname);
		usernameEdit = (EditText) findViewById(R.id.edit_username);
		pwdEdit = (EditText) findViewById(R.id.edit_pwd);
		contentEdit = (EditText) findViewById(R.id.edit_content);
		emailEdit = (EditText) findViewById(R.id.edit_email);
		websiteEdit = (EditText) findViewById(R.id.edit_website);
		if (Contants.hashmap.get("type") == 1) {
			// 银行密码
			webnameEdit.setHint("银行名称");
			usernameEdit.setHint("银行卡号");
			pwdEdit.setHint("密码");
			emailEdit.setHint("邮箱");
			websiteEdit.setHint("手机密码");
			contentEdit.setHint("备注");

		} else if (Contants.hashmap.get("type") == 2) {
			// 个人密码
			webnameEdit.setHint("名称");
			usernameEdit.setHint("时间");
			pwdEdit.setHint("地点");
			contentEdit.setHint("备注");

			emailEdit.setVisibility(View.GONE);
			websiteEdit.setVisibility(View.GONE);
		}
		button = (Button) findViewById(R.id.button);

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!isSaved) {
					saveInputData();
					isSaved = true;
				}

			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (getIntent() != null) {
			// 网站密码
			if (Contants.hashmap.get("type") == 0) {

				App app = (App) getIntent().getSerializableExtra("app");
				if (app == null) {
					return;
				}
				id = app.getId();
				webnameEdit.setText(app.getWebname());
				usernameEdit.setText(app.getUsername());
				pwdEdit.setText(app.getPwd());
				contentEdit.setText(app.getContent());
				emailEdit.setText(app.getEmail());
				websiteEdit.setText(app.getWebsiteAddress());
				// 银行密码
			} else if (Contants.hashmap.get("type") == 1) {
				Bank bank = (Bank) getIntent().getSerializableExtra("bank");
				if (bank == null) {
					return;
				}
				id = bank.getId();
				webnameEdit.setText(bank.getName());
				usernameEdit.setText(bank.getBankNO());
				pwdEdit.setText(bank.getWebpwd());
				emailEdit.setText(bank.getEmail());
				websiteEdit.setText(bank.getMobilePwd());
				contentEdit.setText(bank.getContent());
			} else if (Contants.hashmap.get("type") == 2) {
				User user = (User) getIntent().getSerializableExtra("user");
				if (user == null) {
					return;
				}
				id = user.getId();
				webnameEdit.setText(user.getName());
				usernameEdit.setText(user.getTime());
				pwdEdit.setText(user.getAddress());
				contentEdit.setText(user.getContent());
			}
		}
	}

	/**
	 * 保存数据到数据库 描述: </br> 开发人员：陈泰山</br> 创建时间：2014-9-11</br>
	 */
	private void saveInputData() {

		String webname = webnameEdit.getText().toString();
		String username = usernameEdit.getText().toString();
		String pwd = pwdEdit.getText().toString();
		String content = contentEdit.getText().toString();
		String email = emailEdit.getText().toString();
		String website = websiteEdit.getText().toString();
		try {
			// 网站数据处理
			if (Contants.hashmap.get("type") == 0) {

				if (Contants.hashmap.get("addorupdate") == 0) {

					DbUtils.saveApp(webname,
							username, pwd, content, email, website);

				} else {
					DbUtils.updateApp(id, webname, username, pwd, content,
							email, website);
				}
				// 银行数据处理
			} else if (Contants.hashmap.get("type") == 1) {
				if (Contants.hashmap.get("addorupdate") == 0) {
					Bank bank = new Bank();
					bank.setName(webname);
					bank.setBankNO(username);
					bank.setWebpwd(pwd);

					bank.setMobilePwd(website);
					bank.setContent(content);

					bank.setEmail(email);

					DbUtils.addBankResult(bank);

				} else {
					Bank bank = new Bank();
					bank.setName(webname);
					bank.setBankNO(username);
					bank.setWebpwd(pwd);
					bank.setMobilePwd(website);
					bank.setContent(content);
					bank.setEmail(email);

					DbUtils.updateBankResult(bank, id);
				}
			} else if (Contants.hashmap.get("type") == 2) {
				if (Contants.hashmap.get("addorupdate") == 0) {
					User user = new User();
					user.setName(webname);
					user.setTime(username);
					user.setAddress(pwd);
					user.setContent(content);

					DbUtils.addUser(user);

				} else {
					User user = new User();
					user.setName(webname);
					user.setTime(username);
					user.setAddress(pwd);
					user.setContent(content);

					DbUtils.updateUser(user, id);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finish();
	}

}
