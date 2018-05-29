package com.technium.akash.tieapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class inputdata1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        String response= bundle.getString("response");

        Log.i("response",response+"inside inputdata1");

        setContentView(R.layout.activity_inputdata1);

        EditText et = (EditText) findViewById(R.id.editText3);

        if(response!=null)
               et.setText(response);

    }


}
