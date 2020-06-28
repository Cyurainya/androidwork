package com.wa.mymusicplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.LocalViewHolder> {
    private Context context;
    private List<Music> data;
    public LocalAdapter(Context context,List<Music> data){
        this.context=context;
        this.data=data;
    }
    @NonNull
    @Override
    public LocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LocalViewHolder holder=new LocalViewHolder(LayoutInflater.from(context).inflate(R.layout.item_local,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocalViewHolder holder, int position) {
        Music music=data.get(position);
        holder.tvName.setText(music.getTitle());
        holder.tvDesc.setText(music.getArtist());
        holder.ivHeader.setTag(music.getTitle()+position);
        if(holder.ivHeader.getTag().equals(music.getTitle()+position)){
            if(music.getImagePath()!=null&&!"null".equals(music.getImagePath())){
                Bitmap bitmap= BitmapFactory.decodeFile(music.getImagePath());
                holder.ivHeader.setImageBitmap(bitmap);
            }else{
                holder.ivHeader.setImageResource(R.mipmap.ic_launcher);
            }

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class LocalViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageView ivHeader;
        TextView tvDesc;
        public LocalViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tv_name);
            ivHeader=itemView.findViewById(R.id.iv_header);
            tvDesc=itemView.findViewById(R.id.tv_desc);
        }
    }
}
