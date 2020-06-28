package com.wa.mymusicplayer;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpAsyncTask extends AsyncTask<String,Integer,SongList> {

    public OnPostExecute onPostExecute;
    @Override
    protected SongList doInBackground(String... strings) {
        try {
            URL url=new URL("http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&method=baidu.ting.billboard.billList&type="+strings[0]+"&size=10&offset=0");
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream is=conn.getInputStream();
            byte[] bytes=null;
            BufferedInputStream bis=new BufferedInputStream(is);
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            BufferedOutputStream boss=new BufferedOutputStream(bos);
            byte[] buffer=new byte[1024];
            int len=0;
            while ((len=bis.read(buffer))>0){
                boss.write(buffer,0,len);
            }
            bos.flush();
            boss.flush();
            boss.close();
            bos.close();
            bytes=bos.toByteArray();
            String response=new String(bytes);
            Log.d(HttpAsyncTask.class.getSimpleName(),"response="+response);
            JSONObject jsonObject=new JSONObject(response);
            JSONArray song_list=jsonObject.getJSONArray("song_list");
            SongList songList=new SongList();
            List<SongList.Song_list> list=new ArrayList<>();
            for(int i=0;i<song_list.length();i++){
                JSONObject object=song_list.getJSONObject(i);
                String pic_small=object.getString("pic_small");
                String artist_id=object.getString("artist_id");
                String title=object.getString("title");
                String author=object.getString("author");
                String album_title=object.getString("album_title");
                String album_500_500=object.getString("album_500_500");
                String publishtime=object.getString("publishtime");
                String lrclink=object.getString("lrclink");
                SongList.Song_list song=new SongList.Song_list();
                song.setPic_small(pic_small);
                song.setArtist_id(artist_id);
                song.setTitle(title);
                song.setAuthor(author);
                song.setAlbum_title(album_title);
                song.setAlbum_500_500(album_500_500);
                song.setPublishtime(publishtime);
                song.setLrclink(lrclink);
                list.add(song);
            }
            SongList.Billboard billboard=new SongList.Billboard();
            JSONObject billboardObj=jsonObject.getJSONObject("billboard");
            String update_date=billboardObj.getString("update_date");
            String name=billboardObj.getString("name");
            String comment=billboardObj.getString("comment");
            String pic_s192=billboardObj.getString("pic_s192");
            billboard.setUpdate_date(update_date);
            billboard.setName(name);
            billboard.setComment(comment);
            billboard.setPic_s192(pic_s192);
            songList.setBillboard(billboard);
            songList.setSong_list(list);

            return songList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(SongList s) {
        super.onPostExecute(s);
        if(onPostExecute!=null){
            onPostExecute.onPost(s);
        }
    }

    public void setOnPostExecute(OnPostExecute onPostExecute) {
        this.onPostExecute = onPostExecute;
    }

    interface OnPostExecute{
        void onPost(SongList s);
    }
}
