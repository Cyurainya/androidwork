package com.wa.mymusicplayer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PlayListActivity extends AppCompatActivity {
   Toolbar toolbar;
   RecyclerView rv;
   List<SongList.Song_list> data;
   PlayListAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        toolbar=findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("播放列表");
        toolbar.setTitleTextColor(Color.WHITE);
        rv=findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        data=new ArrayList<>();
        SongList.Song_list m1=new SongList.Song_list();
        m1.setTitle("桥边女孩");
        m1.setAuthor("舞蹈女神");
        data.add(m1);
        mAdapter=new PlayListAdapter(this,data);
        rv.setAdapter(mAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
