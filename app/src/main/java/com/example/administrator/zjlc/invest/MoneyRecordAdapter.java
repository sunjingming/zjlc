package com.example.administrator.zjlc.invest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.zjlc.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */

public class MoneyRecordAdapter extends BaseAdapter {
    private Context context;
    private List<MoneyRecordBean.DataBean>data;

    public MoneyRecordAdapter(Context context, List<MoneyRecordBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemViewHolder holder;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.money_record_item,viewGroup,false);
            holder = new ItemViewHolder();
            holder.title = (TextView) view.findViewById(R.id.money_record_number);
            holder.useMoney = (TextView) view.findViewById(R.id.money_record_use_money);
            holder.time = (TextView) view.findViewById(R.id.money_record_add_time);
            holder.affect = (TextView) view.findViewById(R.id.money_record_affect_money);
            holder.recovery = (TextView) view.findViewById(R.id.money_record_recover_money);
            holder.wait = (TextView) view.findViewById(R.id.money_record_wait_money);
            holder.freeze = (TextView) view.findViewById(R.id.money_record_freeze_money);
            holder.type = (TextView) view.findViewById(R.id.money_record_type);
            holder.intrduce = (TextView) view.findViewById(R.id.money_record_introduce);
            view.setTag(holder);
        }else {
            holder = (ItemViewHolder) view.getTag();
        }
        holder.title.setText("编号："+data.get(i).getId());
        holder.useMoney.setText("可用金额："+data.get(i).getAccount_money());
        holder.time.setText("添加时间："+data.get(i).getAdd_time());
        holder.recovery.setText("回款金额："+data.get(i).getBack_money());
        holder.wait.setText("待收金额："+data.get(i).getCollect_money());
        holder.affect.setText("影响金额："+data.get(i).getAffect_money());
        holder.freeze.setText("冻结金额："+data.get(i).getFreeze_money());
        holder.type.setText("类      型："+data.get(i).getType());
        holder.intrduce.setText("资金记录说明："+data.get(i).getInfo());
        return view;
    }

    class ItemViewHolder{
        TextView title,useMoney,time,affect,recovery,wait,freeze,type,intrduce;
    }
}
