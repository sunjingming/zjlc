package com.example.administrator.zjlc.cash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.zjlc.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class CashRecordAdapter extends BaseAdapter {
    private Context context;
    private List<CashRecordBean.DataBean>data;

    public CashRecordAdapter(Context context, List<CashRecordBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int i ) {
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
            view = LayoutInflater.from(context).inflate(R.layout.cash_record_item,viewGroup,false);
            holder = new ItemViewHolder();
            holder.time = (TextView) view.findViewById(R.id.cash_record_time);
            holder.money = (TextView) view.findViewById(R.id.cash_record_money);
            holder.state = (TextView) view.findViewById(R.id.cash_record_state);
            view.setTag(holder);
        }else {
            holder = (ItemViewHolder) view.getTag();
        }

        holder.time.setText(data.get(i).getAdd_time());
        holder.money.setText(String.valueOf(data.get(i).getWithdraw_money()));
        holder.state.setText(data.get(i).getWithdraw_status());
        return view;
    }
    class  ItemViewHolder{
        TextView time,money,state;
    }

}





