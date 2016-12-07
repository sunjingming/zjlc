package com.example.administrator.zjlc.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/10/29.
 */
public class DateUtilsl {
    private static SimpleDateFormat sf = null;
    //当前时间
    //public static  Date DATE_NOW=new Date();


    /**
     * 得到完整的时间戳，格式:yyyyMMddHHmmssSSS(年月日时分秒毫秒)
     * @return 完整的时间戳
     */
    public static String getFullTimeStamp(){
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) ;
    }
    /**
     * 得到简单的时间戳，格式:yyyyMMdd(年月日)
     * @return 简单的时间戳
     */
    public static String getSimpleTimeStamp(){
        return new SimpleDateFormat("yyyyMMdd").format(new Date()) ;
    }
    /**
     * 根据指定的格式得到时间戳
     * @param pattern 指定的格式
     * @return 指定格式的时间戳
     */
    public static String getTimeStampByPattern(String pattern){
        return new SimpleDateFormat(pattern).format(new Date()) ;
    }
    public static String getTodayStr(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date()) ;
    }



    /**
     * 时间戳，格式:yyyy-MM-dd HH:mm:ss(年-月-日  时：分：秒)
     * @return 简单的时间戳
     */
    public static String getDateTimeStamp(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) ;
    }

    public static Date getDateByString(String str){
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dateTime = null;
        try {
            dateTime = sim.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }
    /**
     * 时间戳转为时间(年月日，时分秒)
     *
     * @param cc_time 时间戳
     * @return
     */
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));

        return re_StrTime;
    }

    public static String getStrTimeMinute(String cc_time) {
        String re_StrTime = null;
        //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd MM-ss");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));

        return re_StrTime;
    }

    public static String getStrTimeDetail(String cc_time) {
        String re_StrTime = null;
        //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年/MM月/dd日");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));

        return re_StrTime;
    }

    public static String getStrTimeLine(String cc_time) {
        String re_StrTime = null;
        //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("yy年\nMM月\ndd日");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));

        return re_StrTime;
    }
    /**
     * 掉此方法输入所要转换的时间输入例如（"2014-06-14-16-09-00"）返回时间戳
     *
     * @param time
     * @return
     */
    public static String dataOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
            Log.d("--444444---", times);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return times;
    }

}
