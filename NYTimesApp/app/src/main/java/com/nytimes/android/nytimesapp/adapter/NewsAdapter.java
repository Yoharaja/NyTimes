package com.nytimes.android.nytimesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nytimes.android.nytimesapp.R;
import com.nytimes.android.nytimesapp.detail.DetailsActivity;
import com.nytimes.android.nytimesapp.model.NewsDataModel;
import com.nytimes.android.nytimesapp.utils.Constants;
import com.nytimes.android.nytimesapp.utils.PicassoCircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> implements View.OnClickListener {

    Context context;
    List<NewsDataModel> mNewsList = new ArrayList<NewsDataModel>();

    @Override
    public void onClick(View view) {

        int pos = (int) view.getTag();
        if (view.getId() == R.id.layout_news_row_parent_rv) {
            openDetails(mNewsList.get(pos));
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView layoutLogoIv;
        private TextView layoutTitleTv, layoutSubTitleTv, layoutDateTv;
        private RelativeLayout layoutParenrtRl;

        public MyViewHolder(View itemView) {
            super(itemView);
            layoutLogoIv = (ImageView) itemView.findViewById(R.id.layout_news_row_logo_iv);
            layoutTitleTv = (TextView) itemView.findViewById(R.id.layout_news_row_title_t);
            layoutSubTitleTv = (TextView) itemView.findViewById(R.id.layout_news_row_sub_tv);
            layoutDateTv = (TextView) itemView.findViewById(R.id.layout_news_row_date_tv);
            layoutParenrtRl = (RelativeLayout) itemView.findViewById(R.id.layout_news_row_parent_rv);
        }
    }

    public NewsAdapter(Context context, List<NewsDataModel> mNewsList) {
        this.context = context;
        this.mNewsList = mNewsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_news_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        holder.layoutTitleTv.setText(mNewsList.get(listPosition).getTitle());
        holder.layoutSubTitleTv.setText(mNewsList.get(listPosition).getByline());
        holder.layoutDateTv.setText(mNewsList.get(listPosition).getPublished_date());
        holder.layoutParenrtRl.setTag(listPosition);
        holder.layoutParenrtRl.setOnClickListener(this);

        Picasso.with(context).load(mNewsList.get(listPosition).getSource())
                .placeholder(R.drawable.circle_image_bg)
                .error(R.mipmap.ic_launcher)
                .transform(new PicassoCircleTransformation())
                .into(holder.layoutLogoIv);


    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    private void openDetails(NewsDataModel mNewsDataModel) {

        Intent intent=new Intent(context, DetailsActivity.class);
        intent.putExtra(Constants.Bundle_Key.NEWS_CONTENT,mNewsDataModel);
        context.startActivity(intent);



    }
}