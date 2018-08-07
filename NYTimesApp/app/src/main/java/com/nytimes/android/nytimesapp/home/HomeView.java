package com.nytimes.android.nytimesapp.home;

import android.content.Context;

import com.nytimes.android.nytimesapp.model.NewsDataModel;

import java.util.List;

public interface HomeView {
    void showMessage(String message);
    void showResult(List<NewsDataModel> list);
    void redirectPage();
    Context getContext();

}
