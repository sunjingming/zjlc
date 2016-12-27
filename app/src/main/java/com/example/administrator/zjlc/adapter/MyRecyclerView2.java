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

public class MyRecyclerView2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    //完成                               投资                        复审                           还款                            申购                      满标                            债权满
    private int[] ic_stat = {R.drawable.ic_status_finish,R.drawable.ic_status_invest,R.drawable.ic_status_rechecking,R.drawable.ic_status_repaying,R.drawable.ic_status_subscribe,R.drawable.icon_zhaiquan_man};
    private static final int TYPE_ITEM =0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //顶部FootView
    //上拉加载更多状态-默认为0
    private int load_more_status1=0;
    private Activity mActivity;
    private ArrayList<ZQZLbean.DataBean> dataBeanArrayList;
    private int id;

    private LayoutInflater inflater;
    private RecyclerView mRecyclerView;//用来计算Child位置
    private AdapterView.OnItemClickListener onItemClickListener;
    //上拉加载更多
    public static final int  PULLUP_LOAD_MORE=0;
    //正在加载中
    public static final int  LOADING_MORE=1;

    //最后一行
    public static final int NO_MORE_DATA=2;
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //进行判断显示类型，来创建返回不同的View
        if(viewType==TYPE_ITEM){
        View itemView = inflater.inflate(R.layout.item_sbtz, parent, false);
        //导入itemView，为itemView设置点击事件
        itemView.setOnClickListener(this);
        return new MyViewHolder(itemView);
        }else if(viewType==TYPE_FOOTER){
            View foot_view=inflater.inflate(R.layout.recycler_load_more_layout,parent,false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            FootViewHolder footViewHolder=new FootViewHolder(foot_view);
            return footViewHolder;
        }
        return null;
    }

    /**
     *绑定VIewHolder，加载数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).tv_titles.setText(dataBeanArrayList.get(position).getBorrow_name());
            ((MyViewHolder) holder).tv_monh.setText(String.valueOf(dataBeanArrayList.get(position).getTransfer_price()) + ".00");
            ((MyViewHolder) holder).tv_nianlilv.setText(String.valueOf(dataBeanArrayList.get(position).getBorrow_interest_rate()) + "%");
            ((MyViewHolder) holder).tv_jinee.setText(new DecimalFormat("00.00").format(dataBeanArrayList.get(position).getMoney()));
            ((MyViewHolder) holder).tv_qishu.setText(String.valueOf(dataBeanArrayList.get(position).getPeriod() + "/" + dataBeanArrayList.get(position).getTotal_period()));

//        holder.im_touzi.setBackgroundResource(ic_stat[dataBeanArrayList.get(position).getStatus()-1]);
            switch (dataBeanArrayList.get(position).getStatus()) {
                case 1:
                    ((MyViewHolder) holder).im_touzi.setBackgroundResource(R.drawable.t6);
                    break;
                case 2:
                    ((MyViewHolder) holder).im_touzi.setBackgroundResource(R.drawable.icon_zhaiquan_debt);
                    break;
                case 3:
                    ((MyViewHolder) holder).im_touzi.setBackgroundResource(R.drawable.t4);
                    break;
                case 4:
                    ((MyViewHolder) holder).im_touzi.setBackgroundResource(R.drawable.ic_status_finish);
                    break;
            }
            //设置标种
            switch (dataBeanArrayList.get(position).getBorrow_type()) {
                case "担保标":
                    ((MyViewHolder) holder).imageView1.setBackgroundResource(R.drawable.ic_type_dan);
                    break;
                case "净值标":
                    ((MyViewHolder) holder).imageView1.setBackgroundResource(R.drawable.ic_type_jing);
                    break;
                case "信用标":
                    ((MyViewHolder) holder).imageView1.setBackgroundResource(R.drawable.ic_type_xin);
                    break;
                case "抵押标":
                    ((MyViewHolder) holder).imageView1.setBackgroundResource(R.drawable.ic_type_ya);
                    break;
                case "秒还标":
                    ((MyViewHolder) holder).imageView1.setBackgroundResource(R.drawable.ic_type_miao);
                    break;
            }
            //设置信用等级
            switch (dataBeanArrayList.get(position).getLevel()) {
                case "A":
                    ((MyViewHolder) holder).imageView2.setBackgroundResource(R.drawable.ic_level_a);
                    break;
                case "B":
                    ((MyViewHolder) holder).imageView2.setBackgroundResource(R.drawable.ic_level_b);
                    break;
                case "C":
                    ((MyViewHolder) holder).imageView2.setBackgroundResource(R.drawable.ic_level_c);
                    break;
                case "D":
                    ((MyViewHolder) holder).imageView2.setBackgroundResource(R.drawable.ic_level_d);
                    break;
                case "E":
                    ((MyViewHolder) holder).imageView2.setBackgroundResource(R.drawable.ic_level_e);
                    break;
                case "HR":
                    ((MyViewHolder) holder).imageView2.setBackgroundResource(R.drawable.ic_level_hr);
                    break;
            }

            holder.itemView.setTag(dataBeanArrayList.get(position));
        }else if(holder instanceof FootViewHolder){
            FootViewHolder footViewHolder=(FootViewHolder)holder;
            switch (load_more_status1){
                case PULLUP_LOAD_MORE:
                    footViewHolder.foot_view_item_tv.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footViewHolder.foot_view_item_tv.setText("正在加载更多数据...");
                    break;
                case NO_MORE_DATA:
                    footViewHolder.foot_view_item_tv.setText("数据已加载完成...");
                    break;
            }
        }
    }

    /**t
     * 数据源的数量，item的个数
     * @return
     */
    @Override
    public int getItemCount() {
        return (dataBeanArrayList!=null?dataBeanArrayList.size():0)+1;
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
public void addMoreItem(ArrayList<ZQZLbean.DataBean> singModelArrayList) {
    dataBeanArrayList.addAll(singModelArrayList);
    notifyDataSetChanged();
}
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
    /**
     * 底部FootView布局
     */
    public static class FootViewHolder extends  RecyclerView.ViewHolder{
        private TextView foot_view_item_tv;
        public FootViewHolder(View view) {
            super(view);
            foot_view_item_tv=(TextView)view.findViewById(R.id.foot_view_item_tv);
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
    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }
    /**
     * //上拉加载更多
     * PULLUP_LOAD_MORE=0;
     * //正在加载中
     * LOADING_MORE=1;
     * //加载完成已经没有更多数据了
     * NO_MORE_DATA=2;
     * @param status
     */
    public void changeMoreStatus(int status){
        load_more_status1=2;
        notifyDataSetChanged();
    }
}