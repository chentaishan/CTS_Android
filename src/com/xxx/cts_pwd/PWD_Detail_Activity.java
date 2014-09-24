/**   
 * 文件名：Activity_1.java</br>
 * 描述： </br>
 * 开发人员：陈泰山</br>
 * 创建时间： 2014-8-12
 */

package com.xxx.cts_pwd;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xxx.cts_pwd.domain.App;
import com.xxx.cts_pwd.domain.Bank;
import com.xxx.cts_pwd.domain.User;
import com.xxx.cts_pwd.utils.Contants;
import com.xxx.cts_pwd.utils.DbUtils;
import com.xxx.cts_pwd.utils.YoumiUtil;

/**
 * 类名: Activity_1</br> 包名：com.xxx.cts_pwd </br> 描述: </br> 发布版本号：</br> 开发人员：
 * 陈泰山</br> 创建时间： 2014-8-12
 */

public class PWD_Detail_Activity extends BaseActivity {

	TextView content;
	App app;
	Bank bank;
	User user ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pwd_detail);

		YoumiUtil.initYoumiSetting(this);
		initView();
	}
	private void initView() {

		initTitleView(this);
		left.setVisibility(View.GONE);
		title.setText("密码详情");
		right.setText("编辑");

		content = (TextView) this.findViewById(R.id.content);
		
//		########################################################################
		AdView adView = new AdView(this, AdSize.FIT_SCREEN);
		// 获取要嵌入广告条的布局
		LinearLayout adLayout = (LinearLayout) this.findViewById(R.id.adLayout);
		// 将广告条加入到布局中
		adLayout.addView(adView);
		
//		########################################################################
	
		right.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Contants.hashmap.put("addorupdate", Contants.ISUPTE_APP_DATA);
				Intent in = new Intent(PWD_Detail_Activity.this,
						EditApp_Activity.class);
				if (Contants.hashmap.get("type") == 0) {
					in.putExtra("app", app);

				} else if (Contants.hashmap.get("type") == 1) {

					in.putExtra("bank", bank);
				}else if (Contants.hashmap.get("type") == 2) {

					in.putExtra("user", user);
				}
				startActivity(in);
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		SpotManager.getInstance(this).showSpotAds(this,
				new SpotDialogListener() {
					@Override
					public void onShowSuccess() {
						Log.i("Youmi", "onShowSuccess");
					}

					@Override
					public void onShowFailed() {
						Log.i("Youmi", "onShowFailed");
					}

					@Override
					public void onSpotClosed() {
						Log.e("sdkDemo", "closed");
					}
				});
		StringBuilder sb = new StringBuilder();
		if (getIntent() != null) {
			try {
				int position = getIntent().getIntExtra("position", -1);
				if (Contants.hashmap.get("type") == 0) {

					app = DbUtils.getAppResult(position);
					sb.append(app.getWebname() + "\n");
					sb.append(app.getUsername() + "\n");
					sb.append(app.getPwd() + "\n");
					sb.append(app.getContent() + "\n");
					sb.append(app.getEmail() + "\n");
					sb.append(app.getWebsiteAddress() + "\n");
				} else if (Contants.hashmap.get("type") == 1) {
					bank = DbUtils.getBankDetail(position);
					sb.append(bank.getName() + "\n");
					sb.append(bank.getBankNO() + "\n");
					sb.append(bank.getWebpwd() + "\n");
					sb.append(bank.getEmail() + "\n");
					sb.append(bank.getMobilePwd() + "\n");
					sb.append(bank.getContent() + "\n");
				}else if (Contants.hashmap.get("type") == 2) {
					user = DbUtils.getUserinfo(position);
					sb.append(user.getName() + "\n");
					sb.append(user.getTime() + "\n");
					sb.append(user.getAddress() + "\n");
					sb.append(user.getContent() + "\n");
				}
				content.setText(sb);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	protected void onDestroy() {
		SpotManager.getInstance(this).unregisterSceenReceiver();
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		// 如果有需要，可以点击后退关闭插屏广告（可选）。
		if (!SpotManager.getInstance(this).disMiss(true)) {
			super.onBackPressed();
		}
	}

	@Override
	protected void onStop() {
		// 如果不调用此方法，则按home键的时候会出现图标无法显示的情况。
		SpotManager.getInstance(this).disMiss(false);

		super.onStop();
	}
 

}
