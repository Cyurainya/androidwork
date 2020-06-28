package com.wa.mymusicplayer;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv;
    ArrayList<SongList.Song_list> data;
    PlayListAdapter mAdapter;
    SongList.Billboard billboard;
    ImageView iv;
    TextView tvName;
    TextView tvTime;
    TextView tvDesc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        toolbar=findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        rv=findViewById(R.id.rv);
        iv=findViewById(R.id.iv);
        tvName=findViewById(R.id.tv_name);
        tvTime=findViewById(R.id.tv_time);
        tvDesc=findViewById(R.id.tv_desc);
        rv.setLayoutManager(new LinearLayoutManager(this));
       // data=new ArrayList<>();
        billboard= (SongList.Billboard) getIntent().getExtras().getSerializable("billboard");
        data=getIntent().getParcelableArrayListExtra("song_list");
        Log.d(AlbumActivity.class.getSimpleName(),""+data.toString()+" "+billboard.toString());
        Glide.with(this).load(billboard.getPic_s192()).into(iv);
        tvName.setText(billboard.getName());
        getActionBar().setTitle(billboard.getName());
        tvTime.setText("最近更新："+billboard.getUpdate_date());
        tvDesc.setText(billboard.getComment());
        mAdapter=new PlayListAdapter(this,data);
        rv.setAdapter(mAdapter);
    }
}
