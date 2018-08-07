package com.nytimes.android.nytimesapp.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.nytimes.android.nytimesapp.R;
import com.nytimes.android.nytimesapp.model.NewsDataModel;
import com.nytimes.android.nytimesapp.utils.Constants;
import com.nytimes.android.nytimesapp.utils.PicassoCircleTransformation;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    NewsDataModel model;
    ImageView mPageIv;
    TextView mTitleTv, mSubTv;

    //MVP not require coz action item is low

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        model = getIntent().getParcelableExtra(Constants.Bundle_Key.NEWS_CONTENT);

        mTitleTv = (TextView) findViewById(R.id.activity_detail_title_tv);
        mSubTv = (TextView) findViewById(R.id.activity_detail_sub_tv);
        mPageIv = (ImageView) findViewById(R.id.activity_detail_page_iv);

        mTitleTv.setText(model.getTitle());
        mSubTv.setText(model.getByline());

        Picasso.with(this).load(model.getSource())
                .placeholder(R.drawable.circle_image_bg)
                .error(R.mipmap.ic_launcher)
                .transform(new PicassoCircleTransformation())
                .into(mPageIv);



    }
}
