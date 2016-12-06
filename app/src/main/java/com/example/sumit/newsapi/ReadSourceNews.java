package com.example.sumit.newsapi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReadSourceNews extends AppCompatActivity {
    private String TAG ="res",url,logoview;
    private RecyclerView recyclerViewDetail;

    private LinearLayoutManager linearLayoutManager;

    private CustomAdapterDetail adapterDetail;

    private List<MyDataDetail> data_list_detail;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_source_news);
        pd = new ProgressDialog(ReadSourceNews.this);
        pd.setMessage("Loading . . . ");
        url = "https://newsapi.org/v1/articles?source="+getIntent().getStringExtra("id")+"&apiKey=08139016b79b4fdebd1f0e12ea3ec090";
        logoview = getIntent().getStringExtra("logo").toString();
        recyclerViewDetail = (RecyclerView) findViewById(R.id.recycler_view_detail);
        data_list_detail  = new ArrayList<>();
        load_data_from_server__detail();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewDetail.setLayoutManager(linearLayoutManager);

        adapterDetail = new CustomAdapterDetail(this,data_list_detail);
        recyclerViewDetail.setAdapter(adapterDetail);
    }
    private void load_data_from_server__detail() {

pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Log.d(TAG, response);
                        JSONObject jObject  = null;
                        try {
                            jObject = new JSONObject(response);
                            JSONArray jsonarray = new JSONArray(jObject.getString("articles"));
                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String author       = jsonobject.getString("author");
                                String title       = jsonobject.getString("title");
                                String urlImage       = jsonobject.getString("urlToImage");
                                Log.d(TAG,author+"------"+title+"----"+"-----"+urlImage);
                                MyDataDetail data = new MyDataDetail(title,author,urlImage,logoview);

                                data_list_detail.add(data);
                                adapterDetail.notifyDataSetChanged();
                                pd.hide();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pd.hide();

                        }




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){
                            Log.d(TAG, error.toString());
                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
