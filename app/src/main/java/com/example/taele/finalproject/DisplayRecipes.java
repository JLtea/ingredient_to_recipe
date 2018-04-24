package com.example.taele.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DisplayRecipes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipes);
        findRecipe(getIntent().getStringExtra("key"));
    }
    public void findRecipe(String key) {
        Toast.makeText(this, key + " Recipes", Toast.LENGTH_SHORT).show();
    }
}
