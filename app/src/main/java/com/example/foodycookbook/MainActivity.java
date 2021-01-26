package com.example.foodycookbook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String url1 = "https://www.themealdb.com/images/category/beef.png";
    String url2 = "https://www.themealdb.com/images/category/chicken.png";
    String url3 = "https://www.themealdb.com/images/category/dessert.png";
    String url4 = "https://www.themealdb.com/images/category/lamb.png";
    String url5 = "https://www.themealdb.com/images/category/miscellaneous.png";
    String url6 = "https://www.themealdb.com/images/category/pasta.png";
    String url7 = "https://www.themealdb.com/images/category/pork.png";
    String url8 = "https://www.themealdb.com/images/category/seafood.png";
    String url9 = "https://www.themealdb.com/images/category/side.png";
    String url10 = "https://www.themealdb.com/images/category/starter.png";
    String url11 = "https://www.themealdb.com/images/category/vegan.png";
    String url12 = "https://www.themealdb.com/images/category/vegetarian.png";
    String url13 = "https://www.themealdb.com/images/category/breakfast.png";
    String url14 = "https://www.themealdb.com/images/category/goat.png";
 String baseurl;
    int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        CallApi();
        CallApi();
        CallApi();
        CallApi();
        CallApi();
        CallApi();
        CallApi();
        CallApi();



        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        View view =getSupportActionBar().getCustomView();

        ImageButton imageButton= view.findViewById(R.id.action_bar_back);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Search.class);
                startActivity(intent);




            }
        });

        ImageButton imageButton2= view.findViewById(R.id.action_bar_forward);

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"added to wishlist", Toast.LENGTH_LONG).show();
            }
        });
        // we are creating array list for storing our image urls.
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = findViewById(R.id.slider);

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));
        sliderDataArrayList.add(new SliderData(url4));
        sliderDataArrayList.add(new SliderData(url5));
        sliderDataArrayList.add(new SliderData(url6));
        sliderDataArrayList.add(new SliderData(url7));
        sliderDataArrayList.add(new SliderData(url8));
        sliderDataArrayList.add(new SliderData(url9));
        sliderDataArrayList.add(new SliderData(url10));
        sliderDataArrayList.add(new SliderData(url11));
        sliderDataArrayList.add(new SliderData(url12));
        sliderDataArrayList.add(new SliderData(url13));
        sliderDataArrayList.add(new SliderData(url14));


        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();

    }
    private void CallApi(){

        RequestQueue requestQueue;
// Instantiate the cache
                Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
                Network network = new BasicNetwork(new HurlStack());

                requestQueue = new RequestQueue(cache, network);
                requestQueue.start();


                setContentView(R.layout.activity_main);
                String url = "https://www.themealdb.com/api/json/v1/1/random.php";
// Formulate the request and handle the response.

                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        int array1[] = new int[8];
                        array1[0] = R.id.text1;
                        array1[1] = R.id.text2;
                        array1[2] = R.id.text3;
                        array1[3] = R.id.text4;
                        array1[4] = R.id.text5;
                        array1[5] = R.id.text6;
                        array1[6] = R.id.text7;
                        array1[7] = R.id.text8;
                        int array2[] = new int[8];
                        array2[0] = R.id.image1;
                        array2[1] = R.id.image2;
                        array2[2] = R.id.image3;
                        array2[3] = R.id.image4;
                        array2[4] = R.id.image5;
                        array2[5] = R.id.image6;
                        array2[6] = R.id.image7;
                        array2[7] = R.id.image8;


                        try {

                            Log.d("result", "Response: " + response.getJSONArray("meals").getJSONObject(0).getString("strMeal").toString());
                            String titlee = response.getJSONArray("meals").getJSONObject(0).getString("strMeal").toString();
                            baseurl = response.getJSONArray("meals").getJSONObject(0).getString("strMealThumb").toString();
                            Log.d("url", baseurl);

                            int id1 = array1[i - 1];
                            TextView textView = (TextView) findViewById(id1);
                            textView.setText(titlee);
                            int id2 = array2[i - 1];
                            ImageView imageView = findViewById(id2);
                            Glide.with(MainActivity.this)
                                    .load(baseurl)
                                    .into(imageView);
                            i++;

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
