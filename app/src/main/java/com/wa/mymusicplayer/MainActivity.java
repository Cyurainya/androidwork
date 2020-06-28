package com.wa.mymusicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivList;
    LinearLayout llRoot;
    ViewPager vp;
    VpAdapter mAdapter;
    List<Fragment> data;
    LocalFragment localFragment;
    OnlineFragment onlineFragment;
    TextView tvLocal;
    TextView tvOnline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        ivList=findViewById(R.id.iv_list);
        llRoot=findViewById(R.id.ll_root);
        vp=findViewById(R.id.vp);
        tvLocal=findViewById(R.id.tv_local);
        tvOnline=findViewById(R.id.tv_online);
        data=new ArrayList<>();
        localFragment=new LocalFragment();
        onlineFragment=new OnlineFragment();
        data.add(localFragment);
        data.add(onlineFragment);
        mAdapter=new VpAdapter(getSupportFragmentManager(),data);
        vp.setAdapter(mAdapter);
        ivList.setOnClickListener(this);
        llRoot.setOnClickListener(this);
        tvLocal.setOnClickListener(this);
        tvOnline.setOnClickListener(this);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    tvLocal.setTextColor(Color.parseColor("#ffffff"));
                    tvOnline.setTextColor(Color.parseColor("#7bffffff"));
                }else if(position==1){
                    tvLocal.setTextColor(Color.parseColor("#7bffffff"));
                    tvOnline.setTextColor(Color.parseColor("#ffffff"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_list:
                startActivity(new Intent(this,PlayListActivity.class));
                break;
            case R.id.ll_root:
                Intent playIntent=new Intent(this,PlayActivity.class);
                startActivity(playIntent);
                break;
            case R.id.tv_local:
                vp.setCurrentItem(0);

                break;
            case R.id.tv_online:
                vp.setCurrentItem(1);

                break;
        }
    }
    public void checkPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1004);
        }
    }
}
