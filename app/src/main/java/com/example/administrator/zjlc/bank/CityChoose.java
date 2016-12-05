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

public class CityChoose extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView title;
    private ListView listView;
    private List<String> listCityName = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choose);
        initView();
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("市区列表");
        Intent intent = getIntent();
        final String provinceId = intent.getStringExtra("provinceId");

        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCCity_list);
        params.addBodyParameter("id",provinceId);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("dats地市",data);
                Gson gson = new Gson();
                CityBean cityBean = gson.fromJson(data,CityBean.class);
                final List<CityBean.DataBean> obj = cityBean.getData();
                CityAdapter adapter = new CityAdapter(CityChoose.this,obj);
                listView.setAdapter(adapter);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                            SharedPreferences ference = getSharedPreferences("bankName",MODE_APPEND);
                            SharedPreferences.Editor editor = ference.edit();
                            editor.putString("cityId",obj.get(i).getId());
                            editor.putString("cityName",obj.get(i).getName());
                            editor.commit();

                            finish();
                            AreaChoose.test_a.finish();
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
        listView = (ListView) findViewById(R.id.city_list);
    }
}
