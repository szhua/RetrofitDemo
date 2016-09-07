package szhua.demo.retrofitdemo.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import szhua.demo.retrofitdemo.R;

/**
 * Created by Administrator on 2015/11/2 0002.
 */
public class UiUtil {
    /**
     * 自定义toast样式
     *
     * @param context
     * @param message
     */


    public static Toast toast;

    public static void showLongToast(Context context, String message) {
        if (context == null) {
            return;
        }
        //这样做的原因是连续的出现Toast ；
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(context);
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView textMsg = (TextView) view.findViewById(R.id.toastMsg);
        textMsg.setText(message);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }




    /*
     * 验证手机号码，（请自觉使用规范的正则表达式）
     *
     * @param mobileNo
     * @return
     */
    public static boolean isValidMobileNo(String mobileNo) {
        boolean flag = false;
        // Pattern p = Pattern.compile("^(1[358][13567890])(\\d{8})$");
        Pattern p = Pattern
                .compile("^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$");
        Matcher match = p.matcher(mobileNo);
        if (mobileNo != null) {
            flag = match.matches();
        }
        return flag;
    }

}
