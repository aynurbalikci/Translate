package com.serifgungor.myapplication;

import android.app.DownloadManager;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Hafta_Sonu on 15.04.2017.
 */

public class TranslateTask extends AsyncTask<TranslateInput ,Void, ArrayList<String>> {
     TranslateResultListener listener;

    public TranslateTask(TranslateResultListener listener) {
        this.listener=listener;
    }

    @Override
    protected ArrayList<String> doInBackground(TranslateInput... params) {
        ArrayList<String> words =null;
        TranslateInput input= params[0];
        try{
            words=Translate(input.phrase,input.from,input.to);
        }catch(Exception e){
            e.printStackTrace();
        }

        return words;
    }

    @Override
    protected void onPostExecute(ArrayList<String> words) {
        super.onPostExecute(words);
        if(words==null){
            listener.onTranslateResultError();
        }
        else{
            listener.onTranslateResultSuccess(words);
        }
    }

    private ArrayList<String> Translate(String paraphrase, String from, String to) throws Exception {

        ArrayList<String> words= new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://glosbe.com/gapi/translate?from="+from+"&dest="+to+"&format=json&phrase="+ paraphrase+"&pretty=true")
                .build();

        Response response =client.newCall(request).execute();
        if(!response.isSuccessful()) throw new IOException("Unexpected code " +response);


        String result = response.body().string();
        JSONObject base= new JSONObject(result);
        JSONArray tucs =base.getJSONArray("tuc");

        for(int i=0; i<tucs.length(); i++){

            JSONObject item= tucs.getJSONObject(i);



            if(item.has("phrase")){
                JSONObject phrase = item.getJSONObject("phrase");
                String text= phrase.getString("text");
                words.add(text);
            }

        }

        return words ;
    }
}
