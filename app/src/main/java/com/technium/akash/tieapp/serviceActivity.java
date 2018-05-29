package com.technium.akash.tieapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;

public class serviceActivity extends AppCompatActivity {

    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_service);
    }

    public void serviceParameters(View view)
    {
        //Which selection has been made..

        RadioButton rd= (RadioButton) findViewById(R.id.mip1);

        Button b = (Button) findViewById(R.id.button2);

       // b.setClickable(false);


        if(rd.isChecked()){

            new LongRunningGetIO().execute();

                // /startActivity(new Intent(serviceActivity.this,inputdata1.class));

        }
        else
                    startActivity(new Intent(serviceActivity.this,inputdata2.class));

    }

    private class LongRunningGetIO extends AsyncTask<Void, Void, String>
    {

        protected  String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException{

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

            HttpGet httpGet = new HttpGet("https://api.github.com/");

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

                Intent i=new Intent(serviceActivity.this,inputdata1.class);

                Bundle data = new Bundle();
                data.putString("response",results);

                i.putExtras(data);
                startActivity(i);

            }
            else
            {
                Intent i=new Intent(serviceActivity.this,inputdata1.class);

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
