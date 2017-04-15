package com.serifgungor.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TranslateResultListener {
    ArrayList<String> words;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        words= new ArrayList<>();
        words.add("Doğan");
        words.add("aynur");
        words.add("Doğan");

        RecyclerView rvWords= (RecyclerView) findViewById(R.id.rvWords);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvWords.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(words);
        rvWords.setAdapter(mAdapter);

    }

    @Override
    public void onTranslateResultSuccess(ArrayList<String> words) {
        for(String item :words)
        {
            this.words.add(item);
        }
       mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTranslateResultError() {

    }

    public void button(View view) {
        EditText etPhrase= (EditText)findViewById(R.id.etPhrase);
        EditText etFrom =(EditText)findViewById(R.id.etFrom);
        EditText etTo=(EditText) findViewById(R.id.etTo);
        String phrase = etPhrase.getText().toString();
        String from = etFrom.getText().toString();
        String to = etTo.getText().toString();

        TranslateInput input= new TranslateInput(phrase,from,to);
        new TranslateTask(this).execute(input);

    }
}
