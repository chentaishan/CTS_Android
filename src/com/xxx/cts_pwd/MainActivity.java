package com.xxx.cts_pwd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.xxx.cts_pwd.utils.Contants;
import com.xxx.cts_pwd.utils.DbUtils;
import com.xxx.cts_pwd.utils.YoumiUtil;

/**
 * 类名: MainActivity</br> 包名：com.xxx.cts_pwd </br> 描述: </br> 发布版本号：</br> 开发人员：
 * 陈泰山</br> 创建时间： 2014-8-11
 */

public class MainActivity extends BaseActivity {

	private GridView grid;
	DbUtils util;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		YoumiUtil.initYoumiSetting(this);

		util = new DbUtils(this);

		String pwd = util.getPWD();

		if (!pwd.equals("-1")) {
			dialogDeal(true);
		} else {
			dialogDeal(false);
		}

	}
	private void initView() {
		setContentView(R.layout.main);
		initTitleView(this);
		left.setVisibility(View.GONE);
		right.setVisibility(View.GONE);
		title.setText("首页");
		grid = (GridView) findViewById(R.id.grid);

		// ########################################################################
		AdView adView = new AdView(this, AdSize.FIT_SCREEN);
		// 获取要嵌入广告条的布局
		LinearLayout adLayout = (LinearLayout) findViewById(R.id.adLayout);
		// 将广告条加入到布局中
		adLayout.addView(adView);

		// ########################################################################

		SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
				initData(), R.layout.grid_item, new String[]{"name", "icon"},
				new int[]{R.id.name, R.id.grid_icon});

		grid.setAdapter(adapter);
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Intent in;
				switch (position) {
					case 0 :
						in = new Intent(MainActivity.this, App_Activity.class);
						startActivity(in);
						Contants.hashmap.put("type", 0);
						break;
					case 1 :
						in = new Intent(MainActivity.this, Bank_Activity.class);
						startActivity(in);
						Contants.hashmap.put("type", 1);
						break;
					case 2 :
						in = new Intent(MainActivity.this,
								UserInfo_Activity.class);
						startActivity(in);
						Contants.hashmap.put("type", 2);
						break;
					case 3 :
						// in = new Intent(MainActivity.this, Setting.class);
						// startActivity(in);
						dialogDeal(false);
						break;
					case 4 :
						in = new Intent(MainActivity.this, DataManager.class);
						startActivity(in);
						Toast.makeText(getApplicationContext(), "建设中...",
								Toast.LENGTH_SHORT).show();
						break;
					case 5 :
						in = new Intent(MainActivity.this, Notice.class);
						startActivity(in);
						break;
				}
			}
		});
	}
	private List<HashMap<String, Object>> initData() {
		List<HashMap<String, Object>> lists = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "网站密码");
		map.put("icon", R.drawable.one);
		lists.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "银行密码");
		map.put("icon", R.drawable.two);
		lists.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "个人密码");
		map.put("icon", R.drawable.three);
		lists.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "设置密码");
		map.put("icon", R.drawable.five);
		lists.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "数据管理");
		map.put("icon", R.drawable.four);
		lists.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "声明");
		map.put("icon", R.drawable.six);
		lists.add(map);

		return lists;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == Contants.REQUEST_CODE
				&& resultCode == Contants.RESULT_CODE) {
			Log.i("XXX", "----------data---" + data.getStringExtra("content"));
		}
	}

	private void dialogDeal(final boolean isSettingPWD) {
		String title = "";
		if (isSettingPWD) {
			title = "请输入密码";
		} else {

			title = "请设置密码";
		}
		View view = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.dialog, null);

		final EditText editText = (EditText) view.findViewById(R.id.editText);

		new AlertDialog.Builder(this).setTitle(title)
				.setIcon(android.R.drawable.ic_dialog_info).setView(view)
				.setPositiveButton("确定", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String input = editText.getText().toString();
						if (isSettingPWD) {
							Boolean isCorrect = util.comparedPP(input);
							if (isCorrect) {
								initView();
							} else {
								dialogDeal(true);
								Toast.makeText(MainActivity.this, "密码错误",
										Toast.LENGTH_SHORT).show();
							}
						} else {
							util.settingMainPWD(input);
							initView();
						}
					}
				}).setNegativeButton("取消", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialogDeal(true);
					}
				}).show();
	}

}
