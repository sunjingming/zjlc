package com.example.administrator.zjlc.bank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.zjlc.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */

public class ProvinceAdapter extends BaseAdapter {
    private Context context;
    private List<ProvinceBean.DataBean>data ;

    public ProvinceAdapter(Context context, List<ProvinceBean.DataBean> data) {
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
            view = LayoutInflater.from(context).inflate(R.layout.bank_item,viewGroup,false);
            holder = new ItemViewHolder();
            holder.province = (TextView) view.findViewById(R.id.bank_item);

            view.setTag(holder);
        }else {
            holder = (ItemViewHolder) view.getTag();
        }

        holder.province.setText(data.get(i).getName());
        return view;
    }

    class  ItemViewHolder{
        TextView province;
    }
}
