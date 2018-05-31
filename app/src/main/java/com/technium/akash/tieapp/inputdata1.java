package com.technium.akash.tieapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;


import java.util.Scanner;

public class inputdata1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        String response= bundle.getString("response");

        Log.i("response",response+"inside inputdata1");

        setContentView(R.layout.activity_inputdata1);

       // EditText et = (EditText) findViewById(R.id.editText3);

        TextView tn = (TextView) findViewById(R.id.tp);
        TextView pc = (TextView) findViewById(R.id.pc);
        TextView hn = (TextView) findViewById(R.id.hn);
        TextView st = (TextView) findViewById(R.id.st);
        TextView cf = (TextView) findViewById(R.id.cf);
        TextView rs = (TextView) findViewById(R.id.rs);
        TextView hc = (TextView) findViewById(R.id.hc);
        TextView br = (TextView) findViewById(R.id.br);
        TextView status = (TextView) findViewById(R.id.status);
        TextView mn = (TextView) findViewById(R.id.mn);

        String result="";

        JSONObject ob = null;
        String telephone="";
        if(response!=null) {



            try {
                ob = new JSONObject(response);

                 telephone = ob.getString("telephone");

                 Log.i("response",ob.getString("telephone"));
                Log.i("response",telephone);

                tn.setText(ob.getString("telephone"));
                pc.setText(ob.getString("postalCode"));
                hn.setText(ob.getString("houseNumber"));
                st.setText(ob.getString("straat"));
                cf.setText(ob.getString("currentFuture"));
                rs.setText(ob.getString("residence"));
                hc.setText(ob.getString("highCapacity"));
                br.setText(ob.getString("broadband"));
                mn.setText(ob.getString("mainNumber"));
                status.setText(ob.getString("status"));

                Log.i("response",ob.getString("telephone"));
                Log.i("response",ob.getString("postalCode"));

            }catch(Exception e)
            {
                Log.i("response",e.getMessage());

            }
            Log.i("response",ob.toString());


            Scanner s = new Scanner(response);
            s.useDelimiter(",");
            while (s.hasNext()) {
                result += s.next() + "\n";

            }

           /* try {
                 reader = (JSONObject) parser.parse(response);

                Log.i("response", "Inside Response");

                result=reader.get("postalCode").toString();
            }
            catch(Exception e)
            {
                Log.i("response", "Inside Response"+e.getMessage());
            }*/

           // et.setText(telephone);


        }

    }

    public void goback(View view)
    {
        Intent i = new Intent(inputdata1.this,serviceActivity.class);

        startActivity(i);

    }


}
