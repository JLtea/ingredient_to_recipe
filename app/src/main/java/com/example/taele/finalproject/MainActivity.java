package com.example.taele.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button apple = findViewById(R.id.button2);
        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecipe("Apple");
            }
        });
        final Button potato = findViewById(R.id.button3);
        potato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecipe("Potato");
            }
        });
        final Button spinach = findViewById(R.id.button4);
        spinach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecipe("Spinach");
            }
        });
    }
    public void startRecipe(String key) {
        Intent intent = new Intent(this, DisplayRecipes.class);
        intent.putExtra("key", key);
        startActivity(intent);
    }
}
