package com.woodys.fingerprint;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.woodys.devicelib.KeplerSdk;

public class MainActivity extends Activity {
    private TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMsg = ((TextView)findViewById(R.id.text));
        tvMsg.setText(KeplerSdk.getInstance().getDeviceInfo(this).toString());
        //KeplerSdk.getInstance().initSdk("111",this);
    }

    public void onClickCopy(View v) {
        // 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(tvMsg.getText());
        Toast.makeText(this, "复制成功，可以发给朋友们了。", Toast.LENGTH_LONG).show();
    }
}
