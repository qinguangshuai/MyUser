package com.umeng.soexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.umeng.soexample.R;
import com.umeng.soexample.bean.LoginUser;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/11/16    19:17
 * author:秦广帅(Lenovo)
 * fileName:TwoAdapter
 */
public class TwoAdapter extends RecyclerView.Adapter<TwoAdapter.MyViewHolder> {

    private Context mContext;
    private List<LoginUser.ResultsBean> list;
    private RecycleitemClick click;

    public void setClick(RecycleitemClick click) {
        this.click = click;
    }

    public TwoAdapter(Context context, List<LoginUser.ResultsBean> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view,click);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        LoginUser.ResultsBean bean = list.get(i);
        Picasso.with(mContext).load(bean.getUrl()).into(myViewHolder.image9);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public interface RecycleitemClick{
        void onItemClick(View view,int position);
    }

    public void addData(int position){
        list.add(0,list.get(position));
        notifyItemInserted(position);
    }

    public void removeData(int position){
        list.remove(0);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image9;

        public MyViewHolder(@NonNull View itemView, final RecycleitemClick click) {
            super(itemView);
            image9 = itemView.findViewById(R.id.image9);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onItemClick(v,getAdapterPosition());
                }
            });
        }
    }
}
