package com.example.administrator.zjlc.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.domain.SanBiaoGouBean;
import com.example.administrator.zjlc.domain.SanBiaobean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/10.
 */

public class AdapterJiLu extends RecyclerView.Adapter<AdapterJiLu.MyViewHolder> implements View.OnClickListener {
    private LayoutInflater inflater;
    private Activity activity;
    private ArrayList<SanBiaoGouBean.DataBean> dataBeanArrayList;

    public AdapterJiLu(Activity mActivity, ArrayList<SanBiaoGouBean.DataBean> dataBeanArrayList){
        this.activity = mActivity;
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
    public AdapterJiLu.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.jilu, parent, false);
        //导入itemView，为itemView设置点击事件
        itemView.setOnClickListener(this);
        return new AdapterJiLu.MyViewHolder(itemView);
    }


    /**
     *绑定VIewHolder，加载数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv1.setText(dataBeanArrayList.get(position).getAdd_time());
        holder.tv2.setText(dataBeanArrayList.get(position).getIs_auto());
        holder.tv3.setText(String.valueOf(dataBeanArrayList.get(position).getInvestor_capital()));
        holder.tv4.setText(dataBeanArrayList.get(position).getUser_name());
    }

    /**t
     * 数据源的数量，item的个数
     * @return
     */
    @Override
    public int getItemCount() {
        return dataBeanArrayList!=null?dataBeanArrayList.size():0;
    }
    //刷新数据
    public void setDatas(ArrayList<SanBiaoGouBean.DataBean> singModelArrayList) {
        this.dataBeanArrayList = singModelArrayList;
        this.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
            tv3 = (TextView) itemView.findViewById(R.id.tv3);
            tv4 = (TextView) itemView.findViewById(R.id.tv4);
        }
    }
}
