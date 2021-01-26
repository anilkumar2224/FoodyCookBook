package com.example.foodycookbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Search extends AppCompatActivity {
public String input;
    CardView cardView;
public String baseurll;
    EditText edit_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();

        edit_txt = (EditText) findViewById(R.id.editText1);
       cardView=(CardView)findViewById(R.id.cardView);
        edit_txt.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {


                    input=edit_txt.getText().toString();

                    CallApii();

                    handled = true;
                }
                return handled;
            }
        });



    }

    private void CallApii(){

        RequestQueue requestQueue;
// Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();


        setContentView(R.layout.activity_search);
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s="+input;
// Formulate the request and handle the response.

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {



                try {

                    Log.d("result", "Response: " + response.getJSONArray("meals").getJSONObject(0).getString("strMeal").toString());
                    String titlee = response.getJSONArray("meals").getJSONObject(0).getString("strMeal").toString();
                    baseurll = response.getJSONArray("meals").getJSONObject(0).getString("strMealThumb").toString();
                    Log.d("url", baseurll);

                    TextView textView = (TextView) findViewById(R.id.text0);
                    textView.setText(titlee);

                    ImageView imageView = findViewById(R.id.image0);
                    Glide.with(Search.this)
                            .load(baseurll)
                            .into(imageView);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error

            }
        });


// Add the request to the RequestQueue.

        requestQueue.add(stringRequest);


// ...


    }
}
