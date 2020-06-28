package com.wa.mymusicplayer;

import android.os.Bundle;
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

public class OnlineFragment extends Fragment implements HttpAsyncTask.OnPostExecute {
    View mView;
    RecyclerView rv;
    OnlineAdapter mAdapter;
    List<SongList> data;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_online,container,false);
        rv=mView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        data=new ArrayList<>();

        mAdapter=new OnlineAdapter(getContext(),data);
        rv.setAdapter(mAdapter);
        HttpAsyncTask asyncTask1=new HttpAsyncTask();
        HttpAsyncTask asyncTask2=new HttpAsyncTask();
        HttpAsyncTask asyncTask3=new HttpAsyncTask();
        HttpAsyncTask asyncTask4=new HttpAsyncTask();
        HttpAsyncTask asyncTask5=new HttpAsyncTask();
        HttpAsyncTask asyncTask6=new HttpAsyncTask();
        HttpAsyncTask asyncTask7=new HttpAsyncTask();
        HttpAsyncTask asyncTask8=new HttpAsyncTask();
        HttpAsyncTask asyncTask9=new HttpAsyncTask();
        asyncTask1.setOnPostExecute(this);
        asyncTask2.setOnPostExecute(this);
        asyncTask3.setOnPostExecute(this);
        asyncTask4.setOnPostExecute(this);
        asyncTask5.setOnPostExecute(this);
        asyncTask6.setOnPostExecute(this);
        asyncTask7.setOnPostExecute(this);
        asyncTask8.setOnPostExecute(this);
        asyncTask9.setOnPostExecute(this);
        asyncTask1.execute("1");
        asyncTask2.execute("2");
        asyncTask3.execute("11");
        asyncTask4.execute("21");
        asyncTask5.execute("22");
        asyncTask6.execute("23");
        asyncTask7.execute("24");
        asyncTask8.execute("25");
        asyncTask9.execute("16");
        return mView;
    }

    @Override
    public void onPost(SongList s) {
        Log.d(OnlineFragment.class.getSimpleName(),"onPost");
        if(s!=null){
            data.add(s);
            mAdapter.notifyDataSetChanged();
        }

    }
}
