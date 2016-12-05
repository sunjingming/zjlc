package com.example.administrator.zjlc.bank;

import android.content.Intent;
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

public class AreaChoose extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView title;
    private ListView listView;
    private List<String> listAreaName = new ArrayList<String>();
    public static AreaChoose test_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_choose);
        initView();
        test_a = this;
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();            }
        });
        title.setText("省份列表");

        /*
        * 获得省份列表
        * */
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCProvince_list);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("dats省份",data);
                Gson gson = new Gson();
                ProvinceBean provinceBean = gson.fromJson(data,ProvinceBean.class);
                final List<ProvinceBean.DataBean> data1 = provinceBean.getData();
                ProvinceAdapter adapter = new ProvinceAdapter(AreaChoose.this,data1);
                listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                            Intent intent = new Intent(AreaChoose.this,CityChoose.class);
                            intent.putExtra("provinceId",data1.get(i).getId());
                            startActivity(intent);
                            SharedPreferences ference = getSharedPreferences("bankName",MODE_APPEND);
                            SharedPreferences.Editor ediotor = ference.edit();
                            ediotor.putString("provinceId",data1.get(i).getId());
                            ediotor.putString("provinceName",data1.get(i).getName());
                            ediotor.commit();

                        }
                    });



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
        listView = (ListView) findViewById(R.id.province_list);
    }
}
