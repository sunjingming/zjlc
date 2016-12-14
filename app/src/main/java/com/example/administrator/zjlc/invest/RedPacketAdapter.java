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

public class RedPacketAdapter extends BaseAdapter {
    private Context context;
    private List<RedPacketBean.DataBean>data;

    public RedPacketAdapter(Context context, List<RedPacketBean.DataBean> data) {
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
            view = LayoutInflater.from(context).inflate(R.layout.red_packet_item,viewGroup,false);
            holder = new ItemViewHolder();
            holder.money = (TextView) view.findViewById(R.id.red_packet_money);
            holder.source = (TextView) view.findViewById(R.id.red_packet_source);
            holder.state = (TextView) view.findViewById(R.id.red_packet_state);
            holder.introduce = (TextView) view.findViewById(R.id.red_packet_introduce);
            holder.time = (TextView) view.findViewById(R.id.red_packet_time);
            view.setTag(holder);
        }else {
            holder = (ItemViewHolder) view.getTag();
        }
        final String status = data.get(i).getStatus();
        if ("1".equals(status)){
            holder.state.setText("特权金状态：可用");
        }else if ("2".equals(status)){
            holder.state.setText("特权金状态：锁定");
        }else if ("3".equals(status)){
            holder.state.setText("特权金状态：过期");
        }else if ("4".equals(status)){
            holder.state.setText("特权金状态：已用");
        }
        holder.money.setText("¥"+data.get(i).getMoney());
        holder.source.setText("活动来源"+data.get(i).getName());

        holder.introduce.setText(data.get(i).getStr());
        holder.time.setText("截止日期"+data.get(i).getDeadline());


        return view;
    }

    class ItemViewHolder{
        TextView money,source,state,introduce,time;
    }
}
