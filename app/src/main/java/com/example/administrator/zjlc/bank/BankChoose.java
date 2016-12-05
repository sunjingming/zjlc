package com.example.administrator.zjlc.bank;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;
import com.example.administrator.zjlc.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class BankChoose extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView title;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_choose);
        initView();
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("选择银行卡");

        //获取银行列表
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCBank_list);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data银行",data);
                Gson gson = new Gson();
                BankBean bankBean = gson.fromJson(data,BankBean.class);
                final List<BankBean.DataBean> data1 = bankBean.getData();
          //      BankApapter
//                listBankName.addAll(bankBean.getData());
//                ArrayAdapter<BankBean.> adapter = new ArrayAdapter<String>(BankChoose.this, android.R.layout.simple_list_item_1, listBankName);
//                list.setAdapter(adapter);
//                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        SharedPreferences frerences = getSharedPreferences("bankName",MODE_APPEND);
//                        SharedPreferences.Editor editor = frerences.edit();
//                        editor.putString("bankChoose",listBankName.get(position));
//                        editor.commit();
//                        finish();
//                    }
//                });

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.tv_title);
        list = (ListView) findViewById(R.id.bank_list);
    }
}
