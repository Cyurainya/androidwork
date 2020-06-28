package com.wa.mymusicplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class OnlineAdapter extends RecyclerView.Adapter<OnlineAdapter.OnlineViewHolder>  {
    Context context;
    List<SongList> data;

    public OnlineAdapter(Context context, List<SongList> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public OnlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OnlineViewHolder holder=new OnlineViewHolder(LayoutInflater.from(context).inflate(R.layout.item_online,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final OnlineViewHolder holder, int position) {
        final SongList songList=data.get(position);

        final List<SongList.Song_list> list=songList.getSong_list();
        if(list.size()==1){
            holder.tv1.setText("1."+list.get(0).getTitle()+"-"+list.get(0).getAuthor());
        }else if(list.size()==2){
            holder.tv1.setText("1."+list.get(0).getTitle()+"-"+list.get(0).getAuthor());
            holder.tv2.setText("2."+list.get(1).getTitle()+"-"+list.get(1).getAuthor());
        }else if(list.size()>2){
            holder.tv1.setText("1."+list.get(0).getTitle()+"-"+list.get(0).getAuthor());
            holder.tv2.setText("2."+list.get(1).getTitle()+"-"+list.get(1).getAuthor());
            holder.tv3.setText("3."+list.get(2).getTitle()+"-"+list.get(2).getAuthor());
        }

        Glide.with(context).load(songList.getBillboard().getPic_s192()).into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,AlbumActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("billboard",songList.getBillboard());
                bundle.putParcelableArrayList("song_list" , (ArrayList<? extends Parcelable>) songList.getSong_list());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class OnlineViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        public OnlineViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
            tv3=itemView.findViewById(R.id.tv3);
        }
    }
}
