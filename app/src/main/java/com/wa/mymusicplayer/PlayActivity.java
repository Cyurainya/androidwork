package com.wa.mymusicplayer;


import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class PlayActivity extends AppCompatActivity {
    ViewPager vp;
    VpAdapter mAdapter;
    List<Fragment> data;
    ImageFragment imageFragment;
    LyricFragment lyricFragment;
    ImageView ivIndex1;
    ImageView ivIndex2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        vp=findViewById(R.id.vp);
        ivIndex1=findViewById(R.id.iv_index1);
        ivIndex2=findViewById(R.id.iv_index2);
        imageFragment=new ImageFragment();
        lyricFragment=new LyricFragment();
        data=new ArrayList<>();
        data.add(imageFragment);
        data.add(lyricFragment);
        mAdapter=new VpAdapter(getSupportFragmentManager(),data);
        vp.setAdapter(mAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    ivIndex1.setImageResource(R.drawable.ic_seek_bar_progress_btn);
                    ivIndex2.setImageResource(R.drawable.ic_seek_bar_volume_btn);
                }else if(position==1){
                    ivIndex1.setImageResource(R.drawable.ic_seek_bar_volume_btn);
                    ivIndex2.setImageResource(R.drawable.ic_seek_bar_progress_btn);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
