
package com.summer.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.summer.note.task.TaskActivity;
import com.summer.note.util.Constant;
import com.summer.note.util.SharePreferenceUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean adState = SharePreferenceUtil.getBoolean(this, Constant.ADVERTISING_PAGE,false);
        if (adState){
            //显示广告页
            showAdvertisingPage();
        }else{
            startActivity(new Intent(this, TaskActivity.class));
            finish();
        }
    }

    /**
     * 显示广告页
     */
    private void showAdvertisingPage(){

    }
}
