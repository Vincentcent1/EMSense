package com.example.android.emsense3.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.emsense3.R;

import java.util.ArrayList;
import java.util.List;

public class ObjectsActivity extends AppCompatActivity {
    private List<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objects);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList objectList = new ArrayList<>();


    }

}
