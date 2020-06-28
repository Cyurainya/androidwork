package com.wa.mymusicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ListViewHolder> {
   private Context context;
   private List<SongList.Song_list> data;

    public PlayListAdapter(Context context, List<SongList.Song_list> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListViewHolder holder=new ListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_play_list,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        SongList.Song_list m=data.get(position);
        holder.tvName.setText(m.getTitle());
        holder.tvAuthor.setText(m.getAuthor());
        Glide.with(context).load(m.getPic_small()).into(holder.ivHeader);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder{
      ImageView ivHeader;
      TextView tvName;
      TextView tvAuthor;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHeader=itemView.findViewById(R.id.iv_header);
            tvName=itemView.findViewById(R.id.tv_name);
            tvAuthor=itemView.findViewById(R.id.tv_author);
        }
    }
}
