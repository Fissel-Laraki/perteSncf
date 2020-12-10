package com.example.pertesncf.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pertesncf.R;
import com.example.pertesncf.model.beans.Fields;
import com.example.pertesncf.model.webservice.SncfDataWs;
import com.example.pertesncf.view.FieldAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_reload, iv_previous, iv_next;
    private TextView tv_page;
    private RecyclerView rv;
    private ArrayList<Fields> fields;
    private FieldAdapter fieldAdapter;

    public static int start = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        tv_page = findViewById(R.id.tv_page);
        iv_reload = findViewById(R.id.iv_reload);
        iv_next = findViewById(R.id.iv_next);
        iv_previous = findViewById(R.id.iv_previous);

        rv = findViewById(R.id.rv);
        fields = new ArrayList<>();
        fieldAdapter = new FieldAdapter(fields);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(fieldAdapter);

        doAsyncTask();
        iv_reload.setOnClickListener(this);
        iv_previous.setOnClickListener(this);
        iv_next.setOnClickListener(this);

    }

    public void doAsyncTask() {
        try{
            MonAT at = new MonAT();
            at.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == iv_next) {
            start++;
            doAsyncTask();
            tv_page.setText(start+"");
        }
        if( v == iv_previous) {
            start--;
            doAsyncTask();
            tv_page.setText(start+"");
        }

    }

    public class MonAT extends AsyncTask {
        private ArrayList<Fields> resultat = null;
        private Exception exception = null;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {

                resultat = SncfDataWs.getFieldsDuServeur((start-1)*SncfDataWs.ROWS);
            } catch (Exception e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPreExecute();
            if (exception == null) {
                fields.clear();
                fields.addAll(resultat);
                fieldAdapter.notifyDataSetChanged();
            }
        }
    }
}