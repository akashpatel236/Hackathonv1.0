package com.technium.akash.tieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;



public class Searchform extends AppCompatActivity {

    private String telephone="";
    private String postalcode="";
    private String housecode="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchform);
    }

    public void RestServiceCall(View v) {
        boolean validationCheck = true;

        TextView tn = (TextView) findViewById(R.id.tn);
        TextView pc = (TextView) findViewById(R.id.pc);
        TextView hc = (TextView) findViewById(R.id.hc);

        telephone = tn.getText().toString();
        postalcode = pc.getText().toString();
        housecode = hc.getText().toString();

        if (telephone.equals("")) {
            tn.setError("Telephone number should not be empty.");
            validationCheck = false;
        }
        if (postalcode.equals("")) {
            pc.setError("Postalcode should not be empty.");
            validationCheck = false;
        }
        if (housecode.equals("")){
            hc.setError("Housenumber should not be empty.");
        validationCheck = false;
        }

            if(validationCheck)
                new LongRunningGetIO().execute();
    }

    private class LongRunningGetIO extends AsyncTask<Void, Void, String>
    {

        protected  String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {

            InputStream in = entity.getContent();


            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n>0) {
                byte[] b = new byte[4096];
                n =  in.read(b);


                if (n>0) out.append(new String(b, 0, n));
            }


            return out.toString();


        }

        @Override

        protected  String doInBackground(Void... params)
        {

            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            //  HttpGet httpGet = new HttpGet("https://jsonplaceholder.typicode.com/posts/1");

            //HttpGet httpGet = new HttpGet("https://api.github.com/");

            HttpGet httpGet = new HttpGet("https://event-hackathon.herokuapp.com/kpn-wholesale/wipservices/wip1/"+telephone+"/"+postalcode+"/"+housecode);

            String text = null;
            try {
                HttpResponse response = httpClient.execute(httpGet, localContext);


                HttpEntity entity = response.getEntity();


                text = getASCIIContentFromEntity(entity);


            } catch (Exception e) {
                return e.getLocalizedMessage();
            }


            return text;
        }

        protected void onPostExecute(String results) {
            if (results!=null) {

                // EditText et = (EditText)findViewById(R.id.editText2);

                // et.setText(results);

                Intent i=new Intent(Searchform.this,inputdata1.class);

                Bundle data = new Bundle();
                data.putString("response",results);

                i.putExtras(data);
                startActivity(i);

            }
            else
            {
                Intent i=new Intent(Searchform.this,inputdata1.class);

                Bundle data = new Bundle();
                data.putString("response","Error : Something went Wrong.");

                i.putExtras(data);
                startActivity(i);

            }
            // Button b = (Button)findViewById(R.id.button2);
            // b.setClickable(true);
        }
    }
}
