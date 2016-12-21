package com.example.administrator.zjlc.invest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.pager.HomePager;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class WaitRecoverAdapter extends BaseAdapter {
    private Context context;
    private List<WaitRecoverBean.DataBean> data;

    public WaitRecoverAdapter(Context context, List<WaitRecoverBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
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
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.invest_item, viewGroup, false);
            holder = new ItemViewHolder();
            holder.number = (TextView) view.findViewById(R.id.invest_number);
            holder.time = (TextView) view.findViewById(R.id.invest_time);
            holder.grage = (TextView) view.findViewById(R.id.invest_grage);
            holder.name = (TextView) view.findViewById(R.id.invest_name);
            holder.money = (TextView) view.findViewById(R.id.invest_money);
            holder.fee = (TextView) view.findViewById(R.id.invest_fee);
            holder.automatic = (TextView) view.findViewById(R.id.invest_automatic);
            holder.year = (TextView) view.findViewById(R.id.invest_year);
            holder.repayMoney = (TextView) view.findViewById(R.id.invest_repayMoney);
            holder.repayFee = (TextView) view.findViewById(R.id.invest_repayFee);
            view.setTag(holder);
        }else {
            holder = (ItemViewHolder) view.getTag();
        }
        holder.number.setText("标号："+data.get(i).getBorrow_id());
        holder.time.setText("添加时间："+data.get(i).getAdd_time());
        holder.grage.setText("原始借款标号："+data.get(i).getBorrow_id());
        holder.name.setText("原始借款名称："+data.get(i).getBorrow_name());
        holder.money.setText("投资本金："+data.get(i).getInvestor_capital());
        holder.fee.setText("投资利息："+data.get(i).getInvestor_interest());
        final String is_auto = data.get(i).getIs_auto();
        if ("0".equals(is_auto)) {
            holder.automatic.setText("是否自动投标：否");
        }else {
            holder.automatic.setText("是否自动投标：是");
        }
        holder.year.setText("年化收益："+data.get(i).getRate()+"%");
        holder.repayMoney.setText("已还本金："+data.get(i).getReceive_capital());
        holder.repayFee.setText("已还利息："+data.get(i).getReceive_interest());

        return view;
    }

    class ItemViewHolder {
        TextView number, time, grage, name, money, fee, automatic, year, repayMoney, repayFee;
    }

}
