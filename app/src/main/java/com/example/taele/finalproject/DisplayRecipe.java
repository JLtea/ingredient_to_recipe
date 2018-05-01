package com.example.taele.finalproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayRecipe extends AppCompatActivity{
    private static String URL = "http://food2fork.com/api";
    private static final String SUBSCRIPTION_KEY = "3ba7b11d4ea72344a8b3db730c62f1ec";
    private String queryURL;
    private String publisher;
    private String image;
    private String title;
    private String[] ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipe);
        String id = getIntent().getStringExtra("rId");
        seturl(id);
        Recipe();
    }
    public void seturl(String query) {
        queryURL = URL + "/get?key=" + SUBSCRIPTION_KEY + "&rId=" + query;
    }
    public void Recipe() {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading data...");
        progress.show();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, queryURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progress.dismiss();
                try {
                    JSONObject recipe = response.getJSONObject("recipe");
                    publisher = recipe.getString("publisher");
                    JSONArray ingredientList = recipe.getJSONArray("ingredients");
                    image = recipe.getString("image_url");
                    title = recipe.getString("title");
                    ingredients = new String[ingredientList.length()];
                    for (int i = 0; i < ingredientList.length(); i++) {
                        ingredients[i] = ingredientList.get(i).toString();
                    }
                    ImageView image = (ImageView) findViewById(R.id.Image);
                    Toast.makeText(DisplayRecipe.this, title, Toast.LENGTH_SHORT).show();
                    TextView dish = (TextView) findViewById(R.id.dish);
                    TextView ingredients = (TextView) findViewById(R.id.ingredients);
                    TextView publisher = (TextView) findViewById(R.id.publish);
                    publisher.setText(DisplayRecipe.this.publisher);
                    dish.setText(title);
                    String ingred = "";
                    for (int j = 0; j < DisplayRecipe.this.ingredients.length; j++) {
                        ingred += DisplayRecipe.this.ingredients[j];
                        ingred += "\n";
                    }
                    ingredients.setText(ingred);
                    Picasso.with(DisplayRecipe.this)
                            .load(DisplayRecipe.this.image)
                            .into(image);
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
   /* public void loadScreen() {
        ImageView image = (ImageView) findViewById(R.id.Image);
        Toast.makeText(DisplayRecipe.this, title, Toast.LENGTH_SHORT).show();
        TextView dish = (TextView) findViewById(R.id.dish);
        TextView ingredients = (TextView) findViewById(R.id.ingredients);
        TextView publisher = (TextView) findViewById(R.id.publish);
        publisher.setText(this.publisher);
        dish.setText(this.title);
        //ingredients.setText(this.ingredients[0]);
        Picasso.with(this)
                .load(this.image)
                .into(image);
    }*/
}
