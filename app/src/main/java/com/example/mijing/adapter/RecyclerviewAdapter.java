package com.example.mijing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.mijing.R;
import java.util.List;

/**
 * Created by 程延宏 on 2017/4/25.
 */

public  class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHodler> {
    public RecyclerviewAdapter(List<Integer> list_imags, Context context) {
        this.list_imags = list_imags;
        this.context = context;
    }
    private List<Integer> list_imags;
    private Context context;
    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recv_itme,parent,false);
        ViewHodler ViewHodler=new ViewHodler(view);
        ViewHodler.imagview= (ImageView) view.findViewById(R.id.iv_review_itme);
        return ViewHodler;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        holder.imagview.setImageResource(list_imags.get(position));
    }
    @Override
    public int getItemCount() {
        return list_imags.size();
    }
    public  class ViewHodler extends RecyclerView.ViewHolder{
        public ViewHodler(View itemView) {
            super(itemView);
        }
        public ImageView imagview;
    }
}

