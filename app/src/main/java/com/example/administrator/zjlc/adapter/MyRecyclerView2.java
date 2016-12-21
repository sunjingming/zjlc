package com.example.administrator.zjlc.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.activity.DetailsActivity;
import com.example.administrator.zjlc.activity.ZQZRActivity;
import com.example.administrator.zjlc.domain.SanBiaobean;
import com.example.administrator.zjlc.domain.ZQZLbean;
import com.example.administrator.zjlc.login.Login;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2016/12/8.
 */

public class MyRecyclerView2 extends RecyclerView.Adapter<MyRecyclerView2.MyViewHolder> implements View.OnClickListener {
    //完成                               投资                        复审                           还款                            申购                      满标                            债权满
    private int[] ic_stat = {R.drawable.ic_status_finish,R.drawable.ic_status_invest,R.drawable.ic_status_rechecking,R.drawable.ic_status_repaying,R.drawable.ic_status_subscribe,R.drawable.icon_zhaiquan_man};

    private Activity mActivity;
    private ArrayList<ZQZLbean.DataBean> dataBeanArrayList;
    private LayoutInflater inflater;
    private int id;


    public MyRecyclerView2(Activity mActivity, ArrayList<ZQZLbean.DataBean> dataBeanArrayList) {
        this.mActivity = mActivity;
        this.dataBeanArrayList = dataBeanArrayList;
        inflater= (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 创建VIewHolder，导入布局，实例化itemView
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_sbtz, parent, false);
        //导入itemView，为itemView设置点击事件
        itemView.setOnClickListener(this);
        return new MyViewHolder(itemView);
    }

    /**
     *绑定VIewHolder，加载数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_titles.setText(dataBeanArrayList.get(position).getBorrow_name());
        holder.tv_monh.setText(String.valueOf(dataBeanArrayList.get(position).getTransfer_price())+".00");
        holder.tv_nianlilv.setText(String.valueOf(dataBeanArrayList.get(position).getBorrow_interest_rate())+"%");
        holder.tv_jinee.setText(new DecimalFormat("00.00").format(dataBeanArrayList.get(position).getMoney()));
        holder.tv_qishu.setText(String.valueOf(dataBeanArrayList.get(position).getPeriod()+"/"+dataBeanArrayList.get(position).getTotal_period()));

//        holder.im_touzi.setBackgroundResource(ic_stat[dataBeanArrayList.get(position).getStatus()-1]);
        switch (dataBeanArrayList.get(position).getStatus()){
            case 1:
                holder.im_touzi.setBackgroundResource(R.drawable.t6);
                break;
            case 2:
                holder.im_touzi.setBackgroundResource(R.drawable.icon_zhaiquan_debt);
                break;
            case 3:
                holder.im_touzi.setBackgroundResource(R.drawable.t4);
                break;
            case 4:
                holder.im_touzi.setBackgroundResource(R.drawable.icon_zhaiquan_finish);
                break;
        }
        //设置标种
        switch (dataBeanArrayList.get(position).getBorrow_type()){
            case "担保标":
                holder.imageView1.setBackgroundResource(R.drawable.ic_type_dan);
                break;
            case "净值标":
                holder.imageView1.setBackgroundResource(R.drawable.ic_type_jing);
                break;
            case "信用标":
                holder.imageView1.setBackgroundResource(R.drawable.ic_type_xin);
                break;
            case "抵押标":
                holder.imageView1.setBackgroundResource(R.drawable.ic_type_ya);
                break;
            case "秒还标":
                holder.imageView1.setBackgroundResource(R.drawable.ic_type_miao);
                break;
        }
        //设置信用等级
        switch (dataBeanArrayList.get(position).getLevel()){
            case "A":
                holder.imageView2.setBackgroundResource(R.drawable.ic_level_a);
                break;
            case "B":
                holder.imageView2.setBackgroundResource(R.drawable.ic_level_b);
                break;
            case "C":
                holder.imageView2.setBackgroundResource(R.drawable.ic_level_c);
                break;
            case "D":
                holder.imageView2.setBackgroundResource(R.drawable.ic_level_d);
                break;
            case "E":
                holder.imageView2.setBackgroundResource(R.drawable.ic_level_e);
                break;
            case "HR":
                holder.imageView2.setBackgroundResource(R.drawable.ic_level_hr);
                break;
        }

        holder.itemView.setTag(dataBeanArrayList.get(position));
    }

    /**t
     * 数据源的数量，item的个数
     * @return
     */
    @Override
    public int getItemCount() {
        return dataBeanArrayList!=null?dataBeanArrayList.size():0;
    }
    public void setDatas(ArrayList<ZQZLbean.DataBean> singModelArrayList) {
        this.dataBeanArrayList = singModelArrayList;
        this.notifyDataSetChanged();
    }
//    @Override
//    public void onClick(View v) {
//        SharedPreferences prence = mActivity.getSharedPreferences("usetoken", MODE_PRIVATE);
//        String token = prence.getString("token","");
//        Log.e("cuo",token);
//        if(token.equals("")){
//            Intent intent = new Intent(mActivity , Login.class);
//            mActivity.startActivity(intent);
//        }else{
//            Intent intent = new Intent(mActivity , ZQZRActivity.class);
//            intent.putExtra("id",id);
//            mActivity.startActivity(intent);
//        }
//    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;
        ImageView imageView2;
        ImageView im_touzi;

        TextView tv_titles;
        TextView tv_monh;
        TextView tv_nianlilv;
        TextView tv_jinee;
        TextView tv_qishu;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView1 = (ImageView) itemView.findViewById(R.id.imageView1);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            im_touzi = (ImageView) itemView.findViewById(R.id.im_touzi);

            tv_titles = (TextView) itemView.findViewById(R.id.tv_titles);
            tv_monh = (TextView) itemView.findViewById(R.id.tv_monh);
            tv_nianlilv = (TextView) itemView.findViewById(R.id.tv_nianlilv);
            tv_jinee = (TextView) itemView.findViewById(R.id.tv_jinee);
            tv_qishu = (TextView) itemView.findViewById(R.id.tv_qishu);
        }




    }
    //实现点击事件
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //定义接口
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , ZQZLbean.DataBean data);
    }

    //提供外部调用
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(ZQZLbean.DataBean)v.getTag());
        }
    }
    //暴露给外面调用得方法
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}