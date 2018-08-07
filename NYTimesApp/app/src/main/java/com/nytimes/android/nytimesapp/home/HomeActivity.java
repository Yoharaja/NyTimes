package com.nytimes.android.nytimesapp.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nytimes.android.nytimesapp.R;
import com.nytimes.android.nytimesapp.adapter.NewsAdapter;
import com.nytimes.android.nytimesapp.listener.RecyclerItemListener;
import com.nytimes.android.nytimesapp.model.NewsDataModel;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements HomeView {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    List<NewsDataModel> newsList = new ArrayList<>();

    private ProgressDialog pd;

    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        showProgressdialog();
        homePresenter = new HomePresenterImpl(this);
        homePresenter.onCreate();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.most_popular);
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        recyclerView = (RecyclerView) findViewById(R.id.activity_home_news_rv);

    }


    private void hideProgressDialog() {
        if (pd != null)
            pd.dismiss();
    }

    private void showProgressdialog() {
        pd = new ProgressDialog(HomeActivity.this);
        pd.setCancelable(false);
        pd.setMessage("Loading News");
        pd.show();
    }

    private void setAdapter() {

        NewsAdapter adapter = new NewsAdapter(this, newsList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(List<NewsDataModel> list) {
        if (list.size() > 0) {
            newsList.clear();
            newsList = list;
            setAdapter();
        }
        hideProgressDialog();
    }

    @Override
    public void redirectPage() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.onDestroy();
    }
}
