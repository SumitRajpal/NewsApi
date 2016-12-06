package com.example.sumit.newsapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

/**
 * Created by sumit on 12/6/2016.
 */

public class CustomAdapterDetail extends RecyclerView.Adapter<CustomAdapterDetail.ViewHolder> {

    private Context context;
    private List<MyDataDetail> my_data_detail;

    public CustomAdapterDetail(Context context, List<MyDataDetail> my_data_detail) {
        this.context = context;
        this.my_data_detail = my_data_detail;
    }

    @Override
    public CustomAdapterDetail.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.carddetail,parent,false);

        return new CustomAdapterDetail.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomAdapterDetail.ViewHolder holder, final int position) {

        Glide.with(context).load(my_data_detail.get(position).getUrlToImage()).fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.nassscomImageView);
        holder.cardauthor.setText(my_data_detail.get(position).getUrl().toString());
        holder.ngdctitle.setText(my_data_detail.get(position).getTitle());
        Glide.with(context).load(my_data_detail.get(position).getLogo()).override(500,500).fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.ngdclogoImageView);
    }

    @Override
    public int getItemCount() {
        return my_data_detail.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public ImageView nassscomImageView;
        public TextView cardauthor,ngdctitle;
        public TextView nasscomText;
        public CircularImageView ngdclogoImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            ngdclogoImageView=(CircularImageView)itemView.findViewById(R.id.ngdclogoImageView);
            nassscomImageView = (ImageView) itemView.findViewById(R.id.ngdcImageView);
            cardauthor = (TextView) itemView.findViewById(R.id.cardauthor);
            ngdctitle = (TextView) itemView.findViewById(R.id.ngdctitle);
            nasscomText = (TextView) itemView.findViewById(R.id.ngdcTextView);
        }
    }
}