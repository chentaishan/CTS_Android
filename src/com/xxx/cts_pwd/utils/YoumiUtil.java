/**   
 * 文件名：YoumiUtil.java</br>
 * 描述： </br>
 * 开发人员：陈泰山</br>
 * 创建时间： 2014-9-19
 */

package com.xxx.cts_pwd.utils;

import android.content.Context;
import net.youmi.android.AdManager;
import net.youmi.android.spot.SpotManager;

/**
 * 类名: YoumiUtil</br> 包名：com.xxx.cts_pwd.utils </br> 描述: </br> 发布版本号：</br> 开发人员：
 * 陈泰山</br> 创建时间： 2014-9-19
 */

public class YoumiUtil {

	public static final String appId = "f678a54cbe5f3856";
	public static final String appSecret = "542e16f70920dd2b";

	public static void initYoumiSetting(Context context) {
		
//		appId 和 appSecret 分别为应用的发布 ID 和密钥，由有米后台自动生成，通过在有米后台 > 应用详细信息 可以获得；
//		isTestModel为是否开启测试模式，true 为是，false 为否。（上传有米审核及发布到市场版本，请设置为 false）
//		未上传应用安装包、未通过审核的应用、模拟器运行，都只能获得测试广告，审核通过后，模拟器上依旧是测试广告，真机才会获取到正常的广告。
		AdManager.getInstance(context).init(appId, appSecret, false);
//		SpotManager.getInstance(context).loadSpotAds();

//		SpotManager.getInstance(context).showSpotAds(context);s
		AdManager.getInstance(context).setUserDataCollect(true);

		AdManager.setIsDownloadTipsDisplayOnNotification(true);

		// 获取是否在通知栏显示下载进度的值。
		AdManager.isDownloadTipsDisplayOnNotification();

		AdManager.setIsInstallationSuccessTipsDisplayOnNotification(true);

		// 获取安装完成后是否在通知栏显示已安装成功的通知的值。
		AdManager.isInstallationSuccessTipsDisplayOnNotification();

	}
}
