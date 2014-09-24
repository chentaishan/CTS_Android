package com.xxx.cts_pwd;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Notice extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notice);

		initTitleView(this);
		right.setVisibility(View.GONE);
		left.setVisibility(View.GONE);
		title.setText("声明");
		StringBuilder sb = new StringBuilder();

		TextView textView = (TextView) findViewById(R.id.notice);
		String notice = "本程序为了安全考虑，只会把数据保存到本地，以及本客户端使用AES 128bit 加密，相对安全，且没有链接网络,请大家放心使用,为了更好做好本软件，如果您有什么建议和意见，请联系QQ:1329596182";
		String one = "主要功能：";
		String two = "1.网站密码，主要保存经常使用的网站及App的信息";
		String three = "2.银行密码，主要保存银行卡的信息";
		String four = "3.个人密码，主要保存个人生活的信息";
		String five = "4.设置密码，设置登录密码";

		sb.append(notice + "\n");
		sb.append(one + "\n");
		sb.append(two + "\n");
		sb.append(three + "\n");
		sb.append(four + "\n");
		sb.append(five + "\n");

		textView.setText(sb.toString());

	}
}
