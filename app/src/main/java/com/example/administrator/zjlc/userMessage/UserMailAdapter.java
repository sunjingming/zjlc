package com.example.administrator.zjlc.userMessage;

import android.content.Context;
import android.graphics.Color;
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

public class UserMailAdapter extends BaseAdapter {
    private Context context;
    private List<UserMailBean.DataBean>data;

    public UserMailAdapter(Context context, List<UserMailBean.DataBean> data) {
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
        ItemViewHolder holder ;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.user_mail_message,viewGroup,false);
            holder = new ItemViewHolder();
            holder.time = (TextView) view.findViewById(R.id.user_mail_time);
            holder.title = (TextView) view.findViewById(R.id.user_mail_title);
            holder.status = (TextView) view.findViewById(R.id.user_mail_status);
            holder.content = (TextView) view.findViewById(R.id.user_mail_content);
            holder.str = (TextView) view.findViewById(R.id.user_mail_str);
            view.setTag(holder);
        }else{
            holder = (ItemViewHolder) view.getTag();
        }
        final int status = data.get(i).getStatus();
        if (status==1){
            holder.str.setTextColor(Color.BLACK);
            holder.title.setTextColor(Color.BLACK);
            holder.content.setTextColor(Color.GRAY);
            holder.str.setText("(已读)");
        }else {
            holder.str.setTextColor(Color.RED);
            holder.title.setTextColor(Color.RED);
            holder.content.setTextColor(Color.BLACK);
            holder.str.setText("(未读)");
        }

        holder.title.setText(data.get(i).getTitle());
        holder.time.setText(data.get(i).getSend_time());
        holder.content.setText(data.get(i).getMsg());
        holder.status.setText(String.valueOf(data.get(i).getId()));
        return view;
    }
    class ItemViewHolder{
        TextView title,time,status,content,str;
    }
}
