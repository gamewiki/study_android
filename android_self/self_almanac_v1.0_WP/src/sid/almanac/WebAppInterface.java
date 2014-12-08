package sid.almanac;

import sid.utils.AppConstant;
import android.content.Context;
import android.widget.Toast;

/**
 * 自定义的Android代码和JavaScript代码之间的桥梁类
 * 
 * @author 1
 * 
 */
public class WebAppInterface {
	Context mContext;

	/** Instantiate the interface and set the context */
	WebAppInterface(Context c) {
		mContext = c;
	}

	/** Show a toast from the web page */
	// 如果target 大于等于API 17，则需要加上如下注解
//	@JavascriptInterface
	public void showToast(String toast) {
		Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
	}
	
	public String getInfo(){
		return AppConstant.TYPE;
	}
}