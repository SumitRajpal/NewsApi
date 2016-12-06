package com.example.sumit.newsapi;

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

public class MainActivity extends AppCompatActivity {
String TAG ="res";
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    private List<MyData> data_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        data_list  = new ArrayList<>();
        load_data_from_server();

        gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CustomAdapter(this,data_list);
        recyclerView.setAdapter(adapter);



    }
    private void load_data_from_server() {
        String url = "https://newsapi.org/v1/sources";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Log.d(TAG, response);
                        JSONObject jObject  = null;
                        try {
                            jObject = new JSONObject(response);
                            JSONArray jsonarray = new JSONArray(jObject.getString("sources"));
                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String id       = jsonobject.getString("id");
                                String name       = jsonobject.getString("name");
                                Log.d(TAG,id+"------"+name+"----"+jsonobject.getJSONObject("urlsToLogos").getString("large"));
                                MyData data = new MyData(id.toString(),name.toString(),jsonobject.getJSONObject("urlsToLogos").getString("large").toString());

                                data_list.add(data);
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

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
}
