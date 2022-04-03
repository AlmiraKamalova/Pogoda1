package com.example.pogoda1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText user_code;
    private Button main_button;
    private TextView info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_code = findViewById(R.id.user_code);
        main_button = findViewById(R.id.main_button);
        info = findViewById(R.id.info);
        main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_code.getText().toString().trim().equals(""))
                    Toast.makeText(MainActivity.this, R.string.no_user_slova, Toast.LENGTH_LONG).show();
                else{
                    String city = user_code.getText().toString();
                    String key = "6c025f47f4268784e0d583f9194f30ab";
                    String url = "https://api.openweathermap.org/data/2.5/weather?q=" +city + "&appid=" + key + "&units=metric&lang=ru";

                    new GetURLData().execute(url);
                }
            }
        });
    }

    private class GetURLData extends AsyncTask<String, String, String> {
protected void onPreExecute(){
    super.onPreExecute();
    info.setText("Загрузка");
}
        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection connection=null;
            BufferedReader reader = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while((line=reader.readLine()) !=null)
                    buffer.append(line).append("\n");
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(connection != null)
                    connection.disconnect();


                try {
                    if(reader !=null)
                      reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
    super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                info.setText("Температура: " + jsonObject.getJSONObject("main").getDouble("temp") +
                        (" Ощущается как:" + jsonObject.getJSONObject("main").getDouble("feels_like")));//+ jsonObject.getJSONObject("weather").getDouble(""));(" ." + jsonObject.getJSONObject("weather").getDouble("main")));info.setText("Ощущается как:" + jsonObject.getJSONObject("main").getDouble("feels_like"));info.setText("" + jsonObject.getJSONObject("weather").getDouble("description"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent i = new Intent(Intent.ACTION_SEND);
                //Intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
                i.setType("text/plan");
                i.putExtra(Intent.EXTRA_SUBJECT, "Прогноз погоды");
                i.putExtra(Intent.EXTRA_TEXT, "Привет! Вот прогноз");
                PackageManager manager = this.getPackageManager();
                /*List<ResolveInfo> infos = manager.queryIntentActivities(i, 0);
                if (infos.size() > 0) {
                    startActivity(i);
                } else {
                  Toast.makeText(this, "Невозможно отправить сообщение",
                            Toast.LENGTH_SHORT).show();
                }*/
                startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }

}