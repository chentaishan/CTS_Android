/**   
 * 文件名：Activity_1.java</br>
 * 描述： </br>
 * 开发人员：陈泰山</br>
 * 创建时间： 2014-8-12
 */

package com.xxx.cts_pwd;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xxx.cts_pwd.domain.App;
import com.xxx.cts_pwd.utils.Contants;
import com.xxx.cts_pwd.utils.DbUtils;

/**
 * 类名: Activity_1</br> 包名：com.xxx.cts_pwd </br> 描述: </br> 发布版本号：</br> 开发人员：
 * 陈泰山</br> 创建时间： 2014-8-12
 */

public class App_Activity extends BaseActivity {

	List<App> lists;
	ListView listview;
	List<String> names = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app);

	}

	/**
	 * 描述: </br> 开发人员：陈泰山</br> 创建时间：2014-9-11</br>
	 * 
	 * @return
	 */
	private List<String> getAppData() {
		// TODO Auto-generated method stub

		try {
			DbUtils util = new DbUtils(this);
			lists = util.getAppsResult();
			names = new ArrayList<String>();
			for (int x = 0; x < lists.size(); x++) {
				names.add(lists.get(x).getWebname());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			names = new ArrayList<String>();
			return names;
		}
		return names;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		initTitleView(this);

		left.setVisibility(View.GONE);
		right.setVisibility(View.GONE);
		title.setText("网站数据");

		listview = (ListView) findViewById(R.id.listview);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, getAppData());

		listview.setAdapter(adapter);
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Contants.hashmap.put("addorupdate", Contants.ISADD_APP_DATA);
				Intent in = new Intent(App_Activity.this,
						EditApp_Activity.class);
				startActivity(in);
			}
		});
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Contants.hashmap.put("addorupdate", Contants.ISUPTE_APP_DATA);
				Contants.hashmap.put("position", position + 1);
				Contants.hashmap.put("type", 0);
				Intent in = new Intent(App_Activity.this,
						PWD_Detail_Activity.class);
				in.putExtra("position", position + 1);
				startActivity(in);
			}
		});
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {

				AlertDialog.Builder builder = new AlertDialog.Builder(
						App_Activity.this);
				builder.setMessage("Are you sure you want to delete this item?")
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {

										DbUtils.deleteApp(position + 1);
										names.remove(position);
										adapter.notifyDataSetChanged();
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});
				builder.create().show();

				return true;
			}
		});
	}

}
