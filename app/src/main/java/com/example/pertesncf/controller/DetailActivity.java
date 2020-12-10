package com.example.pertesncf.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pertesncf.R;
import com.example.pertesncf.model.beans.Fields;

public class DetailActivity extends AppCompatActivity {

    public final static String KEY = "KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Fields field = (Fields)getIntent().getExtras().getSerializable(KEY);

    }
}