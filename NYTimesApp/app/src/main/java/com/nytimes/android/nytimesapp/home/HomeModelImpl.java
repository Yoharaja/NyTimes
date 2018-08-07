package com.nytimes.android.nytimesapp.home;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.nytimes.android.nytimesapp.ApiCallVolley;
import com.nytimes.android.nytimesapp.model.NewsDataModel;
import com.nytimes.android.nytimesapp.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeModelImpl implements HomeModel {
    HomeModelListener homeModelListener;
    private String BaseUrl;
    private List<NewsDataModel> mNewsList = new ArrayList<>();

    public HomeModelImpl(HomeModelListener homeModelListener) {
        this.homeModelListener = homeModelListener;
    }

    @Override
    public void onCreate() {
        makeAPICall();
    }

    @Override
    public void onDestroy() {
        homeModelListener = null;
        mNewsList = null;
    }

    @Override
    public void showList() {
    }

    private void makeAPICall() {
        int type = 0; // Get

        HashMap<String, String> mParamHashMap = new HashMap<>();
        mParamHashMap.put("type", "Article");  // Parameter

        new ApiCallVolley().makeApiCall(Constants.Urls.DEV_API_URL, type, mParamHashMap, false, null, new ApiCallVolley.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                Log.i("TAG", "onResponse: " + response.toString());

                parseData(response);

            }

            @Override
            public void onFailure(VolleyError error) {
                Log.e("TAG", "onErrorResponse: " + error.toString());

            }
        });

    }

    private void parseData(JSONObject response) {
        mNewsList.clear();
        try {
            JSONArray newsJsonArray = response.getJSONArray("results");
            for (int i = 0; i < newsJsonArray.length(); i++) {

                NewsDataModel dataModel = new NewsDataModel();
                dataModel.setTitle(newsJsonArray.getJSONObject(i).getString("title"));
                dataModel.setNewsAbstract(newsJsonArray.getJSONObject(i).getString("abstract"));
                dataModel.setPublished_date(newsJsonArray.getJSONObject(i).getString("published_date"));
                dataModel.setByline(newsJsonArray.getJSONObject(i).getString("byline"));
                dataModel.setSource(newsJsonArray.getJSONObject(i).getJSONArray("media").getJSONObject(0).getJSONArray("media-metadata").getJSONObject(0).getString("url"));
                dataModel.setKeywords(newsJsonArray.getJSONObject(i).getString("adx_keywords"));
                mNewsList.add(dataModel);
                Log.i("TAG", "makeJsonRequest Count: " + mNewsList.size());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        homeModelListener.getResult(mNewsList);
    }


    public interface HomeModelListener {

        void showMessage(String message);

        void getResult(List<NewsDataModel> list);

        void redirectPage();

        Context getContext();
    }
}
