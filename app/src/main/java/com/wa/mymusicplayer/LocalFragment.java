package com.wa.mymusicplayer;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LocalFragment extends Fragment {
    RecyclerView rv;
    View mView;
    LocalAdapter mAdapter;
    List<Music> data;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_local,container,false);
        rv=mView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        data=new ArrayList<>();
       /* Music m1=new Music();
        m1.setName("桥边女孩");
        m1.setDesc("舞蹈女神....");
        data.add(m1);*/
        mAdapter=new LocalAdapter(getContext(),data);
        rv.setAdapter(mAdapter);
        query();
        return mView;
    }
    public void query(){
        Cursor cursor=getContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                Music m=new Music();
               int id= cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
               String displayName=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
               String title=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
               int duration=cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
               String artist=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
               String album=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
               String size=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
               String year=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.YEAR));
               String path=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
               String isMusic=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
               int albumId=cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                Uri uri=Uri.parse("content://media/external/audio/albums/"+albumId);
                Cursor c=getContext().getContentResolver().query(uri,null,null,null,null,null);
                if(c!=null&&c.moveToNext()){
                    String imagePath=c.getString(c.getColumnIndex("album_art"));
                    m.setImagePath(imagePath);
                    Log.d("LocalFragment","path="+imagePath);
                }
                c.close();
               m.setId(id);
               m.setDisplayName(displayName);
               m.setTitle(title);
               m.setDuration(duration);
               m.setArtist(artist);
               m.setAlbum(album);
               m.setSize(size);
               m.setYear(year);
               m.setPath(path);
               m.setIsMusic(isMusic);
               m.setAlbumId(albumId);
               data.add(m);
            }
            cursor.close();
        }
        mAdapter.notifyDataSetChanged();
    }
}
