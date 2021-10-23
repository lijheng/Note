package com.summer.note.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

/**
 *  CreateBy:lijheng at 2019/11/18
 *  describe:{@link android.content.SharedPreferences} 的基本操作工具类
 */
@SuppressWarnings("unused")
public class SharePreferenceUtil {
    private static final String NAME = "share_data";

    /**
     * 保存数据
     * @param key 键名
     * @param value 键值
     */
    public static void save(@NonNull Context context, @NonNull String key, String value){
        Context con = getApplicationContext(context);
        SharedPreferences.Editor editor = con.getSharedPreferences(NAME,Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 获取存储的字符串
     * @param key 键名
     * @param defaultValue 默认值
     */
    public static String getString(@NonNull Context context,@NonNull String key,String defaultValue){
        Context con = getApplicationContext(context);
        return con.getSharedPreferences(NAME,Context.MODE_PRIVATE).getString(key,defaultValue);
    }

    /**
     * @see #save(Context, String, String)
     */
    public static void save(@NonNull Context context,@NonNull String key,boolean value){
        Context con = getApplicationContext(context);
        SharedPreferences.Editor editor = con.getSharedPreferences(NAME,Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * @see #getString(Context, String, String)
     */
    public static boolean getBoolean(@NonNull Context context,@NonNull String key,boolean defaultValue){
        Context con = getApplicationContext(context);
        return con.getSharedPreferences(NAME,Context.MODE_PRIVATE).getBoolean(key,defaultValue);
    }

    /**
     * 根据判断传入的上下文返回应用的上下文
     * @return Application Context
     */
    private static Context getApplicationContext(@NonNull Context context){
        if (context instanceof Application){
            return context;
        }else{
            return context.getApplicationContext();
        }
    }
}
