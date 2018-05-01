package com.example.taele.finalproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowList extends AppCompatActivity {
    private static String URL = "http://food2fork.com/api";
    private static final String SUBSCRIPTION_KEY = "3ba7b11d4ea72344a8b3db730c62f1ec";
    private String queryURL;
    private List<Item> listItems;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        String key = getIntent().getStringExtra("key").toLowerCase();
        //Toast.makeText(this, key + " recipes", Toast.LENGTH_SHORT).show();
        seturl(key);
        searchRecipe();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView text = findViewById(R.id.text);
        text.setText(key + " recipes");
        listItems = new ArrayList<>();
    }

    public void seturl(String query) {
        queryURL = URL + "/search?key=" + SUBSCRIPTION_KEY + "&q=" + query;
    }

    public void searchRecipe() {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading data...");
        progress.show();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, queryURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progress.dismiss();
                try {
                    JSONArray recipes = response.getJSONArray("recipes");
                    for (int i = 0; i < recipes.length(); i++) {
                        String publisher = recipes.getJSONObject(i).getString("publisher");
                        String name = recipes.getJSONObject(i).getString("title");
                        String image = recipes.getJSONObject(i).getString("image_url");
                        String rId = recipes.getJSONObject(i).getString("recipe_id");
                        listItems.add(new Item(name, image, publisher, rId));
                    }
                    ListAdapter adapter = new ListAdapter(listItems, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonRequest);
    }

}
