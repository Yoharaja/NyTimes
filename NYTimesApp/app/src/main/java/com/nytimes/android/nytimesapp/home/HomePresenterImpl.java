package com.nytimes.android.nytimesapp.home;

import android.content.Context;

import com.nytimes.android.nytimesapp.model.NewsDataModel;

import java.util.List;

public class HomePresenterImpl implements HomePresenter, HomeModelImpl.HomeModelListener {
    HomeView homeView;
    HomeModel dashboardModel;
    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
        dashboardModel = new HomeModelImpl(this);
    }

    @Override
    public void onCreate() {
        dashboardModel.onCreate();
    }

    @Override
    public void onDestroy() {
        dashboardModel.onDestroy();

    }


    @Override
    public void showMessage(String message) {
        homeView.showMessage(message);
    }

    @Override
    public void getResult(List<NewsDataModel> list) {
        homeView.showResult(list);
    }

    @Override
    public void redirectPage() {
        homeView.redirectPage();
    }

    @Override
    public Context getContext() {
        return homeView.getContext();
    }
}
