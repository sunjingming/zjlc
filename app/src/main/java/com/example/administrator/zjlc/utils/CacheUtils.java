package com.example.administrator.zjlc.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 作者：杨光福 on 2016/2/16 15:58
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：缓存工具类
 */
public class CacheUtils {
    private static    SharedPreferences sp;
    /**
     * 保存软件参数
     * @param context
     * @param key
     * @param values
     */
    public static void putBoolean(Context context,String key, boolean values) {
        if(sp == null){
            sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        }

        sp.edit().putBoolean(key,values).commit();

    }


    public static boolean getBoolean(Context context,String key) {
        if(sp == null){
            sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        }
        return  sp.getBoolean(key,false);

    }

    /**
     * 缓存文本信息
     * @param context
     * @param key
     * @param values
     */
    public static void putString(Context context, String key, String values) {

        //判断sdcard是否存在，如果不存在就用SharedPreferences去缓存
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

            try {
                //数据缓存，file
                //mnt/sdcard/beijingnews/klsnlka;lklkkllkllkll
                String fileName = MD5Encoder.encode(key);
                File file = new File(Environment.getExternalStorageDirectory() + "/beijingnews", fileName);

                File parenFile = file.getParentFile();////mnt/sdcard/beijingnews/
                if (!parenFile.exists()) {
                    parenFile.mkdirs();
                }

                if(!file.exists()){
                    file.createNewFile();
                }

                FileOutputStream fot = new FileOutputStream(file);
                fot.write(values.getBytes());
                fot.flush();
                fot.close();


            } catch (Exception e) {
                e.printStackTrace();
            }


        }else{

            if(sp == null){
                sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
            }
            sp.edit().putString(key,values).commit();
        }


    }

    /**
     * 得到缓存数据
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {

        //判断sdcard是否存在，如果不存在就用SharedPreferences去缓存
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            try {
                //数据缓存，file
                //mnt/sdcard/beijingnews/klsnlka;lklkkllkllkll
                String fileName = MD5Encoder.encode(key);
                File file = new File(Environment.getExternalStorageDirectory() + "/beijingnews", fileName);


                if (file.exists()) {

                    FileInputStream fis = new FileInputStream(file);

                    byte[] buffer = new byte[1024];
                    ByteArrayOutputStream bops = new ByteArrayOutputStream();
                    int leng = -1;
                    while ((leng=fis.read(buffer))!=-1){
                        bops.write(buffer,0,leng);
                    }
                    bops.flush();
                    bops.close();
                    fis.close();

                    return  bops.toString();

                }



            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";

        } else {
            if (sp == null) {
                sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
            }
            return sp.getString(key, "");
        }

    }
}
